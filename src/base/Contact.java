package base;

import static org.junit.Assert.*;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import capability.Capability;

import org.apache.log4j.*;

import io.appium.java_client.android.AndroidDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Contact{
	static AndroidDriver driver; 
	static Logger logger=Logger.getLogger(Contact.class);

	@Before
	public void setUp() throws Exception {           
	    DesiredCapabilities capabilities = new DesiredCapabilities();
	    capabilities.setCapability("newCommandTimeout",1800000);//30mins
	    capabilities.setCapability("platformName", Capability.platformName);
	    capabilities.setCapability("platformVersion", Capability.platformVersion); 
	    capabilities.setCapability("deviceName", Capability.deviceName);           
//		capabilities.setCapability("app", app.getAbsolutePath());
	    capabilities.setCapability("appPackage", "com.android.contacts");  
	    capabilities.setCapability("appActivity", ".activities.PeopleActivity");   
	    capabilities.setCapability("unicodeKeyboard", "True");  
	    capabilities.setCapability("resetKeyboard", "True");
	    driver = new AndroidDriver(new URL(Capability.driverURL), capabilities); 
	    
	}
	 @After
	 public void tearDown() throws Exception {
		 driver.quit();
		 }
	 
	 @Test
	 public void Contact01_clearAll(){		 
		 try{
			 if(driver.findElementByName("Abc")!=null){			 
				 driver.findElementByAccessibilityId("更多选项").click();
				 driver.findElementByName("删除联系人").click();
				 driver.findElementById("com.android.contacts:id/select_items").click();
				 Thread.sleep(1000);
				 driver.tap(1,380,210,500);  //全选按钮
				 Thread.sleep(1000);
				 driver.findElementByName("确定").click();				 
				 driver.findElementById("android:id/button1").click();				 
				 Thread.sleep(3000);
				 logger.fatal("Contact-clearAll:pass");
				 
			 }
		 }catch(Exception e){
			 if(e.getClass().getName()=="org.openqa.selenium.NoSuchElementException"){
				 logger.fatal("Contact-clearAll:pass (No contact)");
			 }else{
				 e.printStackTrace();
			 	}			 	 
			 }
	 }

	 @Test
	 public void Contact02_creat(){		
		 try{
			 driver.findElementById("com.android.contacts:id/floating_action_button").click();		 
			 WebElement name = driver.findElementByName("姓名");
			 name.click();
			 name.sendKeys("Abc");
			 
//			 driver.sendKeyEvent(66);   //模拟返回键
			 driver.swipe(690, 500, 690, 50, 3000);
			 
			 //输入电话号码
			 WebElement number = driver.findElementByClassName("android.widget.EditText");
			 assertEquals("电话", number.getText());
			 number.click();
			 number.sendKeys("10086");
			 
			 driver.tap(1,50,100,1000);//点击确认按钮
	  		 Thread.sleep(3000);
			 driver.sendKeyEvent(4); //点返回键，到联系人列表
			 Thread.sleep(3000);
			 
			 if(driver.findElementByName("Abc").isDisplayed()){
				 logger.fatal("Contact-creat:pass");
			 }else{
				 logger.fatal("Contact-creat:fail");
			 }
		  	 Thread.sleep(1000);
			 
			 driver.sendKeyEvent(4);	
		 }catch(Exception e){
			 if(e.getClass().getName()=="org.openqa.selenium.NoSuchElementException"){
				 logger.fatal("Contact-creat:fail");
			 }else{
				 e.printStackTrace();
			}
		 }
	}
	 
	 @Test
	 public void Contact03_delete(){	
		 try{
			driver.findElementByAccessibilityId("更多选项").click();  //根据content-desc查找元素
			driver.findElementByName("删除联系人").click();
			driver.findElementByName("Abc").click();
			driver.findElementByName("确定").click();
			driver.findElementById("android:id/button1").click();
			
			logger.fatal("Contac-delete:pass");
			 
		  	Thread.sleep(3000);
		  	
	  	   }catch(Exception e){
		  		 if(e.getClass().getName()=="org.openqa.selenium.NoSuchElementException"){
					 logger.fatal("Contac-delete:fail");
				 }else{
					 e.printStackTrace();
			}
	   }	
	}	
}
