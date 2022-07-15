package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa 
{

	public static void main(String[] args) throws InterruptedException 
	{

		WebDriverManager.chromedriver().setup();
	    ChromeDriver driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
// 1) Go to https://www.nykaa.com/	    
	    driver.get("https://www.nykaa.com/");

// 2) Click Brands and Search L'Oreal Paris	    
	    WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
	    Actions actions = new Actions(driver);
	    actions.moveToElement(brands).perform();
	    Thread.sleep(5000);
	    driver.findElement(By.xpath("//input[contains(@id,'brandSearchBox')]")).sendKeys("L'Oreal Paris");

// 3) Click L'Oreal Paris	
	    driver.findElement(By.linkText("L'Oreal Paris")).click();

// 4) Check the title contains L'Oreal Paris
	    String title = driver.getTitle();
	    System.out.println("Title:" + title);
	    if(title.contains("L'Oreal Paris"))
	    {
	    	System.out.println("Title contains L'Oreal Paris");
	    }
	    else
	    {
	    	System.out.println("Title doesn't contains L'Oreal Paris");
	    }
	    Thread.sleep(2000);
	    
// 5) Click sort By and select customer top rated
	    driver.findElement(By.xpath("//div[@class='css-0']/button")).click();
	    driver.findElement(By.xpath("//span[text()='customer top rated']")).click();

// 6) Click Category and click Hair->Click haircare->Shampoo
	    driver.findElement(By.id("first-filter")).click();
	    Thread.sleep(2000);

// 7) Click->Concern->Color Protections
	    driver.findElement(By.xpath("//span[text()='Hair']")).click();
	    driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
	    driver.findElement(By.xpath("//li[@class='css-1do4irw']//div[@class='control-value']/span[text()='Shampoo']")).click();
	    
// 9) Click on L'Oreal Paris Colour Protect Shampoo	    
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//span[text()='Concern']")).click();
	    driver.findElement(By.xpath("//div[@class='control-value']//span[contains(text(),'Color Protection')]")).click();
	   // WebElement filters = driver.findElement(By.xpath("//div[@id='filters-listing']//div[@class='css-rtde4j']/div"));
	   // System.out.println(filters.getSize());
	    Thread.sleep(2000);
	    
// 10) GO to the new window and select size as 175ml
	    driver.findElement(By.xpath("//div[@class='css-d5z3ro']//span[text()='₹189']")).click();
        Set<String> multipleWindows = driver.getWindowHandles();
        System.out.println(multipleWindows);
        List<String> list = new ArrayList<>(multipleWindows);
        driver.switchTo().window(list.get(1));
        Thread.sleep(2000);
        WebElement size = driver.findElement(By.xpath("//select"));
        size.click();
        Select driver2 = new Select(size);
		driver2.selectByValue("0");

// 11) Print the MRP of the product    	
		String text = driver.findElement(By.xpath("(//div[@class='css-1d0jf8e']/span[@class='css-1jczs19'])[1]")).getText();
		System.out.println("MRP:" + text);

// 12) Click on ADD to BAG		
		driver.findElement(By.xpath("//div[@class='css-hx8d3x']//button")).click();

// 13) Go to Shopping Bag		
		driver.findElement(By.xpath("//button[@class='css-g4vs13']")).click();
		//String grandTotal = driver.findElement(By.xpath("//div[@class='payment-tbl-data']//div[@class='value medium-strong']")).getText();

// 14) Print the Grand Total amount
		driver.findElement(By.xpath("(//iframe)[1]")); //handling frames to get grand total
	    driver.switchTo().frame(0);
	    Thread.sleep(3000);
		String grandTotal = driver.findElement(By.xpath("//div[@class='fixrow']//div[@class='value']")).getText();
		System.out.println("GrandTotal:" + grandTotal);

// 15) Click Proceed
	    driver.findElement(By.xpath("//span[text()='Proceed']")).click();
		driver.switchTo().defaultContent();

// 16) Click on Continue as Guest		
		driver.findElement(By.xpath("(//button)[3]")).click();
		String grandTotal2 = driver.findElement(By.xpath("(//div[@class='value'])[2]")).getText();
        System.out.println("GrandTotal2:" + grandTotal2);

 // 17) Check if this grand total is the same in step 14       
        if(grandTotal.equals(grandTotal2))
        {
        	System.out.println("Both the grand total amount is same");
        }
        else
        {
        	System.out.println("Both the grand total amount is different");
        }
// 18) Close all windows
        driver.quit();
	}
	

}
