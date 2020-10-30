package org.hzc.controller;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hzc.entity.Community;
import org.hzc.entity.House;
import org.hzc.entity.Role;
import org.hzc.entity.User;
import org.hzc.exception.HRSException;
import org.hzc.service.CommunityService;
import org.hzc.service.HouseService;
import org.hzc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private CommunityService communityService;
    @Autowired
    private HouseService houseService;
    //房东导入
    public static final String USER_NAME_HEADER = "姓名";
    public static final String USER_PHONE_HEADER = "电话";
    public static final String USER_PASSWORD_HEADER = "密码";
    //小区导入
    public static final String COMMUNITY_NAME_HEADER = "小区名";
    public static final String COMMUNITY_ADDRESS_HEADER = "小区地址";
    public static final String COMMUNITY_INTRODUCTION_HEADER = "小区简介";
    //房屋导入
    public static final String HOUSE_COMMUNITY_NAME_HEADER = "房屋所在小区名";
    public static final String HOUSE_TITLE_HEADER = "房屋标题";
    public static final String HOUSE_LANDLORD_PHONE_HEADER = "房东电话";
    public static final String HOUSE_ADDRESS_HEADER = "房屋地址";
    public static final String HOUSE_MONTHLYRENT_HEADER = "房屋月租金";
    public static final String HOUSE_AREA_HEADER = "房屋出租面积";
    public static final String HOUSE_UNITTYPE_HEADER = "房屋户型";
    public static final String HOUSE_INTRODUCTION_HEADER = "房屋简介";


    @PostMapping("/importHouseFromExcel")
    public String importHouseFromExcel(@RequestPart MultipartFile file) throws Exception {
        if (Objects.equals(file.getContentType(), "application/vnd.ms-excel") ||
                Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());

            Sheet sheet = workbook.getSheetAt(0);
            List<String> headers = new ArrayList<>();
            Row headerRow = sheet.getRow(0);
            for (int i = headerRow.getFirstCellNum(); i < headerRow.getLastCellNum(); i++) {
                Cell cell = headerRow.getCell(i);
                headers.add(cell.getStringCellValue());
            }

            // 获取row迭代器
            Iterator<Row> iterator = sheet.iterator();
            iterator.next(); // 跳过第一行，头部行
            while (iterator.hasNext()) {
                Row xlRow = iterator.next();

                // Skip empty rows
                if (checkRowEmpty(xlRow)) {
                    continue;
                }

                House house = new House();
                house.setFlag("未发布");
                house.setImage("/HRS/images/house1.png");
                for (int index = 0; index < headers.size(); index++) {
                    String header = headers.get(index);
                    String cellValue = getStringCellValue(xlRow, index);
                    switch (header) {
                        case HOUSE_ADDRESS_HEADER:
                            System.out.println("address->" + cellValue);
                            house.setHouseAddress(cellValue);
                            break;
                        case HOUSE_AREA_HEADER:
                            System.out.println("area->" + cellValue);
                            house.setArea(Double.parseDouble(cellValue));
                            break;
                        case HOUSE_COMMUNITY_NAME_HEADER:
                            System.out.println("community->" + cellValue);
                            Community community  = communityService.getByNameLikeIgnoreCase(cellValue);
                            house.setCommunity(community);
                            break;
                        case HOUSE_LANDLORD_PHONE_HEADER:
                            System.out.println("landlord->" + cellValue);
                            User landlord  = userService.getByPhone(cellValue);
                            house.setLandlord(landlord);
                            break;
                        case HOUSE_MONTHLYRENT_HEADER:
                            System.out.println("MonthlyRent->" + cellValue);
                            house.setMonthlyRent(BigDecimal.valueOf(Double.parseDouble(cellValue)));
                            break;
                        case HOUSE_TITLE_HEADER:
                            System.out.println("Title->" + cellValue);
                            house.setTitle(cellValue);
                            break;
                        case HOUSE_UNITTYPE_HEADER:
                            System.out.println("UnitType->" + cellValue);
                            house.setUnitType(cellValue);
                            break;
                        case HOUSE_INTRODUCTION_HEADER:
                            System.out.println("UnitType->" + cellValue);
                            house.setHouseIntroduction(cellValue);
                            break;
                    }
                }

                saveToDatabaseHouse(house);
            }
            return  "administrator_operation";
        } else {
            throw new HRSException("不是有效的excel文件 Not a valid excel file");
        }
    }

    @PostMapping("/importCommunityFromExcel")
    public String importCommunityFromExcel(@RequestPart MultipartFile file) throws Exception {
        if (Objects.equals(file.getContentType(), "application/vnd.ms-excel") ||
                Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());

            Sheet sheet = workbook.getSheetAt(0);
            List<String> headers = new ArrayList<>();
            Row headerRow = sheet.getRow(0);
            for (int i = headerRow.getFirstCellNum(); i < headerRow.getLastCellNum(); i++) {
                Cell cell = headerRow.getCell(i);
                headers.add(cell.getStringCellValue());
            }

            // 获取row迭代器
            Iterator<Row> iterator = sheet.iterator();
            iterator.next(); // 跳过第一行，头部行
            while (iterator.hasNext()) {
                Row xlRow = iterator.next();

                // Skip empty rows
                if (checkRowEmpty(xlRow)) {
                    continue;
                }

            Community community = new Community();

                for (int index = 0; index < headers.size(); index++) {
                    String header = headers.get(index);
                    String cellValue = getStringCellValue(xlRow, index);
                    switch (header) {
                        case COMMUNITY_NAME_HEADER:
                            System.out.println("name->" + cellValue);
                            community.setName(cellValue);
                            break;
                        case COMMUNITY_ADDRESS_HEADER:
                            System.out.println("Address->" + cellValue);
                            community.setAddress(cellValue);
                            break;
                        case COMMUNITY_INTRODUCTION_HEADER:
                            System.out.println("Introduction->" + cellValue);
                            community.setCommunityIntroduction(cellValue);
                            break;

                    }
                }

                saveToDatabaseCommunity(community);
            }
            return  "administrator_operation";
        } else {
            throw new HRSException("不是有效的excel文件 Not a valid excel file");
        }
    }



    @PostMapping("/importLandlordFromExcel")
    public String importLandlordFromExcel(@RequestPart MultipartFile file) throws Exception {
        if (Objects.equals(file.getContentType(), "application/vnd.ms-excel") ||
                Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
            Workbook workbook = WorkbookFactory.create(file.getInputStream());
            System.out.println("-1");
            Sheet sheet = workbook.getSheetAt(0);
            List<String> headers = new ArrayList<>();
            System.out.println("--------141");
            Row headerRow = sheet.getRow(0);
            for (int i = headerRow.getFirstCellNum(); i < headerRow.getLastCellNum(); i++) {
                Cell cell = headerRow.getCell(i);
                headers.add(cell.getStringCellValue());
            }
            System.out.println("0");
            // 获取row迭代器
            Iterator<Row> iterator = sheet.iterator();
            iterator.next(); // 跳过第一行，头部行
            System.out.println("1");
            while (iterator.hasNext()) {
                Row xlRow = iterator.next();

                // Skip empty rows
                if (checkRowEmpty(xlRow)) {
                    continue;
                }
                System.out.println("2");
                User user = new User();
                user.setRole(Role.LANDLORD);
                for (int index = 0; index < headers.size(); index++) {
                    String header = headers.get(index);
                    String cellValue = getStringCellValue(xlRow, index);
                    System.out.println("3");
                    switch (header) {
                        case USER_NAME_HEADER:
                            System.out.println("name->" + cellValue);
                            user.setName(cellValue);
                            break;
                        case USER_PHONE_HEADER:
                            System.out.println("phone->" + cellValue);
                            user.setPhone(cellValue);
                            break;
                        case USER_PASSWORD_HEADER:
                            System.out.println("password->" + cellValue);
                            user.setPassword(cellValue);
                            break;

                    }
                }
                System.out.println("4");
                saveToDatabaseUser(user);
                System.out.println("5");
            }
            return  "administrator_operation";
        } else {
            throw new HRSException("不是有效的excel文件 Not a valid excel file");
        }
    }

    private void saveToDatabaseUser(User user) throws Exception {
        User dbUser = userService.getByPhone(user.getPhone());
        if(Objects.nonNull(dbUser)) {
            userService.updateById(dbUser.getId(), user);
        } else {
            userService.create(user);
        }
    }
    private void saveToDatabaseCommunity(Community community) throws Exception {
        Community dbCommunity = communityService.getByNameLikeIgnoreCase(community.getName());
        if(Objects.nonNull(dbCommunity)) {
            communityService.updateById(dbCommunity.getId(), community);
        } else {
            communityService.create(community);
        }
    }
    private void saveToDatabaseHouse(House house) throws Exception {
        House dbHouse = houseService.getByTitleLikeIgnoreCase(house.getTitle());
        if(Objects.nonNull(dbHouse)) {
            houseService.updateById(dbHouse.getId(), house);
        } else {
            houseService.create(house);
        }
    }


    private boolean checkRowEmpty(Row row) {
        if (row == null) {
            return true;
        }
        if (row.getLastCellNum() <= 0) {
            return true;
        }
        for (int cellNum = row.getFirstCellNum(); cellNum < row.getLastCellNum(); cellNum++) {
            Cell cell = row.getCell(cellNum);
            if (cell != null && cell.getCellType() != CellType.BLANK
                    && !StringUtils.isEmpty(cell.toString().trim())) {
                return false;
            }
        }
        return true;
    }

    private String getStringCellValue(Row row, int columnIdx) throws HRSException {
        Cell cell = getCell(row, columnIdx);
        String val;
        DataFormatter df = new DataFormatter();
        switch (cell.getCellType()) {
            case FORMULA:
                val = cell.getStringCellValue();
                break;
            default:
                val = df.formatCellValue(cell);
        }

        if (StringUtils.isEmpty(val)) {
            throw new HRSException(String.format("第%d行%d列是空数据。",
                    (row.getRowNum() + 1), (columnIdx + 1), row.getSheet().getSheetName()));
        }
        return val.trim();
    }

    private Cell getCell(Row row, int columnIdx) throws HRSException {
        Cell cell = row.getCell(columnIdx);

        if (null == cell) {
            throw new HRSException("Missing data for column " + (columnIdx + 1));
        }

        return cell;
    }
}
