package rough;

import java.io.IOException;

import utilites.ExcelReader;

public class CheckExcel {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ExcelReader er = new ExcelReader();
		er.setup(System.getProperty("user.dir") + "\\src\\test\\resources\\excelsheets\\testdata.xlsx");
		er.setSheetName("trial");
	   int rows = er.getRows();
	   int cols = er.getCols();
	  
	  for(int row = 0; row <=rows; row++) {
		  for(int col = 0; col < cols; col++) {
			  System.out.println(er.readData(col, row));
		  }
	  }
	   
	  
	}

}
