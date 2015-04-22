package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Category;

public class CategorySeed extends Seed<Category> {
	
	public CategorySeed() {
		this.repository = new EclipseLinkRepository<Category>(Category.class);
		this.list = new ArrayList<Category>();
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
		
		list.add(c1);
		list.add(c2);
		list.add(c3);
		list.add(c4);
	}

}
