import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import java.util.Scanner;
public class Consumer implements  Runnable {
	
	private static int cpt=0;
	private int identifiant;
	private static Object mutex =new Object();
	private PlatformInterface tampon;
	
	public Consumer(String address) {
		synchronized(mutex){
			this.cpt++;
			this.identifiant=cpt;
		}
		
		try {
			//System.setSecurityManager(new RMISecurityManager());
			String url = "rmi://"+address+":2001/platform";
			this.tampon = (PlatformInterface)Naming.lookup(url);
		}
		catch(Exception e) {
			System.out.println("Problème lors de la connexion au serveur !");
		}

		System.out.println("Consumer " + identifiant + " prêt !");
	}
	
	public String consommer() throws RemoteException{
		return tampon.consommer();
	}

	public static void main(String [] args) {
		String address = null;
		
		if (args.length==1){
			address = args[0]; 
		}else{
			address = "localhost";
		}

		Scanner scanner = new Scanner(System.in);
		System.out.println("*******************************************************"
		+ "\nBonjour, et bienvenu chez les consommateurs.");

		System.out.println("\n*******************************************************");
		System.out.println("Les consommateurs lisent infiniment le tampon. "
		+ "\nles messages lus apparaissent avec la forme : "
		+ "\nConsumer (identifiant du consumer) : message lu ");

		System.out.println("\n*******************************************************");
		System.out.println("Veuillez entrer le nombre de consommateurs  : ");
	    int taille = scanner.nextInt();
	
		
		for(int i=0; i<taille ; i++){
			Thread th = new Thread(new Consumer(address));
			th.start();
		}

	}
	
	@Override
	public void run() {
		boolean exception = false;
		while(true) {
				try {
					if(tampon.getAutorisationLecture()) {
						String message = consommer();
						System.out.println("Consumer "+ identifiant +" : " + message);
					}
				} catch (RemoteException e) {
					if(!exception){
						System.out.println("Consumer "+ identifiant+" : Problème lors de l'exécution, une RemoteException a été levé !");
						exception= true;
						break;
					}
					
				}
		}
	}

}
