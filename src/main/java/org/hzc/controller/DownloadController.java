package org.hzc.controller;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hzc.entity.House;
import org.hzc.exception.HRSException;
import org.hzc.service.ApplyService;
import org.hzc.service.ContractService;
import org.hzc.service.HouseService;
import org.hzc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/download")
@Controller
public class DownloadController extends BaseController {
    @Autowired
    ApplyService applyService;
    @Autowired
    HouseService houseService;
    @Autowired
    UserService userService;
    @Autowired
    ContractService contractService;
    @GetMapping
    public String show(Model model)throws HRSException{
        return "index";
    }


    /**
     * 这种形式的导出为文件，不管你是什么格式都可以。只需要会是用指定格式的库。
     * @param response
     */
    @GetMapping("/downloadHouseRentMessage")
    public void download(HttpServletResponse response) throws HRSException, IOException {
        response.setHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=HouseRentMessage.xls");

        try (XSSFWorkbook wb = new XSSFWorkbook()){
            XSSFSheet sheet = wb.createSheet("sheet1");

            // Create a Font for styling header cells
            Font headerFont = wb.createFont();

            // Create a CellStyle with the font
            CellStyle headerCellStyle = wb.createCellStyle();
            headerCellStyle.setFont(headerFont);


            String[] header = new String[]{
                    "该房屋所在小区名"
                    , "该房屋所在小区地址"
                    , "该房屋所在小区简介"
                    , "房屋标题"
                    , "房东电话"
                    , "房东姓名"
                    , "房屋地址"
                    , "房屋月租金"
                    , "房屋出租面积"
                    , "房屋户型"
                    , "房屋出租状态"
                    , "房屋最后修改时间"

            };
            // write header
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < header.length; i++){
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(header[i]);
                cell.setCellStyle(headerCellStyle);
                sheet.autoSizeColumn(i);
            }
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            //List<House> houses = houseService.getAll();
            List<House> houses = new ArrayList<>();
            houses = houseService.getAll();
            for (int i = 0; i < houses.size(); i++){
                XSSFRow row = sheet.createRow(i + 1);
                setCell(row, 0, CellType.STRING, houses.get(i).getCommunity().getName());
                setCell(row, 1, CellType.STRING, houses.get(i).getCommunity().getAddress());
                setCell(row, 2, CellType.STRING, houses.get(i).getCommunity().getCommunityIntroduction());
                setCell(row, 3, CellType.STRING, houses.get(i).getTitle());
                setCell(row, 4, CellType.STRING, houses.get(i).getLandlord().getPhone());
                setCell(row, 5, CellType.STRING, houses.get(i).getLandlord().getName());
                setCell(row, 6, CellType.STRING, houses.get(i).getHouseAddress());
                setCell(row, 7, CellType.STRING, houses.get(i).getMonthlyRent().toString());
                setCell(row, 8, CellType.STRING, houses.get(i).getArea().toString());
                setCell(row, 9, CellType.STRING, houses.get(i).getUnitType());
                setCell(row, 10, CellType.STRING, houses.get(i).getFlag());
                setCell(row, 11, CellType.STRING, houses.get(i).getLastEditTime().format(df));
                //setCell(wb, row, 2, CellType.NUMERIC, houseForm.getFracStartDate());
            }

            wb.write(response.getOutputStream());
        }{
            throw new RuntimeException("Cannot export XLSX file: ");
        }
    }

    private void setCell(XSSFRow row, int i, CellType type, String value)throws HRSException{
        XSSFCell cell = row.createCell(i);
        cell.setCellType(type);
        cell.setCellValue(value);
    }


}