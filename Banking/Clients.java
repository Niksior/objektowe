import java.io.*;

public class Clients implements java.io.Serializable {
	int clientNumber;
	long clientPesel;
	String clientName;
	String clientSurname;
	String clientAdress;
	double clientResources;
	
	public Clients() {	}
	
	public Clients(int number,long pesel,String name,String surname,String adress,double resources) {
		clientNumber = number;
		clientPesel = pesel;
		clientName = name;
		clientSurname = surname;
		clientAdress = adress;
		clientResources = resources;
	}
	
}
