package trabalho1Prog3;

import java.util.List;
import java.util.Collections;

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
	private List<ProducaoCientifica> prodCientificas;
	private List<Orientacao> orientacoes;
	private List<Disciplina> disciplinas;
	
	public Docente(String[] celulas) {
		try {
			this.codigo = Integer.parseInt(celulas[0]);
		} catch (NumberFormatException e) {
			System.out.println("Erro de formatacao");
		}
		this.nome = celulas[1];
		this.departamento = celulas[2];
		this.prodCientificas = new ArrayList<ProducaoCientifica>();
		this.orientacoes = new ArrayList<Orientacao>();
		this.disciplinas = new ArrayList<Disciplina>();
	}
	
	@Override
	public boolean equals(Object o) {
		return this.codigo == ((Docente)o).codigo;
	}

	public boolean docenteJaPossuiDisciplina(int codDisciplina) {
		for(Disciplina disc : disciplinas) {
			if(disc.getCodigo() == codDisciplina)
				return true;
		}
		return false;
	}
	
	public void adicionaDisciplina(Disciplina disc) {
		this.disciplinas.add(disc);
	}
	
	public int compareTo(Docente outroDocente) {
		return this.nome.compareTo(outroDocente.nome);
	}

	public List<Disciplina> getdisciplinas(){
		return this.disciplinas;
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
	
	public List<Disciplina> getDisciplinasDadas(){
		return this.disciplinas;
	}

	public int getTHSemanaisOrientacao() {
		int qtd = 0;
		for (Orientacao orienta : orientacoes)
			qtd += orienta.getCHsemanal();
		return qtd;
	}
	
	public int getTHSemanaisAulas() {
		int hrs = 0;
		for(Disciplina dis : disciplinas) {
			hrs += dis.getcHSemanal();
		}
		return hrs;
	}

	public int getQtdProdCientificasQualificadas() {
		int qtd = 0;
		for (ProducaoCientifica prod : prodCientificas)
			if (prod.ehQualificada() == true)
				qtd++;
		return qtd;
	}

	public int getQtdProdCientificasNQualificadas() {
		return this.prodCientificas.size()-this.getQtdProdCientificasQualificadas();
	}

	public void adicionaProdCientificaALista(ProducaoCientifica prod) {
		this.prodCientificas.add(prod);
	}

	public ArrayList<OrientaPos> getOrientaPos() {
		ArrayList<OrientaPos> oriPos = new ArrayList<OrientaPos>();
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

	public ArrayList<Curso> getCursos() {
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		for(Disciplina disc : disciplinas) {
			Curso curs = disc.getCurso();
			if(!cursos.contains(curs))
				cursos.add(curs);
		}
		Collections.sort(cursos);
		return cursos;
	}
}