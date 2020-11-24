package util;

public class ValidacaoEmail {

	public boolean emailValido(String email) {
		
      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
      return email.matches(regex);
      
   }
}
