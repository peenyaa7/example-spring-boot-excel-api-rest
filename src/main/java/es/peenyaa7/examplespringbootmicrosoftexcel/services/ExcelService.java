package es.peenyaa7.examplespringbootmicrosoftexcel.services;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import es.peenyaa7.examplespringbootmicrosoftexcel.classes.Database;
import es.peenyaa7.examplespringbootmicrosoftexcel.models.Lovebird;
import es.peenyaa7.examplespringbootmicrosoftexcel.models.Owner;

@Service
public class ExcelService {

    @Autowired
    private Database database;
    
    /**
     * Crea el recurso de Excel con los datos de los propietarios y sus agapornis.
     * @return Recurso de Excel
     */
    public Resource exportOwnersToExcel() {
        
        List<Owner> owners = database.getOwners();

        // Creo el archivo de Excel (XLSX)
        Workbook workbook = new XSSFWorkbook();

        // Creamos la hoja "OWNERS" que contendrá los datos de los propietarios
        Sheet ownersSheet = workbook.createSheet("OWNERS");
        buildHeader(workbook, ownersSheet, "ID", "NAME", "SURNAME", "DNI", "LOVEBIRDS");

        // Creamos la hoja "LOVEBIRDS" que contendrá los datos de los agapornis
        Sheet lovebirdsSheet = workbook.createSheet("LOVEBIRDS");
        buildHeader(workbook, lovebirdsSheet, "ID", "NAME", "OWNER");

        // Rellenamos la hoja "OWNERS"
        fillOwnersSheet(workbook, ownersSheet, owners);

        // Rellenamos la hoja "LOVEBIRDS"
        fillLovebirdsSheet(workbook, lovebirdsSheet, owners);

        // Retornamos el recurso
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayResource(outputStream.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al generar el archivo de Excel");
        }

    }

    /**
     * Construye la cabecera de la hoja de cálculo con los nombres de las columnas
     * @param book Archivo de Excel al que pertenece la hoja
     * @param sheet Hoja de cálculo a la que se le construirá la cabecera
     * @param columnNames Nombres de las columnas
     */
    private void buildHeader(Workbook book, Sheet sheet, String... columnNames) {
        Row header = sheet.createRow(0);
        Font headerFont = book.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);

        CellStyle headerCellStyle = book.createCellStyle();
        headerCellStyle.setFont(headerFont);

        for (int i = 0; i < columnNames.length; i++) {
            header.createCell(i).setCellValue(columnNames[i]);
            header.getCell(i).setCellStyle(headerCellStyle);
        }
    }

    /**
     * Rellena la hoja de cálculo "OWNERS" con los datos de los propietarios
     * @param book Archivo de Excel al que pertenece la hoja
     * @param sheet Hoja de cálculo a la que se le rellenarán los datos
     * @param owners Lista de propietarios
     */
    private void fillOwnersSheet(Workbook book, Sheet sheet, List<Owner> owners) {
        int rowNum = 1;
        for (Owner owner : owners) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(owner.getId());
            row.createCell(1).setCellValue(owner.getName());
            row.createCell(2).setCellValue(owner.getSurname());
            row.createCell(3).setCellValue(owner.getDni());
            row.createCell(4).setCellValue(owner.getLovebirds().toString());
        }
    }

    /**
     * Rellena la hoja de cálculo "LOVEBIRDS" con los datos de los agapornis
     * @param book Archivo de Excel al que pertenece la hoja
     * @param sheet Hoja de cálculo a la que se le rellenarán los datos
     * @param owners Lista de propietarios
     */
    private void fillLovebirdsSheet(Workbook book, Sheet sheet, List<Owner> owners) {
        int rowNum = 1;
        for (Owner owner : owners) {
            for (Lovebird lovebird : owner.getLovebirds()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(lovebird.getId());
                row.createCell(1).setCellValue(lovebird.getAlias());
                row.createCell(2).setCellValue(owner.getName() + " " + owner.getSurname());
            }
        }
    }

}
