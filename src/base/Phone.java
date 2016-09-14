package base;


import java.io.File;
import java.io.FileNotFoundException;  
import java.io.FileReader;  
import java.io.FileWriter;  
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import capability.Capability;
import io.appium.java_client.android.AndroidDriver;
import utility.ScreenshotCompare;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Phone{
	
	static AndroidDriver  driver;
	static Logger logger=Logger.getLogger(Phone.class);
	final String TESTCASENAME = "PhoneCall";
	final File IMAGE = new File("D:\\Appium\\expected\\"+TESTCASENAME+".jpg");
	
	@Before
	public void setUp() throws Exception {
	           
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability("newCommandTimeout",1800000);//30mins
	    capabilities.setCapability("platformName", Capability.platformName);
	    capabilities.setCapability("platformVersion", Capability.platformVersion);       
	    capabilities.setCapability("deviceName", Capability.deviceName);       
	//  capabilities.setCapability("app", app.getAbsolutePath());
	    capabilities.setCapability("appPackage", "com.android.dialer");  
	    capabilities.setCapability("appActivity", ".DialtactsActivity");   
	    capabilities.setCapability("unicodeKeyboard", "True");  
	    capabilities.setCapability("resetKeyboard", "True");
	    driver = new AndroidDriver(new URL(Capability.driverURL), capabilities); 
	    
	}
	 @After
	    public void tearDown() throws Exception {
	        driver.quit();
	    }

	@Test
	public void Phone01_dail(){
		try{
			driver.findElementById("com.android.dialer:id/floating_action_button").click();
		  	Thread.sleep(1000);
			
			WebElement dail =  driver.findElementById("com.android.dialer:id/digits");
			dail.click();
			dail.sendKeys("10086");
						
		    Thread.sleep(1000);
		    
			driver.findElementById("com.android.dialer:id/dialpad_floating_action_button").click();
			
		  	Thread.sleep(10000);
  	
		  	driver.tap(1,360,1050,1000);
		  		  	
		  	Thread.sleep(3000);
		  	
		  	 Date now = new Date(); 
			   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");//可以方便地修改日期格式
			   String time = dateFormat.format(now).toString();
			   logger.fatal(time);
		  	
		  	File actualImage = ScreenshotCompare.takeScreenShotat(driver,TESTCASENAME);
		  	logger.fatal(ScreenshotCompare.getResult(actualImage,IMAGE));
		  	
		  	if(driver.findElementByName("10086").isDisplayed()
		  			&driver.findElementById("com.android.dialer:id/call_type_icons").isDisplayed()){
		  		logger.fatal("Phone-dail:pass");		
		  	}
		}catch(Exception e){
			 if(e.getClass().getName()=="org.openqa.selenium.NoSuchElementException"){
				 logger.fatal("Phone-dail:fail");
			 }else{e.printStackTrace();
			}
		}
	}
}
