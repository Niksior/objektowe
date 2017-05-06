
public class Clients {
	int clientNumber;
	long clientPesel;
	String clientName;
	String clientSurname;
	String clientAdress;
	double clientResources;
	boolean isActive;
	
	public Clients() {
		isActive = false;
	}
	
	public Clients(int number,long pesel,String name,String surname,String adress,double resources) {
		isActive = true;
		clientNumber = number;
		clientPesel = pesel;
		clientName = name;
		clientSurname = surname;
		clientAdress = adress;
		clientResources = resources;
	}
	
}
