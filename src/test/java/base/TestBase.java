package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	protected WebDriver driver = null;
	protected Properties or = null;
	protected Properties config = null;
	protected FileInputStream fis = null;



	@BeforeSuite
	public void setup() throws IOException {

		setupProperties();

		String browser = getConfig("browser");

		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}

		driver.get(getConfig("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(getConfig("wait_time")), TimeUnit.SECONDS);



	}


	private void setupProperties() throws IOException {
		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
		or = new Properties();
		config = new Properties();

		// loading the config file

		config.load(fis);

		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\or.properties");

		or.load(fis);
	}

	public String getConfig(String property) {

		if(!property.isEmpty() && !property.isBlank())
			return config.getProperty(property);
		else
			return null;
	}

	public String getOR(String property) {

		if(!property.isEmpty() && !property.isBlank())
			return or.getProperty(property);
		else
			return null;
	}

	public boolean isElementPresent(String locator) {


		try {
			if(locator.endsWith("CSS"))
				driver.findElement(By.cssSelector(or.getProperty(locator)));
			else if (locator.endsWith("XPATH"))
				driver.findElement(By.xpath(or.getProperty(locator)));
			else if (locator.endsWith("LINK"))
				driver.findElement(By.linkText(or.getProperty(locator)));

			return true;
		}catch(NoSuchElementException exe) {
			return false;
		}

	}

	public void click(String locator) {
		if(locator.endsWith("CSS"))
			driver.findElement(By.cssSelector(or.getProperty(locator))).click();
		else if (locator.endsWith("XPATH"))
			driver.findElement(By.xpath(or.getProperty(locator))).click();
		else if (locator.endsWith("ID"))
			driver.findElement(By.id(or.getProperty(locator))).click();
		else if (locator.endsWith("NAME"))
			driver.findElement(By.name(or.getProperty(locator))).click();
	}

	public void sendInput(String locator, String data) {
		if(locator.endsWith("CSS"))
			driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(data);
		else if (locator.endsWith("XPATH"))
			driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(data);
		else if (locator.endsWith("ID"))
			driver.findElement(By.id(or.getProperty(locator))).sendKeys(data);
		else if (locator.endsWith("NAME"))
			driver.findElement(By.name(or.getProperty(locator))).sendKeys(data);
	}


	@AfterSuite
	public void tearDown() throws IOException {
		if(driver != null)
			driver.quit();
		fis.close();
	}

}
