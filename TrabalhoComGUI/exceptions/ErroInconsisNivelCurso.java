package exceptions;

/**
 * Essa classe contém os construtores de Erros de Inconsistência no Nível do Curso.
 * 
 * <p>Essa classe extende a classe Exception</a>.
 * 
 * <p>Essa classe é membro do pacote ufes</a>.
 * 
 * @see Exception
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 */
public class ErroInconsisNivelCurso extends Exception {
	
	//Atributos privados
	/**Atributo que possui a versão de serialização de ErroInconsisNivelCurso*/
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor de ErroInconsisNivelCurso que recebe o código e o nome do curso com nível inconsistente.
	 *  
	 * @param codigoCurso é o código do curso com nível inconsistente.
	 * @param nomeCurso é o nome do curso com nível inconsistente.
	 */
	public ErroInconsisNivelCurso(int codigoCurso, String nomeCurso) {
		super("Inconsistência ao definir o nível do curso: " + codigoCurso + " - " + nomeCurso+".");
	}
}
