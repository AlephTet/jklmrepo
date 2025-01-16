import java.io.BufferedWriter;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SyllableSorter {

	public static void main(String[] args) throws IOException 
	{
		ArrayList<String> words = null;
		try {
			words = (ArrayList<String>) Files.readAllLines(Paths.get("C:\\Users\\1887700\\Downloads\\wordlist2.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.setOut(new PrintStream(new FileOutputStream("Syllables500.txt")));
		//System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
		int count = 0;
		char a1 = 'A';
		char a2 = 'A';
		char a3 = 'A';
		String a = "";
		for(int i = 0;i<26;i++)
		{
			for(int j = 0;j<26;j++)
			{
				a = Character.toString(a1) + Character.toString(a2);
				for(String spec: words)
				{
					if(spec.contains(a.toLowerCase()) || spec.contains(a.toUpperCase()))
					{
						count++;
					}
				}
				if(count>=500)
				{
					System.out.println(a);
				}
				a2++;
				count = 0;
				a=null;
			}
			a2 = 'A';
			a1++;
		}
		a1 = 'A';
		for(int i = 0;i<26;i++)
		{
			for(int j = 0;j<26;j++)
			{
				for(int k = 0;k<26;k++)
				{
					a = Character.toString(a1) + Character.toString(a2) + Character.toString(a3);
					for(String spec: words)
					{
						if(spec.contains(a.toLowerCase()) || spec.contains(a.toUpperCase()))
						{
							count++;
						}
					}
					if(count>=500)
					{
						System.out.println(a);
					}
					a3++;
					count = 0;
					a=null;
				}
				a3 = 'A';
				a2++;
			}
			a2 = 'A';
			a1++;
		}
	}
}
