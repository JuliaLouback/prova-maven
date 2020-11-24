package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Endereco;
import interfaces.IDao;
import repository.CNXJDBC;


public class DaoEndereco extends CNXJDBC implements IDao{
	
	private final String SQL_INSERE_ENDERECO = "INSERT INTO Endereco (Cep,Numero,Rua,Bairro,Cidade,Estado) VALUES (?,?,?,?,?,?);";
	
	private final String SQL_ALTERA_ENDERECO = "UPDATE Endereco SET Cep=?, Numero = ?, Rua = ?, Bairro = ?, Cidade = ?, Estado = ?"
			+ "  WHERE Id_endereco = ?;";
	
	private final String SQL_EXCLUI_ENDERECO = "DELETE FROM Endereco WHERE Id_endereco = ?;";

	
	public long adicionarEndereco(Endereco endereco) {
		
		int id = 0;
		
		try (Connection conn = conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_ENDERECO,  Statement.RETURN_GENERATED_KEYS);) {
			pst.setString(1, endereco.getCep());
			pst.setString(2,String.valueOf(endereco.getNumero()));
			pst.setString(3, endereco.getRua());
			pst.setString(4, endereco.getBairro());
			pst.setString(5, endereco.getCidade());
			pst.setString(6, endereco.getEstado());
			pst.executeUpdate();
			
			try (ResultSet generatedKeys = pst.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                return generatedKeys.getLong(1);
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }			
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		
		return 0;
	}
	
	@Override
	public boolean adicionar(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean atualizar(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object listarUm(String obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList listar() {
		// TODO Auto-generated method stub
		return null;
	}
}
