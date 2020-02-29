package utilites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private XSSFWorkbook workbook = null;
	private Sheet sheet = null;
	private Row row = null;
	private Cell cell = null;
	private File file = null;
	private FileInputStream fis = null;


	public void setup(String path) throws IOException {

		if(!path.isEmpty() && !path.isBlank())
			this.file = new File(path);

		fis = new FileInputStream(file);
		workbook = new XSSFWorkbook(fis);

	}

	public void setSheetName(String sheetName) {

		sheet = workbook.getSheet(sheetName);
	}

	public int getRows() {
		if(sheet == null)
			return -1;
		else
			return sheet.getLastRowNum();
	}

	public int getCols() {
		if(sheet == null)
			return 0;
		else
			return sheet.getRow(0).getPhysicalNumberOfCells();
	}
	
	public String readData(int row, int col) {
		
		this.row = sheet.getRow(row);
		
		this.cell = this.row.getCell(col);
		
		switch(this.cell.getCellType()) {
		
		case BLANK: return null;
		case STRING: return this.cell.getStringCellValue();
		case NUMERIC: return String.valueOf(this.cell.getNumericCellValue());
		default: return null;	
		
		
		}
		
	}

}
