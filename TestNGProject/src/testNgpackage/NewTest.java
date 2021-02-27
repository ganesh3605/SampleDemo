package testNgpackage;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class NewTest {
	WebDriver driver;
	ExtentReports reports;
	ExtentTest test;
	@Test(priority = 0)
	public void test() {
		SoftAssert sa=new SoftAssert();
		sa.assertEquals("Hello", "hello");
		sa.assertAll();
		System.out.println("In test method");
	}

	@Test(priority = 1, enabled = true)
	@Parameters({ "firstname", "lastname" })
	public void f(@Optional("Ganesh123") String n, String l) {
		try {
			System.out.println(n + " " + l);

			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'login')]")));
			// Thread.sleep(5000);
			driver.manage().window().maximize();
			Reporter.log("Window Maximized");
			test.log(LogStatus.INFO, "Window Maximized");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='sample-2']")));

			Actions ac = new Actions(driver);
			WebElement women = driver.findElement(By.xpath("//a[text()='Women']"));
			WebElement casual = driver.findElement(By.xpath("//a[text()='Casual Dresses']"));

			ac.moveToElement(women).moveToElement(casual).click().build().perform();

			// driver.findElement(By.xpath("//a[text()='Contact us']")).click();
			Thread.sleep(5000);
			// driver.navigate().back();
			// Thread.sleep(5000);
			// driver.navigate().forward();
			// Thread.sleep(5000);
			driver.navigate().refresh();
			File f1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(f1, new File(".//Snap.png"));
			
			test.log(LogStatus.INFO, test.addScreenCapture(".//Snap.png"));
			

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	@Test(dataProvider = "creds")
	public void test(String name,String sur) {
		
		System.out.println(name+" "+sur);
		
	}
	/*
	 * @BeforeMethod public void beforeMethod() {
	 * System.out.println("beforemethod Method"); }
	 * 
	 * @AfterMethod public void afterMethod() {
	 * System.out.println("afterMethod Method"); }
	 */

	/*
	 * @BeforeClass public void beforeClass() {
	 * 
	 * }
	 * 
	 * @AfterClass public void afterClass() {
	 * System.out.println("afterClass Method"); }
	 */

	@BeforeTest
	public void beforeTest() {
		try {
		reports = new ExtentReports("C:\\Users\\Ganesh\\TestingPractise\\TestNGProject\\extentreports.html");
		test = reports.startTest("ExtentDemo");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Ganesh\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
		/*ProfilesIni pf=new ProfilesIni();
		FirefoxProfile ffp=pf.getProfile("Selenium");
		ffp.setAcceptUntrustedCertificates(true);
		ffp.setAssumeUntrustedCertificateIssuer(false);*/
		
		//DesiredCapabilities capa=new DesiredCapabilities();
		//capa.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		
		
		ChromeOptions co=new ChromeOptions();
		co.setAcceptInsecureCerts(true);
		driver = new ChromeDriver(co);
		driver.get("http://automationpractice.com/index.php");
		test.log(LogStatus.INFO, "URL is open");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@AfterTest
	public void afterTest() {
		try {
			Thread.sleep(5000);
			
			driver.quit();
			test.log(LogStatus.INFO, "URL is close");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@DataProvider(name = "creds")
	public Object[][] getdata(){
		Object data[][]=new Object[2][2];
		data[0][0]="Ganesh1";
		data[0][1]="patil";
		data[1][0]="XYZ";
		data[1][1]="PQR";
		
		
		return data;
				
	}

	/*
	 * @BeforeSuite public void beforeSuite() {
	 * System.out.println("beforeSuite Method"); }
	 * 
	 * @AfterSuite public void afterSuite() {
	 * System.out.println("afterSuite Method"); }
	 */

}
