package com.example.genealogictree.service;

import com.example.genealogictree.core.utils.ConverterDtoModel;
import com.example.genealogictree.dto.UserGTDto;
import com.example.genealogictree.model.entityaccount.UserGT;
import com.example.genealogictree.repository.UserGTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGTService {

    @Autowired
    private UserGTRepository userGTRepository;

    public void saveUserGT(UserGTDto userGTDto){

        UserGT user = ConverterDtoModel.convertUserGTDtoToUserGT(userGTDto);

        userGTRepository.save(user);

    }


}
