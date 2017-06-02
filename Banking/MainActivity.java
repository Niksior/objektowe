import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class MainActivity {

	List<Clients> clients = new ArrayList<Clients>();
	Globals globals = new Globals();
	Scanners scanners = new Scanners();
	Saving saving = new Saving();
	Searching searching = new Searching();

	MainActivity() {}

	public static void main(String[] args) {
		MainActivity bank = new MainActivity();
		boolean condition = true;

		while (condition) {
			condition = bank.showMenu();
		}
	}

	public void vista() {
		showDialog("Do you really want to make this action? y/n (default is NO)");
		String action = scanners.stringScanner();
		if(!action.equals("y")) {
			showMenu();
		}
	}

	public boolean menuAction(int menuValue) {
		boolean condition = true;
		switch(menuValue) {
			case 1: {
				createAccount();
				break;
			}
			case 2: {
				deleteAccount();
				break;
			}
			case 3: {
				payment();
				break;
			}
			case 4: {
				withdraw();
				break;
			}
			case 5: {
				moneyTransfer();
				break;
			}
			case 6: {
				getAllAccountsInfo();
				break;
			}
			case 7: {
				getAccountInfo();
				break;
			}
			case 8: {
				vista();
				condition = false;
				break;
			}
			case 9: {
				loadAccountsInfo();
				break;
			}
			default: {
				showDialog("Wrong number");
			}
		}
		return condition;
	}

	public boolean showMenu() {
		System.out.println("-------------------------");
		System.out.println("1 - Create account");
		System.out.println("2 - Delete account");
		System.out.println("3 - Make a payment");
		System.out.println("4 - Make a whitdrawal");
		System.out.println("5 - Transfer a money");
		System.out.println("6 - Show info about all accounts");
		System.out.println("7 - Show info about filtered accounts");
		System.out.println("8 - Exit a program");
		System.out.println("9 - Load accounts from file");
		showDialog("Make a decision");
		return menuAction(scanners.intScanner());
	}

	public void showDialog(String string) {
		System.out.println("-------------------------");
		System.out.println(string);
		System.out.println("-------------------------");
	}

	public void getAccountInfo() {
		vista();
		int choosenOption;
		choosenOption = chooseParameter();
		getAccountByParameter(choosenOption);
		showDialog("End of accounts");
	}

	public void getAccountByParameter(int choosenOption) {
		switch(choosenOption) {
		case 1: {
			searching.searchByName(globals, clients);
			break;
		}
		case 2: {
			searching.searchBySurname(globals, clients);
			break;
		}
		case 3: {
			searching.searchByPESEL(globals, clients);
			break;
		}
		case 4: {
			searching.searchByAdress(globals, clients);
			break;
		}
		case 5: {
			searching.searchByNumber(globals, clients);
			break;
		}
		}
	}

	public int chooseParameter() {
		int tmp = 0;
		do {
			showDialog("Select a parameter");
			System.out.println("1 - Name");
			System.out.println("2 - Surname");
			System.out.println("3 - PESEL");
			System.out.println("4 - Adress");
			System.out.println("5 - Client number");
			showDialog("Make a decision");
			tmp = scanners.intScanner();
		} while (tmp < 1 || tmp > 5);
		return tmp;
	}

	public void showClientInfo(int i) {
			System.out.println("-------------------------");
			System.out.println("Number:");
			System.out.println(clients.get(i).clientNumber);
			System.out.println("Name:");
			System.out.println(clients.get(i).clientName);
			System.out.println("Surname:");
			System.out.println(clients.get(i).clientSurname);
			System.out.println("PESEL:");
			System.out.println(clients.get(i).clientPesel);
			System.out.println("Adress:");
			System.out.println(clients.get(i).clientAdress);
			System.out.println("Money:");
			System.out.println(clients.get(i).clientResources);
			System.out.println("-------------------------");
	}

	public void getAllAccountsInfo() {
		vista();
		int i;
		for(i=0; i < clients.size(); i++) {
			showClientInfo(i);
		}
		if(clients.size() == 0) {
			showDialog("There are no records");
		}
	}

	public void moneyTransfer() {
		vista();
		int sourceClient, destinationClient;
		showDialog("Give me a number of source client");
		int tmp = scanners.intScanner();
		sourceClient = searching.searchByClinetNumber(globals, clients, tmp);
		showDialog("Give me a number of destination client");
		int tmp2 = scanners.intScanner();
		destinationClient = searching.searchByClinetNumber(globals, clients, tmp2);
		if(sourceClient == -1 || destinationClient == -1) {
			showDialog("Client don't exist");
			return;
		}
		showDialog("Give me a number of money to transfer");
		double moneyNo = scanners.doubleScanner();
		if(moneyNo >= 0 ) {
			if(moneyNo <= clients.get(sourceClient).clientResources) {
				clients.get(sourceClient).clientResources -= moneyNo;
				clients.get(destinationClient).clientResources += moneyNo;
				showDialog("Success!");
				saving.saveAccountsInfo(globals, clients);
			}
			else {
				showDialog("Too less money");
			}
		}
		else {
			showDialog("It must be positive number");
		}
	}

	public void withdraw() {
		vista();
		showDialog("Give me a number of client");
		int sourceClient;
		int tmp = scanners.intScanner();
		sourceClient = searching.searchByClinetNumber(globals, clients, tmp);
		if(sourceClient == -1) {
			showDialog("Client don't exist");
			return;
		}
		showDialog("Give me a number of money to witdraw");
		double moneyNo = scanners.doubleScanner();
		if(moneyNo >= 0 ) {
			if(moneyNo <= clients.get(sourceClient).clientResources) {
				clients.get(sourceClient).clientResources -= moneyNo;
				showDialog("Success!");
				saving.saveAccountsInfo(globals, clients);
			}
			else {
				showDialog("Too less money");
			}
		}
		else {
			showDialog("It must be positive number");
		}
	}

	public void payment() {
		vista();
		showDialog("Give me a number of client");
		int sourceClient;
		int tmp = scanners.intScanner();
		sourceClient = searching.searchByClinetNumber(globals, clients, tmp);
		if(sourceClient == -1) {
			showDialog("Client don't exist");
			return;
		}
		showDialog("Give me a number of money to make a payment");
		double moneyNo = scanners.doubleScanner();
		if(moneyNo > 0) {
			clients.get(sourceClient).clientResources += moneyNo;
			showDialog("Success!");
			saving.saveAccountsInfo(globals, clients);
		}
		else {
				showDialog("Money must be higer than 0");
		}
	}

	public void deleteAccount() {
		vista();
		showDialog("Give me a client number which you want delete");
		int tmp = scanners.intScanner();
		tmp = searching.searchByClinetNumber(globals, clients, tmp);
		if(tmp == -1) {
			showDialog("Client does not exist");
		}
		else {
			clients.remove(tmp);
			saving.saveAccountsInfo(globals, clients);
		}
	}

	public void createAccount() {
		vista();
		creatingAccountMenu();
		int number = globals.maxClientNumber;
		double pesel2 = scanners.doubleScanner();
		String name = scanners.stringScanner();
		String surname = scanners.stringScanner();
		String adress = scanners.stringScanner();
		double resources = scanners.doubleScanner();
		long pesel = new Double(pesel2).longValue();
		Clients c1 = new Clients(number, pesel, name, surname, adress, resources);
		clients.add(c1);
		globals.maxClientNumber++;
		saving.saveAccountsInfo(globals, clients);
	}

	public void creatingAccountMenu() {
		showDialog("Give me all info, press ENTER after each");
		System.out.println("-------------------------");
		System.out.println("PESEL");
		System.out.println("Name");
		System.out.println("Surname");
		System.out.println("Adress");
		System.out.println("Strating resources");
		System.out.println("-------------------------");
	}

	public void loadAccountsInfo() {
      try {
		 vista();
         FileInputStream fileIn = new FileInputStream(globals.filename);
         ObjectInputStream in = new ObjectInputStream(fileIn);
		 while(fileIn.available() > 0) {
			 Clients c1 = new Clients();
			 c1 = (Clients) in.readObject();
			 clients.add(c1);
		 }
         in.close();
         fileIn.close();
				 globals.maxClientNumber = clients.get(clients.size()-1).clientNumber + 1;
		 showDialog("Loaded");
      }catch(IOException i) {
         showDialog("There was no file to load");
         return;
      }catch(ClassNotFoundException c) {
         System.out.println("Client class not found");
         c.printStackTrace();
         return;
      }
	}

}
