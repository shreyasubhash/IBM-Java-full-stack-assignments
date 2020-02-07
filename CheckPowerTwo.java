/*
 Create a method to check if a number is a power of two or not
*/

 import java.util.Scanner;
import java.lang.*;
class CheckPowerTwo{


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please Enter the number: ");			
		Integer n=scan.nextInt();
		if(checkNumber(n))
		 System.out.println("It is a power of two");
		else
			System.out.println("It is not a power of two");

		}

		static boolean checkNumber(Integer n){	
		while(n>2)
			n=n>>1;	
			if(n==2)
			return true;			 
			else 
				return false;
		}


}
