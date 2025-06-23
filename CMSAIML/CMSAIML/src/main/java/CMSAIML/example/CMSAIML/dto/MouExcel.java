package CMSAIML.example.CMSAIML.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MouExcel {
    private String agencyName;
    private String date;
    private String duration;
    private String description;
    private String funding;
    private String mouPdf;
}
