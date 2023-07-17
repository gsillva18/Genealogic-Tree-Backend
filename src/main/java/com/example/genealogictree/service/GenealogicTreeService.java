package com.example.genealogictree.service;

import com.example.genealogictree.core.exceptions.ExistingEntityException;
import com.example.genealogictree.core.exceptions.NotExistentEntityException;
import com.example.genealogictree.core.utils.ConverterDtoModel;
import com.example.genealogictree.core.utils.CreatorResponses;
import com.example.genealogictree.dto.CreateGenealogicTreeDto;
import com.example.genealogictree.dto.DeleteGenealogicTreeDto;
import com.example.genealogictree.dto.ListGenealogicTreeDto;
import com.example.genealogictree.model.entityaccount.AccountGT;
import com.example.genealogictree.model.entitygenealogictree.GenealogicTree;
import com.example.genealogictree.repository.GenealogicTreeRepository;
import com.example.genealogictree.response.CreateGenealogicTreeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenealogicTreeService {

    @Autowired
    private GenealogicTreeRepository genealogicTreeRepository;

    @Autowired
    private AccountGTService accountGTService;

    public CreateGenealogicTreeResponse createGenealogicTree(CreateGenealogicTreeDto genealogicTreeDto) throws Exception{

        AccountGT accountGT = accountGTService.findAccountGT(genealogicTreeDto.getIdAccount());

        if(genealogicTreeRepository.verificationByName(
                genealogicTreeDto.getName(), genealogicTreeDto.getIdAccount()) != 0){

            throw new ExistingEntityException();
        }

        GenealogicTree genealogicTree = ConverterDtoModel.convertGenealogictreeDtoToGenealogicTree(genealogicTreeDto);

        genealogicTreeRepository.save(genealogicTree);
        accountGT.addGenealogicTree(genealogicTree);
        accountGTService.updateAccountGT(accountGT);

        return CreatorResponses.createResponse(genealogicTree);

    }

    public List<?> listGenealogicTreeByAccount(ListGenealogicTreeDto genealogicTreeDto) throws Exception{

        AccountGT accountGT = accountGTService.findAccountGT(genealogicTreeDto.getIdAccount());

        return CreatorResponses.createResponseList(accountGT.getGenealogicTreeList());
    }

    public void updateGenealogicTree(){

    }

    public void findGenealogicTree(){

    }

    public void findAllGenealogicTree(){

    }

    public void deleteGenealogicTree(DeleteGenealogicTreeDto genealogicTreeDto) throws Exception{

        if(!genealogicTreeRepository.findById(genealogicTreeDto.getIdGenealogicTree()).isPresent()){
            throw new NotExistentEntityException();
        }

        genealogicTreeRepository.deleteById(genealogicTreeDto.getIdGenealogicTree());

    }
}
