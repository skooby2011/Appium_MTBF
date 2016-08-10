package BaseCase;

import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;

public class Contact extends TestCase{
	
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
	    capabilities.setCapability("appPackage", "com.android.contacts");  
	    capabilities.setCapability("appActivity", ".activities.PeopleActivity");   
	    capabilities.setCapability("unicodeKeyboard", "True");  
	    capabilities.setCapability("resetKeyboard", "True");
	    driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities); 
	    
	}
	 @After
	    public void tearDown() throws Exception {
	        driver.quit();
	    }

	 
	public void testCreat(){
		driver.findElementById("com.android.contacts:id/floating_action_button").click();		 
		 
		 WebElement name = driver.findElementByName("姓名");
		 name.click();
		 name.sendKeys("Abc");
		 
//		 driver.sendKeyEvent(66);   //模拟返回键
		 driver.swipe(690, 500, 690, 50, 3000);
		 
		 //输入电话号码
		 WebElement number = driver.findElementByClassName("android.widget.EditText");
		 assertEquals("电话", number.getText());
		 number.click();
		 number.sendKeys("10086");
		 
		 //点击确认按钮
		 driver.tap(1,50,100,1000);
		 
		 try{
  		   Thread.sleep(3000);
  	   }catch(Exception e){
  		   System.out.print(e);
  	   }		 
		 
		 driver.sendKeyEvent(4); //点返回键
		 
		 try{
	  		   Thread.sleep(1000);
	  	   }catch(Exception e){
	  		   System.out.print(e);
	  	   }	
		 
		 driver.sendKeyEvent(4);
		 
	}
	
	public void testDelete(){
		
		driver.startActivity("com.android.contacts", "com.android.contacts.activities.PeopleActivity");
		
		//根据content-desc查找元素
		driver.findElementByAccessibilityId("更多选项").click();
		driver.findElementByName("删除联系人").click();
		driver.findElementByName("Abc").click();
		driver.findElementByName("确定").click();
		driver.findElementById("android:id/button1").click();
		
		 try{
	  		   Thread.sleep(3000);
	  	   }catch(Exception e){
	  		   System.out.print(e);
	   }	
	}
}
