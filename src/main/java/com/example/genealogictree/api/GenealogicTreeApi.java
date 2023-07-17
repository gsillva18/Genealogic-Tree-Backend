package com.example.genealogictree.api;

import com.example.genealogictree.dto.CreateGenealogicTreeDto;
import com.example.genealogictree.dto.DeleteGenealogicTreeDto;
import com.example.genealogictree.dto.ListGenealogicTreeDto;
import com.example.genealogictree.response.BasicResponse;
import com.example.genealogictree.service.GenealogicTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/genealogictree")
public class GenealogicTreeApi {

    @Autowired
    private GenealogicTreeService genealogicTreeService;

    @PostMapping("new-genealogic-tree")
    public ResponseEntity<?> createGenealogicTree(@Valid @RequestBody CreateGenealogicTreeDto genealogicTreeDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(genealogicTreeService.createGenealogicTree(genealogicTreeDto));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"erro ao criar árvore genealógica", null, e.getMessage()));
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"dados incorretos", result.getFieldErrors(), null));

    }

    @GetMapping("/list-genealogic-tree")
    public ResponseEntity<?> listGenealogicTree(@Valid @RequestBody ListGenealogicTreeDto genealogicTreeDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                return ResponseEntity.status(HttpStatus.CREATED).body(genealogicTreeService.listGenealogicTreeByAccount(genealogicTreeDto));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"erro ao listar árvores genealógicas", null, e.getMessage()));
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"dados incorretos", result.getFieldErrors(), null));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteGenealogicTree(@Valid @RequestBody DeleteGenealogicTreeDto genealogicTreeDto, BindingResult result){

        if(!result.hasErrors()){
            try {
                genealogicTreeService.deleteGenealogicTree(genealogicTreeDto);
                return ResponseEntity.status(HttpStatus.CREATED).body(
                        new BasicResponse(HttpStatus.CREATED,"árvore genealógica deletada", null, null));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new BasicResponse(HttpStatus.BAD_REQUEST,"erro ao deletar árvore genealógica", null, e.getMessage()));
            }
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new BasicResponse(HttpStatus.BAD_REQUEST,"dados incorretos", result.getFieldErrors(), null));

    }
}
