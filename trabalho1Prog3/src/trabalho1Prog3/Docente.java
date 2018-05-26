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
	private List<ProducaoCientifica> prodCientificas;
	private List<Orientacao> orientacoes;
	private List<Curso> cursos;
	
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
		this.cursos = new ArrayList<Curso>();
	}
	
	@Override
	public boolean equals(Object o) {
		return this.codigo == ((Docente)o).codigo;
	}

	public boolean docenteJaPossuiCurso(int codCurso) {
		for(Curso cur : cursos) {
			if(cur.getCodigoCurso() == codCurso)
				return true;
		}
		return false;
	}
	
	public void adicionaCurso(Curso cur) {
		this.cursos.add(cur);
	}
	
	public int compareTo(Docente outroDocente) {
		return this.nome.compareTo(outroDocente.nome);
	}

	public List<Curso> getCursos(){
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
	
	public List<Disciplina> getDisciplinasDadas(){
		ArrayList<Disciplina> disci = new ArrayList<Disciplina>();
		for (Curso cur : cursos) {
			for(Disciplina dis : cur.getDisciplinas()) {
				if(dis.getCodigoDocente() == this.codigo)
					disci.add(dis);
			}
		}
		return disci;
	}

	public int getTotalHorasSemanaisAulas() {
		int qtd = 0;
		for (Curso cur : cursos) {
			for(Disciplina dis : cur.getDisciplinas()) {
			if(dis.getCodigoDocente() == this.codigo)	
				qtd += dis.getcHSemanal();
			}
		}
		return qtd;
	}

	public int getTotalHorasSemestraisAulas() {
		int qtd = 0;
		for(Curso cur: cursos) {
			for(Disciplina dis : cur.getDisciplinas()) {
				if(dis.getCodigoDocente() == this.codigo)
					qtd += dis.getcHSemestral();
			}
		}
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