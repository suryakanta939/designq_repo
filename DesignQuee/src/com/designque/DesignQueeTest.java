package com.designque;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class DesignQueeTest {
	WebDriver driver;
	String baseUrl="http://test2-denz.herokuapp.com/web/designer/tshirt#";
	String xpathCopy1="//div[div[a[text()='";
	String xpathCopy2="']]]//button";
	String title="The night everything fell apart-{name}-Crazy dude..";
	String slugUrl="Crazy-Biker-{name}";
	String desc="They Don't Just Look Crazy!-{name} -They Are Actually Crazzy.";
    String Tags="Biker, T-Shirt,{name},Motocycle, Tee, Crazy Bikers, Biker Gift, Motorcycle Present.";
    String BulletPoint2="Great for a Birthday Gift or makes a great Fathers Day present.22";
    String BulletPoint3="Great for a Birthday Gift or makes a great Fathers Day present.233";
    String BulletPoint4="Great for a Birthday Gift or makes a great Fathers Day present.344";
	String colorxpath1="//div[@id='colorschemebox1']/button[";
	String colorxpath2="]";
	boolean flag=true;
	
	
  @Test
  public void f() throws InterruptedException, IOException
  {
	  driver=new FirefoxDriver();
	  driver.get(baseUrl);
	  driver.manage().window().maximize();
	  //login to the site
	  driver.findElement(By.id("loginform-username")).sendKeys("admin");
	  driver.findElement(By.id("loginform-password")).sendKeys("reset123");
	  driver.findElement(By.xpath("//button[text()='Login']")).click();
	  driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	  //click on design
	  driver.findElement(By.xpath("//a[text()='Designs']")).click();
	  //get all the ids
	  List<WebElement> ids=driver.findElements(By.xpath("html/body/div[1]/div/div/div/div[2]//a"));
	  for(int i=0;i<ids.size();i++){
		  String idValue=ids.get(i).getText();
		  System.out.println("The availale ids are :"+idValue);
	  }
	  
	  //let select the id by the user
//	  for(int i=0;i<ids.size();i++){
	  System.out.println("select ur id");
	  Scanner scn=new Scanner(System.in);
	  int myId=scn.nextInt();
	  
	 // scroll to the desired value
	  WebElement element=driver.findElement(By.xpath(xpathCopy1+myId+xpathCopy2));
	  System.out.println("is the element present "+element.isDisplayed());
	//wait and click
//	  waitAndClick(element);
/*put it in try catch and try to scroll to particular part*/	
	  while(flag=true){
try{
	element.click();
	break;
}
catch(Throwable t){
	
	//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
	 
	    ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)", ""); //y value '400' can be altered
	    Thread.sleep(3000);
	
}
	 
	  }//while loop completes here	 
	/*Fill the form for product designer */
	 
	 driver.findElement(By.id("copy-name")).sendKeys(title);
	 
	 driver.findElement(By.id("copy-copied_url")).sendKeys(slugUrl);
	 
	 //get all the products & select the products
	 
	 Select sel=new Select(driver.findElement(By.name("cat[1][name]")));
	 List<WebElement> products=sel.getOptions();
	 for(int i=1;i<products.size();i++){
		 System.out.println("All the available products are :"+products.get(i).getText());
	 }
	 
	 //select the first one
	 sel.selectByValue("1");
	 Thread.sleep(1000);
	 
	 //select the style
	 Select sel1=new Select(driver.findElement(By.name("product[1][name]")));
	 sel1.selectByVisibleText("Hanes Tagless Tee");
	 
	 //select the color
	 WebElement img=driver.findElement(By.xpath("//div[label[text()='Colors:']]//img"));
	 WebDriverWait wait=new WebDriverWait(driver, 10);
	 wait.until(ExpectedConditions.visibilityOf(img));
	
  //  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",img);
	 
	 JavascriptExecutor jse = (JavascriptExecutor)driver;
	 jse.executeScript("window.scrollBy(0,-250)", "");
	 
	 JavascriptExecutor executor = (JavascriptExecutor)driver;
	 executor.executeScript("arguments[0].click()", img);
	 
	 List<WebElement> allcolor=driver.findElements(By.xpath("//div[@id='colorschemebox1']/button"));
	 
	   System.out.println("the total color present is "+allcolor.size());
	   
	  System.out.println("choose the color no from 1 to 4");
	  
     Scanner color=new Scanner(System.in);
     String no=color.next();
     
     driver.findElement(By.xpath(colorxpath1+no+colorxpath2)).click();
     
     //enter the price you want to select.
     
     System.out.println("enter the price");
     Scanner price=new Scanner(System.in);
     String ammount=price.next();
      
     driver.findElement(By.xpath("//input[@name='product[1][price]']")).sendKeys(ammount);
     Thread.sleep(3000);
     //upload file from desktop
     driver.findElement(By.id("copy-file_text")).click();
     
     Runtime.getRuntime().exec("C:\\Users\\ad\\Desktop\\upload.exe");
     
     driver.findElement(By.xpath("//textarea[@id='copy-description']")).sendKeys(desc);
     
     driver.findElement(By.id("copy-tags")).sendKeys(Tags);
     
     driver.findElement(By.xpath("//textarea[contains(@id,'bullet_point_2')]")).sendKeys(BulletPoint2);
     
     driver.findElement(By.xpath("//textarea[contains(@id,'bullet_point_3')]")).sendKeys(BulletPoint3);
     
     driver.findElement(By.xpath("//textarea[contains(@id,'bullet_point_4')]")).sendKeys(BulletPoint4);
     
     
	  
  }
  // this function is to scroll to the desired element and perform the operation.
  
  private void scrollToElement(WebElement el) {
	    if (driver instanceof JavascriptExecutor) {
	        ((JavascriptExecutor) driver)
	            .executeScript("arguments[0].scrollIntoView(true);", el);
	    }
	}
  public void waitAndClick(WebElement el) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, 5, 200);
	        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
	        wait.until(ExpectedConditions.elementToBeClickable(el)).click();
	    }
	    catch (WebDriverException wde) {
	        scrollToElement(el);
	        el.click();
	    }
	}
}
