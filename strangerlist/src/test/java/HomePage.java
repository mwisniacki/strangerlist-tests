import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.util.List;

public class HomePage {
    protected WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    //URL Test Method
    public boolean isRedirectedToURL () {
        return driver.getCurrentUrl().contains("immense-hollows-74271");

    }

    //Sort Test Method
    public boolean sortListTest () {
        /*
        WebDriverWait wait = new WebDriverWait(driver, 120);
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id("inputImage"))));*/
        try { Thread.sleep(1000); } catch (InterruptedException e) { }
        WebElement element = driver.findElement(By.xpath("//li[1]/div[@class = 'media-left']"));
        WebElement target = driver.findElement(By.xpath("//li[2]/div[@class = 'media-left']"));//*[@id="content"]/div[1]/div/ul/li[2]/div
        String elementTitle = element.getText();
        (new Actions(driver)).dragAndDrop(element, target).perform();
        try { Thread.sleep(1000); } catch (InterruptedException e) { }
        String secondRowTitle = driver.findElement(By.xpath("//li[2]/div[@class = 'media-left']")).getText();
        return secondRowTitle.contains(elementTitle);
    }

    //Counter Test Method
    public boolean isCounterOK() {
        try { Thread.sleep(1000); } catch (InterruptedException e) { }
        String counterNumber = driver.findElement(By.cssSelector("h1")).getText().substring(15,17);
        List<WebElement> rows = driver.findElements(By.xpath("//ul[@ui-sortable='strangerlist.sortableOptions']/li"));
        String count = String.valueOf(rows.size());
        return counterNumber.equals(count);
    }

    //Edit Method
    public void editItem (String row, String description, String image){
        try { Thread.sleep(1000); } catch (InterruptedException e) { }
        WebElement element = driver.findElement(By.xpath("//li["+row+"]/div[@class = 'media-left']"));
        WebElement editButton = element.findElement(By.xpath("//button[text()='Edit']"));
        editButton.click();
        driver.findElement(By.name("text")).sendKeys(description);
        driver.findElement(By.id("inputImage")).sendKeys(image);
        driver.findElement(By.xpath("//button[text()='Update Item']")).click();
        try { Thread.sleep(1000); } catch (InterruptedException e) { }

    }

    //AUX Counter items Method
    public String countItems() {
        try { Thread.sleep(1000); } catch (InterruptedException e) { }
        List<WebElement> rows = driver.findElements(By.xpath("//ul[@ui-sortable='strangerlist.sortableOptions']/li"));
        return String.valueOf(rows.size());
    }

    //Delete Method
    public void deleteItem(String row){
        try { Thread.sleep(1000); } catch (InterruptedException e) { }
        WebElement element = driver.findElement(By.xpath("//li["+row+"]/div[@class = 'media-left']"));
        element.findElement(By.xpath("//button[text()='Delete']")).click();
        driver.findElement(By.xpath("//button[@ng-click='submit()']")).click();
        try { Thread.sleep(1000); } catch (InterruptedException e) { }
    }

    //Add Method
    public void addItem (String description, String image){
        try { Thread.sleep(1000); } catch (InterruptedException e) { }
        driver.findElement(By.name("text")).sendKeys(description);
        driver.findElement(By.id("inputImage")).sendKeys(image);
        driver.findElement(By.xpath("//button[text()='Create Item']")).click();
        try { Thread.sleep(1000); } catch (InterruptedException e) { }

    }
}
