package exceptions;

public class ErroCodInvalCurEmDisci extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErroCodInvalCurEmDisci(String nomeDisciplina, int codCursoInvalido) {
		super("Código de curso inválido na disciplina " + nomeDisciplina + ": " + codCursoInvalido+".");
	}
}
