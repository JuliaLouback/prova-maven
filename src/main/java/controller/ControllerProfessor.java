package controller;

import dao.DaoProfessor;
import entity.Professor;

public class ControllerProfessor {

	DaoProfessor daoProfessor;
		
	public ControllerProfessor(DaoProfessor daoProfessor) {
		this.daoProfessor = daoProfessor;
	}
	
	public boolean adicionar(Professor professor) {
		
		return daoProfessor.adicionar(professor);
	} 

}
