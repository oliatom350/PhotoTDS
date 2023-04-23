package tds.PhotoTDS;

import java.io.*;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import tds.PhotoTDS.interfaz.VentanaWarning;

public class GeneradorExcel {
	
	@SuppressWarnings("resource")
	public GeneradorExcel(Usuario user) {
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		wb.setSheetName(0, "Seguidores");
		
		String[] headers = new String[]{
                "Nombre",
                "Email",
                "Presentación"
        };
		
		CellStyle headerStyle = wb.createCellStyle();
		org.apache.poi.ss.usermodel.Font font = wb.createFont();
        font.setBold(true);
        headerStyle.setFont(font);
        
        HSSFRow headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; ++i) {
            String header = headers[i];
            HSSFCell cell = headerRow.createCell(i);
            cell.setCellStyle(headerStyle);
            cell.setCellValue(header);
        }
        
        ArrayList<Integer> seguidores = user.getUsuariosSeguidores();
        for (int i = 0; i < seguidores.size(); ++i) {
            HSSFRow dataRow = sheet.createRow(i + 1);

            Usuario u;
			try {
				u = PhotoTDS.getUnicaInstancia().getUsuario(seguidores.get(i));
				dataRow.createCell(0).setCellValue(u.getNombreCompleto());
	            dataRow.createCell(1).setCellValue(u.getEmail());
	            dataRow.createCell(2).setCellValue(u.getPresentacion());
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        
        FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream("src\\test\\java\\tds\\PhotoTDS\\prueba.xls");
			wb.write(fileOut);
			VentanaWarning vw = new VentanaWarning("Excel creado con éxito");
			vw.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
			VentanaWarning vw = new VentanaWarning("Excel no creado, ruta no encontrada");
			vw.setVisible(true);
		}
		if (fileOut != null) {
			try {
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
