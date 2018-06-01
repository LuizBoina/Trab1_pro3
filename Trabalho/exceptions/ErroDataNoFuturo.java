package exceptions;

public class ErroDataNoFuturo extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErroDataNoFuturo(String nomeAluno, String data) {
		super("Data de ingresso do aluno " + nomeAluno + " está no futuro: data(" + data + ").");
	}
}
