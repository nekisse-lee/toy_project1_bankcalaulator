package com.nekisse.controllers;

import com.nekisse.domain.dto.FindUserRequestDto;
import com.nekisse.domain.dto.responsedto.BankDataResponse;
import com.nekisse.service.BankAccountService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class ApiController {
    private BankAccountService bankAccountService;

    public ApiController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }


    @GetMapping({""})
    public BankDataResponse findAll() {
        return bankAccountService.getAllDepositorDataResponse();
    }

    @GetMapping({"/find"})
    public BankDataResponse findOneDepositor(@Valid FindUserRequestDto name) {
        return bankAccountService.getDataListOfOneDepositor(name);
    }

//    @PostMapping("/deleteData")
    @DeleteMapping("/deleteData")
    public void deleteData() {
        bankAccountService.deleteAllData();
    }


}
