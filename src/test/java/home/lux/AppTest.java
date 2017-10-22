package home.lux;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import home.lux.entry.PhoneBook;

public class AppTest {
	private App junk;
	
	@BeforeMethod
	public void setUp(){
		junk = new App();
		junk.PhoneBook = new HashMap<String,PhoneBook>();
		junk.PhoneBook.put("rrr", new PhoneBook("rrr","5-5-5333"));
		junk.PhoneBook.put("rrr2", new PhoneBook("rrr2","6-6-6444"));
	}
	
	@Test
	public void deleteEntyTest(){
		int currentSize = junk.PhoneBook.size();
		junk.deletekey("rrr");
		System.out.println("Size was " + currentSize + ", and now become " + junk.PhoneBook.size());
		assertEquals(1, junk.PhoneBook.size());
	}
	
}
