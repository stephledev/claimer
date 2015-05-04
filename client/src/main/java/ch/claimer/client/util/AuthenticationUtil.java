package ch.claimer.client.util;

import ch.claimer.shared.models.Login;

public class AuthenticationUtil {
	
	private static Login login = null;
	
	public static Login getLogin() {
		return login;
	}
	 
	public static void setLogin(Login userlogin) {
		login = userlogin;
	}
}
