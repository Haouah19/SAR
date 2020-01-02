package src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Producer implements Runnable{
	// fonctionnement 
	private int port;
	private InetAddress hote;
	private Socket sc=null;
	private BufferedReader in;
	private PrintWriter out;
	
	// algo
    
    public Producer() {
		
		this.port = 4020;
		
		try {
			this.hote = InetAddress.getLocalHost();
			this.sc = new Socket(hote, port);
			this.in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			this.out = new PrintWriter(sc.getOutputStream(), true);
			
		}catch(UnknownHostException e) {
			System.out.println("Problème lors de l'instantiation de 'hote' dans Consumer : ");
			e.printStackTrace();
		}catch (IOException e) {
			System.out.println("Problème lors de l'instantiation de hote : ");
			e.printStackTrace();
		}
	
    }
    
	@Override
	public void run() {
		try{
			// Reception du message du Serveur
			String message_du_serveur = in.readLine();
			System.out.println("Client a reçu " + message_du_serveur);
			
			// Envoie du message au serveur 
			String message_au_serveur ="Bonjour à vous aussi";
			out.println(message_au_serveur);
		

			System.out.println("Fin du CLient");

		}catch(IOException e){
			
			System.err.println("Erreur lors de l'exécution du Consumer : ");
			e.printStackTrace();
		}finally{
			try{
				in.close();
				out.close();
				sc.close();
				
			}catch (final IOException e){
				
				System.err.println("Erreur lors de la fermeture : ");
				e.printStackTrace();
			}
		}
		
	}
	
}
