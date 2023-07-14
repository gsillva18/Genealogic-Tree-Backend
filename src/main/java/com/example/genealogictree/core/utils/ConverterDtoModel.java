package com.example.genealogictree.core.utils;

import com.example.genealogictree.dto.CreateGenealogicTreeDto;
import com.example.genealogictree.dto.UserGTDto;
import com.example.genealogictree.model.entityaccount.UserGT;
import com.example.genealogictree.model.entitygenealogictree.GenealogicTree;

public class ConverterDtoModel {

    public static UserGT convertUserGTDtoToUserGT(UserGTDto userGTDto){

        UserGT userGT = new UserGT();
        userGT.setIdUserAuth0(userGTDto.getIdUserAuth0());

        return userGT;
    }

    public static GenealogicTree convertGenealogictreeDtoToGenealogicTree(CreateGenealogicTreeDto genealogicTreeDto){

        GenealogicTree genealogicTree = new GenealogicTree();

        genealogicTree.setName(genealogicTreeDto.getName());

        return genealogicTree;
    }
}
