import java.rmi.RemoteException;

public class Platform extends java.rmi.server.UnicastRemoteObject implements PlatformInterface{
	// algo
	private String [] tampon;
	private int indexProduction; // Pour écrire 
	private int indexConsommation; // Pour lire
	private int nbAutorisationEcriture; // Pour pouvoir écrire
	private int nbAutorisationLecture; // Pour pouvoir lire
	
	
	public Platform(int taille) throws RemoteException {
		this.tampon = new String[taille];
		this.indexProduction = 0;
		this.indexConsommation = 0;	
		this.nbAutorisationEcriture = 0;
		this.nbAutorisationLecture = 0;
	}
	

	public void produire(String message) throws RemoteException {
		tampon[indexProduction] = message;
		indexProduction= (indexProduction+1)%tampon.length;
		this.nbAutorisationEcriture--;
		
	}
	
	public String consommer() throws RemoteException {
		String message = tampon[indexConsommation];
		tampon[indexConsommation]= null;
		indexConsommation= (indexConsommation+1)%tampon.length;
		this.nbAutorisationLecture --;
		return message;
	}
	
	public boolean getAutorisationEcriture() throws RemoteException{
		int nbCaseVide = 0;
		for(String message : this.tampon ) {
			if(message == null) {
				nbCaseVide++;
			}
		}
		if(nbCaseVide > this.nbAutorisationEcriture) {
			nbAutorisationEcriture++;
			return true;
		}			
		return false;
	}
	
	
	public boolean getAutorisationLecture() {
		int nbCasePleine =0;
		for(String message : this.tampon ) {
			if(message != null) {
				nbCasePleine ++;
			}
		}
		if(nbCasePleine > this.nbAutorisationLecture) {
			nbAutorisationLecture++;
			return true;
		}
		return false;
	}
}
