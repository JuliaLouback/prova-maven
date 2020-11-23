package entity;

import java.time.LocalDate;

public class Pessoa {

	private int NR;
	 
	private String Nome;
	
	private String CPF;
	
	private String Email;
	
	private LocalDate Data_nascimento;
	
	private long Id_endereco;
	
	private Endereco Endereco;

	public Endereco getEndereco() {
		return Endereco;
	}

	public void setEndereco(Endereco endereco) {
		Endereco = endereco;
	}

	public int getNR() {
		return NR;
	}

	public void setNR(int nR) {
		NR = nR;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public LocalDate getData_nascimento() {
		return Data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		Data_nascimento = data_nascimento;
	}

	public long getId_endereco() {
		return Id_endereco;
	}

	public void setId_endereco(long id_endereco) {
		Id_endereco = id_endereco;
	}

	public Pessoa () {
		
	}
	
	public Pessoa(int nR, String nome, String cPF, String email, LocalDate data_nascimento) {
		super();
		NR = nR;
		Nome = nome;
		CPF = cPF;
		Email = email;
		Data_nascimento = data_nascimento;
	}

	@Override
	public String toString() {
		return "Pessoa [NR=" + NR + ", Nome=" + Nome + ", CPF=" + CPF + ", Email=" + Email + ", Data_nascimento="
				+ Data_nascimento + "]";
	}
	
	
	
	
}
