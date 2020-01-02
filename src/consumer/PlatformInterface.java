import java.rmi.Remote;

public interface PlatformInterface extends Remote {
	
	public void produire(String message) throws java.rmi.RemoteException;
	public String consommer() throws java.rmi.RemoteException;
	public boolean getAutorisationEcriture()throws java.rmi.RemoteException;
	public boolean getAutorisationLecture() throws java.rmi.RemoteException;
}
