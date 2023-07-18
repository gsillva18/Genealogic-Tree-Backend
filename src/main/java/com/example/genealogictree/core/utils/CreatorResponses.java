package com.example.genealogictree.core.utils;

import com.example.genealogictree.core.enums.TypePerson;
import com.example.genealogictree.dto.ParentsAndChildrenDto;
import com.example.genealogictree.model.entitygenealogictree.GenealogicTree;
import com.example.genealogictree.model.entitygenealogictree.Person;
import com.example.genealogictree.response.CreateGenealogicTreeResponse;
import com.example.genealogictree.response.ListGenealogicTreeResponse;
import com.example.genealogictree.response.PersonResponse;

import java.util.ArrayList;
import java.util.List;

public class CreatorResponses {

    public static CreateGenealogicTreeResponse createResponse(GenealogicTree genealogicTree){

        CreateGenealogicTreeResponse response = new CreateGenealogicTreeResponse();

        response.setIdGenealogicTree(genealogicTree.getId());
        response.setNameGenealogicTree(genealogicTree.getName());
        response.setInitialPerson(genealogicTree.getInitialPerson());

        return response;
    }

    public static List<ListGenealogicTreeResponse> createResponseList(List<GenealogicTree> genealogicTreeList){

        List<ListGenealogicTreeResponse> responseList = new ArrayList<>();

        for (GenealogicTree tree: genealogicTreeList) {

            ListGenealogicTreeResponse response = new ListGenealogicTreeResponse();

            response.setIdGenealogicTree(tree.getId());
            response.setName(tree.getName());
            response.setCriationDate(tree.getCreationDate());

            responseList.add(response);
        }

        return responseList;
    }

    public static PersonResponse createPersonResponse(Person person){

        PersonResponse personResponse = new PersonResponse();
        List<ParentsAndChildrenDto> listParents = new ArrayList<>();
        List<ParentsAndChildrenDto> listChildren = new ArrayList<>();

        personResponse.setId(person.getId());
        personResponse.setName(person.getName());
        personResponse.setBirth(person.getBirth());
        personResponse.setImage(person.getImage());
        personResponse.setIsActive(person.isActive());

        if(person.getBiologicalFather() != null){
            listParents.add(createParentsAndChildrenDto(person.getBiologicalFather(), TypePerson.BIOLOGIC_FATHER));
        }

        if(person.getBiologicalMother() != null){
            listParents.add(createParentsAndChildrenDto(person.getBiologicalMother(), TypePerson.BIOLOGIC_MOTHER));
        }

        person.getAdoptiveParents().stream().forEach(adoptiveParentsPerson ->{
            listParents.add(createParentsAndChildrenDto(adoptiveParentsPerson, TypePerson.ADOPTIVE_FATHER_MOTHER));
        });

        person.getBiologicalChildren().stream().forEach(biologicChildrenPerson ->{
            listChildren.add(createParentsAndChildrenDto(biologicChildrenPerson, TypePerson.BIOLOGIC_CHILDREN));
        });

        person.getAdoptiveChildren().stream().forEach(adoptiveChildrenPerson->{
            listChildren.add(createParentsAndChildrenDto(adoptiveChildrenPerson, TypePerson.ADOPTIVE_CHILDREN));
        });

        personResponse.setListParents(listParents);
        personResponse.setListChildren(listChildren);


        return personResponse;
    }

    private static ParentsAndChildrenDto createParentsAndChildrenDto(Person person, TypePerson typePerson){

        ParentsAndChildrenDto dto = null;

        if(person != null){
            dto = new ParentsAndChildrenDto();
            dto.setIdPerson(person.getId());
            dto.setName(person.getName());
            dto.setType(typePerson);
        }

        return dto;
    }
}
