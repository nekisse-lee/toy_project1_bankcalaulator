package com.nekisse.domain;

public class DepositHistoryDto {


    private String tradingDate;

    private int withdrawalAmount;

    private int depositAmount;

    public DepositHistoryDto(String tradingDate, int withdrawalAmount, int depositAmount) {
        this.tradingDate = tradingDate;
        this.withdrawalAmount = withdrawalAmount;
        this.depositAmount = depositAmount;
    }

    public String getTradingDate() {
        return tradingDate;
    }

    public void setTradingDate(String tradingDate) {
        this.tradingDate = tradingDate;
    }

    public int getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(int withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public int getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(int depositAmount) {
        this.depositAmount = depositAmount;
    }
}
