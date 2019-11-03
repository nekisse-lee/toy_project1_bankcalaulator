package com.nekisse.service.read;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;

import java.text.SimpleDateFormat;

public class ExcelCellRef {
    /**
     * Cell에 해당하는 Column Name을 가젼온다(A,B,C..)
     * 만약 Cell이 Null이라면 int cellIndex의 값으로
     * Column Name을 가져온다.
     *
     * @param cell
     * @param cellIndex
     * @return
     */
    public static String getName(Cell cell, int cellIndex) {
        int cellNum = 0;
        if (cell != null) {
            cellNum = cell.getColumnIndex();
        } else {
            cellNum = cellIndex;
        }

        return CellReference.convertNumToColString(cellNum);
    }


    public static String getValue(Cell cell, String cellName) {
        String value = "";

        if (cell == null) {
            value = "";
        } else {
            if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                value = cell.getCellFormula();
            } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                if (cellName.equals("A") && HSSFDateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat fommatter = new SimpleDateFormat("yyyy-MM-dd HH:ss");
                    value = fommatter.format(cell.getDateCellValue());
                } else {
//                    double ddata = cell.getNumericCellValue();
//                    if (HSSFDateUtil.isValidExcelDate(ddata)) {
//                        SimpleDateFormat fommatter = new SimpleDateFormat("yyyy-MM-dd HH:ss");
//                        value = fommatter.format(cell.getDateCellValue());
//                    } else {
//                        value = cell.getNumericCellValue() + "";
//                    }
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                    value = cell.getStringCellValue();
                }


            } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
//                if ((cellName.equals("C") && !cell.getStringCellValue().contains("이자세금"))) {
//                    String stringCellValue = cell.getStringCellValue();
//                    System.out.println("valueaa = " + stringCellValue.trim());
//                    value = cell.getStringCellValue().substring(1, stringCellValue.length()).trim();
//                }
                String stringCellValue = cell.getStringCellValue();
                value = cell.getStringCellValue().substring(1, stringCellValue.length()).trim();
            } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                value = cell.getBooleanCellValue() + "";
            } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
                value = cell.getErrorCellValue() + "";
            } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                value = "";
            } else {
                value = cell.getStringCellValue();
            }
        }

        return value;
    }

    public static String test(Cell cell) {
        cell.setCellType(Cell.CELL_TYPE_STRING);
        return cell.getStringCellValue();
    }
}

