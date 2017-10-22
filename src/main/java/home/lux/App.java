package home.lux;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import home.lux.entry.PhoneBook;

public class App 
{
	static HashMap<String,PhoneBook> PhoneBook;
    public static void main( String[] args )
    {
    	PhoneBook = getAll();
        Scanner scan = new Scanner(System.in);
        int wait = 0;	//base
        while(wait == 0){
       
        	System.out.println("1. Add");
            System.out.println("2. Remove");
            System.out.println("3. Find");
            System.out.println("4. Show all");
            System.out.println("5. Cancel");
            System.out.println("Please, choose one:\n");
            wait = scan.nextInt();
            
            switch(wait){
            	case 1:	add();
            			wait = 0;
            			break;
            	case 2:	removeEnty();
            			wait = 0;
            			break;
            	case 3: findEntity();
            			wait = 0;
            			break;
            	case 4:	view();
            			wait = 0;
            			break;
            	case 5:	System.exit(0);
            			break;
            	default: System.out.println("Error. Please provide correct number.");
            			 wait = 0;
            }
        }
    }
    
    // return all entities
    public static HashMap<String, home.lux.entry.PhoneBook> getAll(){
    	HashMap<String,PhoneBook> book = null;
    	try{
    		FileInputStream file = new FileInputStream("book.bin");
    		ObjectInputStream obj = new ObjectInputStream(file);
    		book = (HashMap<String,PhoneBook>)obj.readObject();
    		obj.close();
    	} catch(Exception e){
    		System.out.println(e.getMessage());
    		
    		return book;}
    	return book;
    }
    
    //add new enty
    public static void add(){
    	try{
    	if(PhoneBook == null){	// no file
    		PhoneBook = new HashMap<String,PhoneBook>();
    		
    			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    			System.out.println("New name:"); String name = br.readLine();
    			System.out.println("New phone:"); String phone = br.readLine();
    			writeEnty(name,phone);
    		
    	} else{
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("New name:"); 
				String name = br.readLine();
			System.out.println("New phone:"); 
				String phone = br.readLine();
			writeEnty(name,phone);
    	}
    	} catch(Exception e){
    		System.out.println("add()." + e.getMessage());
    	}
    }
    
    //find
    public static void findEntity(){
    	if(PhoneBook != null){
    		System.out.print("Name to search: ");
    		try{
    			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    			String serKey = br.readLine();
    			PhoneBook search = verifyEntity(serKey);
    			if(search != null){
    				System.out.println("======SEARCH RESULT======");
    				System.out.println("Name: " + serKey + ", Phone: " + search.getNumber()+"\n"); 
    			} else {System.out.println("Not found phone with name " + serKey + "\n");}
    		} catch(Exception e){
    			System.out.println("findEntity()." + e.getMessage());
    		}
    	}
    }
    
    public static PhoneBook verifyEntity(String key){
    	if(key != null){
    		return PhoneBook.get(key);
    	} else{return null;}
    }
    
    //remove
    public static void removeEnty(){
    	if(PhoneBook != null){	// no file
    		System.out.print("Please enter entity name to delete: ");
    		int tSize = PhoneBook.size();
    		try{
	    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    		String delKey = br.readLine();
	    		deletekey(delKey);
	    		if(PhoneBook.size() < tSize){
	    			//writeEnty(PhoneBook);
	    			System.out.println("Entity deleted");
	    			System.out.println();
	    		} else{System.out.println("Can't find entity with such name\n");}
    		} catch(Exception e){
    			System.out.println("remoteEnty()." + e.getMessage());
    		}
    	}
    }
    
    public static void deletekey(String key){
    	PhoneBook.remove(key);
    }
    
    //show em
    public static void view(){
    	if(PhoneBook != null){ //!no file
    		Set<String> keys = PhoneBook.keySet();
    		System.out.println("===========PHONE LIST==========");
    		for(String key: keys){
    			System.out.println("Name: " + key + ", Phone: " + PhoneBook.get(key).getNumber()); 
    		}
    		System.out.println();
    	}
    }
    
    //write enty in file
    public static void writeEnty(String name, String phone){
    	try{
    		PhoneBook sub = new PhoneBook(name,phone);
			PhoneBook.put(name, sub);
    	    FileOutputStream fos=new FileOutputStream("book.bin");
    	    ObjectOutputStream oos=new ObjectOutputStream(fos);
    	    oos.writeObject(PhoneBook);
    	    oos.flush();
    	    oos.close();
    	    System.out.println();
    	    }catch(Exception e){
    	    	System.out.println("writeEnty()." + e.getMessage());
    	    }
    }
}
