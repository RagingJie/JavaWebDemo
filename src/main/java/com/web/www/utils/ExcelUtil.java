package com.web.www.utils;

import cn.hutool.core.util.StrUtil;
import com.web.www.commom.enums.SysCommonStatusEnum;
import com.web.www.exception.BusinessRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ExcelUtil {

    /**
     * 导出数据，本方法仅适用于表头全部在第一行的情况
     *
     * @param fileName   文件名,为空时，默认为时间戳命名
     * @param filePath   文件路径
     * @param sheetNames sheet名称，为空时，默认为sheet1
     * @param exportData 导出数据,一个表示一个sheet的表单数据
     */
    public static void exportData(String fileName, String filePath, List<String> sheetNames, List<Map<String, Object>>... exportData) {
        // 创建工作簿
        SXSSFWorkbook workbook = new SXSSFWorkbook(100);
        // 文件名，为空时，默认为时间戳命名
        fileName = StrUtil.isBlank(fileName) ? "export_" + System.currentTimeMillis() : fileName;
        // 判断导出数据是否为空
        if (exportData == null) {
            throw new BusinessRuntimeException(SysCommonStatusEnum.EXPORT_ERROR.getCode(), SysCommonStatusEnum.EXPORT_ERROR.getMessage(), "导出数据为空");
        }
        // 判断导出文件路径是否为空
        if (StrUtil.isBlank(filePath)) {
            throw new BusinessRuntimeException(SysCommonStatusEnum.EXPORT_ERROR.getCode(), SysCommonStatusEnum.EXPORT_ERROR.getMessage(), "导出文件路径为空");
        }
        // sheet名称，为空时，默认为sheet1
        if (sheetNames == null) {
            sheetNames = Arrays.asList("sheet1");
        } else if (sheetNames.size() == 0) {
            sheetNames.add("sheet1");
        }
        // 创建sheet
  /*      for (int i = 0; i < sheetNames.size(); i++) {
            if (sheetNames.get(i) == null || sheetNames.get(i).trim().length() == 0) {
                sheetNames.set(i, "sheet" + (i + 1));
            }
            // 创建sheet
            SXSSFSheet sheet = workbook.createSheet(sheetNames.get(i));
            if (exportData[i] == null) continue;
            createExcel(exportData[i], sheet, workbook);
        }*/

        // AtomicInteger
        AtomicInteger index = new AtomicInteger(0);
        // 串行处理Sheet，workbook不支持并行处理，因为Apache POI的XSSFWorkbook并非线程安全，并行流操作可能导致部件名称冲突（part name derived from another part）
        sheetNames.stream().forEach(sheetName -> {
            // 获取当前索引并递增
            int currentIndex = index.getAndIncrement();
            if (sheetName.trim().length() == 0 || sheetName == null) {
                sheetName = "sheet" + (currentIndex + 1);
            }
            SXSSFSheet sheet = workbook.createSheet(sheetName);
            if (exportData[currentIndex] != null) {
                createExcel(exportData[currentIndex], sheet, workbook);
            }
        });

        // 生成文件
        File file = Paths.get(filePath, fileName + ".xlsx").toFile();

        // 生成输出流
        // 使用了Java 7引入的try-with-resources语法，用于自动管理FileInputStream文件输入流的资源释放
        // try-with-resources会确保在try代码块执行完毕后（无论是否发生异常），自动调用FileInputStream的close()方法释放资源，无需手动编写finally块
        // 语法要求：
        // 资源类（如FileInputStream）必须实现AutoCloseable或Closeable接口，FileInputStream已实现Closeable接口，因此支持该语法
        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
        } catch (FileNotFoundException e) {
            throw new BusinessRuntimeException(SysCommonStatusEnum.FILE_NOT_FOUND.getCode(), SysCommonStatusEnum.FILE_NOT_FOUND.getMessage(), e.getMessage());
        } catch (IOException e) {
            throw new BusinessRuntimeException(SysCommonStatusEnum.EXPORT_ERROR.getCode(), SysCommonStatusEnum.EXPORT_ERROR.getMessage(), e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                throw new BusinessRuntimeException(SysCommonStatusEnum.EXPORT_ERROR.getCode(), SysCommonStatusEnum.EXPORT_ERROR.getMessage(), e.getMessage());
            }
        }
    }

    /**
     * 创建表单
     *
     * @param exportData 导出数据
     * @param sheet      sheet
     * @param workbook   工作簿
     */
    private static void createExcel(List<Map<String, Object>> exportData, SXSSFSheet sheet, SXSSFWorkbook workbook) {
        for (int i = 0; i < exportData.size(); i++) {
            SXSSFRow row = sheet.createRow(i);
            if (i == 0) {
                row.setHeightInPoints(21.5f);
                int cellIndex = 0;
                for (Map.Entry<String, Object> entry : exportData.get(i).entrySet()) {
                    // 设置列宽
                    sheet.setColumnWidth(cellIndex, 12 * 256);
                    // 创建表单标题单元格
                    SXSSFCell cell = row.createCell(cellIndex);
                    // 设置表单标题单元格内容
                    cell.setCellValue(entry.getValue().toString());
                    CellStyle cellStyle = createHeaderStyle(workbook);
                    // 设置表单标题单元格样式
                    cell.setCellStyle(cellStyle);
                    // 索引加1
                    cellIndex++;
                }
            } else {
                int cellIndex = 0;
                for (Map.Entry<String, Object> entry : exportData.get(i).entrySet()) {
                    // 创建表单标题单元格
                    SXSSFCell cell = row.createCell(cellIndex);
                    // 设置表单标题单元格内容
                    cell.setCellValue(entry.getValue().toString());
                    // 设置表单标题单元格样式
                    CellStyle cellStyle = createContentStyle(workbook);
                    // 设置表单标题单元格样式
                    cell.setCellStyle(cellStyle);
                    // 索引加1
                    cellIndex++;
                }

            }
        }
    }

    /**
     * 创建内容样式
     *
     * @param workbook 工作簿
     * @return 内容样式
     */
    private static CellStyle createContentStyle(SXSSFWorkbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        // 设置四周边框为实线
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        return cellStyle;
    }

    /**
     * 创建表头样式
     *
     * @param workbook 工作簿
     * @return 表头样式
     */
    private static CellStyle createHeaderStyle(SXSSFWorkbook workbook) {
        // 设置单元格样式
        CellStyle cellStyle = workbook.createCellStyle();
        // 创建自定义颜色（RGB: 232, 232, 232）,背景颜色
        XSSFColor bgColor = new XSSFColor(new java.awt.Color(8, 188, 220, 255), new DefaultIndexedColorMap());
        cellStyle.setFillForegroundColor(bgColor);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 必须设置，否则背景色无效
        // 设置四周边框为实线
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);

        // 设置字体样式
        Font font = workbook.createFont();
        // 设置字体加粗
        font.setBold(true);
        // 设置字体大小为14
        font.setFontHeightInPoints((short) 14);

        // 设置字体
        cellStyle.setFont(font);
        return cellStyle;
    }

    /**
     * 导入数据 -- 读取数据信息
     * <p>
     * 注：
     * 只读取sheet1的数据信息
     *
     * @param is 文件流
     */
    public static void readData(InputStream is) throws IOException {
        System.out.println("导入---读取数据信息");
        // 语法要求：try-with-resource优势：
        // 1. try-with-resource语法要求：资源类（如FileInputStream）必须实现AutoCloseable或Closeable接口，FileInputStream已实现Closeable接口，因此支持该语法
        // 2. try-with-resource语法要求：try-with-resource语句会自动调用close()方法，因此不需要try-catch-finally语句
        try (XSSFWorkbook workbook = new XSSFWorkbook(is)) {
            // 获取sheet1
            Sheet sheet = workbook.getSheetAt(0);
            // 获取最后一行行号
            int lastRowNum = sheet.getLastRowNum();
            // 遍历每一行
            for (int i = 0; i <= lastRowNum; i++) {
                // 获取每一行
                Row row = sheet.getRow(i);
                // 获取最后一列列号
                short lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    // 获取每一列
                    Cell cell = row.getCell(j);
                    if (cell == null) {

                    } else {
                        String cellValue = getCellValue(cell);
                    }
                }
            }
        }
    }

    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        CellType cellType = cell.getCellType();
        if (cellType.equals(CellType.BLANK) || cellType.equals(CellType._NONE)) {
            return "";
        } else if (cellType.equals(CellType.STRING)) {// 字符串
            return String.valueOf(cell.getStringCellValue());
        } else if (cellType.equals(CellType.NUMERIC)) {// 数字
            cell.setCellType(CellType.STRING);
            return String.valueOf(cell.getStringCellValue());
        } else if (cellType.equals(CellType.BOOLEAN)) {// Boolean
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cellType.equals(CellType.ERROR)) {// 故障
            return String.valueOf(cell.getErrorCellValue());
        } else if (cellType.equals(CellType.FORMULA)) {// 公式
            String value = "";
            try {
                value = String.valueOf(cell.getNumericCellValue());
            } catch (IllegalStateException e) {
                value = String.valueOf(cell.getRichStringCellValue());
            }
            return value;
        } else {
            return "";
        }
    }
}
