package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Endereco;
import entity.Funcionario;
import entity.Professor;
import interfaces.IDao;
import repository.CNXJDBC;

public class DaoFuncionario extends CNXJDBC implements IDao{

	private final String SQL_SELECIONA_FUNCIONARIO = "SELECT * FROM Funcionario order by Nome";

	private final String SQL_INSERE_FUNCIONARIO = "INSERT INTO Funcionario (Cpf,NR,Nome,Email,Cargo,Data_nascimento,Setor," + 
		"Endereco_Id_endereco) VALUES (?,?,?,?,?,?,?,?);";

	private final String SQL_EXCLUI_FUNCIONARIO = "DELETE FROM Funcionario  WHERE Cpf = ?;";

	private final String SQL_ALTERA_FUNCIONARIO = "UPDATE Funcionario SET NR = ?, Nome = ? ,Email = ?, Cargo = ?, Data_nascimento = ?, Setor = ?"
			+ "  WHERE Cpf = ?;";
	
	
	@Override
	public boolean adicionar(Object obj) {
		
		Funcionario funcionario = (Funcionario) obj;
		
		try (Connection conn = conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_FUNCIONARIO);) {
			
			pst.setString(1, funcionario.getCPF());
			pst.setInt(2, funcionario.getNR());
			pst.setString(3, funcionario.getNome());
			pst.setString(4, funcionario.getEmail());
			pst.setString(5, funcionario.getCargo());
			pst.setDate(6, Date.valueOf(funcionario.getData_nascimento()));
			pst.setString(7, funcionario.getSetor());
			pst.setString(8, Long.toString(funcionario.getId_endereco()));
			pst.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
		}
		
		return false;
	}

	@Override
	public boolean atualizar(Object obj) {
		
		Funcionario funcionario = (Funcionario) obj;
		
		try (Connection conn = conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_ALTERA_FUNCIONARIO);) {
			pst.setInt(1, funcionario.getNR());
			pst.setString(2, funcionario.getNome());
			pst.setString(3, funcionario.getEmail());
			pst.setString(4, funcionario.getCargo());
			pst.setDate(5, Date.valueOf(funcionario.getData_nascimento()));
			pst.setString(6, funcionario.getSetor());
			pst.setString(7,  funcionario.getCPF());
			pst.execute();
				
			return true;
		} catch (SQLException e) {
	    	System.out.println(e.getLocalizedMessage());
		}
		
		return false;
	}

	@Override
	public boolean excluir(Object obj) {
		
		Funcionario funcionario = (Funcionario) obj;
		
		try (Connection conn = this.conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_FUNCIONARIO);) {
			pst.setString(1, funcionario.getCPF());
			
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
	public ArrayList listar() {
		
		ArrayList<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();

		Funcionario funcionario;
		try (Connection conn = conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_FUNCIONARIO); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				funcionario = new Funcionario();
				
				funcionario.setCPF(rs.getString("CPF"));
				funcionario.setNome(rs.getString("NOME"));
				funcionario.setEmail(rs.getString("EMAIL"));
				funcionario.setData_nascimento(rs.getDate("DATA_NASCIMENTO").toLocalDate());
				funcionario.setCargo(rs.getString("CARGO"));
				funcionario.setSetor(rs.getString("SETOR"));
				funcionario.setId_endereco(rs.getLong("ENDERECO_ID_ENDERECO"));
				funcionario.setNR(rs.getInt("NR"));
				funcionario.setEndereco((Endereco) new DaoEndereco().listarUm(String.valueOf(funcionario.getId_endereco())));
				listaFuncionarios.add(funcionario);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		
		return listaFuncionarios;
	}

	@Override
	public ArrayList pesquisa(String obj) {
		
		String SQL_SELECIONA_PESQUISA = "SELECT * FROM Funcionario WHERE Setor = '"+obj+"';";
		
		ArrayList<Funcionario> listaFuncionarios = new ArrayList<Funcionario>();

		Funcionario funcionario;
		try (Connection conn = conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_PESQUISA); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				funcionario = new Funcionario();
				
				funcionario.setCPF(rs.getString("CPF"));
				funcionario.setNome(rs.getString("NOME"));
				funcionario.setEmail(rs.getString("EMAIL"));
				funcionario.setData_nascimento(rs.getDate("DATA_NASCIMENTO").toLocalDate());
				funcionario.setCargo(rs.getString("CARGO"));
				funcionario.setSetor(rs.getString("SETOR"));
				funcionario.setId_endereco(rs.getLong("ENDERECO_ID_ENDERECO"));
				funcionario.setNR(rs.getInt("NR"));
				funcionario.setEndereco((Endereco) new DaoEndereco().listarUm(String.valueOf(funcionario.getId_endereco())));
				listaFuncionarios.add(funcionario);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		
		return listaFuncionarios;
	}


}
