package entity;

import java.time.LocalDate;

public class Funcionario extends Pessoa{

	private String Cargo;
	
	private String Setor;

	public String getCargo() {
		return Cargo;
	}

	public void setCargo(String cargo) {
		Cargo = cargo;
	}

	public String getSetor() {
		return Setor;
	}

	public void setSetor(String setor) {
		Setor = setor;
	}
	
	public Funcionario () {
		
	}

	public Funcionario(int nR, String nome, String cPF, String email, LocalDate data_nascimento, String cargo, String setor) {
		super(nR, nome, cPF, email, data_nascimento);
		this.Cargo = cargo;
		this.Setor = setor;
	}

	@Override
	public String toString() {
		return "Funcionario [Cargo=" + Cargo + ", Setor=" + Setor + ", getEndereco()=" + getEndereco() + ", getNR()="
				+ getNR() + ", getNome()=" + getNome() + ", getCPF()=" + getCPF() + ", getEmail()=" + getEmail()
				+ ", getData_nascimento()=" + getData_nascimento() + ", getId_endereco()=" + getId_endereco()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
	
	
	
	
	
	
}
