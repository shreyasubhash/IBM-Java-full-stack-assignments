/*
Create a method to check if a number is an increasing number
*/

import java.util.Scanner;
import java.lang.*;
class IncreasingNumber{


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please Enter the number: ");			
		Integer n=scan.nextInt();

		if(checkNumber(n))
		 System.out.println("It is an increasing number");			
		else
			System.out.println("It is not an increasing number");

		}

		static boolean checkNumber(Integer n){					
			String s=n.toString();
			for(int i=0;i<s.length()-1;i++){

				if(s.charAt(i) > s.charAt(i+1))
					return false;

				
			}
			return true;
			}




}

