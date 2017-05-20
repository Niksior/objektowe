import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Saving {

	public Saving() { }

	public void showDialog(String string) {
		System.out.println("-------------------------");
		System.out.println(string);
		System.out.println("-------------------------");
	}

	public void saveAccountsInfo(Globals globals, Clients clients[]) {
		try{
			saveToFile(globals, clients);
			saveAccountsInfo2(globals, clients);
		}
		catch(FileNotFoundException e) {
			showDialog("There was no file to load");
		}
	}

	public void saveToFile(Globals globals, Clients clients[]) throws FileNotFoundException {
		PrintWriter save = new PrintWriter(globals.fileWithNumberOfAccounts);
		save.println(globals.numberOfAccounts);
		save.println(globals.maxClientNumber);
		save.close();
	}

	public void saveAccountsInfo2(Globals globals, Clients clients[]) {
		try{
		FileOutputStream fileOut = new FileOutputStream(globals.filename);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		for(int i=0; i < globals.numberOfAccounts; i++) {
			out.writeObject(clients[i]);
		}
		out.close();
		fileOut.close();
		}
		catch(IOException e) {
			showDialog("There was no file to load");
		}
	}




}
