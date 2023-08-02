package com.example.genealogictree.api;

import com.example.genealogictree.dto.UserGTDto;
import com.example.genealogictree.response.BasicResponse;
import com.example.genealogictree.service.UserGTService;
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
@Api(tags = "UserGT", value = "API for registering users and accounts")
public class UserApi {

    @Autowired
    private UserGTService userGTService;


    @PostMapping("/usergt/create-account")
    @ApiOperation("Create user and account")
    @ApiResponses({
            @ApiResponse(code = 200, message = "user successfully saved", response = BasicResponse.class),
            @ApiResponse(code = 400, message = "error creating user", response = BasicResponse.class)
    })
    public ResponseEntity<?> saveUserGT(@Valid @RequestBody UserGTDto userGTDto, BindingResult result){
        if(!result.hasErrors()){
            try {
                userGTService.saveUserGT(userGTDto);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new BasicResponse(HttpStatus.CREATED,"user successfully saved", null, null));
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"error creating user", null, e.getMessage()));
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"incorrect data", result.getFieldErrors(), null));

    }
}

