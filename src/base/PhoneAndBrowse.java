package base;

import java.net.URL;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import capability.Capability;
import io.appium.java_client.android.AndroidDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PhoneAndBrowse {
	static AndroidDriver driver;
	static Logger logger=Logger.getLogger(Phone.class);
	
	@Before
	public void setUp() throws Exception {
	           
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability("newCommandTimeout",1800000);//30mins
	    capabilities.setCapability("platformName", Capability.platformName);
	    capabilities.setCapability("platformVersion", Capability.platformVersion);       
	    capabilities.setCapability("deviceName", Capability.deviceName);       
	//  capabilities.setCapability("app", app.getAbsolutePath());
	    capabilities.setCapability("appPackage", "com.apusapps.launcher");  
	    capabilities.setCapability("appActivity", "com.apusapps.launcher.launcher.ApusLauncherActivity");   
	    capabilities.setCapability("unicodeKeyboard", "True");  
	    capabilities.setCapability("resetKeyboard", "True");
	    driver = new AndroidDriver(new URL(Capability.driverURL), capabilities); 
	    
	}
	 @After
	    public void tearDown() throws Exception {
	        driver.quit();
	    }
	 
	 @Test
	 public void PhoneAndBrowse01_bingfa(){
		 try{
			 driver.tap(1,80,440,10);  //点击屏幕上浏览器icon
			 Thread.sleep(3000);
			 driver.sendKeyEvent(3);  //Home键
			 Thread.sleep(1000);
			 driver.tap(1,105,1010,10);  //点击屏幕上电话icon
			 Thread.sleep(1000);
			 
			 //拨打电话
			 driver.findElementById("com.android.dialer:id/floating_action_button").click();
			 Thread.sleep(1000);
			 WebElement dail =  driver.findElementById("com.android.dialer:id/digits");
			 dail.click();
			 dail.sendKeys("10086");
			 Thread.sleep(1000);
			 driver.findElementById("com.android.dialer:id/dialpad_floating_action_button").click();
			 Thread.sleep(10000);
			 
			 //返货主页再打开浏览器
			 driver.sendKeyEvent(3);
			 Thread.sleep(1000);
			 driver.tap(1,80,440,10);  //点击屏幕上浏览器icon
			 Thread.sleep(1000);
			 driver.tap(1,650,100,10);  //刷新页面
			 Thread.sleep(3000);
			 
			 //下拉通知栏点击当前通话
			 driver.swipe(300, 10, 300, 880, 1000);
			 Thread.sleep(1000);
			 driver.tap(1,300,485,10);  //点击下拉菜单当前通话
			 Thread.sleep(3000);
			 driver.sendKeyEvent(6);  //挂断电话
			 Thread.sleep(3000);
			 
			 logger.fatal("PhoneAndBrowse01_bingfa:pass");
			 
		 }catch(Exception e){
			 if(e.getClass().getName()=="org.openqa.selenium.NoSuchElementException"){
				 logger.fatal("PhoneAndBrowse01_bingfa:fail");
			 }else{
				 e.printStackTrace();
			 	}
		 	}
	 }
}
