package ch.claimer.appserver.seeds;

import java.util.ArrayList;

import ch.claimer.appserver.repositories.eclipselink.EclipseLinkRepository;
import ch.claimer.shared.models.Subcontractor;

/** 
 * In der SubcontractorSeed-Klasse werden die Subunternehmer mit den 
 * Adressen und Kontaktdaten in die Datenbank geschrieben.
 * Die Klasse erbt von der Klasse Seed und setzt Subcontractor als Generic.
 * 
 * @author Fabio Baviera
 * @version 1.0
 * @since 1.0
 */

public class SubcontractorSeed extends Seed<Subcontractor> {
	
	public SubcontractorSeed() {
		this.repository = new EclipseLinkRepository<Subcontractor>(Subcontractor.class);
		this.seed = new ArrayList<Subcontractor>();
	}

	@Override
	public void setup() {
		Subcontractor Sub1 = new Subcontractor();
		Sub1.setName("Meier Maurer AG");
		Sub1.setPlace("Luzern");
		Sub1.setStreet("Kauffmannweg 7");
		Sub1.setZip("6003");
		Sub1.setEmail("meier_maurer@bluewin.ch");
		Sub1.setPhone("0413214354");
		
		Subcontractor Sub2 = new Subcontractor();
		Sub2.setName("Müller Schreiner AG");
		Sub2.setPlace("Luzern");
		Sub2.setStreet("Hirschmattweg 8");
		Sub2.setZip("6003");
		Sub2.setEmail("müller_schreiner@bluewin.ch");
		Sub2.setPhone("0416457865");
		
		Subcontractor Sub3 = new Subcontractor();
		Sub3.setName("Spenglerei Fischer GmbH");
		Sub3.setPlace("Zürich");
		Sub3.setStreet("Anemonenstrasse 50");
		Sub3.setZip("8047");
		Sub3.setEmail("spenglerei_fischer@bluewin.ch");
		Sub3.setPhone("0443214657");
		
		Subcontractor Sub4 = new Subcontractor();
		Sub4.setName("Gibser Gubler AG");
		Sub4.setPlace("Luzern");
		Sub4.setStreet("Langstrasse 77");
		Sub4.setZip("8004");
		Sub4.setEmail("gubler@bluewin.ch");
		Sub4.setPhone("0433219324");
		
		Subcontractor Sub5 = new Subcontractor();
		Sub5.setName("Sanitär Santis GmbH");
		Sub5.setPlace("Schöneberg");
		Sub5.setStreet("Mattenweg 15");
		Sub5.setZip("8850");
		Sub5.setEmail("santis_sanitaer@hotmail.ch");
		Sub5.setPhone("0446754354");
		
		seed.add(Sub1);
		seed.add(Sub2);
		seed.add(Sub3);
		seed.add(Sub4);
		seed.add(Sub5);
		
		Seed.seeds.put("Subcontractor", seed);
	}

}
