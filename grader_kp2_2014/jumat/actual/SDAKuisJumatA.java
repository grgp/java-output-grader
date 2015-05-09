//package kp1jumat.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.EmptyStackException;

/**
 * Source code KP 1 Jumat Soal A
 * Dynamic Shrinking Queue
 * Nilai : 30
 * 
 * ============================================
 * NAMA : ISI NAMA ANDA
 * NPM : ISI NPM ANDA
 * ============================================
 *
 * Lengkapi method dequeue() pada kelas MyArrayQueue saja.
 * DILARANG mengubah implementasi lain pada kode template. 
 * DILARANG menggunakan struktur data dan method dari Java Collections API.
 * Asisten akan memeriksa kode sumber Anda untuk penilaian White Box.
 * @author SG
 * 
 */

import java.io.*;

public class SDAKuisJumatA {

	public static void main(String[] args) throws IOException {

		MyArrayQueue<Long> queue = new MyArrayQueue<>();

		BufferedReader scan;

		if (args.length == 0)
			scan = new BufferedReader(new InputStreamReader(System.in));
		else
			scan = new BufferedReader(new FileReader(new File(args[0])));
		
		StringTokenizer token = new StringTokenizer(scan.readLine());
		String number = token.nextToken();
		int input = Integer.parseInt(number);

		for (int x = 0; x < input; x++) {
			token = new StringTokenizer(scan.readLine());
			String action = token.nextToken();

			switch (action) {
			case "size":
				System.out.println(queue.getSize());
				break;
			case "enqueue":
				long num = Long.parseLong(token.nextToken());
				queue.enqueue(num);
				break;
			case "dequeue":
				queue.dequeue();
				break;
			}
		}

	}
}

/**
 * Kelas MyArrayQueue untuk merepresentasikan struktur data Queue menggunakan array
 * @author Tim Dosen dan Asisten SDA
 *
 * @param <T>
 */
class MyArrayQueue<T> {

	public T[] array;
	private int front;
	private int back;
	private static final int DEFAULT_CAPACITY = 2;
	public int size;

	public MyArrayQueue() {
		array = (T[]) new Object[DEFAULT_CAPACITY];
		makeEmpty();
	}
	
	/**
	 * Method untuk dequeue dengan teknik Dynamic Shrinking
	 *
	 * @todo IMPLEMENTASIKAN METHOD INI
	 * 
	 * @return kembalikan front dari queue
	 */
	public T dequeue() {
		T toReturn = getFront();
		
		size--;
		front = increment(front);

		if (size <= array.length/4) {
			T[] tmp = (T[]) new Object[array.length/2];
			for (int i = 0; i < size; i++, front = increment(front)) {
				tmp[i] = array[front];
			}

			array = tmp;
			front = 0;
			back = size;

		}
		try {
			return getFront();
		}
		catch (EmptyStackException ex) {
			return null;
		}
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void makeEmpty() {
		size = 0;
		front = 0;
		back = 0;
	}

	public T getFront() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return array[front];
	}

	public void enqueue(T x) {
		if (size == array.length) {
			doubleQueue(array.length);
		}
		array[back] = x;
		back = increment(back);
		size++;

	}

	public int increment(int x) {
		if (++x == array.length) {
			x = 0;
		}
		return x;
	}

	private void doubleQueue(int cap) {
		T[] tmp = (T[]) new Object[2 * cap];
		for (int i = 0; i < size; i++, front = increment(front)) {
			tmp[i] = array[front];
		}
		array = tmp;
		front = 0;
		back = size;
	}

	public String print() {
		String elementNode = "";
		int i = 0;
		while (i < array.length && array[i] != null) {
			elementNode += " [" + array[i] + "] ";
			i++;
		}
		return elementNode;
	}

	public int getSize() {
		return array.length;
	}

}
