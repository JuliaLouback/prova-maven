package enumType;

public enum Materias {
	
	Materia1("Redes de Computadores"),
	Materia2("Análise de Dados"),
	Materia3 ("Programação Orientada a Objetos"),
	Materia4("Banco de Dados");
	
    private final String Nome;       

	private Materias(String nome) {
		this.Nome = nome;
	}
	
	@Override
   public String toString() {
        return Nome;
   }
}
