/* Create a method which accepts an integer array, reverse the numbers in the
array and returns the resulting array in sorted order */

import java.util.Scanner;
import java.lang.*;
class ArrayReverse{


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

		arr=new Sort().getSort(arr);
		for(int i=0;i<n;i++)
		System.out.println(arr[i]);

	}
}

class Sort{
	 Integer[] getSort(Integer [] arr){
		for(int i=0;i<arr.length;i++){
				StringBuilder s= new StringBuilder(arr[i].toString());			
				s.reverse();
				arr[i]=Integer.parseInt(new String(s));
			}
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
		return arr;
			


		}


	}




