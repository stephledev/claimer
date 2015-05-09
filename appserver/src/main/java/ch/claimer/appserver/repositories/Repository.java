package ch.claimer.appserver.repositories;

import java.util.List;

import ch.claimer.shared.models.Model;

/**
 * Verantwortlich für die Abfrage und das Persistieren in die Datenbank von
 * sämtlichen Entiäten.
 * 
 * 
 * @author Stephan Beeler
 * @version 1.0
 * @since 1.0
 *
 * @param <T> Entität, die behandlet werden soll
 */
public interface Repository<T extends Model> {

	/**
	 * Holt eine Entität mit dem angegebenen Indentifikator
	 * 
	 * @param id Identifikator der anzuzeigenden Entität
	 * @return Instanz der Entität
	 */
	T getById(int id);

	/**
	 * Holt alle Entitäten
	 * 
	 * @return Liste von Entitäten
	 */
	List<T> getAll();

	/**
	 * Holt alle Entitäten mit der angegebenen Eigenschaft
	 * 
	 * @param name Name der Eigenschaft
	 * @param value Werte der Eigenschaft
	 * @return Liste von Entitäten
	 */
	List<T> getByProperty(String name, Object value);

	/**
	 * Holt alle Entitäten mit der angegebenen Beziehung
	 * 
	 * @param relation Entität der Beziehung
	 * @param id Identifikator der Beziehungs-Entität
	 * @return Liste von Entitäten
	 */
	List<T> getByRelation(Class<?> relation, int id);

	/**
	 * Erstellt eine neue Entität
	 * 
	 * @param t zu erstellende Entität
	 * @return erstelle Entität
	 */
	T create(T t);

	/**
	 * Aktualisiert eine bestehende Entität
	 * 
	 * @param t zu aktualisierende Entität
	 * @return aktualisierte Entität
	 */
	T update(T t);

	/**
	 * Löscht eine Entität mit dem angegebenen Indentifikator
	 * 
	 * @param id Identifikator der zu löschenden Entität
	 */
	void delete(int id);

}
