package automation_analyst;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FitPeo_Assesment {

	public static void main(String[] args) throws InterruptedException {
	 
		//1. Navigate to the FitPeo Homepage
		WebDriver driver = new ChromeDriver();
        driver.get("https://www.fitpeo.com/home");
        driver.manage().window().maximize();
        
        //2.Navigate to the Revenue Calculator Page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement revenueCalculatorLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Revenue Calculator']")));
        revenueCalculatorLink.click();
        
        // 3.Scroll Down to the Slider Section
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement sliderSection = wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[contains(@class,'MuiBox-root')]/span/span)[3]")));
        Actions ac=new Actions(driver);
        ac.dragAndDropBy(sliderSection,94, 0).build().perform();
        
        //4.Adjust the Slider         
        WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement textField = wait2.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='number']")));
        textField.clear();
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].value='820'",sliderSection);
        
        System.out.println("Updated value is:"+sliderSection.getAttribute("value"));
      
        //5.Update the Text Field
        textField.clear();
        js.executeScript("arguments[0].value='560'",textField);
        System.out.println("Updated value is:"+textField.getAttribute("value"));
        
        //6.Validate Slider Value
        String sliderValue = textField.getAttribute("value");
        if (sliderValue.equals("560")) {
            System.out.println("Slider value updated to: " + sliderValue);
        } else {
            System.out.println("Slider value did not update correctly. Actual:" + sliderValue);
        }
        
        //7.Select CPT Codes
        driver.findElement(By.xpath("(//input[@type='checkbox'])[1]")).click();
        driver.findElement(By.xpath("(//input[@type='checkbox'])[2]")).click();
        driver.findElement(By.xpath("(//input[@type='checkbox'])[3]")).click();
        driver.findElement(By.xpath("(//input[@type='checkbox'])[8]")).click();
                
        //8.Validate Total Recurring Reimbursement
        WebElement totalReimbursementHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//p[contains(text(), 'Total Recurring Reimbursement')])[1]")));  
        String headerText = totalReimbursementHeader.getText();
        System.out.println("Header text:"+headerText);

        // Step 5: Validate if the header text matches the expected value
        if (headerText.contains("$111105")) {
            System.out.println("Validation Passed: The Total Recurring Reimbursement is $111105.");
            
            } else {
                System.out.println("Validation Failed: The Total Recurring Reimbursement is not $111105. Actual value: " + headerText);
            }
            
       

      
               
        Thread.sleep(5000);
        
        driver.close();

       }
}