
public class Clients {
	int clientNumber;
	int clientPesel;
	String clientName;
	String clientSurname;
	String clientAdress;
	double clientResources;
	boolean isActive;
	
	public Clients() {
		isActive = false;
	}
	
	public Clients(int number,int pesel,String name,String surname,String adress,double resources) {
		isActive = true;
		
	}
}
