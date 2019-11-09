package com.nekisse.domain.dto;

import net.bytebuddy.implementation.bind.annotation.Empty;
import org.springframework.lang.Nullable;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.text.DateFormat;
import java.util.regex.Matcher;

public class FindUserRequestDto {


    @NotBlank(message = "유저 검색시 이름이 필요합니다. ex)홍길동")
    private String depositor;

    private final String DATE_FORMAT = "(\\d{4})-(\\d{2})";


    @Pattern(message = "시작 날짜 입력이 잘못 되었습니다. ex) 1989-03" , regexp = DATE_FORMAT)
//            regexp = "((?:(?:[1]{1}\\d{1}\\d{1}\\d{1})|(?:[2]{1}\\d{3}))[-:\\/.](?:[0]?[1-9]|[1][012])[-:\\/.](?:(?:[0-2]?\\d{1})|(?:[3][01]{1})))(?![\\d])")
    private String startDate;

    @Pattern(message = "마지막 날짜 입력이 잘못 되었습니다. ex) 1989-11" , regexp = DATE_FORMAT )
    @Nullable
    private String endDate;

    public String getDepositor() {
        return depositor;
    }

    public String getStartDate() {
        return startDate;
    }


    public String getEndDate() {
        return endDate;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
