package com.nekisse.domain.dto.responsedto;

import com.nekisse.domain.dto.BankDto;

import java.util.List;

public class BankDataResponse {

    private List<BankDto> bankDtoList;

    int totalAmount;

    public BankDataResponse(List<BankDto> bankDtoList, int totalAmount) {
        this.bankDtoList = bankDtoList;
        this.totalAmount = totalAmount;
    }

    public BankDataResponse(List<BankDto> bankDtoList) {
        this.bankDtoList = bankDtoList;
    }

    public List<BankDto> getBankDtoList() {
        return bankDtoList;
    }

    public int getTotalAmount() {
        return totalAmount;
    }
}
