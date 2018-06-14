package exceptions;

/**
 * Essa classe contém os construtores de Erros de Código Inválido de Curso em Disciplina.
 * 
 * <p>Essa classe extende a classe Exception</a>.
 * 
 * <p>Essa classe é membro do pacote ufes</a>.
 * 
 * @see Exception
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 */
public class ErroCodInvalCurEmDisci extends Exception {

	//Atributos privados
	/**Atributo que possui a versão de serialização de ErroCodInvalCurEmDisci*/
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor de ErroCodInvalCurEmDisci que recebe o nome da disciplina e o código do curso inválido.
	 * 
	 * @param nomeDisciplina é o nome da disciplina que possui um curso que não existe.
	 * @param codCursoInvalido é o código do curso que não existe.
	 */
	public ErroCodInvalCurEmDisci(String nomeDisciplina, int codCursoInvalido) {
		super("Código de curso inválido na disciplina " + nomeDisciplina + ": " + codCursoInvalido+".");
	}
}
