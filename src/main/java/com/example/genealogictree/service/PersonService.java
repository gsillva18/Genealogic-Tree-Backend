package com.example.genealogictree.service;

import com.example.genealogictree.core.enums.TypePerson;
import com.example.genealogictree.core.exceptions.ExistingFatherException;
import com.example.genealogictree.core.exceptions.ExistingMotherException;
import com.example.genealogictree.core.exceptions.ExistingPersonInTreeException;
import com.example.genealogictree.core.exceptions.NotExistentEntityException;
import com.example.genealogictree.core.utils.ConverterDtoModel;
import com.example.genealogictree.core.utils.CreatorResponses;
import com.example.genealogictree.dto.*;
import com.example.genealogictree.model.entitygenealogictree.GenealogicTree;
import com.example.genealogictree.model.entitygenealogictree.Person;
import com.example.genealogictree.repository.GenealogicTreeRepository;
import com.example.genealogictree.repository.PersonRepository;
import com.example.genealogictree.response.PersonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private GenealogicTreeRepository genealogicTreeRepository;

    public void createPerson(CreatePersonDto personDto) throws Exception{

        GenealogicTree genealogicTree = genealogicTreeRepository.findById(personDto.getIdGenealogicTree()).orElse(null);

        //verificar se árvore genealógica existe
        if(genealogicTree == null){
            throw new NotExistentEntityException();
        }

        //verificar se já existe uma pessoa na árvore
        if(genealogicTree.getInitialPerson() != null){
            throw new ExistingPersonInTreeException();
        }

        //converter createPersonDto para person
        Person person = ConverterDtoModel.convertCreatePersonDtoToPerson(personDto);

        //salvar a pessoa no banco de dados
        personRepository.save(person);

        //adicionar a pessoa como nó inicial da árvore
        genealogicTree.setInitialPerson(person);

        //atualiza a árvore com a pessoa inicial
        genealogicTreeRepository.save(genealogicTree);


    }

    public PersonResponse findInformationPerson(InformationPersonDto personDto) throws Exception{

        Person person = findByIdPerson(personDto.getIdPerson());

        return CreatorResponses.createPersonResponse(person);

    }

    public void updateInformationPerson(UpdatePersonDto updatePersonDto) throws Exception{

        Person person = findByIdPerson(updatePersonDto.getIdPerson());

        person.setName(updatePersonDto.getName());
        person.setBirth(updatePersonDto.getBirth());
        person.setActive(updatePersonDto.getIsActive());
        person.setImage(updatePersonDto.getImage());

        personRepository.save(person);

    }

    public void createParents(CreateParentChildrenDto dto) throws Exception{

        Person personChildren = findByIdPerson(dto.getIdPerson());

        //se já existe pai biológico
        if(dto.getTypePerson() == TypePerson.BIOLOGICAL_FATHER && personChildren.getBiologicalFather() != null){
            throw new ExistingFatherException();
        }

        //se já existe mãe biológica
        if(dto.getTypePerson() == TypePerson.BIOLOGICAL_MOTHER && personChildren.getBiologicalMother() !=null){
            throw new ExistingMotherException();
        }

        Person personFatherMother = null;

        if(dto.getIdParentExisting() != null && dto.getIdParentExisting() != 0){
            personFatherMother = findByIdPerson(dto.getIdParentExisting());
        }else{
            personFatherMother = new Person(dto.getName(), true, personChildren.getLayer()+1);
            personRepository.save(personFatherMother);
        }


        addChildrenAndParents(personFatherMother.getId(), dto.getIdPerson(), dto.getTypePerson(), dto.getTypeParent());

    }

    public void createChildren(CreateParentChildrenDto dto) throws Exception{

        Person personParent = findByIdPerson(dto.getIdPerson());

        Person personChildren = new Person(dto.getName(), true, personParent.getLayer()+1);

        personRepository.save(personChildren);

        addChildrenAndParents(dto.getIdPerson(), personChildren.getId(), dto.getTypePerson(), dto.getTypeParent());

    }

    public void removeRelationshipsPerson(DeletePersonDto personDto) throws Exception{

        //deixa de referenciar (ter relação) os pais que fazem parte do ramo principal
        deleteParents(personDto.getIdPerson());

        //deixa de referenciar (ter relação) os filhos que fazem parte do ramo principal
        deleteChildren(personDto.getIdPerson());

        //caso a pessoa a ser deletada seja a initial person de uma árvore genealógica
        personRepository.deleteInitialPersonOfTheGenealogicTree(personDto.getIdPerson());

    }

    public void deletePerson(DeletePersonDto personDto){
        personRepository.deleteById(personDto.getIdPerson());
    }

    public void deleteParents(Integer personId) throws Exception{

        Person person = findByIdPerson(personId);

        List<Person> biologicalParents = personRepository.findBiologicalParents(person.getId());

        //caso ele não possua pais biológicos não será iniciado o for
        biologicalParents.stream().forEach(biologicalParent -> {

            //verifica se o pai/mãe faz parte do ramo principal e se não faz, ele verifica se pode deletar
            if(biologicalParent.getLayer() < person.getLayer() || (biologicalParents.size() == 2 &&
                            personRepository.personHasOneBiologicalParentsToTheMainBranch(person.getId(), person.getLayer()) == 1 &&
                            personRepository.checkBrothersPerson(person.getId(), biologicalParent.getId()).size() > 0)){

                //remove esse filho da lista de filhos biológicos que esse pai/mãe possui
                personRepository.deleteBiologicalChildren(biologicalParent.getId(), person.getId());

                //remove a relação desse filho com esse pai/mãe
                personRepository.deleteFatherOrMotherBiologic(biologicalParent.getId(), person.getId());

            }

        });

        //desconectar pais adotivos que fazem parte do ramo princiapal
        person.getAdoptiveParents().stream().forEach(adoptiveParent -> {
            if(adoptiveParent.getLayer() < person.getLayer()){
                //remove esse pai da lista de pais adotivos que esse filho tem
                personRepository.deleteFatherOrMotherAdoptive(adoptiveParent.getId(), person.getId());

                //remove o filho da lista de filhos adotivos que esse pai tem
                personRepository.deleteAdoptiveChildren(adoptiveParent.getId(),person.getId());
            }
        });


    }

    public void deleteChildren( Integer personId) throws Exception{

        Person person = findByIdPerson(personId);

        //desconectar filhos biológicos que fazem parte do ramo princiapal
        person.getBiologicalChildren().stream().forEach(personChildren ->{
            if(personChildren.getLayer() < person.getLayer()){
                //remove esse filho da lista de filhos biológicos que ele tem antes de apagar
                personRepository.deleteBiologicalChildren(person.getId(), personChildren.getId());
                //aqui deixa o pai biológico desse filho como nulo
                personRepository.deleteFatherOrMotherBiologic(person.getId(), personChildren.getId());
            }
        });

        //desconectar filhos adotivos que fazem parte do ramo princiapal
        person.getAdoptiveChildren().stream().forEach(personChildren ->{
            if(personChildren.getLayer() < person.getLayer()){
                //remove esse filho da lista de filhos adotivos que ele tem
                personRepository.deleteAdoptiveChildren(person.getId(), personChildren.getId());

                //remove esse pai da lista de pais adotivos que esse filho tem
                personRepository.deleteFatherOrMotherAdoptive(person.getId(), personChildren.getId());
            }
        });

    }

    /**
     * método responsável por atribuir um filho [BIOLOGICO ou ADOTIVO] ao seu pai/mãe [BIOLOGICO ou ADOTIVO] ou vice versa
     */
    public void addChildrenAndParents(Integer idPersonParent, Integer idPersonChildren, TypePerson typePerson, TypePerson typeParent) throws Exception{

        Person personParent = findByIdPerson(idPersonParent);
        Person personChildren = findByIdPerson(idPersonChildren);
        List<Person> listBiologicalChildren = new ArrayList<>();
        List<Person> listAdoptiveChildren = new ArrayList<>();
        List<Person> listAdoptiveParents = new ArrayList<>();

        if(typePerson == TypePerson.BIOLOGICAL_FATHER){

            listBiologicalChildren.addAll(personParent.getBiologicalChildren());

            personChildren.setBiologicalFather(personParent); //adiciona o pai biologico ao filho(a)
            listBiologicalChildren.add(personChildren); //add filho biológico a lista de filhos biológicos do pai

            personParent.setBiologicalChildren(listBiologicalChildren); //add nova lista de filhos biológicos do pai

        }else if(typePerson == TypePerson.BIOLOGICAL_MOTHER){

            listBiologicalChildren.addAll(personParent.getBiologicalChildren());

            personChildren.setBiologicalMother(personParent);
            listBiologicalChildren.add(personChildren);

            personParent.setBiologicalChildren(listBiologicalChildren);

        }else if(typePerson == TypePerson.ADOPTIVE_FATHER_MOTHER){

            listAdoptiveParents.addAll(personChildren.getAdoptiveParents()); //lista de pais adotivos do filho
            listAdoptiveChildren.addAll(personParent.getAdoptiveChildren()); //lista de filhos aotivos do pai

            listAdoptiveParents.add(personParent);
            listAdoptiveChildren.add(personChildren);

            personChildren.setAdoptiveParents(listAdoptiveParents);
            personParent.setAdoptiveChildren(listAdoptiveChildren);

        }else if(typePerson == TypePerson.BIOLOGICAL_CHILDREN){
            listBiologicalChildren.addAll(personParent.getBiologicalChildren());

            listBiologicalChildren.add(personChildren);
            personParent.setBiologicalChildren(listBiologicalChildren);

            if(typeParent == TypePerson.BIOLOGICAL_FATHER){
                personChildren.setBiologicalFather(personParent);
            }else if(typeParent == TypePerson.BIOLOGICAL_MOTHER){
                personChildren.setBiologicalMother(personParent);
            }


        }else if(typePerson == TypePerson.ADOPTIVE_CHILDREN){
            listAdoptiveChildren.addAll(personParent.getAdoptiveChildren());
            listAdoptiveParents.addAll(personChildren.getAdoptiveParents());

            listAdoptiveChildren.add(personChildren);
            listAdoptiveParents.add(personParent);

            personParent.setAdoptiveChildren(listAdoptiveChildren);
            personChildren.setAdoptiveParents(listAdoptiveParents);
        }

        personRepository.save(personParent);
        personRepository.save(personChildren);

    }

    public Person findByIdPerson(Integer id) throws Exception{

        Person person = personRepository.findById(id).orElse(null);

        if(person == null){
            throw new NotExistentEntityException();
        }

        return person;
    }


}
