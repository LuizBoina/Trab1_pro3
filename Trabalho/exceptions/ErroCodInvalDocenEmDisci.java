package exceptions;

public class ErroCodInvalDocenEmDisci extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErroCodInvalDocenEmDisci(String nomeDisciplina, int codigoInvalido) {
		super("Código de docente inválido na disciplina " + nomeDisciplina + ": " + codigoInvalido+".");
	}
}
