//package org.hzc.view;
//
//import com.lowagie.text.BadElementException;
//import com.lowagie.text.Document;
//import com.lowagie.text.Table;
//import com.lowagie.text.pdf.PdfWriter;
//import org.hzc.entity.User;
//import org.springframework.web.servlet.view.document.AbstractPdfView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.util.List;
//import java.util.Map;
//
//public class OpenPdfUsersSummaryView extends AbstractPdfView {
//    @Override
//    protected void buildPdfDocument(Map<String, Object> model,
//                                    Document document,
//                                    PdfWriter writer,
//                                    HttpServletRequest request,
//                                    HttpServletResponse response) throws Exception {
//        List<User> users =
//                (List<User>) model.get("users");
//
//        Table table = new Table(2);
//
//        addTableHeader(table);
//        for (User user : users) {
//            addContent(table, user);
//        }
//        document.add(table);
//    }
//
//
//    private void addContent(Table table, User user) throws BadElementException {
//        table.addCell(user.getFullName());
//        table.addCell(user.getPhone());
//    }
//
//    private void addTableHeader(Table table) throws BadElementException {
//        table.addCell("Full Name");
//        table.addCell("Phone");
//    }
//}
