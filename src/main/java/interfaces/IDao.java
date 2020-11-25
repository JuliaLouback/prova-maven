package interfaces;

import java.util.ArrayList;

public interface IDao {

	public boolean adicionar(Object obj);
	public boolean atualizar(Object obj);
	public boolean excluir(Object obj);
	public ArrayList pesquisa(String obj);
	public ArrayList listar();
}
