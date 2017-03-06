import acm.program.*;

public class RemoveOccurances extends ConsoleProgram{

	public void run(){
		while(true){
			String line = readLine("Enter a line: ");
			String charStr = readLine("Enter a charactor: ");
			char character = charStr.charAt(0);
			if(line.length() == 0) break;
			println(removeAllOccurances(line, character));
		}
	}
	
	public String removeAllOccurances(String line, char character){
		int length = line.length();
		String result = "";
		for(int i = 0; i < length; i++ ){
			if(line.charAt(i) != character){
				result += line.charAt(i);
			}
		}
		return result;
	}
}
