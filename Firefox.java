import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.grid.internal.TestSession;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import com.sun.jna.platform.win32.BaseTSD.ULONG_PTRByReference;

public class Firefox {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private static User[] users;
  @BeforeClass
  public static void getUsers(){
	  users=Excel.getUserURL();
  }
  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://psych.liebes.top";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  private String deleteEndSlash(String url){
	  if(url.length()>0&&url.charAt(url.length()-1)=='/'){
		  url=url.substring(0,url.length()-1);
	  }return url;
  }
  public void testEach(User user)throws Exception{
	  driver.get(baseUrl + "/st");
	  driver.findElement(By.id("username")).clear();
	  driver.findElement(By.id("username")).sendKeys(user.getUserName());
	  driver.findElement(By.id("password")).clear();
	  driver.findElement(By.id("password")).sendKeys(user.getPassword());
	  driver.findElement(By.id("submitButton")).click();
	  String urlOnWeb=driver.findElement(By.cssSelector("p.login-box-msg")).getText();
	  urlOnWeb=deleteEndSlash(urlOnWeb);
	  assertEquals(urlOnWeb.trim(), deleteEndSlash(user.getURL()).trim());
	  System.out.println(user.getUserName());
  }
  @Test
  public void testAll()throws Exception{
	  for(int i=0;i<users.length;i++)if(users[i].getUserName().trim().length()>0){
		  testEach(users[i]);
	  }
  }
  public void testLab2() throws Exception {
	  driver.get(baseUrl + "/st");
	  driver.findElement(By.id("username")).clear();
	  driver.findElement(By.id("username")).sendKeys("3015218088");
	  driver.findElement(By.id("password")).clear();
	  driver.findElement(By.id("password")).sendKeys("218088");
	  driver.findElement(By.id("submitButton")).click();
	  System.out.println(driver.findElement(By.cssSelector("p.login-box-msg")).getText());
	  
	  driver.get(baseUrl + "/st");
	  driver.findElement(By.id("username")).clear();
	  driver.findElement(By.id("username")).sendKeys("3015218050");
	  driver.findElement(By.id("password")).clear();
	  driver.findElement(By.id("password")).sendKeys("218050");
	  driver.findElement(By.id("submitButton")).click();
	  System.out.println(driver.findElement(By.cssSelector("p.login-box-msg")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
