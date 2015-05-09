//package kp1senin.c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Source code KP 1 Senin Soal C
 * Delete Range Doubly Linked List
 * Nilai : 30
 *
 * ============================================
 * NAMA : ISI NAMA ANDA
 * NPM : ISI NPM ANDA
 * ============================================
 * 
 * Lengkapi method deleteRange(int x, int y) pada kelas DoublyLinkedList saja
 * DILARANG mengubah implementasi lain pada kode template. 
 * DILARANG menggunakan struktur data dan method dari Java Collections API.
 * Asisten akan memeriksa kode sumber Anda untuk penilaian White Box.
 * @author RAMZ
 */

import java.io.*;

public class SDAKuisSeninC {
	public static void main(String[] args) throws IOException {
		
		BufferedReader scan;

		if (args.length == 0)
			scan = new BufferedReader(new InputStreamReader(System.in));
		else
			scan = new BufferedReader(new FileReader(new File(args[0])));
			
		try {
			int jumlahMasukan = Integer.parseInt(scan.readLine());
			DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
			
			while (jumlahMasukan-- > 0) {
				String[] pisah = scan.readLine().split(" ");
				
				switch (pisah[0]) {
					case "add":
						int data = Integer.parseInt(pisah[1]);
						dll.insertFirst(data);
						break;
					case "deleteRange":
						int x = Integer.parseInt(pisah[1]);
						int y = Integer.parseInt(pisah[2]);
						dll.deleteRange(x, y);
						break;
				}
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}

/**
 * Kelas DoublyLinkedList untuk merepresentasikan struktur data Doubly Linked List
 * @author Tim Dosen dan Asisten SDA
 *
 * @param <T>
 */
class DoublyLinkedList<T> {
	private ListNode2<T> first;
	private ListNode2<T> last;

	public DoublyLinkedList() {
		first = null;
		last = null;
	}

	public boolean isEmpty() {
		return first == null;
	}
	
	/**
	 * Menghapus elemen list dari x sampai y. 
	 * Jika linked list kosong, cetak baris kosong
	 * Jika x > y, cetak "x > y" (tanpa tanda kutip)
	 * Jika x / y berada di luar indeks list, cetak "x/y out of bounds" (tanpa tanda kutip)
	 * Jika valid, cetak list dengan method display forward
	 * DILARANG menggunakan struktur data dan method dari Java Collections API.
	 * 
	 * @todo IMPLEMENTASIKAN METHOD INI
	 * @param x posisi awal
	 * @param y posisi akhir
	 */
	public void deleteRange(int x, int y) {
		if (this.isEmpty()) {
			System.out.println("");
			return;
		}
		
		if (x > y) {
			System.out.println("x > y");
			return;
		}
		
		int countN = 0;
		ListNode2<T> node = first;
		if (first == last && first.element.equals(last.element))
			countN = 1;
		else {
			while (node != null) {
				countN++;
				node = node.next;
			}
		}

		if (x < 0 || y < 0 || x > countN-1 || y > countN-1) {
			System.out.println("x/y out of bounds");
			return;
		}
		
		int z = y-x;
		node = first;

		for (int i = 0; i < x-1; i++) node = node.next;
		
		ListNode2<T> node2 = node;
		for (int i = 0; i <= z+1-(x == 0 ? 1 : 0); i++) if (node2 != null) node2 = node2.next;

		if (x == 0 && y == countN-1) {
			first = null;
			last = null;
		}
		else if (y == countN-1) {
			last = node;
			if (node != null) node.next = null;
		}
		else if (x == 0) {
			first = node2;
			if (node2 != null) node2.previous = null;
		}
		else if (x == y) {
			if (node != null) node.next = node2;
			if (node2 != null) node2.previous = node;
		} else {
			if (node!= null) node.next = node2;
			if (node2 != null) node2.previous = node;
		}

		System.out.println(displayForward());
	}

	public void insertFirst(T data) // insert at front of list
	{
		ListNode2<T> newLink = new ListNode2<T>(data);

		if (isEmpty())
			last = newLink;
		else
			first.previous = newLink;
		newLink.next = first;
		first = newLink;
	}

	public void insertLast(T data) // insert at end of list
	{
		ListNode2<T> newLink = new ListNode2<T>(data);
		if (isEmpty())
			first = newLink;
		else {
			last.next = newLink;
			newLink.previous = last;
		}
		last = newLink;
	}

	public ListNode2<T> deleteFirst() // delete first link
	{ // (assumes non-empty list)
		ListNode2<T> temp = first;
		if (first.next == null) // if only one item
			last = null; // null <-- last
		else
			first.next.previous = null; // null <-- old next
		first = first.next; // first --> old next
		return temp;
	}

	public ListNode2<T> deleteLast() // delete last link
	{ // (assumes non-empty list)
		ListNode2<T> temp = last;
		if (first.next == null) // if only one item
			first = null; // first --> null
		else
			last.previous.next = null; // old previous --> null
		last = last.previous; // old previous <-- last
		return temp;
	}

	// insert data just after key
	public boolean insertAfter(T key, T data) { // (assumes non-empty list)
		ListNode2<T> current = first; // start at beginning
		while (current.element != key) // until match is found,
		{
			current = current.next; // move to next link
			if (current == null)
				return false; // didn't find it
		}
		ListNode2<T> newLink = new ListNode2<T>(data); // make new link

		if (current == last) // if last link,
		{
			newLink.next = null; // newLink --> null
			last = newLink; // newLink <-- last
		} else // not last link,
		{
			newLink.next = current.next; // newLink --> old next
											// newLink <-- old next
			current.next.previous = newLink;
		}
		newLink.previous = current; // old current <-- newLink
		current.next = newLink; // old current --> newLink
		return true; // found it, did insertion
	}

	public ListNode2<T> deleteKey(T key) // delete item w/ given key
	{ // (assumes non-empty list)
		ListNode2<T> current = first; // start at beginning
		while (current.element != key) // until match is found,
		{
			current = current.next; // move to next link
			if (current == null)
				return null; // didn't find it
		}
		if (current == first) // found it; first item?
			first = current.next; // first --> old next
		else
			// not first
			// old previous --> old next
			current.previous.next = current.next;

		if (current == last) // last item?
			last = current.previous; // old previous <-- last
		else
			// not last
			// old previous <-- old next
			current.next.previous = current.previous;
		return current; // return value
	}

	public String displayForward() {
		String elementNode = "";
		ListNode2<T> current = first;
		while (current != null) {
			elementNode += current.element + " "; // display data
			current = current.next; // move to next link
		}
		return elementNode;
	}

	public String displayBackward() {
		String elementNode = "";
		ListNode2<T> current = last; // start at end
		while (current != null) // until start of list,
		{
			elementNode += current.element + " "; // display data
			current = current.previous; // move to previous link
		}
		return elementNode;
	}
}

/**
 * Kelas ListNode2, representasi Node dalam doubly linked list
 * 
 * @author Tim Dosen dan Asisten SDA
 * @param <T>
 */
class ListNode2<T> {

	T element;
	ListNode2<T> next;
	ListNode2<T> previous;

	ListNode2(T e, ListNode2<T> n, ListNode2<T> p) {
		element = e;
		next = n;
		previous = p;
	}

	ListNode2(T e) {
		this(e, null, null);
	}

	ListNode2() {
		this(null, null, null);
	}
}
