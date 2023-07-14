package com.example.genealogictree.service;

import com.example.genealogictree.core.exceptions.NotExistentAccount;
import com.example.genealogictree.core.exceptions.NotExistentEntityException;
import com.example.genealogictree.model.entityaccount.AccountGT;
import com.example.genealogictree.model.entityaccount.UserGT;
import com.example.genealogictree.repository.AccountGTRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountGTService {

    @Autowired
    private AccountGTRepository accountGTRepository;

    public void createAccountGT(UserGT userGT){

        accountGTRepository.save(new AccountGT(userGT));

    }

    public AccountGT findAccountGT(Integer id) throws Exception{
       AccountGT accountGT = accountGTRepository.findById(id).orElse(null);

       if(accountGT == null){
           throw new NotExistentAccount();
       }

       return accountGT;
    }

    public void updateAccountGT(AccountGT accountGT){
        accountGTRepository.save(accountGT);
    }

    public void findAccountGT(){

    }

    public void deleteAccountGT(){

    }
}
