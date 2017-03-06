import acm.program.ConsoleProgram;


public class Test extends ConsoleProgram{
	public void run(){ 
		String line = "Abbie 431 552 742 924 0 0 0 0 752 644 601";
		NameSurferEntry entry = new NameSurferEntry(line);
		println(entry.getName() + " rank at third decade: " + entry.getRank(3) + "to string :" + entry.toString());
	}
}
