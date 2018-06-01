package exceptions;

public class ErroMesmoCodigo extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErroMesmoCodigo(Object obj, String codigo) {
		super("Código repetido para " + obj.toString() + ": " + codigo + ".");
	}
	
	public ErroMesmoCodigo(Object obj, int codigo) {
		super("Código repetido para " + obj.toString() + ": " + codigo + ".");
	}
}
