// Write a Java program that reads on file name from the user, then displays information
// about whether the file exists, whether the file is readable, whether the file is writable, the type of file
// and the length of the file
// in bytes
import java.io.*;
import java.util.Scanner;
class FileReadUsr {
	

public static void main(String [] args) {
	Scanner scan=new Scanner(System.in);
char[] in = new char[100]; // to store input
int size = 0;
//try{
System.out.println("enter the file name");
	String name=scan.nextLine();
File file = new File(name);
if(file.exists()) 
System.out.println("file exists");
if(file.canRead())
	System.out.println("file is readable");
if(file.canWrite())
	System.out.println("file is writeable");

//System.out.println("the file type is"+);

System.out.println("file length is "+(byte)file.length());
String []ext=new String[2];
 ext=name.split("\\.");
System.out.println("the type of file is "+ext[1]);



}
}
