package base;

import java.net.URL;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import capability.Capability;
import io.appium.java_client.android.AndroidDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Browser{
	
	static AndroidDriver driver; 
	static Logger logger=Logger.getLogger(Browser.class);
	
	@Before
	public void setUp() throws Exception {
	           
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability("newCommandTimeout",1800000);//30mins
	    capabilities.setCapability("platformName", Capability.platformName);
	    capabilities.setCapability("platformVersion",Capability.platformVersion );     
	    capabilities.setCapability("deviceName", Capability.deviceName);         
	//  capabilities.setCapability("app", app.getAbsolutePath());
	    capabilities.setCapability("appPackage", "com.tencent.mtt");  
	    capabilities.setCapability("appActivity", "com.tencent.mtt.MainActivity");   
	    capabilities.setCapability("unicodeKeyboard", "True");  
	    capabilities.setCapability("resetKeyboard", "True");
	    driver = new AndroidDriver(new URL(Capability.driverURL), capabilities); 
	    
	}
	
	 @After
	    public void tearDown() throws Exception {
	        driver.quit();
	    }
	 
	 @Test
	 public void Browser01_clearData(){
		 try{
			 driver.findElementByAccessibilityId("菜单").click();
			 driver.findElementByName("设置").click();
			 Thread.sleep(3000);
			 driver.swipe(600, 860, 600, 100, 3000);
			 Thread.sleep(1000);
			 driver.tap(1,300,760,500);
			 Thread.sleep(1000);
			 driver.tap(1,350,1120,500);
			 Thread.sleep(1000);
			 driver.findElementByName("清除").click();	
			 driver.sendKeyEvent(4);
			 driver.sendKeyEvent(4);
			 logger.fatal("Browser-clearData:pass");
		 }catch(Exception e){
			 if(e.getClass().getName()=="org.openqa.selenium.NoSuchElementException"){
				 logger.fatal("Browser-clearData:fail");
			 }else{
				 e.printStackTrace();
			}
		 }
	 }
	 
	 @Test
	 public void Browser02_browse(){
		 try{
			 driver.findElementByAccessibilityId("菜单").click();		 
			 driver.findElementByAccessibilityId("书签/历史").click();
			 
			 Thread.sleep(1000);
			 
			 driver.findElementByName("百度一下").click();
			 
			 Thread.sleep(10000);
			 
			 if(driver.findElementByAccessibilityId("Web View").isDisplayed()){
				 logger.fatal("Browser-browse:pass");
			 }
				 
	  	   }catch(Exception e){
	  		 if(e.getClass().getName()=="org.openqa.selenium.NoSuchElementException"){
				 logger.fatal("Browser-browse:fail");
			 }else{
				 e.printStackTrace();
			 	}	
	  	   }		 
	 }
}
