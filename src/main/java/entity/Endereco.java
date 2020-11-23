package entity;

public class Endereco {
	
	private long Id_endereco;
	
	private String Cep;
	
	private int Numero;
	
	private String Rua;
	
	private String Bairro;
	
	private String Cidade;
	
	private String Estado;
	
	public long getId_endereco() {
		return Id_endereco;
	}

	public void setId_endereco(long id_endereco) {
		Id_endereco = id_endereco;
	}

	public String getCep() {
		return Cep;
	}

	public void setCep(String cep) {
		Cep = cep;
	}


	public int getNumero() {
		return Numero;
	}


	public void setNumero(int numero) {
		Numero = numero;
	}

	public String getRua() {
		return Rua;
	}

	public void setRua(String rua) {
		Rua = rua;
	}

	public String getBairro() {
		return Bairro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
	}

	public String getCidade() {
		return Cidade;
	}

	public void setCidade(String cidade) {
		Cidade = cidade;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public Endereco(int id_endereco, String cep, int numero, String rua, String bairro, String cidade, String estado) {
		super();
		Id_endereco = id_endereco;
		Cep = cep;
		Numero = numero;
		Rua = rua;
		Bairro = bairro;
		Cidade = cidade;
		Estado = estado;
	}

	public Endereco() {
		
	}


	@Override
	public String toString() {
		return "Endereco [Id_endereco=" + Id_endereco + ", Cep=" + Cep + ", Numero=" + Numero + ", Rua=" + Rua
				+ ", Bairro=" + Bairro + ", Cidade=" + Cidade + ", Estado=" + Estado + "]";
	}
	
}
