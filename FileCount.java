//Write a Java program that displays the number of characters, lines and words in a
// text read from a file ?
import java.io.*;
class FileCount {
public static void main(String [] args) {
 // to store input
int size = 0,line=0,word=0,ch=0;
try{
File file = new File("qwe.txt"); 

BufferedReader br = new BufferedReader(new FileReader(file));
String str = "";
while((str = br.readLine()) != null)
{
	String[] w = new String[str.length()];
	w=str.split(" ");
	word+=w.length;
	line++;
	ch+=str.length();


}
br.close();
System.out.println("no of lines is "+line);
System.out.println("no of words is "+word);
System.out.println("no of char is "+ch);

  } catch(IOException e) {System.out.println("Can't read from the file currently..."); }
}
}