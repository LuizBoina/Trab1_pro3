package exceptions;

/**
 * Essa classe contém os construtores de Erros de Mesmo Código.
 * 
 * <p>Essa classe extende a classe Exception</a>.
 * 
 * <p>Essa classe é membro do pacote ufes</a>.
 * 
 * @see Exception
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 */
public class ErroMesmoCodigo extends Exception {

	//Atributos privados
	/**Atributo que possui a versão de serialização de ErroMesmoCodigo*/
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor de ErroMesmoCodigo que recebe um objeto e seu código.
	 * 
	 * @param obj é o objeto cujo código está repetido.
	 * @param codigo é a String com o código repetido.
	 */
	public ErroMesmoCodigo(Object obj, String codigo) {
		super("Código repetido para " + obj.toString() + ": " + codigo + ".");
	}
	
	/**
	 * Construtor de ErroMesmoCodigo que recebe um objeto e seu código.
	 *  
	 * @param obj é o objeto cujo código está repetido.
	 * @param codigo é o int com o código repetido.
	 */
	public ErroMesmoCodigo(Object obj, int codigo) {
		super("Código repetido para " + obj.toString() + ": " + codigo + ".");
	}
}
