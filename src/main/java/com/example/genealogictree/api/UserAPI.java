package com.example.genealogictree.api;

import com.example.genealogictree.dto.UserGTDto;
import com.example.genealogictree.response.BasicResponse;
import com.example.genealogictree.service.UserGTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/genealogictree")
public class UserAPI {

    @Autowired
    private UserGTService userGTService;

    @PostMapping("/usergt/create-account")
    public ResponseEntity<?> createUserGT(@Valid @RequestBody UserGTDto userGTDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                userGTService.crateUserGT(userGTDto);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new BasicResponse(HttpStatus.CREATED,"usuário criado com sucesso", null, null));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"erro ao criar usuário", null, e.getMessage()));
            }

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"dados incorretos", result.getFieldErrors(), null));

    }
}
