package dominio;

import java.io.Serializable;

/**
 * Essa classe contém as informações de uma Produção Científica e os métodos necessários para sua manipulação.
 * 
 * <p>Essa classe é membro do pacote ufes</a>.
 * 
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 */
public class ProducaoCientifica implements Serializable {

	//Atributos privados
	/**Atributo que possui a versão de serialização de ProducaoCientifica*/
	private static final long serialVersionUID = 1L;
	/**Atributo que possui o código do docente autor da publicação*/
	private int codigoDocente;
	/**Atributo que possui o título da publicação*/
	private String titulo;
	/**Atributo que determina se a publicação é qualificada*/
	private boolean qualificada;

	/**
	 * Construtor de ProducaoCientifica que recebe um array de String.
	 * 
	 * @param celulas é o array de String que contém informações de ProducaoCientifica de uma tabela de produções
	 * 		  científicas.
	 * @throws NumberFormatException se não for possível converter uma String em um int.
	 */
	public ProducaoCientifica(String[] celulas) throws NumberFormatException {
		this.codigoDocente = Integer.parseInt(celulas[0].trim());
		this.titulo = celulas[1].trim();
		try {
			if ((celulas[2].trim()).equals("X"))
				this.qualificada = true;
		} catch (ArrayIndexOutOfBoundsException e) {
			this.qualificada = false;
		}
	}

	/**
	 * Método que retorna o título da publicação.
	 * 
	 * @return uma String com o título da publicação.
	 */
	public String getString() {
		return this.titulo;
	}

	/**
	 * Método que verifica se a publicação é qualificada.
	 * 
	 * @return um boolean, true se a publicação for qualificada, false caso não.
	 */
	public boolean ehQualificada() {
		return this.qualificada;
	}

	/**
	 * Método que retorna o código do docente autor da publicação.
	 * 
	 * @return um int com o código do docente autor da publicação.
	 */
	public int getCodigoDocente() {
		return this.codigoDocente;
	}
}
