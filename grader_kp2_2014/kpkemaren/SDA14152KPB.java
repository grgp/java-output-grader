import java.util.EmptyStackException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.*;

public class SDA14152KPB {
	//Pesan Error
	private static final String ERROR_MESSAGE = "Ekspresi Invalid";		
	//Nilai char dari karakter nol '0'
	private static final char CHAR_ZERO = '0';
	
	public static void main(String[] args) throws IOException {		

		BufferedReader reader;
		if (args.length != 0) 
			reader = new BufferedReader(new FileReader(new File(args[0])));
		else 
			reader = new BufferedReader(new InputStreamReader(System.in));
		
		//Variable untuk menyimpan ekspresi yang diberikan
		String input;
		//Looping sampai EOF
		while((input=reader.readLine())!=null){
			try {
				//TODO

			} catch(/*TODO*/){
				//TODO
			} catch(EmptyStackException ex){
				//TODO
			} catch(/*TODO hint, apa yang terjadi ketika sebuah bilangan dimodulo dengan angka 0 */){
				//TODO
			}
		}
	}
	private static boolean isOperand(char c){
		//TODO
		return false;
	}

	private static long evaluate(long operand1, long operand2, char operator) throws OperatorUndefined{
		//TODO
		return 0;
	}
}

interface Stack<T>{
	T pop();
	T top();
	void push(T data);
	boolean isEmpty();
	void makeEmpty();
}

class MyArrayStack<T> implements Stack<T>
{
    private T [] array;
    private int tos;
    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayStack() 
	{
		this(DEFAULT_CAPACITY);
	}
	public MyArrayStack(int capacity) 
	{
		array = (T []) new Object [capacity];
		tos = -1;
	}
    public boolean isEmpty() 
    {
		return tos==-1;
	}
	public void makeEmpty() 
    {
		tos = -1;
	}
	public T top() 
    {
		if (isEmpty())
			throw new EmptyStackException();
		return array[tos];
	}
	public T pop() 
    {
		if (isEmpty())
			throw new EmptyStackException();
		return array[tos--];
	}
	public void push(T x) 
	{
		if (tos+1 == array.length)
			doubleArray(array.length);
		array [++tos] = x;
	}
    private void doubleArray(int capacity) 
	{
		T[] tmp = (T[]) new Object[2*capacity];
        	System.arraycopy(array, 0, tmp, 0, capacity);
        	array = tmp;
	}
	public String toString() 
	{
        String elementNode = "";
		int i =0;
        while (i<array.length&&array[i]!=null) 
		{
			elementNode += "[" + array[i]+"] ";
			i++;
		}
        return elementNode;
    }
}

class OperatorUndefined extends Exception {
    public OperatorUndefined() {

    }
}
