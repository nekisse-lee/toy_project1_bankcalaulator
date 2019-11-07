package com.nekisse.domain.dto.responsedto;

import com.nekisse.domain.dto.BankDto;

import java.util.List;

public class BankDataResponse {

    private List<BankDto> bankDtoList;

    public BankDataResponse(List<BankDto> bankDtoList) {
        this.bankDtoList = bankDtoList;
    }

    public List<BankDto> getBankDtoList() {
        return bankDtoList;
    }
}
