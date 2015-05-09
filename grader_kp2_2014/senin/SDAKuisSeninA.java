//package kp1senin.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;

/**
 * Source code KP 1 Senin Soal A
 * Balanced Symbol
 * Nilai : 35
 *
 * ============================================
 * NAMA : ISI NAMA ANDA
 * NPM : ISI NPM ANDA
 * ============================================
 *
 * Lengkapi method isValid() pada kelas SDA13141KPA saja.
 * DILARANG mengubah implementasi lain pada kode template. 
 * DILARANG menggunakan struktur data dan method dari Java Collections API.
 * Asisten akan memeriksa kode sumber Anda untuk penilaian White Box.
 * @author CWW
 */

import java.io.*;

public class SDAKuisSeninA {
	public static void main(String[] args) throws IOException {
		
		BufferedReader scan;

		if (args.length == 0)
			scan = new BufferedReader(new InputStreamReader(System.in));
		else
			scan = new BufferedReader(new FileReader(new File(args[0])));

		try {
			int input = Integer.parseInt(scan.readLine());

			for (int i = 1; i <= input; i++) {
				System.out.println("Case #"
						+ i
						+ ": "
						+ (isValid(scan.readLine().toCharArray()) ? "Valid"
								: "Tidak Valid"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method untuk mengecek apakah pasangan tanda kurung valid atau tidak
	 * DILARANG menggunakan struktur data dan method dari Java Collections API.
	 *
	 * @todo IMPLEMENTASIKAN METHOD INI
	 * @param par array of char dari pasangan tanda kurung
	 * @return true jika valid, false jika tidak valid
	 */
	public static boolean isValid(char[] par) {
		MyArrayStack<Character> mStack = new MyArrayStack<Character>();

		for (Character c : par) {
			try {
				if (c.equals(']')) {
					if (mStack.top().equals('['))
						mStack.pop();
					else
						return false;
				}
				else if (c.equals('}')) {
					if (mStack.top().equals('{'))
						mStack.pop();
					else
						return false;
				}
				else if (c.equals(')')) {
					if (mStack.top().equals('('))
						mStack.pop();
					else
						return false;
				}
				else {
					mStack.push(c);
				}
			} catch (EmptyStackException ex) {
				return false;
			}
		}

		return (mStack.isEmpty() ? true : false); 
	}

}

/**
 * Kelas MyArrayStack untuk merepresentasikan struktur data Stack menggunakan array
 * @author Tim Dosen dan Asisten SDA
 *
 * @param <T>
 */
class MyArrayStack<T> {
	private T[] array;
	public int tos;
	private static final int DEFAULT_CAPACITY = 2;

	public MyArrayStack() {
		array = (T[]) new Object[DEFAULT_CAPACITY];
		tos = -1;
	}

	public boolean isEmpty() {
		return tos == -1;
	}

	public void makeEmpty() {
		tos = -1;
	}

	public T top() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();
		return array[tos];
	}

	public void pop() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();
		tos--;
	}

	public T topAndPop() throws EmptyStackException {
		if (isEmpty())
			throw new EmptyStackException();
		return array[tos--];
	}

	public void push(T x) {
		if (tos + 1 == array.length)
			doubleArray(array.length);
		array[++tos] = x;
	}

	private void doubleArray(int capacity) {
		T[] tmp = (T[]) new Object[2 * capacity];
		for (int i = 0; i <= tos; i++)
			tmp[i] = array[i];
		// System.arraycopy(array, 0, tmp, 0, capacity-1);
		array = tmp;
	}

	public String print() {
		String elementNode = "";
		int i = 0;
		while (i < tos + 1) {
			elementNode += "" + array[i] + "";
			i++;
		}
		return elementNode;
	}

}