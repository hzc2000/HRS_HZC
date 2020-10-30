//package org.hzc.view;
//
//import org.hzc.entity.User;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.springframework.web.servlet.view.document.AbstractXlsxView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//import java.util.Map;
//
//public class ExcelUsersSummaryView extends AbstractXlsxView {
//    @Override
//    protected void buildExcelDocument(
//            Map<String, Object> model,
//            Workbook workbook,
//            HttpServletRequest request,
//            HttpServletResponse response) throws Exception {
//        @SuppressWarnings({"unchecked"})
//        final List<User> users = (List<User>) model.get("users");
//        final Sheet sheet = workbook.createSheet();
//        addHeaderRow(sheet);
//
//        users.forEach(user -> createRow(sheet, user));
//    }
//
//
//    private void addHeaderRow(Sheet sheet) {
//        Row header = sheet.createRow(0);
//        header.createCell((short) 0).setCellValue("User Name");
//        header.createCell((short) 1).setCellValue("Phone");
//    }
//
//    private void createRow(Sheet sheet, User user) {
//        Row row = sheet.createRow(sheet.getLastRowNum() + 1);
//        row.createCell((short) 0).setCellValue(user.getFullName());
//        row.createCell((short) 1).setCellValue(user.getPhone());
//    }
//}
