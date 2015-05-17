package ch.claimer.client.util;

import ch.claimer.shared.models.Login;
import ch.claimer.shared.models.Person;

public class AuthenticationUtil {
	
	private static Login login = null;
	private static Person person = null;
	
	public static Login getLogin() {
		return login;
	}
	
	public static Person getPerson() {
		return person;
	}

	public static void load(Person person) {
		AuthenticationUtil.person = person;
		AuthenticationUtil.login = person.getLogin();
	}
	
	public static void close() {
		AuthenticationUtil.person = null;
		AuthenticationUtil.login = null;
	}
}
