package com.amazon.qa.testcases;

import java.util.Date;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class login {
	@Test
	public void VerifyLoginWithVaildCredentials() throws InterruptedException {
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("http://tutorialsninja.com/demo");
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("rajeshwar@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Ram@1234");
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		//String AccountText=("//a[text()="Edit your account information"]");
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
		Thread.sleep(1000);
		driver.quit();
	}
	
	@Test
	public void verifyLoginWithInvalidCredentials() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("http://tutorialsninja.com/demo");
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("OMshiv"+generateDateAndTimeStamp()+"@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Ram@1234");
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		String WarnMsg= driver.findElement(By.xpath("//div[contains(@class,\"alert-dismissible\")]")).getText();
		Assert.assertEquals(WarnMsg, "Warning: No match for E-Mail Address and/or Password.");
		driver.quit();
		
		
	}
	
	public String generateDateAndTimeStamp() {
		
		 	Date date= new Date();
			return date.toString().replace(" ", "_").replace(":", "_");
	}

}
