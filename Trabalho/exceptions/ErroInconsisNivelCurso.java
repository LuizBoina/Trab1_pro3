package exceptions;

public class ErroInconsisNivelCurso extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErroInconsisNivelCurso(int codigoCurso, String nomeCurso) {
		super("Inconsistência ao definir o nível do curso: " + codigoCurso + " - " + nomeCurso);
	}
}
