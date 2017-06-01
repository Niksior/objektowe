import java.util.ArrayList;
import java.util.List;

public class Searching {
	Scanners scanners = new Scanners();

	Searching () { }

	public int searchByClinetNumber(Globals globals, List<Clients> clients, int givenNumber) {
		boolean flag = true;
		for(int i = 0; i < clients.size(); i++) {
			if(clients.get(i).clientNumber == givenNumber) {
				return i;
			}
		}
		if(flag) {
			return -1;
		}
		return -2;
	}


	public void searchByNumber(Globals globals, List<Clients> clients) {
		showDialog("Give me a client number");
		int givenNumber = scanners.intScanner();
		int i = searchByClinetNumber(globals, clients, givenNumber);
		if(i == -1) {
			showDialog("There was no client");
		}
		else {
			showClientInfo(clients, i);
		}
	}

	public void searchByAdress(Globals globals, List<Clients> clients) {
		showDialog("Give me a client adress");
		String tmpAdress = scanners.stringScanner();
		boolean flag = true;
		int i;
		for(i = 0; i < clients.size(); i++) {
			if(tmpAdress.equals(clients.get(i).clientAdress)) {
				flag = false;
				showClientInfo(clients, i);
			}
		}
		if(flag) {
			showDialog("There are no records");
		}
	}

	public void searchByPESEL(Globals globals, List<Clients> clients) {
		showDialog("Give me a client PESEL");
		double givenNumber = scanners.doubleScanner();
		boolean flag = true;
		int i;
		for(i = 0; i < clients.size(); i++) {
			if(givenNumber == clients.get(i).clientPesel) {
				flag = false;
				showClientInfo(clients, i);
			}
		}
		if(flag) {
			showDialog("There are no records");
		}
	}

	public void searchBySurname(Globals globals, List<Clients> clients) {
		showDialog("Give me a client surname");
		String tmpAdress = scanners.stringScanner();
		boolean flag = true;
		int i;
		for(i = 0; i < clients.size(); i++) {
			if(tmpAdress.equals(clients.get(i).clientSurname)) {
				flag = false;
				showClientInfo(clients, i);
			}
		}
		if(flag) {
			showDialog("There are no records");
		}
	}

	public void searchByName(Globals globals, List<Clients> clients) {
		showDialog("Give me a client name");
		String tmpAdress = scanners.stringScanner();
		boolean flag = true;
		int i;
		for(i = 0; i < clients.size(); i++) {
			if(tmpAdress.equals(clients.get(i).clientName)) {
				flag = false;
				showClientInfo(clients, i);
			}
		}
		if(flag) {
			showDialog("There are no records");
		}
	}

	public void showDialog(String string) {
		System.out.println("-------------------------");
		System.out.println(string);
		System.out.println("-------------------------");
	}

	public void showClientInfo(List<Clients> clients ,int i) {
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
}
