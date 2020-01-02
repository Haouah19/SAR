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
    
    public Producer() {
		synchronized(mutex){
			this.cpt++;
			this.identifiant=cpt;
		}
		
		try {
			//System.setSecurityManager(new RMISecurityManager());
			String url = "rmi://localhost:2001/platform";
			this.tampon = (PlatformInterface)Naming.lookup(url);
		}
		catch(Exception e) {
			System.out.println("Erreur Producer : " +e);
			e.printStackTrace();
		}
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
		
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Veuillez saisir le nombre de producer :");
	    int taille = scanner.nextInt();
		
		for(int i=0; i<taille ; i++){
			Thread th = new Thread(new Producer());
			th.start();
		}

	}
	@Override
	public void run() {
		int i =0; 
		while(i<10) { // while(true) mais pour les tests 10 i3oum
			try {
				if(tampon.getAutorisationEcriture()) {
					produire(this.generate());
					i++;
				}
	
			} catch (RemoteException e) {
				e.printStackTrace();
			}
	
		}

	}
	
}
