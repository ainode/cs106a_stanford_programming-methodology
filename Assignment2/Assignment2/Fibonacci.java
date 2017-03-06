import acm.program.ConsoleProgram;


public class Fibonacci extends ConsoleProgram{
	int fibPrevious = 1;
	int fib2Previous = 0;
	int result = 0;
	public void run(){
		final int MAXIMUM_NUMBER  = 10000;
		while(true){
			println(result);
			result = fib();
			if(result >= MAXIMUM_NUMBER) break;	
			}
	}
	
	public int fib(){
	    result = fibPrevious + fib2Previous;
		fib2Previous = fibPrevious;
		fibPrevious = result;
		return (result);
	}				
}
