package controller;

import java.util.ArrayList;

import dao.DaoProfessor;
import entity.Professor;
import interfaces.ICrud;
import interfaces.IDao;

public class ControllerProfessor implements ICrud{

	private IDao daoProfessor;
		
	public ControllerProfessor(IDao daoProfessor) {
		this.daoProfessor = daoProfessor;
	}
	
	@Override
	public boolean adicionar(Object obj) {
		
		Professor professor = (Professor) obj;
		
		return daoProfessor.adicionar(professor);
	}


	@Override
	public boolean atualizar(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(Object obj) {
		return daoProfessor.excluir(obj);
	}

	@Override
	public Object listarUm(String obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList listar() {
		return daoProfessor.listar();
	} 

}
