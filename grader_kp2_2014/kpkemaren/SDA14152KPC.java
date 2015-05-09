import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;

/**
 * Kelas SDA14152KPC
 * 
 * @author asisten
 */
public class SDA14152KPC {
	public static void main(String[] args) throws IOException {
		
		BufferedReader scan;
		if (args.length != 0) 
			scan = new BufferedReader(new FileReader(new File(args[0])));
		else 
			scan = new BufferedReader(new InputStreamReader(System.in));

		try {
			LinkedList<Integer> linkedList = new LinkedList<>();
			String read;
			while ((read = scan.readLine()) != null) {
				String[] pisah = read.split(" ");
				switch (pisah[0]) {
				case "insert":
					linkedList.insert(Integer.parseInt(pisah[1]));
					break;
				case "descending":
					linkedList.printDescending();
					break;
				case "ascending":
					linkedList.printAscending();
					break;

				}
			}

		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}

/**
 * Kelas LinkedList untuk merepresentasikan struktur data Linked List
 * menggunakan header node
 * 
 * @author Tim Dosen dan Asisten SDA
 *
 * @param <T>
 */
class LinkedList<T extends Comparable<T>> {

	ListNode<T> header;

	public LinkedList() {
		header = new ListNode<T>(null);
	}

	/**
	 * Method insert untuk memasukkan data agar selalu terurut DILARANG
	 * menggunakan struktur data dan method dari Java Collections API. Tidak
	 * perlu mencetak apa-apa di method ini
	 *
	 * @todo IMPLEMENTASIKAN METHOD INI
	 * @param other
	 *            data yang ingin dimasukkan
	 */
	public void insert(T other) {
	
	}

	/**
	 * Method printDescending untuk mencetak data secara terurut menurun
	 * DILARANG menggunakan struktur data dan method dari Java Collections API.
	 * Cetak baris kosong jika list kosong Cetak seluruh isi list dipisahkan
	 * oleh spasi secara terurut menurun
	 * 
	 * @todo IMPLEMENTASIKAN METHOD INI
	 */
	public void printDescending() {
	}

	/**
	 * Method printAscending untuk mencetak data secara terurut menaik DILARANG
	 * menggunakan struktur data dan method dari Java Collections API. Cetak
	 * baris kosong jika list kosong.
	 * 
	 * Cetak seluruh isi list dipisahkan oleh spasi secara terurut menaik Method
	 * ini tidak mengubah isi data
	 * 
	 * @todo IMPLEMENTASIKAN METHOD INI
	 */
	public void printAscending() {
		
	}
}

/**
 * Kelas ListNode2, representasi Node dalam linked list
 * 
 * @author Tim Dosen dan Asisten SDA
 * @param <T>
 */
class ListNode<T> implements Comparable<ListNode> {

	T element;
	ListNode<T> next;

	ListNode(T e, ListNode<T> n) {
		element = e;
		next = n;
	}

	ListNode(T e) {
		this(e, null);
	}

	ListNode() {
		this(null, null);
	}

	public int compareTo(ListNode e) {
		return this.element.toString().compareTo(e.element.toString());
	}
}