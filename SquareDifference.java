/*
 Create a class with a method to find the difference between the sum of the
squares and the square of the sum of the first n natural numbers.
*/

import java.util.Scanner;
import java.lang.*;
class SquareDifference{


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please Enter the value of n: ");			
		int n=scan.nextInt();

		
		 System.out.println("The difference is:  "+calculateDifference(n));

		
	}

	static int calculateDifference(int n){
			int sum,sumofsquare,squareofsum,sumofn;
			sumofsquare=(n*(n+1)*(2*n+1))/6;
			sumofn=(n*(n+1))/2;
			squareofsum=sumofn*sumofn;
			return (sumofsquare - squareofsum);
	}


}