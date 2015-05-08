package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Principal;

/** 
 * In der PrincipalSeed-Klasse werden die Bauherren in die
 * Datenbank geschrieben, welche einem Projekt zugewiesen werden.
 * Die Klasse erbt von der Klasse Seed und setzt Principal als Generic.
 * 
 * @author Fabio Baviera
 * @version 1.0
 * @since 1.0
 */

public class PrincipalSeed extends Seed<Principal> {
	
	public PrincipalSeed() {
		this.repository = new EclipseLinkRepository<Principal>(Principal.class);
		this.seed = new ArrayList<Principal>();
	}

	@Override
	public void setup() {
		Principal Principal1 = new Principal();
		Principal1.setCompany("Siemens AG");
		Principal1.setStreet("Kochstrasse 4");
		Principal1.setZip("8044");
		Principal1.setPlace("Zürich");
		Principal1.setPhone("0444356787");
		Principal1.setEmail("info@siemens.ch");
		
		Principal Principal2 = new Principal();
		Principal2.setLastname("Tobias");
		Principal2.setFirstname("Spengler");
		Principal2.setStreet("Langstrasse 55");
		Principal2.setZip("8005");
		Principal2.setPlace("Zürich");
		Principal2.setPhone("044673297");
		Principal2.setEmail("tobias.spengler@bluewin.ch");
		
		Principal Principal3 = new Principal();

		Principal3.setCompany("Exon GmbH");
		Principal3.setStreet("Blüemligasse 7");
		Principal3.setZip("8048");
		Principal3.setPlace("Zürich");
		Principal3.setPhone("0445556743");
		Principal3.setEmail("exon@bluewin.ch");
		
		Principal Principal4 = new Principal();
		Principal4.setLastname("Celine");
		Principal4.setFirstname("Hofstetter");
		Principal4.setStreet("Hirschmattstrasse 77");
		Principal4.setZip("6048");
		Principal4.setPlace("Luzern");
		Principal4.setPhone("0413245633");
		Principal4.setEmail("celine.hofstetter@bluewin.ch");
				
		
		seed.add(Principal1);
		seed.add(Principal2);
		seed.add(Principal3);
		seed.add(Principal4);
		
		Seed.seeds.put("Principal", seed);
	}
}