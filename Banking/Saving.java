
public class Saving {

	public static Clients main(String[] args) {
		int clientNumber = Integer.parseInt(args[0]);
		long clientPesel = new Double(Double.parseDouble(args[3])).longValue();
		String clientName = args[1];
		String clientSurname = args[2];
		String clientAdress = args[4];
		double clientResources = Double.parseDouble(args[5]);
		
		Clients client = new Clients(clientNumber, clientPesel, clientName, clientSurname, clientAdress, clientResources);
		return client;
	}
	
	public Saving(String line) {
		main(line);
	}

}
