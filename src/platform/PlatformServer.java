import java.rmi.Naming;
import java.util.Scanner;
public class PlatformServer {
	
	public static void main(String [] args) {
		
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Veuillez saisir la taille du tampon :");
	    int taille = scanner.nextInt();
	    
	    try {
		    String url = "rmi://localhost:2001/platform";
		    Naming.rebind(url, new Platform(taille));
	    }catch(Exception e) {
	    	System.out.println("Problème lors de l'instantiation de PlatformServer");
	    }

	}
}
