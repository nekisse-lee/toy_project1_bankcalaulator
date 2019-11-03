package com.nekisse.service.read;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.nekisse.exception.SendUserFileBadRequestException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileType {
    /**
     * 엑셀파일을 읽어서 Workbook 객체에 리턴한다.
     * XLS와 XLSX 확장자를 비교한다.
     *
     * @param filePath
     * @return
     */
    public static Workbook getWorkbook(String filePath) {
        /*
         * FileInputStream은 파일의 경로에 있는 파일을
         * 읽어서 Byte로 가져온다.
         *
         * 파일이 존재하지 않는다면은
         * RuntimeException이 발생된다.
         */
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("파일이 존재하지 않습니다.", e);
        }
        Workbook wb = null;
        /*
         * 파일의 확장자를 체크해서 .XLS 라면 HSSFWorkbook에
         * .XLSX라면 XSSFWorkbook에 각각 초기화 한다.
         */
        if (checkFileTypeForXls(filePath)) {
            try {
                wb = new HSSFWorkbook(fis);
            } catch (IOException e) {
                throw new SendUserFileBadRequestException();
            }
        } else if (filePath.toUpperCase().endsWith(".XLSX")) {
            try {
                wb = WorkbookFactory.create(fis);
            } catch (IOException | InvalidFormatException e) {
                throw new SendUserFileBadRequestException();
            }
        }

        return wb;
    }

    public static boolean checkFileTypeForXls(String filePath) {
        return filePath.toUpperCase().endsWith(".XLS");
    }

}

