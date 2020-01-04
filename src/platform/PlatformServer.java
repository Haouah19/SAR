import java.rmi.Naming;
import java.util.Scanner;
import java.net.*;
public class PlatformServer {
	
	public static void main(String [] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("*******************************************************"
		+ "\nBonjour, et bienvenu sur la plateforme d'échanges. ");

		System.out.println("*******************************************************");
		System.out.println("Veuillez saisir la taille du tampon :");
		
		int taille = scanner.nextInt();
	    try {
			String myIP = InetAddress.getLocalHost().getHostAddress();
			System.out.println("*******************************************************");
			System.out.println("Adresse du serveur : "+myIP);
		    String url = "rmi://"+myIP+":2001/platform";
		    Naming.rebind(url, new Platform(taille));
	    }catch(Exception e) {
	    	System.out.println("Problème lors de l'instantiation de PlatformServer");
	    }

	}
}
