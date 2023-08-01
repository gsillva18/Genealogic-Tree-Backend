package com.example.genealogictree.service;

import com.example.genealogictree.core.exceptions.NotExistentAccountException;
import com.example.genealogictree.model.entityaccount.AccountGT;
import com.example.genealogictree.model.entityaccount.UserGT;
import com.example.genealogictree.repository.AccountGTRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountGTService {

    @Autowired
    private AccountGTRepository accountGTRepository;

    public void createAccountGT(UserGT userGT){

        accountGTRepository.save(new AccountGT(userGT));
        log.info("conta criada com sucesso");

    }

    public AccountGT findAccountGT(Integer id) throws Exception{
       AccountGT accountGT = accountGTRepository.findById(id).orElse(null);

       if(accountGT == null){
           throw new NotExistentAccountException();
       }

       return accountGT;
    }

    public void updateAccountGT(AccountGT accountGT){
        accountGTRepository.save(accountGT);
        log.info("conta atualizada com sucesso");
    }

}
