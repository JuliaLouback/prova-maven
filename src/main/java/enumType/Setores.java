package enumType;

public enum Setores {

	Setor1("Financeiro"),
	Setor2("Administrativo"),
	Setor3("TI");
	
    private final String Nome;       

	private Setores(String nome) {
		this.Nome = nome;
	}
	
	@Override
   public String toString() {
        return Nome;
   }
	
   public static Setores listarUm(String text) {
	    for (Setores e : values()) {
	        if (e.Nome.equals(text)) {
	            return e;
	        }
	    }
	    return null;
	}
}
