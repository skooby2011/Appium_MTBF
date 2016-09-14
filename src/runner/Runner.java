package runner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import base.Browser;
import base.Contact;
import base.Phone;
import base.PhoneAndBrowse;
import capability.Capability;
import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.*;

public class Runner extends TestCase {
	static Logger logger=Logger.getLogger(Runner.class);
	
	public void testRunner(){
		
		for(int i=0;i<10;i++){
			logger.fatal("circle"+i+"-------------------------------------start");
			
			try{
				execCommand("adb connect "+Capability.deviceName);
			}catch(Exception e){
				e.printStackTrace();
			}
			
			//MTBF002
//			JUnitCore.runClasses(Contact.class);  //返回值为Result类型
			
			//MTBF003
			Result phone = JUnitCore.runClasses(Phone.class);
			passOrFail(phone,"phone");

			
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
	
	public void execCommand(String command) throws IOException {
	    Runtime runtime = Runtime.getRuntime();
	    Process proc = runtime.exec(command);
	    try {
	        if (proc.waitFor() != 0) {
	            System.err.println("exit value = " + proc.exitValue());
	        }
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                proc.getInputStream()));
	        StringBuffer stringBuffer = new StringBuffer();
	        String line = null;
	        while ((line = in.readLine()) != null) {
	            stringBuffer.append(line+" ");
	        }
	        System.out.println(stringBuffer.toString());
	 
	    } catch (InterruptedException e) {
	        System.err.println(e);
	    }finally{
	        try {
	            proc.destroy();
	        } catch (Exception e2) {
	        }
	    }
	}
	
}
