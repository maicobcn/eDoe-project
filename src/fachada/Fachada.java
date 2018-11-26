package fachada;

import java.io.IOException;

import controllers.ControllerDescricoes;
import controllers.ControllerUsuario;

public class Fachada {

	ControllerUsuario controllerUsuario = new ControllerUsuario();
	ControllerDescricoes controllerDescritor = new ControllerDescricoes();
	
	public void lerReceptores(String caminho) throws IOException{
		controllerUsuario.lerReceptores(caminho);
	}
	
	public String adicionaDoador(String id, String nome, String email, String celular, String classe) {
		return controllerUsuario.adicionaDoador(id, nome, email, celular, classe);
	}
	
	public String pesquisaUsuarioPorId(String id) {
		return controllerUsuario.pesquisaUsuarioPorId(id);
	}
	
	public String pesquisaUsuarioPorNome(String nome) {
		return controllerUsuario.pesquisaUsuarioPorNome(nome);
	}
	
	public String atualizaUsuario(String id, String nome, String email, String celular) {
		return controllerUsuario.atualizaUsuario(id, nome, email, celular);
	}
	
	public void removeUsuario(String id) {
		controllerUsuario.removeUsuario(id);
	}
	
	public String listaDescritorDeItensParaDoacao() {
		return controllerDescritor.listagemPorQuantidadeEDescricao(controllerUsuario.getUsuarios());
	}
	
	public String listaItensParaDoacao() {
		return controllerDescritor.listagemPorItem(controllerUsuario.getUsuarios());
	}
	
	public String pesquisaItemParaDoacaoPorDescricao() {
		return "";
	}
	
	// MÉTODO IMPLEMENTADO
		public void adicionaDescritor(String descritor) {
			controllerDescritor.cadastraDescritor(descritor);
		}
		
		public void adicionaItemParaDoacao(String idDoador, String descricaoItem, int quantidade, String tags) {
			controllerUsuario.cadastraItem(idDoador, descricaoItem, quantidade, tags);
		}
		
		public String exibeItem() {
			return null;
		}
		
		public void atualizaItem() {
			
		}
		
		public void removeItem() {
			
	}
	 
	
}
