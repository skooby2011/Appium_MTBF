package base;


import java.io.File;
import java.net.URL;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import capability.Capability;
import db.ResultDB;
import io.appium.java_client.android.AndroidDriver;
import runner.Runner;
import utility.ScreenshotCompare;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Phone{
	
	static AndroidDriver  driver;
	static Logger logger=Logger.getLogger(Phone.class);
	final String TESTCASENAME = "PhoneCall";
	final File IMAGE = new File("D:\\Appium\\expected\\"+TESTCASENAME+".jpg");
	
	public static int phonePassNum = 0 ; 
	public static int phonePailNum = 0 ;
	
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
		  	
		  	File actualImage = ScreenshotCompare.takeScreenShotat(driver,TESTCASENAME);
		  	logger.fatal(ScreenshotCompare.getResult(actualImage,IMAGE));
		  	
		  	if(driver.findElementByName("10086").isDisplayed()
		  			&driver.findElementById("com.android.dialer:id/call_type_icons").isDisplayed()){
		  		logger.fatal("Phone-dail:pass");	
		  		phonePassNum ++;
		  		ResultDB.updatePassDB(phonePassNum);
		  	}
		}catch(Exception e){
			 if(e.getClass().getName()=="org.openqa.selenium.NoSuchElementException"){
				 logger.fatal("Phone-dail:fail");
				 phonePailNum++;
				 ResultDB.updateFailDB(phonePailNum);
				 
			 }else{e.printStackTrace();
			}
		}
	}
}
