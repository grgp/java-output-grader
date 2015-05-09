//package kp1senin.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Source code KP 1 Senin Soal B
 * Linked List Intersection
 * Nilai : 35
 * 
 * ============================================
 * NAMA : ISI NAMA ANDA
 * NPM : ISI NPM ANDA
 * ============================================
 *
 * Lengkapi method intersection() pada kelas LinkedList saja.
 * DILARANG mengubah implementasi lain pada kode template. 
 * DILARANG menggunakan struktur data dan method dari Java Collections API.
 * Asisten akan memeriksa kode sumber Anda untuk penilaian White Box.
 * @author SG
 */

import java.io.*;

public class SDAKuisSeninB {
	public static void main(String[] args) throws IOException {

		LinkedList<Long> linkedList1 = new LinkedList<Long>();
		LinkedList<Long> linkedList2 = new LinkedList<Long>();
		LinkedList<Long> linkedList3 = new LinkedList<Long>();

		BufferedReader scan;

		if (args.length == 0)
			scan = new BufferedReader(new InputStreamReader(System.in));
		else
			scan = new BufferedReader(new FileReader(new File(args[0])));
		
		try {
			StringTokenizer ll1 = new StringTokenizer(scan.readLine());
			while (ll1.hasMoreTokens()) {
				linkedList1.insertFront(Long.parseLong(ll1.nextToken()));
			}

			StringTokenizer ll2 = new StringTokenizer(scan.readLine());
			while (ll2.hasMoreTokens()) {
				linkedList2.insertFront(Long.parseLong(ll2.nextToken()));
			}

			linkedList3 = linkedList1.intersection(linkedList2);

			System.out.println(linkedList3);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}

/**
 * Kelas LinkedList untuk merepresentasikan struktur data Linked List menggunakan header node
 * @author Tim Dosen dan Asisten SDA
 *
 * @param <T>
 */
class LinkedList<T> {

	ListNode<T> header;

	public LinkedList() {
		header = new ListNode(null);
	}

	/**
	 * Mencari intersection dari linked list kelas ini dengan linked list other
	 * terurut berdasarkan kemunculannya pada linked list ini
	 * DILARANG menggunakan struktur data dan method dari Java Collections API.
	 *
	 * @todo IMPLEMENTASIKAN METHOD INI
	 * @param other linked list yang ingin dicari intersectionnya
	 * @return LinkedList hasil intersectionnya. LinkedList kosong jika tidak ada
	 */
	public LinkedList<T> intersection(LinkedList<T> other) {
		LinkedList<T> retList = new LinkedList<T>();
		LinkedList<T> reverseList = new LinkedList<T>();

		ListNode<T> orig = header.next;
 
		while (orig != null) {
			ListNode<T> othe = other.header.next;
			while (othe != null) {
				if (orig.element.equals(othe.element)) {
					retList.insertFront(othe.element);
					othe = othe.next;
					break;
				}
				othe = othe.next;
			}
			orig = orig.next;
		}

		return retList;

	}

	public boolean isEmpty() {
		return header.next == null;
	}

	public void insertFront(T data) {
		ListNode<T> newNode = new ListNode(data, null);
		newNode.next = header.next;
		header.next = newNode;
	}

	public void delete(T data) {

		ListNode<T> itr = header;

		while (itr.next != null && !itr.next.element.equals(data)) {
			itr = itr.next;
		}

		if (itr.next != null) {
			itr.next = itr.next.next;
		}
	}

	public void delete2(T data) {
		ListNode<T> itr = header.next;
		ListNode<T> prev = header;

		while (itr != null && !itr.element.equals(data)) {
			itr = itr.next;
			prev = prev.next;
		}

		if (itr.element != null) {
			prev.next = prev.next.next;
		}
	}

	public int getSize() {

		ListNode<T> current = header;
		int size = 0;
		while (current.next != null) {
			size++;
			current = current.next;
		}
		return size;
	}

	public void makeEmpty() {
		header.next = null;
	}

	public String toString() {
		ListNode<T> current = header.next;
		String elementNode = "";

		while (current != null) {
			elementNode += current.element + " ";
			current = current.next;
		}
		return elementNode;
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
