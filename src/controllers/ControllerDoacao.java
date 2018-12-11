package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entidades.DoacaoComparavelPorData;

public class ControllerDoacao implements Serializable{
	private List<String> doacoes = new ArrayList<String>();
	
	public String adicionaDoacao(String doacao) {
		this.doacoes.add(doacao);
		return doacao;
	}
	
	public String listaDoacoes() {
		String doacoesParaExibicao = "";		
		Collections.sort(doacoes, new DoacaoComparavelPorData());
		for(String doacao : doacoes) {
			doacoesParaExibicao += doacao + " | ";
		}
		return doacoesParaExibicao.substring(0, doacoesParaExibicao.length() - 3);
	}
	
	
}
