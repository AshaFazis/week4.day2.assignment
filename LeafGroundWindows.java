package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LeafGroundWindows 
{

	public static void main(String[] args) throws InterruptedException 
	{

		WebDriverManager.chromedriver().setup();
	    ChromeDriver driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //putting implicit wait for the whole code
	    driver.get("http://www.leafground.com/pages/Window.html"); //launching URL
// 1. Open home page by clicking on button
	    String parentWindow = driver.getWindowHandle();  //Handling parent window
	    System.out.println(parentWindow);
	    System.out.println(driver.getCurrentUrl());
	    driver.findElement(By.id("home")).click();
	    Set<String> multipleWindows = driver.getWindowHandles();  //Handling multiple window
	    List<String> list = new ArrayList<>(multipleWindows);
	    System.out.println(multipleWindows);
	    driver.switchTo().window(list.get(1)); //switching to child window
	    System.out.println(driver.getCurrentUrl());
	    driver.close(); //closing child window
	    driver.switchTo().window(parentWindow);
// 2. Open Multiple Windows	    
	    //driver.get("http://www.leafground.com/pages/Window.html");
	    driver.findElement(By.xpath("//button[text()='Open Multiple Windows']")).click();
	    Set<String> multipleWindows1 = driver.getWindowHandles(); //Handling multiple window
	    List<String> list1 = new ArrayList<>(multipleWindows1); 
		System.out.println(multipleWindows1);
		System.out.println(driver.getCurrentUrl());
        driver.switchTo().window(list1.get(1));  //switching to child 1 window
        System.out.println(driver.getCurrentUrl());
        driver.switchTo().window(list1.get(2)); //switching to child 2 window
        System.out.println(driver.getCurrentUrl());
        System.out.println("Number of opened windows:"+ list1.size()); //finding number of opened windows
        driver.switchTo().window(parentWindow);
// 3. Closing all windows except parent        
        driver.findElement(By.xpath("//button[text()='Do not close me ']")).click();
        Set<String> multipleWindows2 = driver.getWindowHandles(); //Handling multiple window
	    List<String> list2 = new ArrayList<>(multipleWindows2);
	    System.out.println(multipleWindows2.size());
		System.out.println(driver.getCurrentUrl());
        driver.switchTo().window(list2.get(1)); //switching to child 1 window
        System.out.println(driver.getCurrentUrl());
        driver.switchTo().window(list2.get(2)); //switching to child 2 window
        System.out.println(driver.getCurrentUrl());
        for (int i = 1; i < list2.size(); i++)  //using for loop to close all except parent window
        {
			driver.switchTo().window(list2.get(i));
			driver.close();
        }
// 4. Wait for 5 seconds       
        driver.switchTo().window(parentWindow); //switching to parent window
		Thread.sleep(5000);//wait for 5 seconds
        driver.findElement(By.xpath("//button[text()='Wait for 5 seconds']")).click();
        driver.quit(); //closing all windows
	}

}
