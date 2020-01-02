package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Platform {
	
	// fonctionnement 
	private int port;
	private ServerSocket serverSocket;
	private Socket ssv ;
	private PrintWriter out;
	private BufferedReader in;
	
	
	// algo
	private String [] tampon;
	private int indexProduction;
	private int indexConsommation;
	
	
	public Platform(int taille) {
		this.tampon = new String[taille];
		this.indexProduction = 0;
		this.indexConsommation =0;
		this.port=4020;
		
		try {
			this.serverSocket = new ServerSocket(port);
			this.ssv=serverSocket.accept();
			
			this.in = new BufferedReader(new InputStreamReader(ssv.getInputStream()));
			this.out = new PrintWriter(ssv.getOutputStream(), true);
			
		} catch (IOException e) {
			
			System.out.println("Problème lors de l'instantiation de la Platform : ");
			e.printStackTrace();
		}
	}
	
	
	public boolean getAutorisation() {
		
	}
	public void produire(String message) throws IOException {
		tampon[indexProduction] = message;
		indexProduction= (indexProduction+1)%tampon.length;
		
		
		// Exécution 
		String str = in.readLine();
		System.out.println("Le serveur a reçu : "+str);
		out.println("Bienvenu");
	}
	
	public void Consommer() {
		out.println(tampon[indexConsommation]);
		indexConsommation= (indexConsommation+1)%tampon.length;
	}
	
	
	// ne pas oublier de fermer 
	
}
