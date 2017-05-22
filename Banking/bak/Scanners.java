import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Scanners {

    public Scanners() { }

    public void showDialog(String string) {
		System.out.println("-------------------------");
		System.out.println(string);
		System.out.println("-------------------------");
	}

    public int intScanner() {
		boolean flag = true;
		while(flag){
			try {
				Scanner scan = new Scanner(System.in);
				int scannedValue;
				scannedValue = scan.nextInt();
				return scannedValue;
			}
			catch(InputMismatchException exception) {
				showDialog("It should be a number, try one more time");
			}
		}
		return 0;
	}

    public double doubleScanner() {
		boolean flag = true;
		while(flag) {
			try {
				Scanner scan = new Scanner(System.in);
				double scannedValue;
				scannedValue = scan.nextDouble();
				return scannedValue;
			}
			catch(InputMismatchException exception) {
				showDialog("It should be a number, try one more time");
			}
		}

		return 0;
	}

	public String stringScanner() {
		boolean flag = true;
		while(flag) {
			try {
				Scanner scan = new Scanner(System.in);
				String scannedValue;
				scannedValue = scan.nextLine();
				return scannedValue;
			}
			catch(NoSuchElementException exception) {
				showDialog("Why empty line? One more time please");
			}
		}
		return null;
	}
}
