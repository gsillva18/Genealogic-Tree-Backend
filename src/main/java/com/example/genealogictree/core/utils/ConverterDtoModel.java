package com.example.genealogictree.core.utils;

import com.example.genealogictree.dto.UserGTDto;
import com.example.genealogictree.model.entityaccount.UserGT;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ConverterDtoModel {

    public static UserGT convertUserGTDtoToUserGT(UserGTDto userGTDto){

        UserGT userGT = new UserGT();
        userGT.setIdUserAuth0(userGTDto.getIdUserAuth0());

        return userGT;
    }
}
