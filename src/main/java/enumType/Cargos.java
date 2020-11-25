package enumType;

public enum Cargos {

	Cargo1("RH"),
	Cargo2("Contador"),
	Cargo3("Analista de Sistema"),
	Cargo4("Gerencia");
	
    private final String Nome;       

	private Cargos(String nome) {
		this.Nome = nome;
	}
	
	@Override
   public String toString() {
        return Nome;
   }
	
   public static Cargos listarUm(String text) {
	    for (Cargos e : values()) {
	        if (e.Nome.equals(text)) {
	            return e;
	        }
	    }
	    return null;
	}
	
}
