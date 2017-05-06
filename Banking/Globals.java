import java.nio.file.*;

public class Globals {
	Path filename;
	int numberOfAccounts;
	
	public Globals() {
		filename = Paths.get("./accounts.txt");
		numberOfAccounts = 0;
	}
}

