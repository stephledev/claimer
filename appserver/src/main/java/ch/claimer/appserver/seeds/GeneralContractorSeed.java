package ch.claimer.appserver.seeds;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.GeneralContractor;

/** 
 * @author Raoul Ackermann
 */

public class GeneralContractorSeed extends Seed<GeneralContractor> {
	
	public GeneralContractorSeed() {
		this.repository = new EclipseLinkRepository<GeneralContractor>(GeneralContractor.class);
		
	}

	@Override
	public void setup() {
		GeneralContractor Gub1 = new GeneralContractor();
		Gub1.setName("Hauri GU AG");
		Gub1.setPlace("Luzern");
		Gub1.setStreet("Hubacherstrasse 12");
		Gub1.setZip("6003");
		Gub1.setEmail("sekretariat@hauriag.ch");
		Gub1.setPhone("0413212254");
		
		GeneralContractor Gub2 = new GeneralContractor();
		Gub2.setName("Schrader Generalunternehmen GmbH");
		Gub2.setPlace("Luzern");
		Gub2.setStreet("Bahnhofstrasse 12");
		Gub2.setZip("6003");
		Gub2.setEmail("Büro@schrader.ch");
		Gub2.setPhone("0416447865");
		
		GeneralContractor Gub3 = new GeneralContractor();
		Gub3.setName("Fischer AG");
		Gub3.setPlace("Lenzburg");
		Gub3.setStreet("Industriestrasse 11");
		Gub3.setZip("5600");
		Gub3.setEmail("administration@fischerag.ch");
		Gub3.setPhone("0443774657");
		
		GeneralContractor Gub4 = new GeneralContractor();
		Gub4.setName("Gubser AG");
		Gub4.setPlace("Zürich");
		Gub4.setStreet("Bahnhofstrasse 35");
		Gub4.setZip("8004");
		Gub4.setEmail("gubser@swissonline.ch");
		Gub4.setPhone("0433218824");
		
		GeneralContractor Gub5 = new GeneralContractor();
		Gub5.setName("Karlson & Söhne AG");
		Gub5.setPlace("Aarau");
		Gub5.setStreet("Blaicherain 4");
		Gub5.setZip("5000");
		Gub5.setEmail("santis_sanitaer@hotmail.ch");
		Gub5.setPhone("0628224433");
		
		list.add(Gub1);
		list.add(Gub2);
		list.add(Gub3);
		list.add(Gub4);
		list.add(Gub5);
		
		this.seeds.put("GeneralContractor", list);
	}

}
