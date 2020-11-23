package entity;

import java.time.LocalDate;

public class Aluno extends Pessoa{

	private String Curso;

	public String getCurso() {
		return Curso;
	}


	public void setCurso(String curso) {
		Curso = curso;
	}


	public Aluno() {
		
	}
	
	public Aluno(int nR, String nome, String cPF, String email, LocalDate data_nascimento, String curso) {
		super(nR, nome, cPF, email, data_nascimento);
		this.Curso = curso;
	}


	@Override
	public String toString() {
		return "Aluno [Curso=" + Curso + ", getCurso()=" + getCurso() + ", getNR()=" + getNR() + ", getNome()="
				+ getNome() + ", getCPF()=" + getCPF() + ", getEmail()=" + getEmail() + ", getData_nascimento()="
				+ getData_nascimento() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

	
}
