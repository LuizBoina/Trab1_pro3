package exceptions;

/**
 * Essa classe contém os construtores de Erros de Código Inválido de Curso em Orientação.
 * 
 * <p>Essa classe extende a classe Exception</a>.
 * 
 * <p>Essa classe é membro do pacote ufes</a>.
 * 
 * @see Exception
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 */
public class ErroCodInvalCursoEmOri extends Exception {
	
	//Atributos privados
	/**Atributo que possui a versão de serialização de ErroCodInvalCursoEmOri*/
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor de ErroCodInvalCursoEmOri que recebe o nome do discente e o código de curso inválido na orientação.
	 * 
	 * @param nomeAluno é nome do aluno cuja orientação possui um curso que não existe.
	 * @param codigoInvalido
	 */
	public ErroCodInvalCursoEmOri(String nomeAluno, int codigoInvalido) {
		super("Código de curso inválido na orientação do aluno " + nomeAluno + ": " + codigoInvalido+".");
	}

}
