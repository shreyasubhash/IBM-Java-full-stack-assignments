//Write a program that replaces a particular word(taken at runtime) in a file
import java.io.*;
import java.util.Scanner;
class FileSearch {
	public static void main(String [] args) {
 // to store input
int size = 0;
try{
	Scanner scan=new Scanner(System.in);

File file = new File("qwe.txt"); 
//FileWriter fw = new FileWriter(file);
System.out.println("enter the word u want to replace");
String s=scan.next();
System.out.println("enter the new word");
String r=scan.next();

BufferedReader br = new BufferedReader(new FileReader(file));
String str = "";
String st="";
//i=0;

while((str = br.readLine()) != null)
{
	String[] w = new String[str.length()];

	
	w=str.split(s);
	for(int i=0;i<w.length;i++){
		if(w[i].equals(s))
			w[i]=r;
	}

	str=str.replace(s,r);
	System.out.println(str);
	st=st.concat(str+'\n');
	//st=st.concat('\n');
	

}
System.out.println("the new "+st);
br.close();
FileWriter fw = new FileWriter(file);
 fw.write(st);
 fw.flush();
 fw.close();



  } catch(IOException e) {System.out.println("Can't read from the file currently..."); }
}
}