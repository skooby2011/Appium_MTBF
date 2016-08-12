package MTBF_Runner;

import org.junit.runner.JUnitCore;

import BaseCase.Browser;
import BaseCase.Contact;
import BaseCase.Phone;
import BaseCase.PhoneAndBrowse;
import junit.framework.TestCase;

import org.apache.log4j.*;

public class Runner extends TestCase {
	static Logger logger=Logger.getLogger(Runner.class);
	
	public void testRunner(){
		
		for(int i=0;i<2;i++){
			logger.fatal("circle"+i+"-------------------------------------start");
			
			//MTBF002
			JUnitCore.runClasses(Contact.class);  //返回值为Result类型
			
			//MTBF003
			JUnitCore.runClasses(Phone.class);
			
			//MTBF011
			JUnitCore.runClasses(Browser.class);
			
			//MTBF027
			JUnitCore.runClasses(PhoneAndBrowse.class);
			
			logger.fatal("circle"+i+"---------------------------------------end");
		}
	}
}
