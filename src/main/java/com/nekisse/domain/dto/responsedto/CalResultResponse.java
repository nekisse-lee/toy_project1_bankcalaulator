package com.nekisse.domain.dto.responsedto;

import lombok.Builder;

@Builder
public class CalResultResponse {

    int targetMoney;

    int userTotalAmount;

    int result;

    public CalResultResponse() {
    }

    public CalResultResponse(int targetMoney, int userTotalAmount, int result) {
        this.targetMoney = targetMoney;
        this.userTotalAmount = userTotalAmount;
        this.result = result;
    }

    public int getTargetMoney() {
        return targetMoney;
    }

    public int getUserTotalAmount() {
        return userTotalAmount;
    }

    public int getResult() {
        return result;
    }
}
