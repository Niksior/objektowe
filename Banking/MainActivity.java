import java.io.*;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainActivity {

	Clients clients[] = new Clients[500];
	Globals globals = new Globals();
	Scanners scanners = new Scanners();
	Saving saving = new Saving();

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

	public MainActivity() {}

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
			searchByNumber();
			break;
		}
		}
	}

	public int searchByClinetNumber(int givenNumber) {
		boolean flag = true;
		for(int i = 0; i < globals.numberOfAccounts; i++) {
			if(clients[i].clientNumber == givenNumber) {
				return i;
			}
		}
		if(flag) {
			return -1;
		}
		return 0;
	}


	public void searchByNumber() {
		showDialog("Give me a client number");
		int givenNumber = scanners.intScanner();
		int i = searchByClinetNumber(givenNumber);
		if(i == -1) {
			showDialog("There was no client");
		}
		else {
			showClientInfo(i);
		}
	}

	public void searchByAdress() {
		showDialog("Give me a client adress");
		String tmpAdress = scanners.stringScanner();
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
		double givenNumber = scanners.doubleScanner();
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
		String tmpAdress = scanners.stringScanner();
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
		String tmpAdress = scanners.stringScanner();
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
			tmp = scanners.intScanner();
		} while (tmp < 1 || tmp > 5);
		return tmp;
	}

	public void showClientInfo(int i) {
			System.out.println("-------------------------");
			System.out.println("Number:");
			System.out.println(clients[i].clientNumber);
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

	public void getAllAccountsInfo() {
		vista();
		int i;
		for(i=0; i < globals.numberOfAccounts; i++) {
			showClientInfo(i);
		}
		if(globals.numberOfAccounts == 0) {
			showDialog("There are no records");
		}
	}

	public void moneyTransfer() {
		vista();
		int sourceClient, destinationClient;
		showDialog("Give me a number of source client");
		int tmp = scanners.intScanner();
		sourceClient = searchByClinetNumber(tmp);
		showDialog("Give me a number of destination client");
		int tmp2 = scanners.intScanner();
		destinationClient = searchByClinetNumber(tmp2);
		if(sourceClient == -1 || destinationClient == -1) {
			showDialog("Client don't exist");
			return;
		}
		showDialog("Give me a number of money to transfer");
		double moneyNo = scanners.doubleScanner();
		if(moneyNo <= clients[sourceClient].clientResources) {
			clients[sourceClient].clientResources -= moneyNo;
			clients[destinationClient].clientResources += moneyNo;
			showDialog("Success!");
			saving.saveAccountsInfo(globals, clients);
		}
		else {
			showDialog("Too less money");
		}
	}

	public void withdraw() {
		vista();
		showDialog("Give me a number of client");
		int sourceClient;
		int tmp = scanners.intScanner();
		sourceClient = searchByClinetNumber(tmp);
		if(sourceClient == -1) {
			showDialog("Client don't exist");
			return;
		}
		showDialog("Give me a number of money to witdraw");
		double moneyNo = scanners.doubleScanner();
		if(moneyNo <= clients[sourceClient].clientResources) {
			clients[sourceClient].clientResources -= moneyNo;
			showDialog("Success!");
			saving.saveAccountsInfo(globals, clients);
		}
		else {
			showDialog("Too less money");
		}
	}

	public void payment() {
		vista();
		showDialog("Give me a number of client");
		int sourceClient;
		int tmp = scanners.intScanner();
		sourceClient = searchByClinetNumber(tmp);
		if(sourceClient == -1) {
			showDialog("Client don't exist");
			return;
		}
		showDialog("Give me a number of money to make a payment");
		double moneyNo = scanners.doubleScanner();
		if(moneyNo > 0) {
			clients[sourceClient].clientResources += moneyNo;
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
		tmp = searchByClinetNumber(tmp);
		if(tmp == -1) {
			showDialog("Client does not exist");
		}
		else {
			for(int i = tmp; i < globals.numberOfAccounts; i++) {
				clients[i] = clients[i+1];
			}
			globals.numberOfAccounts--;
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
		clients[globals.maxClientNumber] = new Clients(number, pesel, name, surname, adress, resources);
		globals.numberOfAccounts++;
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
		try{
			vista();
			readNumberOfAccounts();
			loadAccountsInfo2();
			showDialog("Loaded");
		}
		catch(IOException e) {
			showDialog("There was no file to load");
		}
	}

	public void readNumberOfAccounts() throws IOException {
		File file = new File(globals.fileWithNumberOfAccounts);
		Scanner in = new Scanner(file);
		globals.numberOfAccounts = in.nextInt();
		globals.maxClientNumber = in.nextInt();
	}

	public void loadAccountsInfo2() {
      try {
         FileInputStream fileIn = new FileInputStream(globals.filename);
         ObjectInputStream in = new ObjectInputStream(fileIn);
		 for(int i=0; i<globals.numberOfAccounts; i++) {
			 clients[i] = (Clients) in.readObject();
		 }
         in.close();
         fileIn.close();
      }catch(IOException i) {
         showDialog("There was no file to load");
         return;
      }catch(ClassNotFoundException c) {
         System.out.println("Employee class not found");
         c.printStackTrace();
         return;
      }
	}

}
