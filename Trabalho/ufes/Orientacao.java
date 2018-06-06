package ufes;

import java.io.Serializable;

/**
 * Essa classe contém as informações de uma Orientação e os métodos necessários para sua manipulação.
 * 
 * <p>Essa classe é membro do pacote ufes</a>.
 * 
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 */
public class Orientacao implements Serializable {
	
	//Atributos privados
	/**Atributo que possui a versão de serialização de Orientacao*/
	private static final long serialVersionUID = 1L;
	/**Atributo que armazena o código do docente responsável pela Orientacao*/
	private int codigoDocente;
	/**Atributo que armazena a carga horária semanal da Orientacao*/
	private int CHsemanal;
	
	//Atributo protegido
	/**Atributo que armazena o discente orientado*/
	protected Discente discente;

	/**
	 * Construtor de Orientacao que recebe o cogido do docente, a carga horária semanal e o discente orientado.
	 * 
	 * @param codigoDocente é uma String com o código do Docente responsável pela Orientacao.
	 * @param CHsemanal é um String com a carga horária semanal da Orientacao.
	 * @param dis é o Discente orientado. 
	 * @throws NumberFormatException se não for possível converter uma String em um int.
	 */
	public Orientacao(String codigoDocente, String CHsemanal, Discente dis) throws NumberFormatException {
		this.codigoDocente = Integer.parseInt(codigoDocente.trim());
		this.CHsemanal = Integer.parseInt(CHsemanal.trim());
		this.discente = dis;
	}

	/**
	 * Método que retorna o código do docente responsável pela Orientacao.
	 * 
	 * @return um int com o código do docente responsável pela Orientacao.
	 */
	public int getCodigoDocente() {
		return this.codigoDocente;
	}

	/**
	 * Método que retorna o nome do discente orientado.
	 * 
	 * @return uma String com o nome do discente orientado. 
	 */
	public String getNomeDiscente() {
		return this.discente.getNome();
	}

	/**
	 * Método que retorna a carga horária semanal da Orientacao.
	 * 
	 * @return um int com a carga horária semanal da Orientacao.
	 */
	public int getCHsemanal() {
		return this.CHsemanal;
	}
}
