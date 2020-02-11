// Write a Java program that reads a file and displays the file on the screen, with a line
// number before each line?
import java.io.*;
class FileReadDisp {
public static void main(String [] args) {
char[] in = new char[100]; // to store input
int size = 0;
try{
File file = new File("anotherFile.txt"); 


BufferedReader br = new BufferedReader(new FileReader(file));
String str = "";
int i=1;
while((str = br.readLine()) != null)
{
	System.out.print(i++ + " ");
System.out.println(str);
}
br.close();
  } catch(IOException e) {System.out.println("Can't read from the file currently..."); }
}
}
