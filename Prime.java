
import java.util.Scanner;
class CalPrime{
	Interger [] primeNum(int n){
		int z=0;
		Integer []arr=new Integer [n];
		for(int i=0;i<n;i++){
			for(j=0;j<(i/2)+1;j++){
				if(i/j==0)
					break;

			}
			if(j==(i/2)+1)
				arr[z]=1;
			z++;

	}
}

class Prime{
	public static void main(String[] args) {
		Scanncer sacn=new Scanner(System.in);
		System.out.println("enter the no");
		int n=Interger.parsInt(scan.next());
		Integer []arr=new Integer [n];
		arr=new CalPrime().primeNum(n);
		for (i=0;arr[i]!='null';i++)
			System.out.println(arr[i])
	}
}
