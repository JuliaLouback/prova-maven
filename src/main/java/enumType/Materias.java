package enumType;

public enum Materias {
	
	Materia1("Redes de Computadores"),
	Materia2("Análise de Dados"),
	Materia3 ("Programação Orientada a Objetos"),
	Materia4("Banco de Dados"),
	Materia5("Anatomia"),
	Materia6("Biologia Celular"),
	Materia7("Fisiologia"),
	Materia8("Biogeografia"),
	Materia9("Cartografia Geral"),
	Materia10("Matemática"),
	Materia11("Teoria da Comunicação");
	
	
    private final String Nome;       

	private Materias(String nome) {
		this.Nome = nome;
	}
	
	@Override
   public String toString() {
        return Nome;
   }
	
   public static Materias listarUm(String text) {
	    for (Materias e : values()) {
	        if (e.Nome.equals(text)) {
	            return e;
	        }
	    }
	    return null;
	}
}
