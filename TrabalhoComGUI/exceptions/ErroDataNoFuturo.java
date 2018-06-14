package exceptions;

/**
 * Essa classe contém os construtores de Erros de Data No Futuro.
 * 
 * <p>Essa classe extende a classe Exception</a>.
 * 
 * <p>Essa classe é membro do pacote ufes</a>.
 * 
 * @see Exception
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 */
public class ErroDataNoFuturo extends Exception {
	
	//Atributos privados
	/**Atributo que possui a versão de serialização de ErroDataNoFuturo*/
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor de ErroDataNoFuturo que recebe o nome do discente e a data inválida.
	 * 
	 * @param nomeAluno é o nome do discente cuja data de ingresso no programa de pós-graduação está no futuro.
	 * @param data é a data no futuro.
	 */
	public ErroDataNoFuturo(String nomeAluno, String data) {
		super("Data de ingresso do aluno " + nomeAluno + " está no futuro: data(" + data + ").");
	}
}
