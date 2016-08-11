package BaseCase;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

import CapabilityInfo.Capability;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Browser{
	
public static AndroidDriver driver; 
	
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
	 public void Browser01_ClearData(){
		 
		 driver.findElementByAccessibilityId("菜单").click();
		 
		 driver.findElementByName("设置").click();
		 
		 try{
	  		   Thread.sleep(3000);
	  	   }catch(Exception e){
	  		   System.out.print(e);
	  	   }	
		 
		 driver.swipe(600, 860, 600, 100, 3000);
		 
		 try{
	  		   Thread.sleep(1000);
	  	   }catch(Exception e){
	  		   System.out.print(e);
	  	   }		 
		 
		 driver.tap(1,300,760,500);
		 
		 try{
	  		   Thread.sleep(1000);
	  	   }catch(Exception e){
	  		   System.out.print(e);
	  	   }
			 
		 driver.tap(1,350,1120,500);
		 
		 try{Thread.sleep(1000);
	  	   }catch(Exception e){System.out.print(e);}
		 
		 driver.findElementByName("清除").click();		 		 		 
	 }
	 
	 @Test
	 public void Browser02_Browse(){
		 driver.findElementByAccessibilityId("菜单").click();		 
		 driver.findElementByAccessibilityId("书签/历史").click();
		 
		 try{Thread.sleep(1000);
	  	   }catch(Exception e){System.out.print(e);}
		 
		 driver.findElementByName("百度一下").click();
		 
		 try{Thread.sleep(3000);
	  	   }catch(Exception e){System.out.print(e);}		 
	 }
}
