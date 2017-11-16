package ie.lyit.hotel;

// INHERITANCE - Employee IS-A Person, and CAN-DO Payable methods
public class Employee extends Person implements Payable {
    private Date dob;		// Employee has name,address&phoneNumber from Person
    private Date startDate;	// AND dob,startdate,salary, & number	
    private double salary;
    private int number;

	private static int nextNumber=1;	// static for unique number - starts off at 1
	
	private final double MAX_SALARY = 150000;	

    // Default Constructor
	// Called when object is created like this ==> Employee eObj = new Employee();	
    public Employee(){
    	super();		// NOTE:Not needed
    	dob=new Date();
    	startDate=new Date();
 		salary=0.0;
		// Set number to static nextNumber before incrementing nextNumber
 		number=nextNumber++;
    }
	
    // Initialization Constructor
	// Called when object is created like this ==>
	//    Employee empObj = new Employee("Mr","Joe","Doe","12 Lowlands,Ardara",
    //                                   "087 1234567",25,12,1970,new Date(10,11,2009),55000);
    //    NOTE:For dob and startDate you can either pass in a d,m,y OR a date object
    //         To show this, I have done dob as d,m,y, and startDate as a date object
    public Employee(String t, String fN, String sn, String address, String phoneNo, 
    		        int d, int m, int y,Date startDate, double salary){
       // Call super class constructor - Passing parameters required by Person ONLY!
	   super(t, fN, sn, address, phoneNo);
	   // And then initialise Employees own instance variables
	   dob=new Date(d,m,y);
	   this.startDate=startDate;	// Set instance variable to parameter
	   this.salary=salary;
	   
		// Set number to static nextNumber before incrementing nextNumber
	   number = nextNumber++;						
	}
    
	// OVERRIDING the Person toString() method!
	// Calling Persons toString() method, and adding additional bits
	@Override
    public String toString(){
		return number + " " + super.toString() + " €" + salary;
	}

	// equals() method
	// ==> Called when comparing an object with another object, 
	//     e.g. - if(e1.equals(e2))				
	// ==> Probably sufficient to compare customer numbers as they're unique
	@Override
	public boolean equals(Object obj){
		Employee eObject;
		if (obj instanceof Employee)
		   eObject = (Employee)obj;
		else
		   return false;
		   
	    return(this.number==eObject.number);
	}

	// set() and get() methods
	public void setDOB(Date dob){
		this.dob=dob;
	}
	public Date getDOB(){
		return dob;
	}
	public void setStartDate(Date startDate){
		this.startDate=startDate;
	}
	public Date getStartDate(){
		return startDate;
	}	
	public void setSalary(int salary){
		this.salary=salary;
	}
	public double getSalary(){
		return salary;
	}
	// You shouldn't be able to setNumber() as it is unique, 
	// so don't provide a setNumber() method
	public int getNumber(){
		return number;
	}	
	
	@Override
	public double calculateWage(double taxPercentage) {
		// calculate and return the wage as salary/12 less taxPercentage
		double wage=salary/12;
		wage -= (wage * (taxPercentage/100));
		return wage;
	}

	@Override
	public double incrementSalary(double incrementAmount) {
		// add incrementAmount to, and return the new salary
		// salary should not go over a maximum salary of €150,000
		salary += incrementAmount;
		
		if(salary > MAX_SALARY)
			salary = MAX_SALARY;
		
		return salary;
	}

}
