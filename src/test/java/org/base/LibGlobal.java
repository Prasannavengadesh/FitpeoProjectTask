package org.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v119.input.model.DragData;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class LibGlobal {

	public static WebDriver d;
	public static Select s;

	public static void browserLaunch(String browser) {

		switch (browser) {
		case "Chrome":
			d = new ChromeDriver();
			break;
		case "Edge":
			d = new EdgeDriver();
			break;
		case "Firefox":
			d = new EdgeDriver();
			break;
		default:
			break;
		}
		d.manage().window().maximize();
	}
	
	public static void implWaits() {

		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	public static void urlLaunch(String url) {

		d.get(url);
	}
	
	
	public static void pressEnter() throws AWTException {

		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER); 
	}
	
	public static void passTextUsingJs(WebElement targetWebelement, String val) {

		JavascriptExecutor js = (JavascriptExecutor)d;
		js.executeScript("arguments[0].setAttribute('value','" + val +"' )", targetWebelement);
	}
	
	public static String getUrl() { 

		String url = d.getCurrentUrl();
		return url; 
	}
	
	public static void switchWin(int index) {

		Set<String> mul = d.getWindowHandles();
		List<String> l = new ArrayList<String>();
		l.addAll(mul);
		d.switchTo().window(l.get(index));
	}

	public static void sendText(WebElement element, String txt) {

		element.sendKeys(txt);
	}

	public static void clickButton(WebElement element) {

		element.click();
	}	
	
	public static void quitBrowser() {

		d.quit();
	}
	
	public static String readDataFromExcel(int rowNum, int cellNum) throws IOException {
		 
		File f = new File("C:\\Users\\VISHAL\\eclipse-workspace\\Fitpeo\\ExcelFiles\\Fitpeo.xlsx");

		FileInputStream fis = new FileInputStream(f);

		Workbook book = new XSSFWorkbook(fis); 

		Sheet s = book.getSheetAt(0);

		Row r = s.getRow(rowNum);

		Cell c = r.getCell(cellNum);

		int cellType = c.getCellType();

		String value;
		
		if (cellType == 1) {

			value = c.getStringCellValue();
		} 
		else if (DateUtil.isCellDateFormatted(c)) {

			Date date = c.getDateCellValue();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			value = sdf.format(date);			
		} 
		else {

			double num = c.getNumericCellValue(); // 456765.0
			long l = (long) num;
			value = String.valueOf(l);
		}
		
		return value;  
	}

}
