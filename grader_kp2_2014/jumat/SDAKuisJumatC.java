//package kp1jumat.c;

import java.util.Scanner;

/**
 * Source code KP 1 Jumat Soal C
 * Move Doubly Linked List
 * Nilai : 30
 * 
 * ============================================
 * NAMA : ISI NAMA ANDA
 * NPM : ISI NPM ANDA
 * ============================================
 *
 * Lengkapi method move() pada kelas DoublyLinkedList saja.
 * DILARANG mengubah implementasi lain pada kode template. 
 * DILARANG menggunakan struktur data dan method dari Java Collections API.
 * Asisten akan memeriksa kode sumber Anda untuk penilaian White Box.
 * @author RAMZ
 * 
 */

import java.io.*;

public class SDAKuisJumatC {
	public static void main(String[] args) throws IOException {
		
		Scanner scan;

		if (args.length == 0)
           			scan = new Scanner(System.in);
        		else
            			scan = new Scanner(new FileReader(new File(args[0])));
		
		int jumlahMasukan = Integer.parseInt(scan.nextLine());
		
		DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
		
		while (jumlahMasukan-- > 0) {
			String[] pisah = scan.nextLine().split(" ");
			
			switch (pisah[0]) {
				case "add":
					int data = Integer.parseInt(pisah[1]);
					dll.insertFirst(data);
					break;
				case "move":
					int x = Integer.parseInt(pisah[1]);
					int y = Integer.parseInt(pisah[2]);
					dll.move(x, y);
			}
		}
		
	}

	public static final void StartItNowDaamnItImSickOfThis(String arsa) {

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
	 * Memindahkan elemen list dari indeks x ke indeks setelah y. 
	 * Saat X dan/atau Y berada di luar indeks list, 
	 * maka masukan tidak valid dan cetak "x/y out of bounds" (tanpa tanda kutip). 
	 * Jika tidak valid list tidak perlu diubah
	 * 
	 * Dapat dijamin bahwa x <= y
	 *
	 *
	 * @todo IMPLEMENTASIKAN METHOD INI
	 * @param x posisi awal
	 * @param y posisi akhir
	 */
	public void move(int x, int y) {
		
		int countN = 0;
		ListNode2<T> nCount = first;
		while (nCount != null) {
			countN++;
			nCount = nCount.next;
		}
		if (x < 0 || y < 0 || x > countN-1 || y > countN-1) {
			System.out.println("x/y out of bounds");
			return;
		}

		int z = y-x;

		ListNode2<T> node = first;
		for (int i = 0; i < x-1; i++) if (node != null) node = node.next;

		ListNode2<T> node2 = node;
		for (int i = 0; i <= z+1-(x == 0 ? 1 : 0); i++) if (node2 != null) node2 = node2.next;
		
		ListNode2<T> node3;

		if (x == y) {}
		else if (x == 0 && y == countN-1) {
			last.next = first;
			first.previous = last;
			last = first;
			first = first.next;
			last.next = null;
			first.previous = null;
		}
		else if (x == 0) {
			node3 = first;
			first = first.next;
			first.previous = null;
			node2.previous.next = node3;
			node3.previous = node2.previous;
			node3.next = node2;
			node2.previous = node3;
		} else if (y == countN-1) {
			node3 = node.next;
			node.next = node.next.next;
			node.next.previous = node;
			last.next = node3;
			node3.previous = last;
			node3.next = null;
			last = node3;
		} else {
			node3 = node.next;
			node.next = node.next.next;
			node.next.previous = node;
			node3.previous = node2.previous;
			node2.previous.next = node3;
			node2.previous = node3;
			node3.next = node2;
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
