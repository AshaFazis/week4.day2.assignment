package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForce 
{

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();
	    ChromeDriver driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
// 1. Login to https://login.salesforce.com
	    driver.get("https://login.salesforce.com/"); //loading URL
	    driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com"); //Entering username input
	    driver.findElement(By.id("password")).sendKeys("Password@123"); //Entering password input
	    driver.findElement(By.id("Login")).click(); //clicking on login button
	    
	    ChromeOptions notifications = new ChromeOptions(); //Chromeoptions class used here to disable notifications
	    notifications.addArguments("--disable-notifications"); 
// 2. Click on the toggle menu button from the left corner	    
	    driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click(); //clicking toggle menu button from the left corner
        
// 3. Click View All and click Dashboards from App Launcher
	    driver.findElement(By.xpath("//button[text()='View All']")).click(); //Clicking View All
        Thread.sleep(2000);
        WebElement scroll = driver.findElement(By.xpath("//p[text()='Dashboards']")); //putting scroll and click Dashboards from App Launcher
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", scroll);
        scroll.click();

// 4. Click on the New Dashboard option 
        driver.findElement(By.xpath("//div[text()='New Dashboard']")).click(); //Click on the New Dashboard option 
        
// 5.Handle the frame
        driver.findElement(By.xpath("//iframe")); //finding frame webelement
        driver.switchTo().frame(0); //switching to frames
        Thread.sleep(2000);
        
// 6. Enter Name as 'Salesforce Automation by Your Name ' and Click on Create.
        driver.findElement(By.id("dashboardNameInput")).sendKeys("Salesforce Automation by Asha");//Enter Name as 'Salesforce Automation by Your Name 
        driver.findElement(By.id("submitBtn")).click(); //Click on Create.
        driver.switchTo().defaultContent(); //switching to default webpage
        Thread.sleep(2000);
        driver.findElement(By.xpath("//iframe")); //again switching to frame
        driver.switchTo().frame(0);
        
// 7.Click on Save and Verify Dashboard name.    
        driver.findElement(By.xpath("//div[@class='slds-button-group']/button")).click(); //click on save button
        driver.switchTo().defaultContent(); //switching to default webpage
        WebElement dashboard = driver.findElement(By.xpath("//span[text()='Dashboards']/ancestor::a"));//click on dashboard to verify dashboard name
        driver.executeScript("arguments[0].click();",dashboard);
        String dashboardName = driver.findElement(By.xpath("//table/tbody/tr//a[text()='Salesforce Automation by Asha']")).getText(); //finding all dashboard names and getting the text
        System.out.println("Dashboard name: " + dashboardName);
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//span[@class='uiImage'])[1]")).click(); //click on profile
        driver.findElement(By.linkText("Log Out")).click(); //click on logout button
       
        System.out.println("-------------Edit---------------");
// 1. Login to https://login.salesforce.com     
        driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com"); //Entering username input
	    driver.findElement(By.id("password")).sendKeys("Password@123"); //Entering password input
	    driver.findElement(By.id("Login")).click(); //clicking login button
	    Thread.sleep(2000);
	    
// 2. Click on the toggle menu button from the left corner
        driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click(); //clicking toggle menu button from the left corner
        
// 3. Click View All and click Dashboards from App Launcher        
        driver.findElement(By.xpath("//button[text()='View All']")).click(); //Clicking view all
        
// 4. Click on the Dashboards tab         
        WebElement scroll1 = driver.findElement(By.xpath("//p[text()='Dashboards']")); //putting scroll and click Dashboards from App Launcher
        js.executeScript("arguments[0].scrollIntoView();", scroll1); 
        scroll1.click();
// 5. Search the Dashboard 'Salesforce Automation by Your Name'
        driver.findElement(By.xpath("//input[contains(@class,'search-text-field slds-input')]")).sendKeys("Salesforce Automation by Asha",Keys.ENTER); //Search the Dashboard 'Salesforce Automation by Your Name'
        Thread.sleep(3000);
        List<WebElement> DashboardNames = driver.findElements(By.xpath("//table/tbody/tr/th")); //finding all similar names which has Salesforce Automation by
        System.out.println("All Dashboard names:" + DashboardNames.size());
        for (int i = 1; i <= DashboardNames.size(); i++) //using for loop to get all dashboardnames
        {
    	  String allDashboardNames = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/th")).getText(); 
    	  System.out.println(allDashboardNames);
    	  String myName = "Salesforce Automation by Asha";
    	  Thread.sleep(3000);
    	  if(allDashboardNames.contains(myName)) //using if condition to check for Salesforce Automation by Asha string
    	  {   
    		  
    		  WebElement dropDown = driver.findElement(By.xpath("//table//tbody/tr/td//button")); //if exists click on Dropdown icon
    		  dropDown.click();
    	  }
		
	  }
// 6. Click on the Dropdown icon and Select Edit
      Thread.sleep(3000);
      driver.findElement(By.xpath("//table//tbody/tr/td//button/following-sibling::div//slot//a//span[text()='Edit']")).click();//Select Edit
      Thread.sleep(3000);
// 7.Click on the Edit Dashboard Properties icon     
      driver.findElement(By.xpath("//iframe")); //switching to frame
      driver.switchTo().frame(0);
      WebElement propertyButton = driver.findElement(By.xpath("//span[text()='Edit Dashboard Properties']")); //Click on the Edit Dashboard Properties icon
      driver.executeScript("arguments[0].click();",propertyButton);
      driver.findElement(By.id("dashboardDescriptionInput")).sendKeys("SalesForce"); //Enter Description as 'SalesForce'
      WebElement saveButton = driver.findElement(By.id("submitBtn")); //click on save button
      driver.executeScript("arguments[0].click();",saveButton);
      WebElement doneButton = driver.findElement(By.xpath("//button[@class='slds-button doneEditing']")); // Click on Done
      driver.executeScript("arguments[0].click();",doneButton);
// 8. Enter Description as 'SalesForce' and click on save.
      Thread.sleep(3000);
      WebElement save = driver.findElement(By.id("modalBtn2")); //Click on save in the popup window displayed.
      driver.executeScript("arguments[0].click();",save);
       
      driver.switchTo().defaultContent(); //switching to webpage
      driver.navigate().refresh(); //refreshing the page
// 9. Click on Done and  Click on save in the popup window displayed.    
      WebElement dashBoard2 = driver.findElement(By.xpath("(//span[text()='Dashboards'])[1]"));//clicking on the dashboard again to verify description
      driver.executeScript("arguments[0].click();",dashBoard2);
// 10. Verify the Description.    
      String descriptionName = driver.findElement(By.xpath("//div[@class='slds-hyphenate']//lightning-base-formatted-text[text()='SalesForce']")).getText(); //verifying the description for my name
      System.out.println("Description:" + descriptionName);
      driver.close();
	}
}


