package com.nekisse.controllers;

import com.nekisse.domain.dto.BankDto;
import com.nekisse.domain.dto.FindUserRequestDto;
import com.nekisse.domain.dto.responsedto.BankDataResponse;
import com.nekisse.service.BankAccountService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private BankAccountService bankAccountService;
    public ApiController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }



    @GetMapping({""})
    public BankDataResponse findAll() {

        return bankAccountService.getBankDataResponse();
    }

}
