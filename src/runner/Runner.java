package runner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import base.Browser;
import base.Contact;
import base.Phone;
import base.PhoneAndBrowse;
import capability.Capability;
import db.ResultDB;
import junit.framework.TestCase;
import utility.AdbCommand;


import org.apache.log4j.*;

public class Runner extends TestCase {
	

	 
	static Logger logger=Logger.getLogger(Runner.class);
	final int CIRCLE = 3 ;
	
	public void testRunner(){
		
		for(int i=1;i<=CIRCLE;i++){
			logger.fatal("circle"+i+"-------------------------------------start");
			
			try{
				AdbCommand.execCommand("adb connect "+Capability.deviceName);
				AdbCommand.takeScreenshot();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			//MTBF002
//			JUnitCore.runClasses(Contact.class);  //返回值为Result类型
			
			//MTBF003
			Result phone = JUnitCore.runClasses(Phone.class);
			passOrFail(phone,"phone");
			ResultDB.getResultDB();

			
			//MTBF011
//			JUnitCore.runClasses(Browser.class);
			
			//MTBF027
//			JUnitCore.runClasses(PhoneAndBrowse.class);
			
			logger.fatal("circle"+i+"---------------------------------------end");
		}
	}
	
	public void passOrFail(Result result,String testCaseName){
		if(result.wasSuccessful()){
			logger.fatal(testCaseName+ ":pass");
		}else{
			logger.fatal(testCaseName+":fail" + result.getFailures());
		}
	}
}
