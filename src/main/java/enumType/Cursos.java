package enumType;

public enum Cursos {
	
	Curso1("Engenharia de Software"),
	Curso2("Engenharia de Energia"),
	Curso3("Engenharia Civil"),
	Curso4("Engenharia Elétrica");
	
    private final String Nome;       

	private Cursos(String nome) {
		this.Nome = nome;
	}
	
	@Override
   public String toString() {
        return Nome;
   }
	
	
	
}
