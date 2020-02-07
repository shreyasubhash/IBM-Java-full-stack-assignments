/*
 You are asked to create an applic
ation for registering the details of jobseeker. The
requirement is:
Username should always end with _job and there should be at least minimum of 8 characters to the
left of _job. Write a function to validate the same. Return true in case the validation is
passed. In case
of validation failure return false

*/

import java.util.Scanner;
class JobSeeker{


	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please Enter the username ");			
		String str=new String(scan.next());
		if(new UserName().validateUserName(str))
			System.out.println("Valid User Name");
		else
			System.out.println("Not a valid User Name");

	}
}
class UserName{

	static boolean validateUserName(String str){
			
			int j=str.length()-4;
			String ext=new String("_job");
			String strone=str.substring(0,j);
			String strtwo=str.substring(j,str.length());
			if(strtwo.matches(ext)&&(strone.length()>7))
				return true;
			return false;

	}
}