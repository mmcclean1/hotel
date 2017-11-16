package ie.lyit.serialize;

import ie.lyit.hotel.Customer;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class CustomerSerializer {

	private ArrayList<Customer> customers;
	
	private final String FILENAME = "customers.ser";
	private final String CUSTOMERNUMBERFILENAME = "noOfCustomers.ser";
	
	//Indicates how many customers are in file
	//When new customer is made, will increase by 1
	//Each new customer will have new value as customer number (Customer.number)
	private static int noOfCustomers = 1;
	
	//Default Constructor
	public CustomerSerializer()
	{
		//Construct Customer ArrayList
		customers = new ArrayList<Customer>();
	}
	
	public void add()
	{
		// Create a Customer object
		Customer customer = new Customer();	
		//Read it's details
		customer.read(false);
		// And add it to the books ArrayList
		customers.add(customer);
		//Increase noOfCustomers by 1
		noOfCustomers = noOfCustomers + 1;
	}
	
	public Customer view()
	{
		@SuppressWarnings("resource")
		Scanner keyboard = new Scanner(System.in);
		int customerToView = 0;
		
		// Read the number of the customer to be viewed from the user
		System.out.println("ENTER NUMBER OF CUSTOMER : ");
		try {
			customerToView=keyboard.nextInt();
		}
		catch(InputMismatchException iME)
		{
			System.out.println("Must enter integer.");
			keyboard.next();
		}
				
		// for every Book object in books
		for(Customer tmpCustomer:customers){
			// if it's number equals the number of the bookToView
			if(tmpCustomer.getNumber() == customerToView){
			 // display it
				System.out.println(tmpCustomer);
				return tmpCustomer;
			}
		}
			    
		// if we reach this code the book was not found so return null
		return null;
			    
	}
	
	
	public void list()
	{
		// for every Customer object in customers
	      for(Customer tmpCustomer:customers)
	    	  // display it
	    	  System.out.println(tmpCustomer);
	}
	
	public void edit()
	{
		// Call view() to find, display, & return the customer to edit
				Customer tempCustomer = view();
				// If the customer != null, i.e. it was found then...
			    if(tempCustomer != null){
				   // get it's index
				   int index=customers.indexOf(tempCustomer);
				   // read in a new customer and...
				   tempCustomer.read(true);
				   // reset the object in customers
				   customers.set(index, tempCustomer);
			    }
	}
	
	public void delete()
	{
		// Call view() to find, display, & return the customer to delete
				Customer tempCustomer = view();
				// If the customer != null, i.e. it was found then...
			    if(tempCustomer != null)
				   // ...remove it from customers
			       customers.remove(tempCustomer);
	}
	
	// This method will serialize the customers ArrayList and int noOfCustomers when called, 
		// i.e. it will write it to a file called customers.ser & noOfCustomers.ser
		public void writeRecordsToFile(){
			ObjectOutputStream os=null;
			DataOutputStream dOs = null;
			try {
				//p references the Path object returned by Paths.get()
				Path p = Paths.get(FILENAME);
				
				// Call Files static exists() method to ensure p exists
				 if(Files.exists(p))
				 {
					 // Serialize the ArrayList...
					 FileOutputStream fileStream = new FileOutputStream(FILENAME);
			
					 os = new ObjectOutputStream(fileStream);
					
					 os.writeObject(customers);
				
				 }
				 else // file doesn't exist
					 System.out.println("File "+p.getFileName()+" doesn’t exist");
				
				//p references the Path object returned by Paths.get()
					Path nP = Paths.get(CUSTOMERNUMBERFILENAME);
					
					// Call Files static exists() method to ensure p exists
					 if(Files.exists(nP))
					 {
						 //Serialize noOfCustomers...
						 FileOutputStream numberFileStream = new FileOutputStream(CUSTOMERNUMBERFILENAME);
				
						 dOs = new DataOutputStream(numberFileStream);
				
						 dOs.writeInt(noOfCustomers);
					 }
					 else // file doesn't exist
						 System.out.println("File "+nP.getFileName()+" doesn’t exist");
			}
			catch(FileNotFoundException fNFE){
				System.out.println("Cannot create file to store customers.");
			}
			catch(IOException ioE){
				System.out.println(ioE.getMessage());
			}
			finally {
				try {
					os.close();
				}
				catch(IOException ioE){
					System.out.println(ioE.getMessage());
				}
			}
		}
		
		// This method will deserialize the customers ArrayList & noOfCustomers when called, 
		// i.e. it will restore the ArrayList from the file customers.ser & the noOfCustomers from the file noOfCustomers.ser
		public void readRecordsFromFile(){
			ObjectInputStream is=null;
			DataInputStream dIs = null;
			try {
				//p references the Path object returned by Paths.get()
				Path p = Paths.get(FILENAME);
				
				// Call Files static exists() method to ensure p exists
				 if(Files.exists(p))
				 {
					 // Deserialize the ArrayList...
					 FileInputStream fileStream = new FileInputStream(FILENAME);
			
					 is = new ObjectInputStream(fileStream);
					
					 customers = (ArrayList<Customer>)is.readObject();
				 }
				 else // file doesn't exist
					 System.out.println("File "+p.getFileName()+" doesn’t exist");
				
				//p references the Path object returned by Paths.get()
					Path nP = Paths.get(CUSTOMERNUMBERFILENAME);
					
					// Call Files static exists() method to ensure p exists
					 if(Files.exists(nP))
					 {
						 //Deserialize noOfCustomers...
						 FileInputStream numberFileStream = new FileInputStream("noOfCustomers.ser");
				
						 dIs = new DataInputStream(numberFileStream);
				
						 noOfCustomers = dIs.readInt();
					 }
					 else // file doesn't exist
						 System.out.println("File "+nP.getFileName()+" doesn’t exist");
			}
			catch(FileNotFoundException fNFE){
				System.out.println("Cannot create file to store customers.");
			}
			catch(IOException ioE){
				System.out.println(ioE.getMessage());
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			finally {
				try {
					is.close();
				}
				catch(IOException ioE){
					System.out.println(ioE.getMessage());
				}
			}
		}
		
		public static int getNoOfCustomers()
		{
			return noOfCustomers;
		}
}
