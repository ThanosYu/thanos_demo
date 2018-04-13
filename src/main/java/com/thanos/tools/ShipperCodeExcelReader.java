package com.thanos.tools;

import com.thanos.common.ZLException;
import com.thanos.model.kdniao.ShipperCode;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thanos_Yu
 * @date 2018/2/5.
 */
public class ShipperCodeExcelReader extends AbstractExcelReader<ShipperCode> {

    @Override
    public List<ShipperCode> readExcel(String path) throws ZLException, IOException {
        System.out.println(ExcelCommon.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        // Read the Sheet
        List<ShipperCode> list = new ArrayList<>();
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
//            List<String> headerNames = getHeaderNames();
//            int i = 0;
//            for (String name : headerNames) {
//                if (!name.equals(get2010Value(xssfSheet.getRow(0).getCell(i)))) {
//                    throw new ZLException(ZLErrorMessage.ZL_ERROR_FORM_FILE);
//                }
//                i++;
//            }
            // Read the Row
            for (int rowNum = 2; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (!isEmptyRow(xssfRow)) {
                    readDetail(list, xssfRow);
                }
            }
        }
        System.out.println("------------------------list size: " + list.size());
        return list;
    }

    @Override
    protected void readDetail(List<ShipperCode> list, XSSFRow xssfRow) {
        ShipperCode shipperCode = new ShipperCode();
        shipperCode.setCompany(get2010Value(xssfRow.getCell(2)));
        shipperCode.setCode(get2010Value(xssfRow.getCell(3)));
        shipperCode.setTrace(get2010Value(xssfRow.getCell(4)));
        shipperCode.seteTicket(get2010Value(xssfRow.getCell(5)));
        shipperCode.setReservation(get2010Value(xssfRow.getCell(6)));
        list.add(shipperCode);
    }

//    @Override
//    protected List<String> getHeaderNames() {
//        List<String> list = new ArrayList<>();
//        list.add("快递公司");
//        list.add("编码");
//        list.add("轨迹");
//        list.add("大盘价");
//        list.add("成本");
//        return list;
//    }


    public static void main(String[] args) {
        try {
            ShipperCodeExcelReader reader = new ShipperCodeExcelReader();
            List<ShipperCode> list = reader.readExcel("D:\\ASUS_SVN\\项目文档\\新零售\\8、开发环境及第三方组件\\快递鸟\\2018快递鸟接口支持快递公司编码.xlsx");
            System.out.println("--------size: " + list.size());
            System.out.println(list.get(0).getCompany());
        } catch (ZLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
