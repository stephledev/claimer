package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Category;

/** 
 * In der CategorySeed-Klasse werden die verschiedenen Kategorien
 * von Gebäuden in die Datenbank abgefüllt. 
 * Die Klasse erbt von der Seed Klasse und setzt Category als Generic.
 * 
 * @author Raoul Ackermann
 * @author Fabio Baviera
 * @version 1.0
 * @since 1.0
 */
public class CategorySeed extends Seed<Category> {
	
	public CategorySeed() {
		this.repository = new EclipseLinkRepository<Category>(Category.class);
		this.seed = new ArrayList<Category>();
	}

	@Override
	public void setup() {
		Category c1 = new Category();
		c1.setName("Einfamilienhaus");
		Category c2 = new Category();
		c2.setName("Mehrfamilienhaus");
		Category c3 = new Category();
		c3.setName("Bürogebäude");
		Category c4 = new Category();
		c4.setName("Geschäftshaus");
		Category c5 = new Category();
		c5.setName("Lagerhaus");
		
		seed.add(c1);
		seed.add(c2);
		seed.add(c3);
		seed.add(c4);
		seed.add(c5);
		
		Seed.seeds.put("Category", seed);
		
	}

}
