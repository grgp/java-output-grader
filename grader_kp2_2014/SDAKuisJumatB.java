//package kp1jumat.b;
import java.util.Scanner;

/**
 * Source code KP 1 Jumat Soal B
 * Linked List Merge Operation
 * Nilai : 40
 * 
 * ============================================
 * NAMA : ISI NAMA ANDA
 * NPM : ISI NPM ANDA
 * ============================================
 *
 * Lengkapi method merge() pada kelas LinkedList saja.
 * DILARANG mengubah implementasi lain pada kode template. 
 * DILARANG memanggil Collections.sort dan menggunakan struktur data dan method dari Java Collections API.
 * Asisten akan memeriksa kode sumber Anda untuk penilaian White Box.
 * @author CWW
 * 
 */

import java.io.*;

public class SDAKuisJumatB {
    public static void main(String args[]) throws IOException {
        
        Scanner in;

        if (args.length == 0)
            in = new Scanner(System.in);
        else
            in = new Scanner(new FileReader(new File(args[0])));

        LinkedList<Integer> lista = new LinkedList<Integer>();
        LinkedList<Integer> listb = new LinkedList<Integer>();
        
        String str = in.nextLine();
        String strarr[] = str.split(" ");
        
        for ( int i = strarr.length-1; i >= 0; i-- )
            lista.insertFront(Integer.parseInt(strarr[i]));
        
        str = in.nextLine();
        strarr = str.split(" ");
        
        for ( int i = strarr.length-1; i >= 0; i-- )
            listb.insertFront(Integer.parseInt(strarr[i]));
        
        LinkedList<Integer> result = lista.merge(listb);
        LinkedList<Integer> resrev = new LinkedList<Integer>();
        
        ListNode<Integer> head = result.header.next;
        while ( head != null ) {
            resrev.insertFront(head.element);
            head = head.next;
        }
        System.out.println(resrev);
    }
}

/**
 * Kelas LinkedList untuk merepresentasikan struktur data Linked List menggunakan header node
 * @author Tim Dosen dan Asisten SDA
 *
 * @param <T>
 */
class LinkedList<T extends Comparable<T>> {
    ListNode<T> header;

    public LinkedList() {
        header = new ListNode(null);
    }
    
	/**
	 * Merge linked list ini dengan linked list partner 
	 * DILARANG menggunakan struktur data dan method dari Java Collections API.
	 *
	 * HINT
	 * Perhatikan bahwa pada template, kelas LinkedList hanya memiliki method insertFront(), 
	 * hal tersebut mengakibatkan list A dan B pada awalnya akan terurut terbalik. 
	 * Oleh karena itu, kalian harus mengimplementasikan method merge pada list yang terurut terbalik. 
	 * Kalian tidak perlu mengurutkannya lagi saat mencetak output karena sudah tersedia di template.
	 *
	 * @todo IMPLEMENTASIKAN METHOD INI
	 * @param partner linked list yang ingin dimerge yang terurut terbalik
	 * @return LinkedList hasil merge yang terurut terbalik
	 */
    public LinkedList<T> merge(LinkedList<T> partner) {
	LinkedList<T> mergor = new LinkedList<T>();

	ListNode<T> node = header.next;
	ListNode<T> node2 = partner.header.next;
	ListNode<T> current = node;

	while (current != null) {
		if (node.compareTo(node2) <= 0) {
			mergor.insertFront(node.element);
			node = node.next;
			current = node;
		} else {
			mergor.insertFront(node2.element);
			node2 = node2.next;
			current = node2;
		}
	}

	while (node != null || node2 != null) {
		if (node == null) {
			mergor.insertFront(node2.element);
			node2 = node2.next;
		}
		else if (node2 == null) {
			mergor.insertFront(node.element);
			node = node.next;
		}
	}

	/*

	if (this.getSize() > partner.getSize()) {
	while (node != null && node2.element != null) {
			while (node2 != null && node2.element != null) {
				if (tnode.compareTo(node2) <= 0) {
					mergor.insertFront(node.element);
					node = node.next;
				}
				else {
					mergor.insertFront(node2.element);
					node2 = node2.next;
				}
			}
			mergor.insertFront(node.element);
			node = node.next;
		} 
	} else {
		while (node2 != null) {
			while (node != null) {
				if (node.compareTo(node2) <= 0) {
					mergor.insertFront(node.element);
					node = node.next;
				}
				else {
					mergor.insertFront(node2.element);
					node2 = node2.next;
				}
			}
			mergor.insertFront(node2.element);
			node2 = node2.next;
		}
	}

	*/

	return mergor;

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

        if(itr.next != null){
            itr.next = itr.next.next;
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

        while (current!= null) {
			elementNode += current.element +" ";
            current = current.next;
        }
        return elementNode;
    }
    
    
}

/**
 * Kelas ListNode, representasi Node dalam linked list
 * 
 * @author Tim Dosen dan Asisten SDA
 * @param <T>
 */
class ListNode<T extends Comparable<T>> implements Comparable<ListNode<T>>{
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
    
    public int compareTo(ListNode<T> e){
        return this.element.compareTo(e.element);
    }
}
