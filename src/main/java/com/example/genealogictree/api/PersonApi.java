package com.example.genealogictree.api;

import com.example.genealogictree.dto.CreatePersonDto;
import com.example.genealogictree.dto.InformationPersonDto;
import com.example.genealogictree.response.BasicResponse;
import com.example.genealogictree.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/genealogictree")
public class PersonApi {

    @Autowired
    private PersonService personService;

    @PostMapping("/person/new-person")
    public ResponseEntity<?> createPerson(@Valid @RequestBody CreatePersonDto personDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                personService.createPerson(personDto);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new BasicResponse(HttpStatus.CREATED,"pessoa salva com sucesso", null, null));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"erro ao criar pessoa", null, e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"dados incorretos", result.getFieldErrors(), null));

    }

    @GetMapping("/person/information")
    public ResponseEntity<?> informationPerson(@Valid @RequestBody InformationPersonDto informationPersonDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        personService.findInformationPerson(informationPersonDto));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"erro ao recuperar informações da pessoa", null, e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"dados incorretos", result.getFieldErrors(), null));

    }
}
