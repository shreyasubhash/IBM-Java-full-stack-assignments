/*
 Create a method which accepts an array of integer elements and return the
second smallest element in the array

*/

import java.util.Scanner;
import java.lang.*;
class SecondSmallest{


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please Enter How many element you want to take in the array: ");			
		int n=scan.nextInt();

		Integer []arr=new Integer[n];

		System.out.println("Please Enter the array elements: ");

		for(int i=0;i<n;i++)
		{
			arr[i]=scan.nextInt();
		}
		System.out.println("The second element is "+getSecondSmallest(arr));


	}
}

class SecSmall{
	 int getSecondSmallest (Integer [] arr){
		int temp;
			for(int i=0 ;i<arr.length;i++){
			flag=0;
			for(int j=i+1 ;j<arr.length;j++){
				if(arr[i]>arr[j]){			
						temp=arr[j];
						arr[j]=arr[i];
						arr[i]=temp;
						
						}
			

			}
				
			
		}
		return arr[1];	




	}	

}
