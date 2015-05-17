package ch.claimer.shared.methods;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import ch.claimer.shared.models.Model;

/**
 * Beschreibt die erlaubten RMI-Methoden, die f�r s�mtliche Entit�ten gelten.
 * 
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 *
 * @param <T> Entit�t, die behandelt werden soll
 */
public interface Method<T extends Model> extends Remote {
	
	/**
	 * Holt alle Entit�ten
	 * 
	 * @return Liste von Entit�ten
	 * @throws RemoteException Falls Verbindungsprobleme bestehen
	 */
	public List<T> getAll() throws RemoteException;
	
	/**
	 * Holt eine Entit�t mit dem angegebenen Indentifikator
	 * 
	 * @param id Identifikator der anzuzeigenden Entit�t
	 * @return Instanz der Entit�t
	 * @throws RemoteException Falls Verbindungsprobleme bestehen
	 */
	public T getById(int id) throws RemoteException;
	
	/**
	 * Holt alle Entit�ten mit der angegebenen Eigenschaft
	 * 
	 * @param name Name der Eigenschaft
	 * @param value Werte der Eigenschaft
	 * @return Liste von Entit�ten
	 * @throws RemoteException Falls Verbindungsprobleme bestehen
	 */
	public List<T> getByProperty(String name, Object value) throws RemoteException;
	
	/**
	 * Holt alle Entit�ten mit der angegebenen Beziehung
	 * 
	 * @param relation Entit�t der Beziehung
	 * @param id Identifikator der Beziehungs-Entit�t
	 * @return Liste von Entit�ten
	 * @throws RemoteException Falls Verbindungsprobleme bestehen
	 */
	public List<T> getByRelation(Class<?> relation, int id) throws RemoteException;
	
	/**
	 * Holt alle Entit�ten mit der angegebenen Mehrfach-Beziehung
	 * 
	 * @param relation Entit�t der Mehrfach-Beziehung
	 * @param id Identifikator der Mehrfach-Beziehungs-Entit�t
	 * @return Liste von Entit�ten
	 * @throws RemoteException Falls Verbindungsprobleme bestehen
	 */
	public List<T> getByRelations(Class<?> relation, int id) throws RemoteException;
	
	/**
	 * Erstellt eine neue Entit�t
	 * 
	 * @param t zu erstellende Entit�t
	 * @return erstelle Entit�t
	 * @throws RemoteException Falls Verbindungsprobleme bestehen
	 */
	public T create(T t) throws RemoteException;
	
	/**
	 * Aktualisiert eine bestehende Entit�t
	 * 
	 * @param t zu aktualisierende Entit�t
	 * @return aktualisierte Entit�t
	 * @throws RemoteException Falls Verbindungsprobleme bestehen
	 */
	public T update(T t) throws RemoteException;
	
	/**
	 * L�scht eine Entit�t mit dem angegebenen Indentifikator
	 * 
	 * @param id Identifikator der zu l�schenden Entit�t
	 * @return Identifikator der zu l�schenden Entit�t
	 * @throws RemoteException Falls Verbindungsprobleme bestehen
	 */
	public Integer delete(int id) throws RemoteException;

}
