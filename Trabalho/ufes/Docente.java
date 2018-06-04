package ufes;

import java.util.List;
import java.util.Locale;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;

/**
 * Essa classe contém as informações de um Docente e os métodos necessários para sua manipulação.
 * 
 * <p>Essa classe é membro do pacote ufes</a>.
 * 
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 */
public class Docente implements Comparable<Docente>, Serializable {
	
	//Atributos privados
	/**Atributo que possui a versão de serialização de Docente*/
	private static final long serialVersionUID = 1L;
	/**Atributo que possui o nome do Docente*/
	private String nome;
	/**Atributo que possui o código do Docente*/
	private int codigo;
	/**Atributo que possui o nome do departamento do Docente*/
	private String departamento;
	/**Atributo que possui uma lista de produções científicas do Docente*/
	private List<ProducaoCientifica> prodCientificas;
	/**Atributo que possui uma lista de orientações do Docente*/
	private List<Orientacao> orientacoes;
	/**Atributo que possui uma lista de cursos do Docente*/
	private List<Curso> cursos;

	/**
	 * Construtor de Docente que extrai as informações de Docente de um array de String.
	 * 
	 * @param celulas é o array de String retirado de uma tabela de docentes.
	 * @throws NumberFormatException se não for possível converter alguma String do array em int.
	 */
	public Docente(String[] celulas) throws NumberFormatException {
		this.codigo = Integer.parseInt(celulas[0]);
		this.prodCientificas = new ArrayList<ProducaoCientifica>();
		this.orientacoes = new ArrayList<Orientacao>();
		this.cursos = new ArrayList<Curso>();
		this.nome = celulas[1];
		this.departamento = celulas[2];
	}

	/**
	 * Método que verifica se o Docente já possui um curso.
	 * 
	 * @param codCurso é o código do curso a ser procurado.
	 * @return um boolean, true se o Docente já possui o curso, false caso não.
	 */
	public boolean docenteJaPossuiCurso(int codCurso) {
		for (Curso cur : cursos) {
			if (cur.getCodigoCurso() == codCurso)
				return true;
		}
		return false;
	}

	/**
	 * Método que adiciona um curso ao Docente.
	 * 
	 * @param cur é o Curso a ser adicionado.
	 */
	public void adicionaCurso(Curso cur) {
		this.cursos.add(cur);
	}

	/**
	 * Método que compara um Docente a outro Docente.
	 * 
	 * @param outroDocente é o outro Docente que vai ser comparado.
	 * @return um int que é menor, maior ou igual a zero se o nome do Docente fonte for antes, depois ou igual ao nome
	 * 		   do outro Docente segundo a ordem alfabética.
	 */
	public int compareTo(Docente outroDocente) {
		Locale loc = new Locale("pt", "BR");
		Collator col = Collator.getInstance(loc);
		return col.compare(this.getNome(), outroDocente.getNome());
	}

	/**
	 * Método que retorna uma lista de cursos do Docente.
	 * 
	 * @return uma List<Curso> de Curso do Docente.
	 */
	public List<Curso> getCursos() {
		return this.cursos;
	}

	/**
	 * Método que retorna o nome do departamento do Docente.
	 * 
	 * @return uma String com o nome do departamento do Docente.
	 */
	public String getDepartamento() {
		return this.departamento;
	}

	/**
	 * Método que retorna o código do Docente.
	 * 
	 * @return um int com o código do Docente.
	 */
	public int getCodigo() {
		return this.codigo;
	}

	/**
	 * Método que retorna o nome do Docente.
	 * 
	 * @return uma String com o nome do Docente.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Método que adiciona uma orientação ao Docente.
	 * 
	 * @param orienta é a Orientacao a ser adicionada ao Docente.
	 */
	public void adicionaOrientacaoALista(Orientacao orienta) {
		this.orientacoes.add(orienta);
	}

	/**
	 * Método que retorna uma lista de disciplinas lecionadas pelo Docente.
	 * 
	 * @return uma List<Disciplina> de Disciplina do Docente.
	 */
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

	/**
	 * Método que retorna o total de horas semanais em orientações do Docente.
	 * 
	 * @return um int com o total de horas semanais em orientações do Docente.
	 */
	public int getTHSemanaisOrientacao() {
		int qtd = 0;
		for (Orientacao orienta : orientacoes)
			qtd += orienta.getCHsemanal();
		return qtd;
	}

	/**
	 * Método que retorna a quantidade de produções científicas qualificadas do Docente.
	 * 
	 * @return um int com a quantidade de produções científicas qualificadas do Docente.
	 */
	public int getQtdProdCientificasQualificadas() {
		int qtd = 0;
		for (ProducaoCientifica prod : prodCientificas)
			if (prod.ehQualificada() == true)
				qtd++;
		return qtd;
	}

	/**
	 * Método que retorna a quantidade de produções científicas não qualificadas do Docente.
	 * 
	 * @return um int com a quantidade de produções científicas não qualificadas do Docente.
	 */
	public int getQtdProdCientificasNQualificadas() {
		return this.prodCientificas.size() - this.getQtdProdCientificasQualificadas();
	}

	/**
	 * Método que adiciona uma produção científica ao Docente.
	 * 
	 * @param prod é o ProducaoCientifica a ser adicionada.
	 */
	public void adicionaProdCientifica(ProducaoCientifica prod) {
		this.prodCientificas.add(prod);
	}

	/**
	 * Método que retorna uma lista de orientações de pós-graduação do Docente.
	 * 
	 * @return uma List<OrientaPos> de OrientaPos do Docente.
	 */
	public List<OrientaPos> getOrientaPos() {
		ArrayList<OrientaPos> oriPos = new ArrayList<OrientaPos>();
		for (Orientacao ori : orientacoes) {
			if (ori instanceof OrientaPos)
				oriPos.add((OrientaPos) ori);
		}
		return oriPos;
	}
}