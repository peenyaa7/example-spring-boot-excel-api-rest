package es.peenyaa7.examplespringbootmicrosoftexcel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.peenyaa7.examplespringbootmicrosoftexcel.services.ExcelService;

@RestController
@RequestMapping("/excel")
public class ExcelRestController {
    
    @Autowired
    private ExcelService excelService;

    @GetMapping("/download")
    public ResponseEntity<?> download() {

        Resource resource = excelService.exportOwnersToExcel();

        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=owners.xlsx")
            .body(resource);
    }

}
