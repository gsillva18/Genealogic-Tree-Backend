package com.example.genealogictree.api;

import com.example.genealogictree.dto.*;
import com.example.genealogictree.response.BasicResponse;
import com.example.genealogictree.response.PersonResponse;
import com.example.genealogictree.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = "Person", value = "People Management API")
public class PersonApi {

    @Autowired
    private PersonService personService;

    @PostMapping("/person/new-person")
    @ApiOperation("Create person")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successfully saved person", response = BasicResponse.class),
            @ApiResponse(code = 400, message = "error creating person", response = BasicResponse.class)
    })
    public ResponseEntity<?> createPerson(@Valid @RequestBody CreatePersonDto personDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                personService.createPerson(personDto);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new BasicResponse(HttpStatus.CREATED,"successfully saved person", null, null));
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"error creating person", null, e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"incorrect data", result.getFieldErrors(), null));

    }

    @GetMapping("/person/information")
    @ApiOperation("Get person information")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successfully retrieved information", response = PersonResponse.class),
            @ApiResponse(code = 400, message = "error retrieving information", response = BasicResponse.class)
    })
    public ResponseEntity<?> informationPerson(@Valid @RequestBody InformationPersonDto informationPersonDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        personService.findInformationPerson(informationPersonDto));
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"error retrieving information", null, e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"incorrect data", result.getFieldErrors(), null));

    }

    @PutMapping("/person/update")
    @ApiOperation("Update person")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successfully updated person", response = BasicResponse.class),
            @ApiResponse(code = 400, message = "error updating person", response = BasicResponse.class)
    })
    public ResponseEntity<?> updateInformationPerson(@Valid @RequestBody UpdatePersonDto updatePersonDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                personService.updateInformationPerson(updatePersonDto);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new BasicResponse(HttpStatus.CREATED,"successfully updated person", null, null));
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"error updating person", null, e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"incorrect data", result.getFieldErrors(), null));
    }

    @PostMapping("/person/new-parent")
    @ApiOperation("Create parents")
    @ApiResponses({
            @ApiResponse(code = 200, message = "parent successfully created", response = BasicResponse.class),
            @ApiResponse(code = 400, message = "error creating parent", response = BasicResponse.class)
    })
    public ResponseEntity<?> createParents(@Valid @RequestBody CreateParentChildrenDto createParentChildrenDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                personService.createParents(createParentChildrenDto);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new BasicResponse(HttpStatus.CREATED,"parent successfully created", null, null));
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"error creating parent", null, e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"incorrect data", result.getFieldErrors(), null));


    }

    @PostMapping("/person/new-children")
    @ApiOperation("Create Children")
    @ApiResponses({
            @ApiResponse(code = 200, message = "children successfully created", response = BasicResponse.class),
            @ApiResponse(code = 400, message = "error creating children", response = BasicResponse.class)
    })
    public ResponseEntity<?> createChildren(@Valid @RequestBody CreateParentChildrenDto createParentChildrenDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                personService.createChildren(createParentChildrenDto);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new BasicResponse(HttpStatus.CREATED,"children successfully created", null, null));
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"error creating children", null, e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"incorrect data", result.getFieldErrors(), null));

    }

    @DeleteMapping("/person/delete-relationships")
    @ApiOperation("Delete a person's relationships")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successfully deleted relationships", response = BasicResponse.class),
            @ApiResponse(code = 400, message = "error deleting relationships", response = BasicResponse.class)
    })
    public ResponseEntity<?> deleteRelationshipsPerson(@Valid @RequestBody DeletePersonDto deletePersonDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                personService.removeRelationshipsPerson(deletePersonDto);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new BasicResponse(HttpStatus.CREATED,"successfully deleted relationships", null, null));
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"error deleting relationships", null, e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"incorrect data", result.getFieldErrors(), null));

    }

    @DeleteMapping("/person/delete")
    @ApiOperation("Delete person")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successfully deleted person", response = BasicResponse.class),
            @ApiResponse(code = 400, message = "error deleting person", response = BasicResponse.class)
    })
    public ResponseEntity<?> deletePerson(@Valid @RequestBody DeletePersonDto deletePersonDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                personService.deletePerson(deletePersonDto);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new BasicResponse(HttpStatus.CREATED,"successfully deleted person", null, null));
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"error deleting person", null, e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"incorrect data", result.getFieldErrors(), null));

    }

}
