package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Professor;
import repository.CNXJDBC;

public class DaoProfessor{

	private final String SQL_SELECIONA_PROFESSOR = "SELECT * FROM Professor order by Nome";

	private final String SQL_INSERE_PROFESSOR = "INSERT INTO Professor (Cpf,NR,Nome,Email,Curso,Data_nascimento,Materia," + 
		"Endereco_Id_endereco) VALUES (?,?,?,?,?,?,?,?);";
		
	public ArrayList<Professor> listar() {
		
		ArrayList<Professor> listaProfessores = new ArrayList<Professor>();

		Professor professor;
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_PROFESSOR); ResultSet rs = pst.executeQuery();) {
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
								
				listaProfessores.add(professor);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao executar o Statement" + e.toString());
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		
		return listaProfessores;
	}

	public boolean adicionar(Professor professor) {
		try (Connection conn = new CNXJDBC().conexaoBanco(); PreparedStatement pst = conn.prepareStatement(SQL_INSERE_PROFESSOR);) {
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

	
	public boolean editar() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean excluir() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
