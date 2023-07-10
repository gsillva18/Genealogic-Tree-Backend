package com.example.genealogictree.service;

import com.example.genealogictree.core.exceptions.ExistingUserException;
import com.example.genealogictree.core.exceptions.PasswordException;
import com.example.genealogictree.core.utils.ConverterDtoModel;
import com.example.genealogictree.dto.UserGTDto;
import com.example.genealogictree.model.entityaccount.UserGT;
import com.example.genealogictree.repository.UserGTRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGTService {

    @Autowired
    private UserGTRepository userGTRepository;

    public void crateUserGT(UserGTDto userGTDto) throws Exception{

        if(userGTRepository.findByEmail(userGTDto.getEmail()).isPresent()){
            throw new ExistingUserException();
        }

        if(userGTDto.getPassword() == null || userGTDto.getPassword().length() < 6){
            throw new PasswordException();
        }

        UserGT user = ConverterDtoModel.convertUserGTDtoToUserGT(userGTDto);

        userGTRepository.save(user);

    }


}
