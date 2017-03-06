import acm.program.*;

/** takes a numeric string and returns a string with commas for thousands*/
public class AddCommas extends ConsoleProgram{
	public void run() { 
		 while (true) { 
		 String digits = readLine("Enter a numeric string: "); 
		 if (digits.length() == 0) break; 
		 println(addCommasToNumericString(digits)); 
		 } 
		}

	private String addCommasToNumericString(String digits){
		String numWithComma = "";
		for(int i = digits.length() - 1; i >= 0; i--){
			if (Character.isDigit(digits.charAt(i))){
				//add characters to the end of the string that you are constructing.
				numWithComma =  digits.charAt(i) + numWithComma;
				//see if you hava added 3 digits to the last part of the string.
				if (((digits.length() - i) % 3) == 0 && i != 0){
					numWithComma =  ',' + numWithComma;
				}							
			}
			else{
				return "Non numeric charactor entered.";
			}
		}
		return numWithComma;
	}
}
