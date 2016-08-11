package BaseCase;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Phone{
	
	public static AndroidDriver driver;
	
	@Before
	public void setUp() throws Exception {
	           
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability("newCommandTimeout",1800000);//30mins
	    capabilities.setCapability("platformName", "Android");
//	     capabilities.setCapability("deviceName", "SORG7PEAIF8DOZRO");
	//  capabilities.setCapability("app", app.getAbsolutePath());
	    capabilities.setCapability("deviceName", "EIJZIVGASO9SWCIZ");       
	    capabilities.setCapability("platformVersion", "5.1");       
	    capabilities.setCapability("appPackage", "com.android.dialer");  
	    capabilities.setCapability("appActivity", ".DialtactsActivity");   
	    capabilities.setCapability("unicodeKeyboard", "True");  
	    capabilities.setCapability("resetKeyboard", "True");
	    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); 
	    
	}
	 @After
	    public void tearDown() throws Exception {
	        driver.quit();
	    }

	@Test
	public void Phone01_Dail(){
		driver.findElementById("com.android.dialer:id/floating_action_button").click();
		
		 try{
	  		   Thread.sleep(1000);
	  	   }catch(Exception e){
	  		   System.out.print(e);
	  	   }	
		
		WebElement dail =  driver.findElementById("com.android.dialer:id/digits");
		dail.click();
		dail.sendKeys("10086");
		
		 try{
	  		   Thread.sleep(1000);
	  	   }catch(Exception e){
	  		   System.out.print(e);
	  	   }	
		
		driver.findElementById("com.android.dialer:id/dialpad_floating_action_button").click();
		
		 try{
	  		   Thread.sleep(10000);
	  	   }catch(Exception e){
	  		   System.out.print(e);
	  	   }
	}
}
