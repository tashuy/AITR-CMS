package CMSAIML.example.CMSAIML.Controller;

import CMSAIML.example.CMSAIML.Entity.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/data")
public class ExcelController {

    // Util to read cell value
    private String getCellValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf((long) cell.getNumericCellValue());
            default -> "";
        };
    }

    // Load Excel file from resources
    private Workbook getWorkbook() throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream("data.xlsx");
        return new XSSFWorkbook(is);
    }

    // 👉 1. MOU endpoint
    @GetMapping("/mou")
    public List<MouExcel> getMouData() throws Exception {
        List<MouExcel> list = new ArrayList<>();
        Sheet sheet = getWorkbook().getSheet("MOU");
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            list.add(new MouExcel(
                    getCellValue(row.getCell(0)), // AGENCY NAME
                    getCellValue(row.getCell(1)), // DATE
                    getCellValue(row.getCell(2)), // DURATION
                    getCellValue(row.getCell(3)), // DESCRIPTION
                    getCellValue(row.getCell(4)), // FUNDING
                    getCellValue(row.getCell(5))  // MOU PDF
            ));
        }
        return list;
    }

    // 👉 2. Consultancy endpoint
    @GetMapping("/consultancy")
    public List<ConsultancyExcel> getConsultancyData() throws Exception {
        List<ConsultancyExcel> list = new ArrayList<>();
        Sheet sheet = getWorkbook().getSheet("CONSULTANCY");
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            list.add(new ConsultancyExcel(
                    getCellValue(row.getCell(0)), // AGENCY NAME
                    getCellValue(row.getCell(1)), // DATE
                    getCellValue(row.getCell(2)), // DURATION
                    getCellValue(row.getCell(3)), // DESCRIPTION
                    getCellValue(row.getCell(4)), // FUNDING
                    getCellValue(row.getCell(5))  // PDF
            ));
        }
        return list;
    }

    // 👉 3. R&D endpoint
    @GetMapping("/rnd")
    public List<RdExcel> getRdData() throws Exception {
        List<RdExcel> list = new ArrayList<>();
        Sheet sheet = getWorkbook().getSheet("R&D");
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            list.add(new RdExcel(
                    getCellValue(row.getCell(0)), // AGENCY NAME
                    getCellValue(row.getCell(1)), // DATE
                    getCellValue(row.getCell(2)), // DURATION
                    getCellValue(row.getCell(3)), // DESCRIPTION
                    getCellValue(row.getCell(4)), // FUNDING
                    getCellValue(row.getCell(5))  // PDF
            ));
        }
        return list;
    }

    // 👉 4. Event Grant endpoint
    @GetMapping("/event-grant")
    public List<EventGrantExcel> getEventGrantData() throws Exception {
        List<EventGrantExcel> list = new ArrayList<>();
        Sheet sheet = getWorkbook().getSheet("EVENT GRANT");
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            list.add(new EventGrantExcel(
                    getCellValue(row.getCell(0)), // EVENT NAME
                    getCellValue(row.getCell(1)), // TYPE OF EVENT
                    getCellValue(row.getCell(2)), // AGENCY NAME
                    getCellValue(row.getCell(3)), // DATE
                    getCellValue(row.getCell(4)), // DURATION
                    getCellValue(row.getCell(5)), // DESCRIPTION
                    getCellValue(row.getCell(6)), // FUNDING
                    getCellValue(row.getCell(7))  // PDF
            ));
        }
        return list;
    }

    // 👉 5. Event Organized endpoint
    @GetMapping("/event-organized")
    public List<EventOrganizedExcel> getEventOrganizedData() throws Exception {
        List<EventOrganizedExcel> list = new ArrayList<>();
        Sheet sheet = getWorkbook().getSheet("EVENT ORGANIZED");
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            list.add(new EventOrganizedExcel(
                    getCellValue(row.getCell(0)), // EVENT NAME
                    getCellValue(row.getCell(1)), // TYPE OF EVENT
                    getCellValue(row.getCell(2)), // AGENCY NAME
                    getCellValue(row.getCell(3)), // CATEGORY
                    getCellValue(row.getCell(4)), // PARTICIPANTS
                    getCellValue(row.getCell(5)), // DATE
                    getCellValue(row.getCell(6)), // DURATION
                    getCellValue(row.getCell(7)), // DESCRIPTION
                    getCellValue(row.getCell(8)), // FUNDING
                    getCellValue(row.getCell(9))  // PDF
            ));
        }
        return list;
    }

    // 👉 6. Institute Documents endpoint
    @GetMapping("/institute-documents")
    public InstituteDocumentsExcel getInstituteDocuments() throws Exception {
        Sheet sheet = getWorkbook().getSheet("INSTITUTE DOCUMENTS");
        Row row = sheet.getRow(1);
        return new InstituteDocumentsExcel(
                getCellValue(row.getCell(0)), // AICTE
                getCellValue(row.getCell(1)), // RGPV
                getCellValue(row.getCell(2)), // SOCIETY
                getCellValue(row.getCell(3))  // BYLAWS
        );
    }
}
