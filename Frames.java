package week4.day2.assignments;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Frames 
{

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();
	    ChromeDriver driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
// 1. Launching URL
	    driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
// 2. Handling the first frame	    
	    driver.findElement(By.id("frame1"));
	    driver.switchTo().frame(0); //switching to frame 1 from main web page
	    //driver.findElement(By.xpath("//b[text()='Topic :']/following-sibling::input")).sendKeys("Frames");
	    driver.switchTo().frame("frame3"); //switching to child frame
	    driver.findElement(By.xpath("//input[@type='checkbox']")).click(); //clicking checkbox
	    Thread.sleep(2000);
	    driver.switchTo().parentFrame(); //switching to parent frame
	    driver.findElement(By.xpath("//b[text()='Topic :']/following-sibling::input")).sendKeys("Frames"); //sending input
        driver.switchTo().defaultContent(); //switching to webpage
// 3. Handling the second frame	
        WebElement secondFrame = driver.findElement(By.xpath("(//iframe)[2]")); 
        driver.switchTo().frame(secondFrame);//switching to frame 1 from main web page
        WebElement animals = driver.findElement(By.id("animals")); //selecting from dropdown
        Select driver2 = new Select(animals);
        driver2.selectByIndex(2);
        driver.close();
	}

}
