package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class TestDemo {
    WebDriver driver ;

    @BeforeMethod
    public void BeforeTest(){
        System.setProperty("webdriver.chrome.driver", "E:\\Driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    // Start Testing
    @Test
    public void TestDemo1() throws InterruptedException {

        driver.get("https://www.rentalhomes.com/property/individual/AB-29131127?utm_source");
        driver.findElement(By.xpath("//input[@placeholder='Check In']")).click();
        String mainWindow=driver.getWindowHandle();
        WebElement startMonth = driver.findElement(By.className("month1"));
        List <WebElement> dateLists = startMonth.findElements(By.tagName("td"));

        for(WebElement option : dateLists){
            if(option.getText().equals("25")){
                option.findElement(By.xpath("//div[contains(text(),'25')] ")).click();
                break;
            }
        }
        Thread.sleep(1000);

        for(WebElement option : dateLists){
            if(option.getText().equals("26")){
                option.findElement(By.xpath("//div[contains(text(),'26')] ")).click();
                Thread.sleep(1000);
                break;
            }
        }
        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[@class='search-button js_redirect_btn2']")).click();
        Thread.sleep(4000);

    // Visiting all window , each window visiting duration 8 seconds
        List<String> allWindows = new ArrayList<String>(driver.getWindowHandles()) ;

        int i = 1;
        System.out.println("-------- List of URL -------");
        for(String curWindow : allWindows){
            driver.switchTo().window(curWindow);
            System.out.println(i + ".  " + driver.getCurrentUrl());
    // if overlay come to window i close this
            if(!driver.findElements(By.xpath("//div[@class='IM_overlay_foreground IM_pane_single']")).isEmpty()){
                driver.findElement(By.xpath("//a[@class='IM_overlay_close_container IM_overlay_close_button']")).click();
            }
            Thread.sleep(5000);
            i++;
        }
        driver.switchTo().window(mainWindow);
    }

    @AfterMethod
    public void AfterTest(){
        System.out.println("Close All Window");
        driver.close();
    }
}
