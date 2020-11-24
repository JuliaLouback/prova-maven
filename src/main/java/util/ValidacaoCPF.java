package util;

public class ValidacaoCPF {

	public boolean validaCpf(String Cpf) {
		
		String CpfNovo = Cpf.replaceAll("[^a-zA-Z0-9]", "");  
		
		if(CpfNovo.length() < 11) {
			return false;
		}
		
		if(CpfNovo.equals("11111111111") | CpfNovo.equals("22222222222") | CpfNovo.equals("33333333333")
				| CpfNovo.equals("44444444444") | CpfNovo.equals("55555555555") | CpfNovo.equals("66666666666") |
				CpfNovo.equals("77777777777") | CpfNovo.equals("88888888888") | CpfNovo.equals("99999999999")) {
			return false;
		}
		
		
		int multiplicadorPrimeiro = 10;
		int totalMultiplicacaoPrimeiro = 0;
		
		for(int  i = 0; i < 9; i ++) {
			totalMultiplicacaoPrimeiro += Integer.parseInt(String.valueOf(CpfNovo.charAt(i))) * multiplicadorPrimeiro;
			multiplicadorPrimeiro  -= 1;
		}
		
		int primeiroDigito = (totalMultiplicacaoPrimeiro * 10) % 11;
	
		if(Integer.parseInt(String.valueOf(CpfNovo.charAt(9))) != primeiroDigito){
			return false;
		}
		
		int multiplicadorSegundo = 11;
		int totalMultiplicacaoSegundo = 0;
		
		for(int  i = 0; i < 10; i ++) {
			totalMultiplicacaoSegundo += Integer.parseInt(String.valueOf(CpfNovo.charAt(i))) * multiplicadorSegundo;
			multiplicadorSegundo  -= 1;
		}
	
		int segundoDigito = (totalMultiplicacaoSegundo * 10) % 11;

		if(Integer.parseInt(String.valueOf(CpfNovo.charAt(10))) != segundoDigito){
			return false;
		}
		
		return true;
	}
}
