package exceptions;

/**
 * Essa classe contém os construtores de Erros de Código Inválido de Docente em Disciplina.
 * 
 * <p>Essa classe extende a classe Exception</a>.
 * 
 * <p>Essa classe é membro do pacote ufes</a>.
 * 
 * @see Exception
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 */
public class ErroCodInvalDocenEmDisci extends Exception {
	
	//Atributos privados
	/**Atributo que possui a versão de serialização de ErroCodInvalDocenEmDisci*/
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor de ErroCodInvalDocenEmDisci que recebe o nome da disciplina e o código de docente inválido.
	 * 
	 * @param nomeDisciplina é uma String com o nome da disciplina que possui um docente que não existe.
	 * @param codigoInvalido é um int com o código do docente que não existe.
	 */
	public ErroCodInvalDocenEmDisci(String nomeDisciplina, int codigoInvalido) {
		super("Código de docente inválido na disciplina " + nomeDisciplina + ": " + codigoInvalido+".");
	}
}
