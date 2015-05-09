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
				Stack<Long> stc = new MyArrayStack<>();	// Create operand stack
				char[] inpArr = input.toCharArray();
				int len = inpArr.length;
				for (int i = 0; i < len; i++) {
					if (Character.isDigit(inpArr[i])) {		// Character is a number, push to stack
						stc.push((long) (inpArr[i] - 48));	// Convert to digit from char, then cast to long
					}
					else if (isOperand(inpArr[i])){			// Character is an operator, pop first 2 digits and evaluate
						long a = stc.pop();
						long b = stc.pop();
						stc.push(evaluate(b,a,inpArr[i]));	// b <operator> a, not a <operator> b. Last In First Out!
															// Evaluate value and push it back to the operand stack
					}
					else {
						throw new OperatorUndefined();		// Neither number nor valid operator. Throw exception.
					}
				}
				long ans = stc.pop();
				if (stc.isEmpty()) {		 // Stack must be empty for the expression to be valid. The only
					System.out.println(ans); // value contained before pop() should be the final answer.
				}
				else {		// Stack is not empty after pop().
					System.out.println("Ekspresi Invalid");
				}
			// Any kind of invalid operation exceptions are caught here, except
			// if the stack is not empty at the end of expression. (which is
			// already handled within the try{} block)
			} catch(OperatorUndefined oex){
				System.out.println("Ekspresi Invalid");
			} catch(EmptyStackException ex){
				System.out.println("Ekspresi Invalid");		//Stack is empty
			} catch(ArithmeticException aex){
				System.out.println("Ekspresi Invalid");		// Division by 0. Happens if the operator is "%"
			}												// And the second operand is 0.
		}
	}
	private static boolean isOperand(char c){
		return (c == '-') || (c == '+') || (c == '*') || (c == '%');	// Only +, -, *, and % are valid
	}

	private static long evaluate(long operand1, long operand2, char operator) throws OperatorUndefined{
		switch (operator) {
			case '+' : return operand1 + operand2;		// Addition
			case '-' : return operand1 - operand2;		// Subtraction
			case '*' : return operand1 * operand2;		// Multiplication
			case '%' : return operand1 % operand2;		// Modulo
			default : throw new OperatorUndefined();	// Unknown, throw exception
		}												// Default is obsolete, undefined operators are
	}													// already handled during input.
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
