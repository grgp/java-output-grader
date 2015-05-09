/* @author: G.A. Pitoy
 * @assignment: gatausihisengdoangtrusketerusansampe3jamdebugginginidoangaah
 */

import java.io.*;
import java.util.*;
import java.text.Collator;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.awt.Desktop;

public class GraderYangBaik {
	public static void main (String[] args) throws IOException {

		Collator col = Collator.getInstance();

		if (args[0].equalsIgnoreCase("kpkemaren")) args[0] = "KPKemaren";
		else if (args[0].equalsIgnoreCase("jumat") || args[0].equalsIgnoreCase("senin") ) {
			System.out.println("Wrong folder.");
			System.exit(0);
		} else {
			System.out.println("Invalid arguments.");
		}

		if (args[1].equalsIgnoreCase("A")) args[1] = "A";
		else if (args[1].equalsIgnoreCase("B")) args[1] = "B";
		else if (args[1].equalsIgnoreCase("C")) args[1] = "C";
		
		System.out.println("\nGrader for KP1 2014 " + args[0] + " Soal " + args[1] + "\n");

		int score = 0;
		long startTime, endTime;
		boolean allcorrect = true;
		String n = System.getProperty("line.separator");

		PrintWriter writer = new PrintWriter(new File("_Debug.txt"));
		writer.println("Debug for KP" + args[0] + " Soal " + args[1] + n +  "------------------------------" + n);

		for (int i = 1; i <= 40; i++) {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			PrintStream old = System.out;
			System.setOut(ps);

			String[] ar = new String[3];
			ar[0] = "../data/"+args[0]+args[1]+"/"+ i+"/input";
			ar[1] = "../data/"+args[0]+args[1]+"/"+ i+"/output";
			ar[2] = "kp" + args[0] + args[1];

			startTime = System.nanoTime();
			
			try {
				clsname(ar);
			} catch (FileNotFoundException fn) {
				System.out.flush();
				System.setOut(old);
				break;
			}

			endTime = System.nanoTime();
			double duration = (double) (endTime - startTime) * 25 /1000000000.0;
			NumberFormat formatter = new DecimalFormat("#0.000");     

			System.out.flush();
			String results = baos.toString("UTF-8").trim();
			String expected = "";
			String input = "";
			System.setOut(old);

			try {expected = new Scanner( new File(ar[1]), "UTF-8").useDelimiter("\\A").next().trim();}
			catch (NoSuchElementException exp) {}

			try {	
				input = (new Scanner( new File(ar[0]), "UTF-8").useDelimiter("\\A").next());
			} catch (NoSuchElementException exp) {}	

			if (col.equals(results, expected)) {
				System.out.println("testcase " + (i < 10 ? " " : "")  + i + ": correct - " + formatter.format(duration) + "s");
				writer.println("Testcase " + i + " correct" + n +  "input: " + n + input + n + n + "actual output: " + n + results + n + n + "------------------------------" + n );
				score += 10;
			}
			else {
				System.out.println("testcase  " + (i < 10 ? " " : "") + i + ": wrong - " + formatter.format(duration) + "s");
				writer.println("Testcase " + i + " wrong" + n +  "input: " + n + input + n + n + "actual output: " + n + results + n + "correct output: " + n + expected + n + n + "------------------------------" + n );
			}
			
		}
		writer.close();

		//if (!(args.length > 2 && args[2].equalsIgnoreCase("nodebug"))) openDebug();
		
		System.out.println("\nScore: " + score + "\n\n<Note:>\n Detailnya ada di _Debug.txt di folder yg sama,\n jangan buka pake notepad.\n\n Waktu diatas belum tentu sama dengan grader,\n hanya buat perbandingan/perkiraan");

	}

	private static void clsname(String[] args) throws IOException {
		if (args[2].equals("kpKPKemarenA")) SDA14152KPA.main(args);
		else if (args[2].equals("kpKPKemarenB")) SDA14152KPB.main(args);
		else if (args[2].equals("kpKPKemarenC")) SDA14152KPC.main(args);
	}

	private static void openDebug() throws IOException {
		File file = new File("_Debug.txt");
         
	        	if(!Desktop.isDesktopSupported()){
	            		System.out.println("Desktop is not supported");
	            		return;
	        	}
         
        		Desktop desktop = Desktop.getDesktop();
        		if(file.exists()) desktop.open(file);
    }

}