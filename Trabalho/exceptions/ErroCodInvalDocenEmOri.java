package exceptions;

public class ErroCodInvalDocenEmOri extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErroCodInvalDocenEmOri(String nomeAluno, int codigoInvalido) {
		super("Código de docente inválido na orientação do aluno " + nomeAluno + ": " + codigoInvalido+".");
	}

}
