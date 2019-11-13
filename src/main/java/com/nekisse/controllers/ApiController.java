package com.nekisse.controllers;

import com.nekisse.domain.dto.FindUserRequestDto;
import com.nekisse.domain.dto.responsedto.BankDataResponse;
import com.nekisse.service.BankAccountService;
import com.nekisse.service.FileUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public BankDataResponse calculateOneDepositor(@Valid FindUserRequestDto name) {
        return bankAccountService.getDataListOfOneDepositor(name);
    }

    @GetMapping({"/findDepositor"})
    public BankDataResponse findBankListOfOneDepositor(FindUserRequestDto name) {
        return bankAccountService.getListOfOneDepositor(name);
    }

    //    @PostMapping("/deleteData")
    @DeleteMapping({"/deleteData/{fileName}","/deleteData"})
    public void deleteData(@RequestParam(required = false) String fileName) {
        System.out.println("fileNamezzzzzzzzzz = " + fileName);
        bankAccountService.deleteAllData();
        FileUtils.deleteFile(fileName);
    }


}
