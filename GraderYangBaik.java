/* @author: G.A. Pitoy
 * @assignment: gatausihisengdoangtrusketerusansampe3jamdebugginginidoangaah
 */

import java.io.*;
import java.util.*;
import java.text.Collator;

public class GraderYangBaik {
	public static void main (String[] args) throws IOException {

		Collator col = Collator.getInstance();

		if (args[0].equalsIgnoreCase("jumat")) args[0] = "Jumat";
		else if (args[0].equalsIgnoreCase("senin")) args[0] = "Senin";

		if (args[1].equalsIgnoreCase("A")) args[1] = "A";
		else if (args[1].equalsIgnoreCase("B")) args[1] = "B";
		else if (args[1].equalsIgnoreCase("C")) args[1] = "C";
		
		System.out.println("\nGrader for KP1 2014 " + args[0] + " Soal " + args[1] + "\n");

		for (int i = 1; i <= 10; i++) {

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			PrintStream old = System.out;
			System.setOut(ps);

			String[] ar = new String[3];
			ar[0] = "data/"+args[0]+args[1]+"/"+ i+"/input";
			ar[1] = "data/"+args[0]+args[1]+"/"+ i+"/output";
			ar[2] = "kp" + args[0] + args[1];

			clsname(ar);

			System.out.flush();
			String results = baos.toString("UTF-8");
			String expected = "";

			try {
				expected = new Scanner( new File(ar[1]), "UTF-8").useDelimiter("\\A").next();
			} catch (NoSuchElementException exp) {}
			System.setOut(old);

			String forTen = " ";

			if (i != 10) {
				if (col.equals(results, expected))
					System.out.println("testcase  " + i + ": correct");
				else
					System.out.println("testcase  " + i + ": wrong");
			} else {
				if (col.equals(results, expected))
					System.out.println("testcase " + i + ": correct");
				else
					System.out.println("testcase " + i + ": wrong");
			}
		}

	}

	private static void clsname(String[] args) throws IOException {
		if (args[2].equals("kpJumatA")) SDAKuisJumatA.main(args);
		else if (args[2].equals("kpJumatB")) SDAKuisJumatB.main(args);
		else if (args[2].equals("kpJumatC")) SDAKuisJumatC.main(args);
		else if (args[2].equals("kpSeninA")) SDAKuisSeninA.main(args);
		else if (args[2].equals("kpSeninB")) SDAKuisSeninB.main(args);
		else if (args[2].equals("kpSeninC")) SDAKuisSeninC.main(args);
	}

}