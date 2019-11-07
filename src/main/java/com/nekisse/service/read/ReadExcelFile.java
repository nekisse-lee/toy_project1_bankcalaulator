package com.nekisse.service.read;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ReadExcelFile {

    public static List<Map<String, String>> read(ExcelReadOption excelReadOption) {
        //엑셀 파일 자체
        //엑셀파일을 읽어 들인다.
        //FileType.getWorkbook() <-- 파일의 확장자에 따라서 적절하게 가져온다.
        Workbook wb = ExcelFileType.getWorkbook(excelReadOption.getFilePath());
        /**
         * 엑셀 파일에서 첫번째 시트를 가지고 온다.
         */
        Sheet sheet = wb.getSheetAt(0);

        System.out.println("Sheet 이름: " + wb.getSheetName(0));
        System.out.println("데이터가 있는 Sheet의 수 :" + wb.getNumberOfSheets());
        /**
         * sheet에서 유효한(데이터가 있는) 행의 개수를 가져온다.
         */
        int numOfRows = sheet.getPhysicalNumberOfRows() - 1;
        int numOfCells = 0;

        Row row = null;
        Cell cell = null;

        String cellName = "";
        /**
         * 각 row마다의 값을 저장할 맵 객체
         * 저장되는 형식은 다음과 같다.
         * put("A", "이름");
         * put("B", "게임명");
         */
        Map<String, String> map = null;
        /*
         * 각 Row를 리스트에 담는다.
         * 하나의 Row를 하나의 Map으로 표현되며
         * List에는 모든 Row가 포함될 것이다.
         */
        List<Map<String, String>> rowResult = new ArrayList<Map<String, String>>();
        /**
         * 각 Row만큼 반복을 한다.
         */
        ;
        for (int rowIndex = excelReadOption.getStartRow(); rowIndex < numOfRows; rowIndex++) {
            /*
             * 워크북에서 가져온 시트에서 rowIndex에 해당하는 Row를 가져온다.
             * 하나의 Row는 여러개의 Cell을 가진다.
             */
            row = sheet.getRow(rowIndex);

//            Row row1 = checkDeleteRow(row, sheet);
//            row= row1;

            if (row != null) {
                /*
                 * 가져온 Row의 Cell의 개수를 구한다.
                 */
                numOfCells = row.getPhysicalNumberOfCells();
                /*
                 * 데이터를 담을 맵 객체 초기화
                 */
                map = new HashMap<String, String>();
                /*
                 * row의 cell의 수 만큼 반복한다.
                 */
                for (int cellIndex = 0; cellIndex < numOfCells; cellIndex++) {
                    /*
                     * Row에서 CellIndex에 해당하는 Cell을 가져온다.
                     */
                    cell = row.getCell(cellIndex);
                    /*
                     * 현재 Cell의 이름을 가져온다
                     * 이름의 예 : A,B,C,D,......
                     */
                    cellName = ExcelCellRef.getName(cell, cellIndex);
//                    System.out.println("cellName = " + cellName);
//                    System.out.println("cellName + cell = " + cell + " : " + cellName);
                    /*
                     * 추출 대상 컬럼인지 확인한다
                     * 추출 대상 컬럼이 아니라면,
                     * for로 다시 올라간다
                     */
//                    if (!excelReadOption.getOutputColumns().contains(cellName)) {
//                        continue;
//                    }

                    /*
                     * map객체의 Cell의 이름을 키(Key)로 데이터를 담는다.
                     */

                    String value = ExcelCellRef.getValue(cell, cellName);
                    map.put(cellName, value);
                }
                /*
                 * 만들어진 Map객체를 List로 넣는다.
                 */
                rowResult.add(map);
            }
        }
//        System.out.println("rowResult = " + rowResult);

        return rowResult;

    }

    private static Row checkDeleteRow(Row row, Sheet sheet) {
        if (row != null) {
            Cell cell = row.getCell(0);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            if (!cell.getStringCellValue().equals("거래일시")) {
                return null;
            }
        }
        return row;
    }


/*
    public static void main(String[] args) {


        List<String> name = new ArrayList<>();
        ExcelReadOption ro = new ExcelReadOption();
//            ro.setFilePath("/Users/nekisse/Downloads/KB_거래내역조회 복사본.xlsx");
//            ro.setFilePath("/Users/nekisse/Downloads/KB_거래내역조회.xls");
        ro.setFilePath("/Users/nekisse/Desktop/크롬 다운/KB_거래내역조회(459601-01-580016_20191020160628).xls");
        ro.setOutputColumns("A", "C", "E", "F", "G");
//            ro.setStartRow(0);

        Set<Member> members = new HashSet<>();
        List<Map<String, String>> result = LocalRead.read(ro);

        for (int i = 1; i < result.size() - 1; i++) {
            String beforeName = result.get(i).get("C");
            Map<String, String> row = result.get(i);
            List<String> outputColumns = ro.getOutputColumns();
            BankDto bankDto = new BankDto();
//            BankAccountService.saveRepository(row, outputColumns, bankDto);


            members.add(new Member(beforeName));
        }
        System.out.println("members = " + members);


//        for (int i = 1; i < result.size() - 1; i++) {
//            String beforeName = result.get(i).get("C");
//            List<String> names = new ArrayList<>();
//            Iterator<Member> iterator = members.iterator();
//
//
//                members.add(new Member(beforeName));
//        }
//        System.out.println("members = " + members);




*/
/*
            for(Map<String, String> map : result) {

                String c = map.get("C").trim();
                String f = map.get("F");

                System.out.println(c + " : " + f);

                if (c.trim().length() == 4) {
                    name.add(c);
                }

            }
*//*

//            System.out.println("name = " + name);
    }
*/
}
