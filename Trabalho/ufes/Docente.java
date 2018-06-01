package ufes;

import java.util.List;
import java.util.Locale;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;

public class Docente implements Comparable<Docente>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private int codigo;
	private String departamento;
	private List<ProducaoCientifica> prodCientificas;
	private List<Orientacao> orientacoes;
	private List<Curso> cursos;

	public Docente(String[] celulas) throws NumberFormatException {
		this.codigo = Integer.parseInt(celulas[0]);
		this.prodCientificas = new ArrayList<ProducaoCientifica>();
		this.orientacoes = new ArrayList<Orientacao>();
		this.cursos = new ArrayList<Curso>();
		this.nome = celulas[1];
		this.departamento = celulas[2];
	}

	@Override
	public boolean equals(Object o) {
		return this.codigo == ((Docente) o).codigo;
	}

	public boolean docenteJaPossuiCurso(int codCurso) {
		for (Curso cur : cursos) {
			if (cur.getCodigoCurso() == codCurso)
				return true;
		}
		return false;
	}

	public void adicionaCurso(Curso cur) {
		this.cursos.add(cur);
	}

	public int compareTo(Docente outroDocente) {
		Locale loc = new Locale("pt", "BR");
		Collator col = Collator.getInstance(loc);
		return col.compare(this.getNome(), outroDocente.getNome());
	}

	public List<Curso> getCursos() {
		return this.cursos;
	}

	public String getDepartamento() {
		return this.departamento;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public String getNome() {
		return this.nome;
	}

	public void adicionaOrientacaoALista(Orientacao orienta) {
		this.orientacoes.add(orienta);
	}

	public List<Disciplina> getDisciplinasDadas() {
		ArrayList<Disciplina> disci = new ArrayList<Disciplina>();
		for (Curso cur : cursos) {
			for (Disciplina dis : cur.getDisciplinas()) {
				if (dis.getCodigoDocente() == this.codigo)
					disci.add(dis);
			}
		}
		return disci;
	}

	public int getTHSemanaisOrientacao() {
		int qtd = 0;
		for (Orientacao orienta : orientacoes)
			qtd += orienta.getCHsemanal();
		return qtd;
	}

	public int getQtdProdCientificasQualificadas() {
		int qtd = 0;
		for (ProducaoCientifica prod : prodCientificas)
			if (prod.ehQualificada() == true)
				qtd++;
		return qtd;
	}

	public int getQtdProdCientificasNQualificadas() {
		return this.prodCientificas.size() - this.getQtdProdCientificasQualificadas();
	}

	public void adicionaProdCientifica(ProducaoCientifica prod) {
		this.prodCientificas.add(prod);
	}

	public ArrayList<OrientaPos> getOrientaPos() {
		ArrayList<OrientaPos> oriPos = new ArrayList<OrientaPos>();
		for (Orientacao ori : orientacoes) {
			if (ori instanceof OrientaPos)
				oriPos.add((OrientaPos) ori);
		}
		return oriPos;
	}
}