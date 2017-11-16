package ie.lyit.hotel;
import java.io.*;

public abstract class Person implements Serializable{
	protected Name name; // COMPOSITION - Person HAS-A name
	protected String address;
	protected String phoneNumber;

	// Default Constructor
	// Called when object is created like this 
	//   ==> Person pObj = new Person();
	//   NOTE-This won't work because Person is abstract
	public Person(){
	   name=new Name();
	   address=null;
	   phoneNumber=null;
	}

	// Initialisation Constructor
	// Called when object would have been created like this (not possible cos abstract!)
	//    ==> Person pObj = new Person("Mr", "Joe", "Doe", "2 Hi Road, Ennis", "087 1234567");
	public Person(String t, String fN, String sn, String address, String phoneNumber){
		name=new Name(t, fN, sn);	// Calls Name initialisation constructor
		this.address=address;
		this.phoneNumber=phoneNumber;
	}
	
	// toString() method
	// ==> Calls Name's toString() to display name and
	//		then displays address and phoneNumber
	@Override  // Overrides Object toString()
	public String toString(){
		return name + "," + address + "," + phoneNumber;
	}	
	
	// equals() method
	// ==> Called when comparing an object with another object, 
	//     e.g. - if(p1.equals(p2))				
	// ==> Calls Name's equals() to compare name to personIn's name, and
	//		compares phoneNumber to personIn's phoneNumber
	@Override  // Overrides Object equals()
	public boolean equals(Object obj){
	   Person pObject;
	   if (obj instanceof Person)
	      pObject = (Person)obj;
	   else
	      return false;
	   
       return(name.equals(pObject.name) &&
    		  address.equals(pObject.address) && 
			  phoneNumber.equals(pObject.phoneNumber));
	}
	
	// set() and get() methods
	public void setName(Name nameIn){
		name = nameIn;
	}
	public Name getName(){
		return name;
	}

	public void setAddress(String addressIn){
		address = addressIn;
	}
	public String getAddress(){
		return address;
	}
	
	public void setPhoneNumber(String phoneNumberIn){
		phoneNumber = phoneNumberIn;
	}
	public String getPhoneNumber(){
		return phoneNumber;
	}
}
