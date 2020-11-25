package enumType;

public enum Cursos {
	
	Curso1("Engenharia de Software"),
	Curso2("Engenharia de Energia"),
	Curso3("Engenharia Civil"),
	Curso4("Engenharia Elétrica"),
	Curso5("Engenharia da Computação"),
	Curso6("Medicina"),
	Curso7("Geografia"),
	Curso8("Farmácia"),
	Curso9("Jornalismo"),
	Curso10("Administração");
	
    private final String Nome;       

	private Cursos(String nome) {
		this.Nome = nome;
	}
	
	@Override
   public String toString() {
        return Nome;
   }
	
   public static Cursos listarUm(String text) {
	    for (Cursos e : values()) {
	        if (e.Nome.equals(text)) {
	            return e;
	        }
	    }
	    return null;
	}
	
	
}
