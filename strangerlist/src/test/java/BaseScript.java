import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class BaseScript {
    private WebDriver driver;


    @BeforeMethod
    public void before() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
        driver.get("http://immense-hollows-74271.herokuapp.com/");

    }

    @Test
    public void navigateToCorrectUrl() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isRedirectedToURL());
    }

    @Test
    public void sortingFlow(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.sortListTest());
    }

    @Test
    public void counterTest(){
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isCounterOK());
    }

    @Test //This test will fail because of Bug 1
    public void editFlow(){
        HomePage homePage = new HomePage(driver);
        //Getting the photo file path
        final String pathDir = new java.io.File("").getAbsolutePath();
        final String pathFile = pathDir + "/src/test/impFile/320x320.jpg";
        //Edit
        homePage.editItem("1","TestEdit",pathFile);
        //CheckValues
        Assert.assertTrue(driver.findElement(By.xpath("//li[1]/div[@class = 'media-left']")).getText().contains("TestEdit"));
        Assert.assertTrue(driver.findElement(By.xpath("//li[1]/div[@class = 'media-left']")).findElement(By.cssSelector(".img-rounded")).getAttribute("src").contains("320x320"));
    }

    @Test
    public void deleteFlow(){
        HomePage homePage = new HomePage(driver);
        String initialTotal = homePage.countItems();
        homePage.deleteItem("1");
        Assert.assertNotEquals(initialTotal,homePage.countItems());
    }

    @Test
    public void addFlow(){
        HomePage homePage = new HomePage(driver);
        //Getting the photo file path
        final String pathDir = new java.io.File("").getAbsolutePath();
        final String pathFile = pathDir + "/src/test/impFile/320x320.jpg";
        //Add new Item
        homePage.addItem("TestEdit",pathFile);
        //Get last row
        String row = homePage.countItems();
        //CheckValues
        Assert.assertTrue(driver.findElement(By.xpath("//li["+row+"]/div[@class = 'media-left']")).getText().contains("TestEdit"));
        Assert.assertTrue(driver.findElement(By.xpath("//li["+row+"]/div[@class = 'media-left']")).findElement(By.cssSelector(".img-rounded")).getAttribute("src").contains("320x320"));
    }

    @AfterMethod
    public void after() {
        driver.quit();
    }

}
