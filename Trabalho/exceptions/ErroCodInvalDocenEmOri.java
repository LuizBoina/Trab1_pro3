package exceptions;

/**
 * Essa classe contém os construtores de Erros de Código Inválido de Docente em Orientação.
 * 
 * <p>Essa classe extende a classe Exception</a>.
 * 
 * <p>Essa classe é membro do pacote ufes</a>.
 * 
 * @see Exception
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 */
public class ErroCodInvalDocenEmOri extends Exception {
	
	//Atributos privados
	/**Atributo que possui a versão de serialização de ErroCodInvalDocenEmOri*/
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor de ErroCodInvalDocenEmOri que recebe o nome do discente e o código inválido.
	 * 
	 * @param nomeAluno é o nome do discente cuja orientação possui um docente que não existe.
	 * @param codigoInvalido é o código do docente que não existe.
	 */
	public ErroCodInvalDocenEmOri(String nomeAluno, int codigoInvalido) {
		super("Código de docente inválido na orientação do aluno " + nomeAluno + ": " + codigoInvalido+".");
	}

}
