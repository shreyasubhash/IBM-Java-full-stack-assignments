/*Create a method that can accept an array of String objects and sort in
alphabetical order. The elements in the left half should be completely in uppercase and the
elements in the right half should be completely in lower case. Return the resulting array.
Note: If there are odd number of String objects, then (n/2) +1 elements should be in
UPPPERCASE
*/
import java.util.Scanner;

class StringArraySort{

	public static void main(String[] args) {
		Scanner scan= new Scanner(System.in);
		System.out.println("How many elements do you want to enter: ");
		int n=scan.nextInt();
		System.out.println("Please enter the array elements: "); //taking array elements
		String []array= new String[n];
		
		for(int i=0;i<n;i++){

			array[i]=new String(scan.next());

		}
		
		array=arraySortCase(array);
		System.out.println("The array after sort: ");
		for(int i=0 ;i<n;i++){
			System.out.println(array[i]);
			
		}
	}

static String[] arraySortCase(String[] array)
{

int flag=0;
	String temp;
		
		for(int i=0 ;i<array.length ;i++){
			flag=0;
			for(int j=i+1 ;j<array.length ;j++){
				if((array[i].compareTo(array[j]))>0){			
						temp=array[j];
						array[j]=array[i];
						array[i]=temp;
						flag=1;
						}
			

			}
			if(flag==0)
				break;			
			
		}
		int k=0,n=array.length;
		if(n%2==1)
			k=(n/2)+1;
		else
			k=n/2;
		for(int i=0 ;i<array.length;i++){
			if(i<
				k)
				array[i]=array[i].toUpperCase();
			else
				array[i]=array[i].toLowerCase();
			
		}
return array;

}
	
}