package com.nekisse.domain.dto.responsedto;

import lombok.Builder;

@Builder
public class CalResultResponse {

    int userTargetMoney;

    int userTotalAmount;

    int result;

    int monthlyTargetMoney;

    public CalResultResponse() {
    }

    public CalResultResponse(int userTargetMoney, int userTotalAmount, int result, int monthlyTargetMoney) {
        this.userTargetMoney = userTargetMoney;
        this.userTotalAmount = userTotalAmount;
        this.result = result;
        this.monthlyTargetMoney = monthlyTargetMoney;
    }

    public int getUserTargetMoney() {
        return userTargetMoney;
    }

    public int getUserTotalAmount() {
        return userTotalAmount;
    }

    public int getResult() {
        return result;
    }

    public int getMonthlyTargetMoney() {
        return monthlyTargetMoney;
    }
}
