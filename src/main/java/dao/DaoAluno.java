package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Aluno;
import entity.Endereco;
import interfaces.IDao;
import repository.CNXJDBC;

public class DaoAluno extends CNXJDBC implements IDao {

	
	private final String SQL_SELECIONA_ALUNO = "SELECT * FROM Aluno order by Nome";

	private final String SQL_INSERE_ALUNO = "INSERT INTO Aluno (Cpf,NR,Nome,Email,Curso,Data_nascimento," + 
		"Endereco_Id_endereco) VALUES (?,?,?,?,?,?,?);";

	private final String SQL_EXCLUI_ALUNO = "DELETE FROM Aluno WHERE Cpf = ?;";

	private final String SQL_ALTERA_ALUNO = "UPDATE Aluno SET NR = ?, Nome = ? ,Email = ?, Curso = ?, Data_nascimento = ?"
			+ "  WHERE Cpf = ?;";
	
	
	@Override
	public boolean adicionar(Object obj) {
		
		Aluno aluno = (Aluno) obj;
		
		try (Connection conn = conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_ALUNO);) {
			
			pst.setString(1, aluno.getCPF());
			pst.setInt(2, aluno.getNR());
			pst.setString(3, aluno.getNome());
			pst.setString(4, aluno.getEmail());
			pst.setString(5, aluno.getCurso());
			pst.setDate(6, Date.valueOf(aluno.getData_nascimento()));
			pst.setString(7, Long.toString(aluno.getId_endereco()));
			pst.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
		}
		
		return false;
	}

	@Override
	public boolean atualizar(Object obj) {
		
		Aluno aluno = (Aluno) obj;
		
		try (Connection conn = conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_ALTERA_ALUNO);) {
			pst.setInt(1, aluno.getNR());
			pst.setString(2, aluno.getNome());
			pst.setString(3, aluno.getEmail());
			pst.setString(4, aluno.getCurso());
			pst.setDate(5, Date.valueOf(aluno.getData_nascimento()));
			pst.setString(6,  aluno.getCPF());
			pst.execute();
				
			return true;
		} catch (SQLException e) {
	    	System.out.println(e.getLocalizedMessage());
		}
		
		return false;
	}

	@Override
	public boolean excluir(Object obj) {
		
		Aluno aluno = (Aluno) obj;
		
		try (Connection conn = this.conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_ALUNO);) {
			pst.setString(1, aluno.getCPF());
			
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
		
		ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();

		Aluno aluno;
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_ALUNO); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				aluno = new Aluno();
				
				aluno.setCPF(rs.getString("CPF"));
				aluno.setNome(rs.getString("NOME"));
				aluno.setEmail(rs.getString("EMAIL"));
				aluno.setData_nascimento(rs.getDate("DATA_NASCIMENTO").toLocalDate());
				aluno.setCurso(rs.getString("CURSO"));
				aluno.setId_endereco(rs.getLong("ENDERECO_ID_ENDERECO"));
				aluno.setNR(rs.getInt("NR"));
				aluno.setEndereco((Endereco) new DaoEndereco().listarUm(String.valueOf(aluno.getId_endereco())));
				listaAlunos.add(aluno);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		
		return listaAlunos;
	}

	@Override
	public ArrayList pesquisa(String obj) {
		
		String SQL_SELECIONA_PESQUISA = "SELECT * FROM Aluno WHERE Curso = '"+obj+"';";
		
		ArrayList<Aluno> listaAlunos = new ArrayList<Aluno>();

		Aluno aluno;
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_PESQUISA); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				aluno = new Aluno();
				
				aluno.setCPF(rs.getString("CPF"));
				aluno.setNome(rs.getString("NOME"));
				aluno.setEmail(rs.getString("EMAIL"));
				aluno.setData_nascimento(rs.getDate("DATA_NASCIMENTO").toLocalDate());
				aluno.setCurso(rs.getString("CURSO"));
				aluno.setId_endereco(rs.getLong("ENDERECO_ID_ENDERECO"));
				aluno.setNR(rs.getInt("NR"));
				aluno.setEndereco((Endereco) new DaoEndereco().listarUm(String.valueOf(aluno.getId_endereco())));
				listaAlunos.add(aluno);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		
		return listaAlunos;
	}
}
