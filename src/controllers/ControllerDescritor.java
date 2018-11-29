package controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import entidades.Item;
import entidades.ItemComparavel;
import entidades.Usuario;

/**
 * Classe que representa o controlador dos descritores cadastrados no sistema.
 * @author
 *
 */
public class ControllerDescritor {
	
	private Set<String> descritores = new HashSet<>();

	/**
	 * Construtor da classe ControllerDescritor
	 */
	public ControllerDescritor() {
		this.descritores = new HashSet<>();
	}

	/**
	 * Metodo responsavel por cadastrar um descritor no sistema. Contem uma excecao para verificar se o parametro inserido
	 * nao ira interferir no funcionamento do programa.
	 * @param descritor Nome do descritor
	 */
	public void cadastraDescritor(String descritor) {
		
		if (descritor == null || descritor.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: descricao nao pode ser vazia ou nula.");
		}
		
		if (descritores.contains(descritor.trim().toLowerCase())) {
			throw new IllegalArgumentException("Descritor de Item ja existente: " + descritor.trim().toLowerCase() + ".");
		}
		
		
		descritores.add(descritor.trim().toLowerCase());
	}
	
	/**
	 * 
	 * @param descritor
	 * @return
	 */
	public boolean contemDescritor(String descritor) {
		if (descritores.contains(descritor)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Metodo que lista os descritores de itens cadastrados no sistema, em ordem alfabetica pela descricao do mesmo.
	 * Na saida, eh exibido a quantidade do item e sua descricao.
	 * @param map Um mapa com os usuarios que sera necessario para acessar as listas de itens dos usuarios.
	 * @return O retorno eh uma string com a representacao textual dos itens
	 */
	public String listaDescritorDeItensParaDoacao(Map<String, Usuario> map) {
		Map<String, Integer> itens = new HashMap<String, Integer>();
		for (Usuario usuario : map.values()) {
			for (Item item : (usuario.getListaItens().values())) {
				itens.put(item.getDescritor(), item.getQuantidade());
			}
		}
		for (String descricao : descritores) {
			if(!itens.containsKey(descricao))
				itens.put(descricao, 0);
		}
		List<String> itensOrdenados = new ArrayList<String>();
		for(String descricao: itens.keySet()) {
			itensOrdenados.add(descricao);
		}
		Collections.sort(itensOrdenados);
		String listaFinal = "";
		for(String descricao : itensOrdenados) {
			listaFinal += itens.get(descricao) + " - " + descricao + " | ";
		}
		return listaFinal.substring(0, listaFinal.length()-3);
	}

	/**
	 * Metodo que lista todos os itens inseridos no sistema ordenada pela quantidade do item no sistema.
	 * Itens com a mesma quantidade serao ordenados pela ordem alfabética de descricao.
	 * @param map Um mapa com os usuarios que sera necessario para acessar as listas de itens dos usuarios.
	 * @return O retorno eh uma string com a representacao do id do item, a descricao, tag, quantidade e o doador.
	 */
	public String listaItensParaDoacao(Map<String, Usuario> map) {
		ArrayList<Item> itensOrdenados = new ArrayList<Item>();
		LinkedHashMap<String, Item> mapDeItens = new LinkedHashMap<String, Item>();
		HashSet<Item> setDeItens = new HashSet<Item>();
		String aux = "";
		
		for (Usuario usuario : map.values()) { 
			for (Item itens : (usuario.getListaItens().values())) {
				setDeItens.add(itens);
			}
		}
		
		//addAll
		
		itensOrdenados.addAll(setDeItens);

		Collections.sort(itensOrdenados, new ItemComparavel());
		
		for (Item itens : itensOrdenados) {
			mapDeItens.put(itens.getDescritor(), itens);
		}
		
		
		for (Item mapItensOrdenado : mapDeItens.values()) { 
			for (Usuario usuario : map.values()) {
				for (Item itens : (usuario.getListaItens().values())) {	
					String str = "";
					if (mapItensOrdenado.equals(itens)) {
						str = mapItensOrdenado.toStringCombo() + "doador: " + usuario.getNome() + "/" + usuario.getId().replace(".", "").replace("-", "")  + " | ";
						aux += str;
						break;
					}
				}
			}
		}
		
		return aux.substring(0, aux.length()-3);
		
	}
	
	/**
	 * Metodo que lista todos os itens relacionados a uma dada string de pesquisa.
	 * A listagem ocorre em ordem alfabética considerando os descritores dos itens. 
	 * @param desc Parametro designado pelo usuario para string de pesquisa.
	 * @param map Um mapa com os usuarios que sera necessario para acessar as listas de itens dos usuarios.
	 * @return O retorno eh uma string com a representacao do id do item, a descricao, tag e quantidade.
	 */
	public String pesquisaItemParaDoacaoPorDescricao(String desc, Map<String, Usuario> map) {
		if (desc == null || desc.trim().equals("")) {
			throw new IllegalArgumentException("Entrada invalida: texto da pesquisa nao pode ser vazio ou nulo.");
		}
		
		List<Item> listDeItens= new ArrayList<Item>();
		HashSet<Item> setDeItens = new HashSet<Item>();
		
		
		for (Usuario usuario : map.values()) {
			for (Item itens : (usuario.getListaItens().values())) {
				setDeItens.add(itens);
			}
		}
		
		for (Item itens : setDeItens) {
			listDeItens.add(itens);
		}
		
		Collections.sort(listDeItens);
		
		String toString = "";
		for (Item itens : listDeItens) {
			if (itens.getDescritor().contains(desc))
				toString += itens.idItemToString() + " | ";
		}
		return toString.substring(0, toString.length()-3);
	}	
}	
