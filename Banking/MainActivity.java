import java.nio.file.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainActivity {

	Clients clients[];
	Globals globals;
	
	public static void main(String[] args) {
		MainActivity bank = new MainActivity();
		boolean condition = true;
		
		while (condition) {
			condition = bank.showMenu(bank);
		}
	}
	
	public void vista() {
		
	}
	
	public boolean menuAction(int menuValue, MainActivity bank) {
		boolean condition = true;
		switch(menuValue) {
			case 1: {
				bank.createAccount();
				break;
			}
			case 2: { 
				bank.deleteAccount();
				break;
			}
			case 3: { 
				bank.payment();
				break;
			}
			case 4: {
				bank.withdraw();
				break;
			}
			case 5: {
				bank.moneyTransfer();
				break;
			}
			case 6: {
				bank.getAllAccountsInfo();
				break;
			}
			case 7: {
				bank.getAccountInfo();
				break;
			}
			case 8: {
				condition = false;
				break;
			}
			default: {
				showDialog("Wrong number");
			}
		}
		return condition;
	}

	public boolean showMenu(MainActivity bank) {
		System.out.println("-------------------------");
		System.out.println("1 - Create account");
		System.out.println("2 - Delete account");
		System.out.println("3 - Make a payment");
		System.out.println("4 - Make a whitdrawal");
		System.out.println("5 - Transfer a money");
		System.out.println("6 - Show info about all accounts");
		System.out.println("7 - Show info about filtered accounts");
		System.out.println("8 - Exit a program");
		showDialog("Make a decision");
		return menuAction(intScanner(), bank);
	}

	public int intScanner() {
		try {
			Scanner scan = new Scanner(System.in);
			int scannedValue;
			scannedValue = scan.nextInt();
			scan.close();
			return scannedValue;
		}
		catch(InputMismatchException exception) {
			showDialog("It should be a number");
			return 0;
		}
	}
	
	public double doubleScanner() {
		try {
			Scanner scan = new Scanner(System.in);
			double scannedValue;
			scannedValue = scan.nextDouble();
			scan.close();
			return scannedValue;
		}
		catch(InputMismatchException exception) {
			showDialog("It should be a number");
			return 0;
		}
	}

	public String stringScanner() {
		try {
			Scanner scan = new Scanner(System.in);
			String scannedValue;
			scannedValue = scan.nextLine();
			scan.close();
			return scannedValue;
		}
		catch(NoSuchElementException exception) {
			showDialog("Why empty line?");
			return null;
		}
	}
	
	

	public void showDialog(String string) {
		System.out.println("-------------------------");
		System.out.println(string);
		System.out.println("-------------------------");
	}
	
	public MainActivity() {
		Clients clients[] = new Clients[500];
		Globals globals = new Globals();
	}

	public void getAccountInfo() {
		int choosenOption;
		choosenOption = chooseParameter();
		getAccountByParameter(choosenOption);
		showDialog("End of accounts");
	}

	public void getAccountByParameter(int choosenOption) {		
		switch(choosenOption) {
		case 1: {
			searchByName();
			break;
		}
		case 2: {
			searchBySurname();
			break;
		}
		case 3: {
			searchByPESEL();
			break;
		}
		case 4: {
			searchByAdress();
			break;
		}
		case 5: {
			searchByClinetNumber();
			break;
		}
		}
	}

	public void searchByClinetNumber() {
		showDialog("Give me a client number");
		int givenNumber = intScanner();
		if(givenNumber < 0 || givenNumber > globals.numberOfAccounts) {
			showDialog("There are no records");
		}
		else {
			showClientInfo(givenNumber);
		}
	}

	public void searchByAdress() {
		showDialog("Give me a client adress");
		String tmpAdress = stringScanner();
		boolean flag = true;
		int i;
		for(i = 0; i < globals.numberOfAccounts; i++) {
			if(tmpAdress.equals(clients[i].clientAdress)) {
				flag = false;
				showClientInfo(i);
			}
		}
		if(flag) {
			showDialog("There are no records");
		}
	}

	public void searchByPESEL() {
		showDialog("Give me a client PESEL");
		int givenNumber = intScanner();
		boolean flag = true;
		int i;
		for(i = 0; i < globals.numberOfAccounts; i++) {
			if(givenNumber == clients[i].clientPesel) {
				flag = false;
				showClientInfo(i);
			}
		}
		if(flag) {
			showDialog("There are no records");
		}
	}

	public void searchBySurname() {
		showDialog("Give me a client surname");
		String tmpAdress = stringScanner();
		boolean flag = true;
		int i;
		for(i = 0; i < globals.numberOfAccounts; i++) {
			if(tmpAdress.equals(clients[i].clientSurname)) {
				flag = false;
				showClientInfo(i);
			}
		}
		if(flag) {
			showDialog("There are no records");
		}
	}

	public void searchByName() {
		showDialog("Give me a client name");
		String tmpAdress = stringScanner();
		boolean flag = true;
		int i;
		for(i = 0; i < globals.numberOfAccounts; i++) {
			if(tmpAdress.equals(clients[i].clientName)) {
				flag = false;
				showClientInfo(i);
			}
		}
		if(flag) {
			showDialog("There are no records");
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
			tmp = intScanner();
		} while (tmp < 1 || tmp > 5);
		return tmp;
	}

	public void showClientInfo(int i) {
		if(clients[i].isActive){
			System.out.println("-------------------------");
			System.out.println("Name:");
			System.out.println(clients[i].clientName);
			System.out.println("Surname:");
			System.out.println(clients[i].clientSurname);
			System.out.println("PESEL:");
			System.out.println(clients[i].clientPesel);
			System.out.println("Adress:");
			System.out.println(clients[i].clientAdress);
			System.out.println("Money:");
			System.out.println(clients[i].clientResources);
			System.out.println("-------------------------");
		}
		else {
			showDialog("This client no longer exist, his no = " + i);
		}
	}

	public void getAllAccountsInfo() {
		int i;
		for(i=0; i < globals.numberOfAccounts; i++) {
			showClientInfo(i);
		}
	}
		
	public void moneyTransfer() {
		showDialog("Give me a number of source client");
		int sourceClient = intScanner();
		showDialog("Give me a number of destination client");
		int destinationClient = intScanner();
		showDialog("Give me a number of moeny to transfer");
		double moneyNo = doubleScanner();
		if(moneyNo <= clients[sourceClient].clientResources) {
			clients[sourceClient].clientResources -= moneyNo;
			clients[destinationClient].clientResources += moneyNo;
			showDialog("Success!");
		}
		else {
			showDialog("Too less money");
		}
	}

	public void withdraw() {
		showDialog("Give me a number of client");
		int sourceClient = intScanner();
		showDialog("Give me a number of moeny to witdraw");
		double moneyNo = doubleScanner();
		if(moneyNo <= clients[sourceClient].clientResources) {
			clients[sourceClient].clientResources -= moneyNo;
			showDialog("Success!");
		}
		else {
			showDialog("Too less money");
		}
	}

	public void payment() {
		showDialog("Give me a number of client");
		int sourceClient = intScanner();
		showDialog("Give me a number of moeny to make a payment");
		double moneyNo = doubleScanner();
		if(moneyNo > 0) {
			clients[sourceClient].clientResources += moneyNo;
			showDialog("Success!");
		}
		else {
			showDialog("Money must be higer than 0");
		}
		
	}
	
	public void deleteAccount() {
		showDialog("Give me a client number which you want delete");
		int tmp = intScanner();
		clients[tmp].isActive = false;
	}

	public void createAccount() {
		creatingAccountMenu();
		int number = intScanner();
		int pesel = intScanner();
		String name = stringScanner();
		String surname = stringScanner();
		String adress = stringScanner();
		double resources = doubleScanner();
		clients[globals.numberOfAccounts] = new Clients(number, pesel, name, surname, adress, resources);
		globals.numberOfAccounts++;
	}

	private void creatingAccountMenu() {
		showDialog("Give me all info, press ENTER after each");
		System.out.println("PESEL");
		System.out.println("Name");
		System.out.println("Surname");
		System.out.println("Adress");
		System.out.println("Strating resources");
	}

}
