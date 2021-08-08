package test;


import java.io.IOException;
import java.util.List;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import config.ReadProperties;

public class bigbasket {
	

	public static void main(String[] args) throws IOException {
		
		ReadProperties rp= new ReadProperties();		
		System.setProperty("webdriver.chrome.driver", rp.getprop("driverPath"));		
		
		WebDriver driver= new ChromeDriver();
		Actions actions = new Actions(driver);					
		
		driver.get(rp.getprop("url"));
		driver.manage().timeouts().pageLoadTimeout(50,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='input']")).sendKeys(rp.getprop("item"));		
		//Storing all the apple link and click with javascript
		List<WebElement> element1=driver.findElements(By.xpath("//ul[@class='search-item-suggesion']/li/div//a"));
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element1.get(4));
			
		//get element for qunatity and clear the box and put value as requirementt
		WebElement quntity=driver.findElement(By.xpath("//input[@name='qty']"));
		quntity.clear();
		quntity.sendKeys(rp.getprop("qunatity"));
		
		// click on add to basket button
		driver.findElement(By.xpath("//div[@data-qa='addToBasket']")).click();
		
		String itemtitle=driver.findElement(By.xpath("//div[@id='title']/h1")).getText();
		System.out.println("item itle is"+" " +itemtitle);
		
		//Hover on cart
		actions.moveToElement(driver.findElement(By.xpath("//div[@data-qa='myBasket']"))).build().perform();
		
		String actualtitle=driver.findElement(By.cssSelector("._3eLxX")).getText();
		String expectedquality= driver.findElement(By.cssSelector("._2Aw53")).getText();
		System.out.println(actualtitle);
			
	
		Assert.assertEquals(rp.getprop("qunatity"), expectedquality, "Quantity is not Same");
		System.out.println("Quantity is same");
		Assert.assertEquals(itemtitle, actualtitle, "item title  is not same");		
		System.out.println("item title is same");
		
		driver.close();
		

	}
	}



