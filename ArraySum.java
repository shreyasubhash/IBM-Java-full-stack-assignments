//  Write a Java program that reads a line of integers and then displays each integer and the
//sum of all integers. (Use StringTokenizer class)?
import java.util.StringTokenizer;
import java.util.Scanner;
class ArraySum{
	public static void main(String[] args) {
		int n,sum=0;
		Scanner scan=new Scanner(System.in);
	
		System.out.println("enter the list");

		//String str=new String(scan.next());
		StringTokenizer s=new StringTokenizer(scan.nextLine()," ");
		while (s.hasMoreTokens()) {
            n=Integer.parseInt(s.nextToken()); 		
        	System.out.println(n);
        	sum=sum+n;	
	}
	System.out.println("The sum is "+sum);
}
}