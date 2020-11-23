package entity;

import java.time.LocalDate;

public class Professor extends Pessoa{

	private String Curso;
	
	private String Materia;

	public String getCurso() {
		return Curso;
	}

	public void setCurso(String curso) {
		Curso = curso;
	}

	public String getMateria() {
		return Materia;
	}

	public void setMateria(String materia) {
		Materia = materia;
	}

	public Professor() {
		
	}
	
	public Professor(int nR, String nome, String cPF, String email, LocalDate data_nascimento, String curso,
			String materia) {
		super(nR, nome, cPF, email, data_nascimento);
		this.Curso = curso;
		this.Materia = materia;
	}

	@Override
	public String toString() {
		return "Professor [Curso=" + Curso + ", Materia=" + Materia + ", getCurso()=" + getCurso() + ", getMateria()="
				+ getMateria() + ", getNR()=" + getNR() + ", getNome()=" + getNome() + ", getCPF()=" + getCPF()
				+ ", getEmail()=" + getEmail() + ", getData_nascimento()=" + getData_nascimento() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

	

	
	
	
}
