package com.nekisse.exception;

public class SendUserFileBadRequestException extends RuntimeException {
    public SendUserFileBadRequestException() {
        super("엑셀 파일이 존재하지 않습니다.( .xls 파일만 지원합니다. )");
    }
}
