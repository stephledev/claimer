package ch.claimer.shared.methods;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ch.claimer.shared.models.Model;

/**
 * Beschreibt die erlaubten RMI-Methoden, die für sämtliche Entitäten gelten.
 * 
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 *
 * @param <T> Entität, die behandelt werden soll
 */
public interface Method<T extends Model> extends Remote {
	
	/**
	 * Holt alle Entitäten
	 * 
	 * @return Liste von Entitäten
	 * @throws RemoteException Falls Verbindungsprobleme bestehen
	 */
	public List<T> getAll() throws RemoteException;
	
	/**
	 * Holt eine Entität mit dem angegebenen Indentifikator
	 * 
	 * @param id Identifikator der anzuzeigenden Entität
	 * @return Instanz der Entität
	 * @throws RemoteException Falls Verbindungsprobleme bestehen
	 */
	public T getById(int id) throws RemoteException;
	
	/**
	 * Holt alle Entitäten mit der angegebenen Eigenschaft
	 * 
	 * @param name Name der Eigenschaft
	 * @param value Werte der Eigenschaft
	 * @return Liste von Entitäten
	 * @throws RemoteException Falls Verbindungsprobleme bestehen
	 */
	public List<T> getByProperty(String name, Object value) throws RemoteException;
	
	/**
	 * Holt alle Entitäten mit der angegebenen Beziehung
	 * 
	 * @param relation Entität der Beziehung
	 * @param id Identifikator der Beziehungs-Entität
	 * @return Liste von Entitäten
	 * @throws RemoteException Falls Verbindungsprobleme bestehen
	 */
	public List<T> getByRelation(Class<?> relation, int id) throws RemoteException;
	
	/**
	 * Holt alle Entitäten mit der angegebenen Mehrfach-Beziehung
	 * 
	 * @param relation Entität der Mehrfach-Beziehung
	 * @param id Identifikator der Mehrfach-Beziehungs-Entität
	 * @return Liste von Entitäten
	 * @throws RemoteException Falls Verbindungsprobleme bestehen
	 */
	public List<T> getByRelations(Class<?> relation, int id) throws RemoteException;
	
	/**
	 * Erstellt eine neue Entität
	 * 
	 * @param t zu erstellende Entität
	 * @return erstelle Entität
	 * @throws RemoteException Falls Verbindungsprobleme bestehen
	 */
	public T create(T t) throws RemoteException;
	
	/**
	 * Aktualisiert eine bestehende Entität
	 * 
	 * @param t zu aktualisierende Entität
	 * @return aktualisierte Entität
	 * @throws RemoteException Falls Verbindungsprobleme bestehen
	 */
	public T update(T t) throws RemoteException;
	
	/**
	 * Löscht eine Entität mit dem angegebenen Indentifikator
	 * 
	 * @param id Identifikator der zu löschenden Entität
	 * @return Identifikator der zu löschenden Entität
	 * @throws RemoteException Falls Verbindungsprobleme bestehen
	 */
	public Integer delete(int id) throws RemoteException;

}
