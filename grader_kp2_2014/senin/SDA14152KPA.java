import java.util.Scanner;

public class SDA14152KPA {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		String albert = in.next();
		String bernard = in.next();
		
		//TODO:
		//Keluarkan string favorit Cheryl dan minimal banyaknya proses 
		//menukar 2 huruf untuk mendapatkan string tersebut dipisahkan oleh spasi. 
		
		String c = albert + bernard;
		char[] d = c.toCharArray();

		boolean sorted = false;

		int count = 0;
		outerloop:
		while (!sorted) {
			for (int i = 0; i < c.length()-1; i++) {
				if (d[i] > d[i+1]) {
					//System.out.println("swapped " + d[i] + " with " + d[i+1]);
					char tmp = d[i];
					d[i] = d[i+1];
					d[i+1] = tmp;
					count++;
					break;
				}
			}

			for (int i = 0; i < c.length()-1; i++) {
				if (d[i] > d[i+1])
					continue outerloop;
			}

			sorted = true;
		}

		String result = "";
		for (int i = 0; i < d.length; i++) {
			result = result + d[i];
		}

		System.out.println(result + " " + count);

	}

}