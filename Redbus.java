package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Redbus 
{
   public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();
	    ChromeDriver driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
// 1.Launch the url https://www.redbus.in/
	    driver.get("https://www.redbus.in/"); 
// 2.Enter From -Madiwala Bangalore
	    driver.findElement(By.id("src")).sendKeys("Madiwala Bangalore",Keys.ENTER); 
// 3.Enter To Koyambedu Chennai
	    driver.findElement(By.id("dest")).sendKeys("Koyambedu Chennai");
// 4.Select the Date 
	    driver.findElement(By.xpath("//ul[@class='autoFill homeSearch']/li[1]")).click(); // Finding the first destination and selecting
	    driver.findElement(By.id("onward_cal")).click(); //Finding the date field
	    List<WebElement> calendarRows = driver.findElements(By.xpath("//table//tr"));  //finding number of rows in date table
	    System.out.println("Number of rows in calendar:" + calendarRows.size()); //number of rows in date table
	    List<WebElement> calendarColumn = driver.findElements(By.xpath("//table//tr/td")); //finding number of columns in date table
	    System.out.println("Number of columns in calendar:" + calendarColumn.size()); //number of columns in date table
	    
	    for (int i = 0; i < calendarColumn.size(); i++) 
	    {
	         //check date
	         String date = calendarColumn.get(i).getText();
	        // System.out.println(date);
	         if (date.equals("18")) 
	         {
	        	 calendarColumn.get(i).click(); //clicking july 18
	             
	         }
	    }
	    
	    Thread.sleep(5000);
// 5. Click Search buses
        WebElement searchBuses = driver.findElement(By.xpath("//button[@id='search_btn']")); //Finding search buses
	    driver.executeScript("arguments[0].click();",searchBuses);
	    driver.get("https://www.redbus.in/search?fromCityName=Madiwala%2C%20Bangalore&fromCityId=66008&toCityName=Koyambedu%2C%20Chennai&toCityId=66065&onward=18-Jul-2022&srcCountry=IND&destCountry=IND&opId=0&busType=Any");
	     //search buses button has application issue so used the next page URL
// 6. Click After 6pm under Departure time	    
	    driver.findElement(By.xpath("//ul[@class='dept-time dt-time-filter']/li[4]")).click(); //Selecting departure time
	    Thread.sleep(5000);
// 7. Click Sleeper under Bus types
	    WebElement busTypes = driver.findElement(By.id("bt_SEATER")); //Selecting seater type
	    driver.executeScript("arguments[0].click();",busTypes);
	    Thread.sleep(5000);
// 8. Select the Primo
	    List<WebElement> primobanners = driver.findElements(By.xpath("//ul[@class='tileContainer clearfix']/li")); //Selecting primo type
	    primobanners.get(0).click();
// 9. Get the number of buses found        
	    List<WebElement> numberOfBuses = driver.findElements(By.xpath("//ul[@class='bus-items']/div")); //finding the number of primo buses
	    Object[] eachBuses = numberOfBuses.toArray(); //converting to array as count is integer
	    int count = eachBuses.length; //getting the count of buses
	    System.out.println("Total number of buses:" + count);
// 10. Get the Bus fare and sort them in ascending order	    
	    List<WebElement> busFare = driver.findElements(By.xpath("//div[@class='seat-fare ']//div[@class='fare d-block']//span")); //getting all the primo buses fare
	    List<String> list = new ArrayList<>(); //storing it in a list
	    for (int i = 0; i < busFare.size(); i++) //looping to get the values
	    {
	    	String eachFare = busFare.get(i).getText(); //storing it in a string
	    	System.out.println(eachFare);
	    	list.add(eachFare); //storing in a list
	    	
		}
	    System.out.println("------------------After Sorting---------------");
	    Collections.sort(list); //sorting the fare
	    System.out.println("Sorted list:" + list);
// 11. Close the application	    
	    driver.close(); 
	}
   

}
