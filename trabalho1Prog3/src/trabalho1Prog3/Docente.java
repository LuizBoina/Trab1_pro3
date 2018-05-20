package trabalho1Prog3;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class Docente {
	private String nome;
	private int codigo;
	private String departamento;
	private List<Disciplina> disciplinasDadas;
	private List<ProducaoCientifica> prodCientificas;
	private List<Orientacao> orientacoes;
	private List<Curso> cursosDados;
	
	public Docente(String[] celulas) {
		try {
			this.codigo = Integer.parseInt(celulas[0]);
		} catch (NumberFormatException e) {
			System.out.println("Erro de formatacao");
		}
		this.nome = celulas[1];
		this.departamento = celulas[2];
		this.prodCientificas = new ArrayList<ProducaoCientifica>();
		this.disciplinasDadas = new ArrayList<Disciplina>();
		this.orientacoes = new ArrayList<Orientacao>();
	}

	public String getDepartamento() {
		return this.departamento;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public void adicionaDisciplinaALista(Disciplina disciplina) {
		this.disciplinasDadas.add(disciplina);
	}

	public String getNome() {
		return this.nome;
	}

	public void adicionaOrientacaoALista(Orientacao orienta) {
		this.orientacoes.add(orienta);
	}

	public int getTotalHorasSemanaisAulas() {
		int qtd = 0;
		for (Disciplina dis : disciplinasDadas)
			qtd += dis.getcHSemanal();
		return qtd;
	}

	public int getTotalHorasSemestraisAulas() {
		int qtd = 0;
		for (Disciplina dis : disciplinasDadas)
			qtd += dis.getcHSemestral();
		return qtd;
	}

	public int getTotalHorasSemanaisOrientacao() {
		int qtd = 0;
		for (Orientacao orienta : orientacoes)
			qtd += orienta.getCHsemanal();
		return qtd;
	}

	public int getQuantidadeProdCientificasQualificadas() {
		int qtd = 0;
		for (ProducaoCientifica prod : prodCientificas)
			if (prod.ehQualificada())
				qtd++;
		return qtd;
	}

	public int getQuantidadeProdCientidicasNQualificadas() {
		int qtd = 0;
		for (ProducaoCientifica prod : prodCientificas)
			if (!prod.ehQualificada())
				qtd++;
		return qtd;
	}

	public void adicionaProdCientificaALista(ProducaoCientifica prod) {
		this.prodCientificas.add(prod);
	}

	public String toStringParaPad() {
		return this.nome + ";" + this.departamento + ";" + String.valueOf(this.getTotalHorasSemanaisAulas()) + ";"
				+ String.valueOf(this.getTotalHorasSemestraisAulas()) + ";"
				+ String.valueOf(this.getTotalHorasSemanaisOrientacao()) + ";"
				+ String.valueOf(this.getQuantidadeProdCientificasQualificadas()) + ";"
				+ String.valueOf(this.getQuantidadeProdCientidicasNQualificadas());
	}

	// metodo que retorna o Curso a partir da disciplina dada
}

class ComparaPorNome implements Comparator<Docente>{
	public int compare(Docente d1, Docente d2) {
		return d1.getNome().compareTo(d2.getNome());
	}
}

class ComparaPorDepartamento implements Comparator<Docente>{
	public int compare(Docente d1, Docente d2) {
		if(d1.getDepartamento().compareTo(d2.getDepartamento())>0)
			return 1;
		else if(d1.getDepartamento().compareTo(d2.getDepartamento())<0)
				return -1;
		else {
			if(d1.getNome().compareTo(d2.getNome())>0)
				return 1;
			else if(d1.getNome().compareTo(d2.getNome())<0)
				return -1;
			else {
				
			}
		}
	}
}

class ComparaPorNomeECodDisciplina implements Comparator<Docente>{
	public int compare(Docente d1, Docente d2) {
		if(d1.getNome().compareTo(d2.getNome())>0)
			return 1;
		else if(d1.getNome().compareTo(d2.getNome())<0)
				return -1;
		else
			
	}
}