package ie.lyit.testers;

import ie.lyit.serialize.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomerSerializerTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner keyIn = new Scanner(System.in);
		//Create object of CustomerSerializer
		CustomerSerializer cusSer = new CustomerSerializer();
		
		int option = 0;
		
		//Deserialize ArrayList from file
		try{
			cusSer.readRecordsFromFile();
		}
		catch(NullPointerException nPE)
		{
			nPE.getMessage();
		}
		
		do
		{
			//Create menu
			System.out.println("\t---Menu---");
			System.out.println("\t1. Add");
			System.out.println("\t2. View");
			System.out.println("\t3. List");
			System.out.println("\t4. Edit");
			System.out.println("\t5. Delete");
			System.out.println("\t6. Quit");
			System.out.println("\n\tEnter option [1|2|3|4|5|6]:");
			
			try{
				option = keyIn.nextInt();
			}
			catch(InputMismatchException iME)
			{
				System.out.println("Must enter integer.");
				keyIn.next();
			}
			
			//Switch on menu option and call appropriate method
			switch(option)
			{
			case 1: cusSer.add();
				break;
			case 2: cusSer.view();
				break;
			case 3: cusSer.list();
				break;
			case 4: cusSer.edit();
				break;
			case 5: cusSer.delete();
				break;
			case 6: System.out.println("\nQuitting...");
				break;
			default: System.out.println("\nInvalid option selected.");
			}
		}while(!(option==6)); //Keep displaying until 6. Quit is chosen
		
		//Serialize ArrayList to file
		try {
		cusSer.writeRecordsToFile();
		}
		catch(NullPointerException nPE)
		{
			nPE.getMessage();
		}
		
		//Close Scanner
		keyIn.close();
	}

}
