package com.example.genealogictree.service;

import com.example.genealogictree.core.enums.TypePerson;
import com.example.genealogictree.core.exceptions.ExistingFatherException;
import com.example.genealogictree.core.exceptions.ExistingMotherException;
import com.example.genealogictree.core.exceptions.ExistingPersonInTreeException;
import com.example.genealogictree.core.exceptions.NotExistentEntityException;
import com.example.genealogictree.core.utils.ConverterDtoModel;
import com.example.genealogictree.core.utils.CreatorResponses;
import com.example.genealogictree.dto.CreateParentChildrenDto;
import com.example.genealogictree.dto.CreatePersonDto;
import com.example.genealogictree.dto.InformationPersonDto;
import com.example.genealogictree.dto.UpdatePersonDto;
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

        Person personFatherMother = new Person(dto.getName(), true);

        personRepository.save(personFatherMother);

        addChildrenAndParents(personFatherMother.getId(), dto.getIdPerson(), dto.getTypePerson(), dto.getTypeParent());

    }

    public void createChildren(CreateParentChildrenDto dto) throws Exception{

        Person personChildren = new Person(dto.getName(), true);

        personRepository.save(personChildren);

        addChildrenAndParents(dto.getIdPerson(), personChildren.getId(), dto.getTypePerson(), dto.getTypeParent());

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
