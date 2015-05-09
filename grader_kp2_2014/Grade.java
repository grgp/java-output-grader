/* @author: G.A. Pitoy
 * @assignment: gatausihisengdoangtrusketerusansampe3jamdebugginginidoangaah
 */

import java.io.*;
import java.util.*;
import java.text.*;

public class Grade {
	public static void main (String[] args) throws IOException {
		Class called = Class.forName(args[0].split(".")[0]);
		System.out.println("\nGrader for " + args[0] + "\n");
		int score = 0;

		long startTime, endTime;

		for (int i = 1; i <= 50; i++) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			PrintStream old = System.out;
			System.setOut(ps);

			String[] ar = new String[3];
			ar[0] = "testcase/"+i+"/input";
			ar[1] = "testcase/"+i+"/output";
			args = Arrays.copyOfRange(args, 1, args.length-1);

			startTime = System.nanoTime();

			try {
				called.main(args);
			} catch (FileNotFoundException fn) {
				System.out.flush();
				System.setOut(old);
				break;
			}

			endTime = System.nanoTime();
			double duration = (double) (endTime - startTime)/1000000000.0;
			NumberFormat formatter = new DecimalFormat("#0.000");   

			System.out.flush();
			String results = baos.toString("UTF-8");
			String expected = "";
			System.setOut(old);

			try {	expected = new Scanner( new File(ar[1]), "UTF-8").useDelimiter("\\A").next();} 
			catch (NoSuchElementException exp) {}

			if (results.equals(expected))
				System.out.println("testcase " + (i < 10 ? " " : "") + i + ": correct");
			else
				System.out.println("testcase " + (i < 10 ? " " : "") + i + ": wrong");
		}

	}

}