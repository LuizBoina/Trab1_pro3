package ufes;

import java.io.Serializable;

/**
 * Essa classe contém as informações de uma Orientação de Graduação e os métodos necessários para sua manipulação.
 * 
 * <p>Essa classe extende a classe Orientacao.
 * 
 * <p>Essa classe é membro do pacote ufes</a>.
 * 
 * @see Orientacao
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 */
public class OrientaGrad extends Orientacao implements Serializable {
	
	//Atributos privados
	/**Atributo que possui a versão de serialização de OrientacaoGrad*/
	private static final long serialVersionUID = 1L;
	/**Atributo que armazena o código do curso de graduação*/
	private int codigoCurso;
	
	/**
	 * Construtor de OrientaGrad que recebe um array de String e um discente.
	 * @param celulas é um array de String que contém as informações de OrientacaoGrad de uma tabela de orientações de 
	 * 		  graduação.
	 * @param dis é o Discente orientado.
	 * @throws NumberFormatException se não for possível converter uma String em um int.
	 */
	public OrientaGrad(String[] celulas, Discente dis) throws NumberFormatException {
		super(celulas[0], celulas[3], dis);
		this.codigoCurso = Integer.parseInt(celulas[2]);
	}

	/**
	 * Método que retorna o código do curso de graduação.
	 * 
	 * @return um int com o código do curso de graduação.
	 */
	public int getCodigoCurso() {
		return this.codigoCurso;
	}

}
