package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Endereco;
import entity.Professor;
import interfaces.IDao;
import repository.CNXJDBC;

public class DaoProfessor extends CNXJDBC implements IDao{

	private final String SQL_SELECIONA_PROFESSOR = "SELECT * FROM Professor order by Nome";

	private final String SQL_INSERE_PROFESSOR = "INSERT INTO Professor (Cpf,NR,Nome,Email,Curso,Data_nascimento,Materia," + 
		"Endereco_Id_endereco) VALUES (?,?,?,?,?,?,?,?);";

	private final String SQL_EXCLUI_PROFESSOR = "DELETE FROM Professor  WHERE Cpf = ?;";

	private final String SQL_ALTERA_PROFESSOR = "UPDATE Professor SET NR = ?, Nome = ? ,Email = ?, Curso = ?, Data_nascimento = ?, Materia = ?"
			+ "  WHERE Cpf = ?;";
	
	
	@Override
	public boolean adicionar(Object obj) {
		
		Professor professor = (Professor) obj;
		
		try (Connection conn = conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_PROFESSOR);) {
			
			pst.setString(1, professor.getCPF());
			pst.setInt(2, professor.getNR());
			pst.setString(3, professor.getNome());
			pst.setString(4, professor.getEmail());
			pst.setString(5, professor.getCurso());
			pst.setDate(6, Date.valueOf(professor.getData_nascimento()));
			pst.setString(7, professor.getMateria());
			pst.setString(8, Long.toString(professor.getId_endereco()));
			pst.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
		}
		
		return false;
	}

	@Override
	public boolean atualizar(Object obj) {
		
		Professor professor = (Professor) obj;
		
		try (Connection conn = conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_ALTERA_PROFESSOR);) {
			pst.setInt(1, professor.getNR());
			pst.setString(2, professor.getNome());
			pst.setString(3, professor.getEmail());
			pst.setString(4, professor.getCurso());
			pst.setDate(5, Date.valueOf(professor.getData_nascimento()));
			pst.setString(6, professor.getMateria());
			pst.setString(7,  professor.getCPF());
			pst.execute();
				
			return true;
		} catch (SQLException e) {
	    	System.out.println(e.getLocalizedMessage());
		}
		
		return false;
	}

	@Override
	public boolean excluir(Object obj) {
		
		Professor professor = (Professor) obj;
		
		try (Connection conn = this.conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_EXCLUI_PROFESSOR);) {
			pst.setString(1, professor.getCPF());
			
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList listar() {
		
		ArrayList<Professor> listaProfessores = new ArrayList<Professor>();

		Professor professor;
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_SELECIONA_PROFESSOR); ResultSet rs = pst.executeQuery();) {
			while (rs.next()) {
				professor = new Professor();
				
				professor.setCPF(rs.getString("CPF"));
				professor.setNome(rs.getString("NOME"));
				professor.setEmail(rs.getString("EMAIL"));
				professor.setData_nascimento(rs.getDate("DATA_NASCIMENTO").toLocalDate());
				professor.setCurso(rs.getString("CURSO"));
				professor.setMateria(rs.getString("MATERIA"));
				professor.setId_endereco(rs.getLong("ENDERECO_ID_ENDERECO"));
				professor.setNR(rs.getInt("NR"));
				professor.setEndereco((Endereco) new DaoEndereco().listarUm(String.valueOf(professor.getId_endereco())));
				listaProfessores.add(professor);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		
		return listaProfessores;
	}

		
	
	
	
}
