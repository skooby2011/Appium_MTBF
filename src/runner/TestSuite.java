package runner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import base.Browser;
import base.Contact;
import base.Phone;


@RunWith(Suite.class)
@Suite.SuiteClasses({
   Contact.class,
   Phone.class,
   Browser.class
})
public class TestSuite {} 