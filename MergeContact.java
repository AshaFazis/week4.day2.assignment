package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact 
{
   public static void main(String[] args) throws InterruptedException 
	{
		
		WebDriverManager.chromedriver().setup();
	    ChromeDriver driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	   
// 1. Launch URL "http://leaftaps.com/opentaps/control/login"
	    driver.get("http://leaftaps.com/opentaps/control/login");
	    
// 2. Enter UserName and Password Using Id Locator
	    driver.findElement(By.id("username")).sendKeys("Demosalesmanager"); 
		driver.findElement(By.id("password")).sendKeys("crmsfa"); 
// 3. Click on Login Button using Class Locator
		driver.findElement(By.className("decorativeSubmit")).click();   
		
// 4. Click on CRM/SFA Link		
		driver.findElement(By.linkText("CRM/SFA")).click();; 

// 5. Click on contacts Button
		driver.findElement(By.linkText("Contacts")).click();  
	
// 6. Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("(//div[@class='frameSectionBody']//li)[4]/a")).click(); //Click on Merge Contacts using Xpath Locator
// 7. Click on Widget of From Contact
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[1]")).click(); //Click on Widget of From Contact
		String parentWindowHandle  = driver.getWindowHandle(); // Handling parent window
		System.out.println(parentWindowHandle);
		System.out.println(driver.getCurrentUrl());
		Set<String> multipleWindows = driver.getWindowHandles(); // Handling multiple window
		List<String> list = new ArrayList<>(multipleWindows);
		System.out.println(multipleWindows);
		driver.switchTo().window(list.get(1)); //switching to child window
		System.out.println("From contact field :" + driver.getCurrentUrl()); //Child URL
		
		List<WebElement> listOfContacts = driver.findElements(By.xpath("//table[@class='x-grid3-row-table']/tbody[1]/tr[1]/td[1]/div[1]/a"));
		//Getting all 10 names in the first page using table
		System.out.println(listOfContacts.size()); //10
		List<String> list1 = new ArrayList<>(); 
		for (WebElement webElement : listOfContacts) //using for loop to iterate all the 10 names
		{
			String text = webElement.getText(); //storing in a string
			list1.add(text); //adding all names in a list

		}
		System.out.println("List:" +list1);
// 8. Click on First Resulting Contact
		listOfContacts.get(0).click(); //clicking on first resulting contact
		driver.switchTo().window(parentWindowHandle); //switching to parent window
		
		System.out.println("--------------------------------");
// 9. Click on Widget of To Contact
		driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]")).click();
		String parentWindowHandle1  = driver.getWindowHandle(); // Handling parent window
		System.out.println(parentWindowHandle1);
		System.out.println(driver.getCurrentUrl());
		Set<String> multipleWindows1 = driver.getWindowHandles(); // Handling parent window
		List<String> list2 = new ArrayList<>(multipleWindows1);
		System.out.println(multipleWindows1);
		driver.switchTo().window(list2.get(1));//switching to child window
		System.out.println("To contact field :" + driver.getCurrentUrl()); //Child URL
        List<WebElement> listOfContacts1 = driver.findElements(By.xpath("//table[@class='x-grid3-row-table']/tbody[1]/tr[1]/td[1]/div[1]/a"));
		System.out.println(listOfContacts1.size());
        
		for (int i = 0; i < listOfContacts1.size(); i++) 
		{
			System.out.println(listOfContacts1.get(i).getText());
			
		}
// 10. Click on Second Resulting Contact
		listOfContacts1.get(0).click(); //clicking on first resulting contact
		driver.switchTo().window(parentWindowHandle1);  //switching to parent window
// 11. Click on Merge button using Xpath Locator
		driver.findElement(By.linkText("Merge")).click();
		Thread.sleep(2000);
// 12. Accept the Alert
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
// 13. Verify the title of the page
		System.out.println(driver.getTitle());
		driver.close();
	}
	
}
