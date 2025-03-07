package com.nekisse.domain.dto;

import lombok.Builder;

@Builder
public class BankDto {

    private String tradingDate;

    private String depositor;

    private String withdrawalAmount;

    private Integer depositAmount;

    private String totalAmount;

    public BankDto(String tradingDate, String depositor, String withdrawalAmount, Integer depositAmount, String totalAmount) {
        this.tradingDate = tradingDate;
        this.depositor = depositor;
        this.withdrawalAmount = withdrawalAmount;
        this.depositAmount = depositAmount;
        this.totalAmount = totalAmount;
    }

    public BankDto() {
    }

    public BankDto(String tradingDate, String depositor, Integer depositAmount) {
        this.depositor = depositor;
        this.tradingDate = tradingDate;
        this.depositAmount = depositAmount;
    }

    public String getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(String tradingDate) {
        this.tradingDate = tradingDate;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public String getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(String withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public Integer getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Integer depositAmount) {
        this.depositAmount = depositAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "BankDto{" +
                "tradingDate='" + tradingDate + '\'' +
                ", depositor='" + depositor + '\'' +
                ", withdrawalAmount='" + withdrawalAmount + '\'' +
                ", depositAmount='" + depositAmount + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                '}' + "\n";
    }
}
