package ch.claimer.appserver.repositories;

import java.util.List;

import ch.claimer.shared.models.Model;

/**
 * Verantwortlich f�r die Abfrage und das Persistieren in die Datenbank von
 * s�mtlichen Enti�ten.
 * 
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 *
 * @param <T> Entit�t, die behandlet werden soll
 */
public interface Repository<T extends Model> {

	/**
	 * Holt eine Entit�t mit dem angegebenen Indentifikator
	 * 
	 * @param id Identifikator der anzuzeigenden Entit�t
	 * @return Instanz der Entit�t
	 */
	T getById(int id);

	/**
	 * Holt alle Entit�ten
	 * 
	 * @return Liste von Entit�ten
	 */
	List<T> getAll();

	/**
	 * Holt alle Entit�ten mit der angegebenen Eigenschaft
	 * 
	 * @param name Name der Eigenschaft
	 * @param value Werte der Eigenschaft
	 * @return Liste von Entit�ten
	 */
	List<T> getByProperty(String name, Object value);

	/**
	 * Holt alle Entit�ten mit der angegebenen Beziehung
	 * 
	 * @param relation Entit�t der Beziehung
	 * @param id Identifikator der Beziehungs-Entit�t
	 * @return Liste von Entit�ten
	 */
	List<T> getByRelation(Class<?> relation, int id);

	/**
	 * Erstellt eine neue Entit�t
	 * 
	 * @param t zu erstellende Entit�t
	 * @return erstelle Entit�t
	 */
	T create(T t);

	/**
	 * Aktualisiert eine bestehende Entit�t
	 * 
	 * @param t zu aktualisierende Entit�t
	 * @return aktualisierte Entit�t
	 */
	T update(T t);

	/**
	 * L�scht eine Entit�t mit dem angegebenen Indentifikator
	 * 
	 * @param id Identifikator der zu l�schenden Entit�t
	 */
	void delete(int id);

}
