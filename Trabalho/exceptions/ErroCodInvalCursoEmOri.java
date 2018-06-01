package exceptions;

public class ErroCodInvalCursoEmOri extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErroCodInvalCursoEmOri(String nomeAluno, int codigoInvalido) {
		super("Código de curso inválido na orientação do aluno " + nomeAluno + ": " + codigoInvalido);
	}

}
