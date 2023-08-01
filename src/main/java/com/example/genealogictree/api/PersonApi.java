package com.example.genealogictree.api;

import com.example.genealogictree.dto.*;
import com.example.genealogictree.response.BasicResponse;
import com.example.genealogictree.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
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
                log.error(e.getMessage());
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
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"erro ao recuperar informações da pessoa", null, e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"dados incorretos", result.getFieldErrors(), null));

    }

    @PutMapping("/person/update")
    public ResponseEntity<?> updateInformationPerson(@Valid @RequestBody UpdatePersonDto updatePersonDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                personService.updateInformationPerson(updatePersonDto);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new BasicResponse(HttpStatus.CREATED,"pessoa atualizada com sucesso", null, null));
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"erro ao atualizar informações da pessoa", null, e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"dados incorretos", result.getFieldErrors(), null));
    }

    @PostMapping("/person/new-parent")
    public ResponseEntity<?> createParents(@Valid @RequestBody CreateParentChildrenDto createParentChildrenDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                personService.createParents(createParentChildrenDto);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new BasicResponse(HttpStatus.CREATED,"pai/mãe criado com sucesso", null, null));
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"erro ao criar pai/mãe", null, e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"dados incorretos", result.getFieldErrors(), null));


    }

    @PostMapping("/person/new-children")
    public ResponseEntity<?> createChildren(@Valid @RequestBody CreateParentChildrenDto createParentChildrenDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                personService.createChildren(createParentChildrenDto);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new BasicResponse(HttpStatus.CREATED,"criança criada com sucesso", null, null));
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"erro ao criar criança", null, e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"dados incorretos", result.getFieldErrors(), null));

    }

    @DeleteMapping("/person/delete-relationships")
    public ResponseEntity<?> deleteRelationshipsPerson(@Valid @RequestBody DeletePersonDto deletePersonDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                personService.removeRelationshipsPerson(deletePersonDto);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new BasicResponse(HttpStatus.CREATED,"relacionamentos deletados com sucesso", null, null));
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"erro ao deletar relacionamentos", null, e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"dados incorretos", result.getFieldErrors(), null));

    }

    @DeleteMapping("/person/delete")
    public ResponseEntity<?> deletePerson(@Valid @RequestBody DeletePersonDto deletePersonDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                personService.deletePerson(deletePersonDto);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new BasicResponse(HttpStatus.CREATED,"pessoa deletada com sucesso", null, null));
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"erro ao deletar pessoa", null, e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"dados incorretos", result.getFieldErrors(), null));

    }

}
