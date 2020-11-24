package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Endereco;
import entity.Professor;
import interfaces.IDao;
import repository.CNXJDBC;


public class DaoEndereco extends CNXJDBC implements IDao{
	
	private final String SQL_INSERE_ENDERECO = "INSERT INTO Endereco (Cep,Numero,Rua,Bairro,Cidade,Estado) VALUES (?,?,?,?,?,?);";
	
	private final String SQL_ALTERA_ENDERECO = "UPDATE Endereco SET Cep=?, Numero = ?, Rua = ?, Bairro = ?, Cidade = ?, Estado = ?"
			+ "  WHERE Id_endereco = ?;";
	
	private final String SQL_EXCLUI_ENDERECO = "DELETE FROM Endereco WHERE Id_endereco = ?;";

	
	public long adicionarEndereco(Endereco endereco) {
		
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
		
		Endereco endereco = (Endereco) obj;
		
		try (Connection conn = conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_ALTERA_ENDERECO);) {
			pst.setString(1, endereco.getCep());
			pst.setString(2,String.valueOf(endereco.getNumero()));
			pst.setString(3, endereco.getRua());
			pst.setString(4, endereco.getBairro());
			pst.setString(5, endereco.getCidade());
			pst.setString(6, endereco.getEstado());
			pst.setLong(7, endereco.getId_endereco());
			pst.execute();
				
			return true;
		} catch (SQLException e) {
	    	System.out.println(e.getLocalizedMessage());
		}
		
		return false;
	}

	@Override
	public boolean excluir(Object obj) {
		
		Endereco endereco = (Endereco) obj;
		
		try (Connection conn = this.conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_ENDERECO);) {
			pst.setLong(1, endereco.getId_endereco());
			
			boolean resultado = false;
			
			int rowsUpdated = pst.executeUpdate();
			if (rowsUpdated > 0) {
				resultado = true;
			}
			
			return resultado;
		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statment " + e.toString());
		}
		
		return false;
	}

	@Override
	public Object listarUm(String obj) {
		
		int id_endereco = Integer.parseInt(obj);
		
		String SQL_SELECIONA_ENDERECO = "SELECT * FROM Endereco WHERE Id_endereco = "+id_endereco+";";
		
		Endereco endereco = new Endereco();
		
		try (Connection conn = new CNXJDBC().conexaoBanco();PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_ENDERECO); ResultSet rs = pst.executeQuery();) {
			
			while (rs.next()) {
				endereco.setId_endereco((rs.getInt("ID_ENDERECO")));
				endereco.setCep(rs.getString("CEP"));
				endereco.setNumero(rs.getInt("NUMERO"));
				endereco.setRua(rs.getString("RUA"));
				endereco.setBairro(rs.getString("BAIRRO"));
				endereco.setCidade(rs.getString("CIDADE"));
				endereco.setEstado(rs.getString("ESTADO"));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		}

		return endereco;
	}

	@Override
	public ArrayList listar() {
		return null;
	}
}
