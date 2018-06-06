package ufes;

import java.io.Serializable;

/**
 * Essa classe contém as informações de uma Disciplina e os métodos necessários para sua manipulação.
 * 
 * <p>Essa classe é membro do pacote ufes</a>.
 * 
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 */
public class Disciplina implements Comparable<Disciplina>, Serializable {
	
	//Atributos privados
	/**Atributo que possui a versão de serialização de Disciplina*/
	private static final long serialVersionUID = 1L;
	/**Atributo que possui o código da Disciplina*/
	private String codigo;
	/**Atributo que possui o nome da Disciplina*/
	private String nome;
	/**Atributo que possui o código do docente que leciona a Disciplina*/
	private int codigoDocente;
	/**Atributo que possui a carga horária semanal da Disciplina*/
	private int cHSemanal;
	/**Atributo que possui a carga horária semestral da Disciplina*/
	private int cHSemestral;
	/**Atributo que possui o código do curso da Disciplina*/
	private int codigoCurso;

	/**
	 * Construtor de Disciplina que extrai as informações de Disciplina de array de String.
	 * 
	 * @param celulas é um array de String retirado de uma planilha de disciplinas.
	 * @throws NumberFormatException se não for possível converter para int as String do array. 
	 */
	public Disciplina(String[] celulas) throws NumberFormatException {
		this.codigo = celulas[0].trim();
		this.codigoDocente = Integer.parseInt(celulas[2].trim());
		this.cHSemanal = Integer.parseInt(celulas[3].trim());
		this.cHSemestral = Integer.parseInt(celulas[4].trim());
		this.codigoCurso = Integer.parseInt(celulas[5].trim());
		this.nome = celulas[1].trim();
	}

	/**
	 * Método que retorna o código da Disciplina.
	 * 
	 * @return um int com o código da Disciplina.
	 */
	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * Método que retorna o nome da Disciplina.
	 * 
	 * @return uma String com o nome da Disciplina.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Método que retorna o código do curso da Disciplina.
	 * 
	 * @return um int com o código do curso da Disciplina.
	 */
	public int getCodigoCurso() {
		return this.codigoCurso;
	}

	/**
	 * Método que compara uma Disciplina a outra Disciplina.
	 * 
	 * @param outraDisciplina é a outra Disciplina que vai ser comparada.
	 * @return um int que é menor, maior ou igual a zero se o código da Disciplina fonte for menor, maior ou igual ao 
	 * 		   código da outra Disciplina.
	 */
	public int compareTo(Disciplina outraDisciplina) {
		return this.codigo.compareTo(outraDisciplina.getCodigo());
	}

	/**
	 * Método que retorna o código do docente que leciona a Disciplina.
	 * 
	 * @return um int com o código do docente que leciona a Disciplina.
	 */
	public int getCodigoDocente() {
		return this.codigoDocente;
	}

	/**
	 * Método que retorna a carga horária semanal da Disciplina.
	 * 
	 * @return um int com a carga horária semanal da Disciplina.
	 */
	public int getcHSemanal() {
		return this.cHSemanal;
	}

	/**
	 * Método que retorna a carga horária semestral da Disciplina.
	 * 
	 * @return um int com a carga horária semestral da Disciplina.
	 */
	public int getcHSemestral() {
		return this.cHSemestral;
	}
}
