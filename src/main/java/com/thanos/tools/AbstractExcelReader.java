package com.thanos.tools;

import com.thanos.common.ZLErrorMessage;
import com.thanos.common.ZLException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thanos_Yu
 * @date 2018/2/5.
 */
public abstract class AbstractExcelReader<T> {

    public List<T> readExcel(String path) throws ZLException, IOException {
        System.out.println(ExcelCommon.PROCESSING + path);
        InputStream is = new FileInputStream(path);
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        // Read the Sheet
        List<T> list = new ArrayList<>();
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            List<String> headerNames = getHeaderNames();
            int i = 0;
            for (String name : headerNames) {
                if (!name.equals(get2010Value(xssfSheet.getRow(0).getCell(i)))) {
                    throw new ZLException(ZLErrorMessage.ZL_ERROR_FORM_FILE);
                }
                i++;
            }
            // Read the Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (!isEmptyRow(xssfRow)) {
                    readDetail(list, xssfRow);
                }
            }
        }
        return list;
    }

    /**
     * 获取表头
     *
     * @return 表头
     */
    protected List<String> getHeaderNames(){
        return null;
    }

    /**
     * 逐行读取excel数据到list
     *
     * @param list    model
     * @param xssfRow 行
     */
    protected void readDetail(List<T> list, XSSFRow xssfRow){
    }

    /**
     * 判断是否空行
     *
     * @param xssfRow 行
     * @return true or false
     */
    public Boolean isEmptyRow(XSSFRow xssfRow) {
        if (xssfRow == null) {
            return true;
        }
        for (int i = xssfRow.getFirstCellNum(); i < xssfRow.getLastCellNum(); i++) {
            if (xssfRow.getCell(i) != null && xssfRow.getCell(i).getCellType() != Cell.CELL_TYPE_BLANK) {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据值类型不同从cell中取值
     *
     * @param xssfRow 行
     * @return String 类型的值
     */
    public String get2010Value(XSSFCell xssfRow) {
        if (xssfRow != null) {
            if (xssfRow.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
                return String.valueOf(xssfRow.getBooleanCellValue());
            } else if (xssfRow.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
                if (DateUtil.isCellDateFormatted(xssfRow)) {
                    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(xssfRow.getDateCellValue());
                }
                return String.valueOf(xssfRow.getNumericCellValue());
            } else {
                return String.valueOf(xssfRow.getStringCellValue());
            }
        } else {
            return null;
        }
    }

    /**
     * 譬如年月日的格式化
     *
     * @param str 待格式化参数
     * @return 格式化后的参数
     */
    public String formatString(String str) {
        if (!StringUtils.isEmpty(str)) {
            str = String.valueOf(Double.valueOf(str).intValue());
            return str;
        }
        return str;
    }

    /**
     * String 转 BigDecimal
     *
     * @param str
     * @return
     */
    public BigDecimal stringToBigDecimal(String str) {
        BigDecimal decimal;
        if (!StringUtils.isEmpty(str)) {
            decimal = BigDecimal.valueOf(Double.valueOf(str));
            return decimal;
        }
        decimal = BigDecimal.ZERO;
        return decimal;
    }
}
