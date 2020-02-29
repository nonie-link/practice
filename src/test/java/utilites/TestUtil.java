package utilites;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import base.TestBase;

public class TestUtil extends TestBase{
	
	@DataProvider(name = "dp")
	public Object[][] getData(Method m) throws IOException {
		
		String sheetName = m.getName();
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\excelsheets\\testdata.xlsx";
		
		ExcelReader er = new ExcelReader();
		er.setup(path);
		er.setSheetName(sheetName);
		
		int rows = er.getRows();
		int cols = er.getCols();
		
		Object [][] data = new Object[rows][1];
		
		for(int i = 1; i <=rows; i++) {
			Hashtable <String, String> ht = new Hashtable <String, String>();
			for(int j = 0; j < cols; j++) {
				ht.put(er.readData(0, j), er.readData(i, j));	
				data[i-1][0] = ht;
		}
		}
		
		return data;
		
	}

}
