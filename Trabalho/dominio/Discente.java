package dominio;

import java.io.Serializable;

/**
 * Essa classe contém as informações de um Discente e os métodos necessários para sua manipulação.
 * 
 * <p>Essa classe é membro do pacote ufes</a>.
 * 
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 */
public class Discente implements Serializable {

	//Atributos privados
	/**Atributo que possui a versão de serialização de Discente*/
	private static final long serialVersionUID = 1L;
	/**Atributo que possui a matricula do Discente*/
	private int matricula;
	/**Atributo que possui o nome do Discente*/
	private String nome;
	/**Atributo que possui o código do curso do Discente*/
	private int codigoCurso;

	/**
	 * Construtor de Discente que extrai as informações do Discente de um array de String.
	 * 
	 * @param celulas é o array de String retirado de uma tabela de discentes 
	 * @throws NumberFormatException se a matricula ou o codigo do curso do array não for possível de ser convertido 
	 *         para int.
	 */
	public Discente(String[] celulas) throws NumberFormatException {
		this.matricula = Integer.parseInt(celulas[0].trim());
		this.codigoCurso = Integer.parseInt(celulas[2].trim());
		this.nome = celulas[1].trim();
	}

	/**
	 * Método que retorna a matrícula do Discente.
	 * 
	 * @return um int com a matrícula do Discente.
	 */
	public int getMatricula() {
		return this.matricula;
	}

	/**
	 * Método que retorna o nome do Discente.
	 * 
	 * @return uma Stringo com o nome do Discente.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Método que retorna o código do curso do Discente.
	 * 
	 * @return um int com o código do curso do Discente.
	 */
	public int getCodigoCurso() {
		return this.codigoCurso;
	}
}
