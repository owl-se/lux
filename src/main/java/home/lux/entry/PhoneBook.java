package home.lux.entry;

import java.io.Serializable;

public class PhoneBook implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String number;
	
	 public PhoneBook(String name,String phone){
		  
		  this.name=name;
		  this.number=phone;
		  
		  
		 }
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
}
