import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Saving {

	Saving() { }

	public void showDialog(String string) {
		System.out.println("-------------------------");
		System.out.println(string);
		System.out.println("-------------------------");
	}

	public void saveAccountsInfo(Globals globals, List<Clients> clients) {
		try{
		FileOutputStream fileOut = new FileOutputStream(globals.filename);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		for(int i=0; i < clients.size(); i++) {
			out.writeObject(clients.get(i));
		}
		out.close();
		fileOut.close();
		}
		catch(IOException e) {
			showDialog("There was no file to load");
		}
	}




}
