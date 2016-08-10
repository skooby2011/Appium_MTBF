package MTBF_Runner;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import BaseCase.Contact;
import BaseCase.Phone;
import BaseCase.Browser;


@RunWith(Suite.class)
@Suite.SuiteClasses({
   Contact.class,
   Phone.class,
   Browser.class
})
public class TestSuite {   
} 