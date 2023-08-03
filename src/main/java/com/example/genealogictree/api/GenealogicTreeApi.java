package com.example.genealogictree.api;

import com.example.genealogictree.dto.CreateGenealogicTreeDto;
import com.example.genealogictree.dto.DeleteGenealogicTreeDto;
import com.example.genealogictree.dto.InformationGenealogicTreeDto;
import com.example.genealogictree.dto.ListGenealogicTreeDto;
import com.example.genealogictree.response.BasicResponse;
import com.example.genealogictree.response.CreateGenealogicTreeResponse;
import com.example.genealogictree.response.ListGenealogicTreeResponse;
import com.example.genealogictree.service.GenealogicTreeService;
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
@Api(tags = "GenealogicTree", value = "API for registering family trees")
public class GenealogicTreeApi {

    @Autowired
    private GenealogicTreeService genealogicTreeService;

    @PostMapping("new-genealogic-tree")
    @ApiOperation("Create family tree")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successfully created family tree", response = CreateGenealogicTreeResponse.class),
            @ApiResponse(code = 400, message = "error creating family tree", response = BasicResponse.class)
    })
    public ResponseEntity<?> createGenealogicTree(@Valid @RequestBody CreateGenealogicTreeDto genealogicTreeDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(genealogicTreeService.createGenealogicTree(genealogicTreeDto));
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"error creating family tree", null, e.getMessage()));
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"incorrect data", result.getFieldErrors(), null));

    }

    @GetMapping("/list-genealogic-tree")
    @ApiOperation("List family trees")
    @ApiResponses({
            @ApiResponse(code = 200, message = "list returned successfully", response = ListGenealogicTreeResponse[].class),
            @ApiResponse(code = 400, message = "error listing family trees", response = BasicResponse.class)
    })
    public ResponseEntity<?> listGenealogicTree(@Valid @RequestBody ListGenealogicTreeDto genealogicTreeDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(genealogicTreeService.listGenealogicTreeByAccount(genealogicTreeDto));
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"error listing family trees", null, e.getMessage()));
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"incorrect data", result.getFieldErrors(), null));

    }

    @GetMapping("/information")
    @ApiOperation("Family tree information")
    @ApiResponses({
            @ApiResponse(code = 200, message = "rescued information", response = CreateGenealogicTreeResponse.class),
            @ApiResponse(code = 400, message = "error retrieving information", response = BasicResponse.class)
    })
    public ResponseEntity<?> informationGenealogicTree(@Valid @RequestBody InformationGenealogicTreeDto genealogicTreeDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        genealogicTreeService.informationGenealogicTree(genealogicTreeDto));
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"error fetching family tree information", null, e.getMessage()));
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"incorrect data", result.getFieldErrors(), null));


    }

    @DeleteMapping("/delete")
    @ApiOperation("Delete family tree")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successfully deleted family tree", response = BasicResponse.class),
            @ApiResponse(code = 400, message = "error deleting family tree", response = BasicResponse.class)
    })
    public ResponseEntity<?> deleteGenealogicTree(@Valid @RequestBody DeleteGenealogicTreeDto genealogicTreeDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                genealogicTreeService.deleteGenealogicTree(genealogicTreeDto);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new BasicResponse(HttpStatus.CREATED,"successfully deleted family tree", null, null));
            } catch (Exception e) {
                log.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"error deleting family tree", null, e.getMessage()));
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"incorrect data", result.getFieldErrors(), null));

    }
}
