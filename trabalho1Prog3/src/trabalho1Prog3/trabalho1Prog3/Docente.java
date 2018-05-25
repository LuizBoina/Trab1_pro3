package trabalho1Prog3;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public class Docente implements Comparable<Docente> , Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private int codigo;
	private String departamento;
	private List<Disciplina> disciplinasDadas;
	private List<ProducaoCientifica> prodCientificas;
	private List<Orientacao> orientacoes;
	
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
	
	@Override
	public boolean equals(Object o) {
		return this.codigo == ((Docente)o).codigo;
	}

	
	public int compareTo(Docente outroDocente) {
		return this.nome.compareTo(outroDocente.nome);
	}

	public List<Disciplina> getDisciplinasDadas() {
		return this.disciplinasDadas;
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

	public ArrayList<OrientaPos> getOrientaPos() {
		ArrayList oriPos = new ArrayList<OrientaPos>();
		for (Orientacao ori : orientacoes) {
			try {
				OrientaPos o = (OrientaPos) ori;
				oriPos.add(o);
			} catch (ClassCastException err) {
				err.printStackTrace();
			}
		}
		return oriPos;
	}
}