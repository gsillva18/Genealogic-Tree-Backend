package com.example.genealogictree.core.utils;

import com.example.genealogictree.dto.UserGTDto;
import com.example.genealogictree.model.entityaccount.UserGT;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class ConverterDtoModel {

    public static UserGT convertUserGTDtoToUserGT(UserGTDto userGTDto){

        UserGT userGT = new UserGT();

        userGT.setEmail(userGTDto.getEmail());
        userGT.setName(userGTDto.getName());
        userGT.setPassword(new BCryptPasswordEncoder().encode(userGTDto.getPassword())); //codifica a senha
        userGT.setProfilePicture(userGTDto.getProfilePicture());

        return userGT;
    }
}
