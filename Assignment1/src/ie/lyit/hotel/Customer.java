package ie.lyit.hotel;
import javax.swing.*;
import java.io.*;
import ie.lyit.serialize.*;

public class Customer extends Person implements Serializable{// INHERITANCE - Customer IS-A Person
	 
	private static final long serialVersionUID = -7026506309101616488L;

	// Customer has name, address, & phoneNumber from Person
	private String emailAddress;    // AND emailAddress
	private int number;			    // AND number

	private static int nextNumber=1;// static for unique number - starts off at 1
	
	// Default Constructor
	// Called when object is created like this ==> 
	//   Customer cObj = new Customer();	
	public Customer(){
		super();			// NOTE:Don't need to call super() explicitly.
		emailAddress=null;
		// Set number to static nextNumber before incrementing nextNumber
		number=nextNumber++;
	}
	
	// Initialization Constructor
	// Called when object is created like this ==>
	// Customer cObj = new Customer("Mr","Joe","Doe","12 Hi Rd,Letterkenny",
	//                              "0871234567","joe@hotmail.com");
	public Customer(String t, String fN, String sn, String address, 
			        String pNo, String email){
		// Call super class constructor - Passing parameters required by Person ONLY!
		super(t, fN, sn, address, pNo);
		// And then initialise Customers own instance variables
		emailAddress=email;
		// And finally set number to static nextNumber before incrementing nextNumber
		number=nextNumber++;
	}

	// OVERRIDING the Person toString() method!
	// Calling Persons toString() method, and adding additional bits
	@Override
	public String toString(){
		return "[Customer " + number + ": " + super.toString() + "," + emailAddress + "]";
	}

	// equals() method
	// ==> Called when comparing an object with another object, 
	//     e.g. - if(c1.equals(c2))				
	// ==> Probably sufficient to compare customer numbers as they're unique
	@Override
	public boolean equals(Object obj){
		Customer cObject;
		if (obj instanceof Customer)
		   cObject = (Customer)obj;
		else
		   return false;
		   
	    return(this.number==cObject.number);
	}

	// set() and get() methods
	public void setEmailAddress(String emailAddress){
		this.emailAddress=emailAddress;
	}
	public String getEmailAddress(){
		return this.emailAddress;
	}	
	// You shouldn't be able to setNumber() as it is unique, 
	// so don't provide a setNumber() method
	public int getNumber(){
		return number;
	}
	
	
	// read() - To read a customer from the user	
	   public void read(boolean edit){
		   
	      JTextField txtCustomerNo = new JTextField();
	      if(edit == false)
	      {
	    	  txtCustomerNo.setText("" + CustomerSerializer.getNoOfCustomers());
	      }
	      else
	      {
	    	  txtCustomerNo.setText("" + this.number);
	      }
	      JTextField txtTitle = new JTextField();
	      JTextField txtFirstName = new JTextField();
	      txtFirstName.requestFocus();
	      JTextField txtSurName = new JTextField();
	      JTextField txtAddress = new JTextField();
	      JTextField txtPhoneNo = new JTextField();
	      JTextField txtEmail = new JTextField();

	      Object[] message = {
	          "Customer Number:", txtCustomerNo,
	          "Name\nTitle:", txtTitle,
	          "First Name:", txtFirstName,
	          "Surname:", txtSurName,
	          "Address:", txtAddress,
	          "Phone No.:", txtPhoneNo,
	          "Email:", txtEmail,
	      };

	      int option = JOptionPane.showConfirmDialog(null, message, "Enter customer details", JOptionPane.OK_CANCEL_OPTION);
	      
	      Name txtName = new Name(txtTitle.getText(), txtFirstName.getText(), txtSurName.getText());

	      if (option == JOptionPane.OK_OPTION){
	    	  if(edit == false)
	    	  {
	    		  this.number = CustomerSerializer.getNoOfCustomers();
	    	  }
	          this.name = txtName;
	          this.address = txtAddress.getText();
	          this.phoneNumber = txtPhoneNo.getText();
	          this.emailAddress = txtEmail.getText();
	      }   
}
}

