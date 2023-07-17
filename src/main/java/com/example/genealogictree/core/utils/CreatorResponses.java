package com.example.genealogictree.core.utils;

import com.example.genealogictree.model.entitygenealogictree.GenealogicTree;
import com.example.genealogictree.response.CreateGenealogicTreeResponse;
import com.example.genealogictree.response.ListGenealogicTreeResponse;

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
}
