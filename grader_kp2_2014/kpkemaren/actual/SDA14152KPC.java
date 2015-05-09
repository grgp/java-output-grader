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
		ListNode<T> node = new ListNode<T>(other);
		ListNode<T> dummy;

		if (header.next == null) {
			header.next = node;
		} else {

		int count = 0;

		dummy = header.next;

		boolean inserted = false;
		if ((Integer) node.element - (Integer) dummy.element > 0) {
			node.next = dummy;
			header.next = node;
			return;
		}


		while (!inserted) {
			if (dummy.next == null) {
				dummy.next = node;
				inserted = true;
			}
			//else if (node.compareTo(dummy.next) >= 0) {
			else if ((Integer) node.element - (Integer) dummy.next.element >= 0) {
				node.next = dummy.next;
				dummy.next = node;
				inserted = true;
			} else {
				dummy = dummy.next;
			}
		}

		}
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
		ListNode<T> current = header.next;

		if (header.next == null) {
			System.out.println("kosong");
			return;
		}

		while(current != null) {
			//System.out.print(current.element + (current.next != null ? " " : ""));
			System.out.print(current.element + " ");
			current = current.next;
		}
		//System.out.print(" ");
		System.out.println();
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
		ListNode<T> dummy = header.next;

		if (header.next == null) {
			System.out.println("kosong");
			return;
		}

		
		int count = 0;
		dummy = header.next;
		while (dummy != null) {
			count++;
			dummy = dummy.next; 
		}

		dummy = header.next;

		int[] arr = new int[count];

		for (int i = 0; i < count; i++) {
			arr[i] = (Integer) dummy.element;
			dummy = dummy.next;
		}

		for (int i = count-1; i >= 0; i--) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
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