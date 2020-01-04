import java.rmi.Naming;

import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Producer implements Runnable{
	
	private static int cpt=0;
	private int identifiant;
	private static Object mutex =new Object();
	private int nb_message =0;
	private PlatformInterface tampon;
    
    public Producer(String address) {
		synchronized(mutex){
			this.cpt++;
			this.identifiant=cpt;
		}
		
		try {
			//System.setSecurityManager(new RMISecurityManager());
			String url = "rmi://"+ address+ ":2001/platform";
			this.tampon = (PlatformInterface)Naming.lookup(url);
		}
		catch(Exception e) {
			System.out.println("Problème lors de la connexion au serveur !");
		}

		System.out.println("Producer " + identifiant + " prêt !");
    }
    
	private String generate() {
		this.nb_message++;
		String message = this.identifiant+"_"+this.nb_message;
		return message;
	}
    
	public void produire(String message) throws RemoteException{
		tampon.produire(message);
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
		+ "\nBonjour, et bienvenu chez les producteurs.");
		
		System.out.println("\n*******************************************************");
		System.out.println("Les producteurs écrivent 10 messages sur le tampon. "
		+ "\nLes messages sont de la forme : identifiant du producteur_numéro du message (entre 1 et 10)."
		+ "\nExemple : 3_9 signifie que le producteur numéro 3 a écrit le message 9."
		+ "\nC'est une manière simple pour vérifier qu'il n'y a pas de perte de messages et pour savoir quel producteur a écrit a tel endroit.");

		System.out.println("\n*******************************************************");
		System.out.println("Veuillez entrer le nombre de producteurs  : ");

		int taille = scanner.nextInt();
		
		for(int i=0; i<taille ; i++){
			Thread th = new Thread(new Producer(address));
			th.start();
		}

	}
	@Override
	public void run() {
		boolean exception = false;
		int i =0; 
		while(i<10) { // while(true) mais pour les tests 10 i3oum
			try {
				if(tampon.getAutorisationEcriture()) {
					produire(this.generate());
					i++;
				}
	
			} catch (RemoteException e) {
				if(!exception){
					System.out.println("Producer "+ identifiant+" : Problème lors de l'exécution, une RemoteException a été levé !");
					exception= true;
					break;
				}
			}
		}
		System.out.println("Producer " + identifiant + " a fini !");

	}
	
}
