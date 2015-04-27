package ch.claimer.appserver.seeds;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Category;

public class CategorySeed extends Seed<Category> {
	
	public CategorySeed() {
		this.repository = new EclipseLinkRepository<Category>(Category.class);
		
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
		
		list.add(c1);
		list.add(c2);
		list.add(c3);
		list.add(c4);
		list.add(c5);
		
		this.seeds.put("Category", list);
	}

}
