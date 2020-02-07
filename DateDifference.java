//Create a method to accept date and print the duration in days, months and years with
//regards to current system date



import java.util.Scanner;


import java.time.LocalDate;
import java.time.Period;

class DateDifference{

	
	public static void main(String[] args) {



		Scanner scan = new Scanner(System.in);
		System.out.println("Please Enter a Date in (yyyy:mm:dd fromat): ");		
		String str=new String(scan.next());
		dateDiff(str);

	}

	static void dateDiff(String date){

		String []token= new String[3]; 
		token=date.split(":");
			int years=Integer.parseInt(token[0]);
			int months=Integer.parseInt(token[1]);
			int days=Integer.parseInt(token[2]);
		LocalDate givenDate = LocalDate.of(years,months,days);
     LocalDate currentDate = LocalDate.now();
     Period diff = Period.between(givenDate, currentDate);
 
System.out.printf("Difference is %d years, %d months and %d days old", 
                    diff.getYears(), diff.getMonths(), diff.getDays());
	}


}