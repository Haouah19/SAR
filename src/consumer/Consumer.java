import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import java.util.Scanner;
public class Consumer implements  Runnable {
	
	private static int cpt=0;
	private int identifiant;
	private static Object mutex =new Object();
	private PlatformInterface tampon;
	
	public Consumer() {
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
			System.out.println("Erreur Cosumer : " +e);
		}
	}
	
	public String consommer() throws RemoteException{
		return tampon.consommer();
	}

	public static void main(String [] args) {
		
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Veuillez saisir le nombre de Consommateur :");
	    int taille = scanner.nextInt();
		
		for(int i=0; i<taille ; i++){
			Thread th = new Thread(new Consumer());
			th.start();
		}

	}
	
	@Override
	public void run() {
		while(true) {
				try {
					if(tampon.getAutorisationLecture()) {
						String message = consommer();
						System.out.println(message);
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
		}
	}

}
