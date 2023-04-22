package tds.PhotoTDS;

import java.io.*;

//import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

import tds.PhotoTDS.interfaz.VentanaWarning;

public class GeneradorExcel {
	
	public GeneradorExcel() {
		
		@SuppressWarnings("resource")
		Workbook wb = new HSSFWorkbook();
		//HSSFSheet sheet = (HSSFSheet) wb.createSheet();
		wb.createSheet();
		FileOutputStream fileOut = null;
		
		try {
			fileOut = new FileOutputStream("src\\test\\java\\tds\\PhotoTDS\\prueba.xls");
			wb.write(fileOut);
			fileOut.close();
			VentanaWarning vw = new VentanaWarning("Excel creado con éxito");
			vw.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
			VentanaWarning vw = new VentanaWarning("Excel no creado, ruta no encontrada");
			vw.setVisible(true);
		}
		
	}
}
