//Create a method to find the sum of the cubes of the digits of an n digit number

import java.util.Scanner;
class SumOfCubes{


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please Enter the number: ");			
		long n=scan.nextLong();
		System.out.println("The sum is "+new CubeAdd().cubeSum(n));

	}
}
class CubeAdd{
	 long cubeSum(long n1){
		long n2=n1,t;
		long sum=0;
		while(n2>0){
			t=n2%10;
			sum=sum+t*t*t;
			n2/=10;
		}
		return sum;
	}
}