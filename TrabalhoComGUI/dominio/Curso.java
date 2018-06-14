package dominio;

import java.util.List;
import java.util.Locale;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;

/**
 * Essa classe contém as informações de um curso e os métodos necessários para sua manipulação.
 * 
 * <p>Essa classe faz parte do pacote ufes</a>.
 * 
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 */
public class Curso implements Comparable<Curso>, Serializable {
	
	//Atributos privados
	/**Atributo que determina a versão de serialização a ser usada*/
	private static final long serialVersionUID = 1L;
	/**Atributo que armazena uma lista de disciplinas do curso*/
	private List<Disciplina> disciplinas;
	/**Atributo que armazena o código do curso*/
	private int codigo;
	/**Atributo que armazena o nome do cuso*/
	private String nome;
	/**Atributo que determina se o curso é de graduação*/
	private boolean ehGrad;
	/**Atributo que determina se o curso é de pós-graduação*/
	private boolean ehPos;
	/**Atributo que armazena uma lista de discentes do curso*/
	private List<Discente> discentes;

	/**
	 * Construto de Curso que extrai as informações do Curso de um array de String.
	 * 
	 * @param celulas é o array de String retirado de uma tabela de cursos.
	 * @throws NumberFormatException caso o código do curso passado em celulas não possa ser convertido em int.
	 */
	public Curso(String[] celulas) throws NumberFormatException {
		this.codigo = Integer.parseInt(celulas[0].trim());
		discentes = new ArrayList<Discente>();
		disciplinas = new ArrayList<Disciplina>();
		this.nome = celulas[1].trim();
		try {
			if ((celulas[2].trim()).equals("X"))
				this.ehGrad = true;
		} catch (ArrayIndexOutOfBoundsException err) {
			this.ehGrad = false;
		}
		try {
			if ((celulas[3].trim()).equals("X"))
				this.ehPos = true;
		} catch (ArrayIndexOutOfBoundsException err) {
			this.ehPos = false;
		}
	}

	/**
	 * Método que retorna se o curso é de pós-graduação.
	 * 
	 * @return um boolean, true se for de pós-graduação, false caso o contrário.
	 */
	public boolean getEhPos() {
		return this.ehPos;
	}

	/**
	 * Método que retorna se o curso é de graduação.
	 * 
	 * @return um boolean, true se for de graduação, false caso o contrário.
	 */
	public boolean getEhGrad() {
		return this.ehGrad;
	}

	/**
	 * Método que retorna o nome do curso.
	 * 
	 * @return uma String com o nome do curso.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Método que calcula o total de horas semestrais de um Docente no Curso.
	 * 
	 * @param codDocente é o código do Docente.
	 * @return um int com o total de horas semestrais do Docente no Curso.
	 */
	public int TotalHorasDeDocente(int codDocente) {
		int qtd = 0;
		for (Disciplina disci : disciplinas) {
			if (disci.getCodigoDocente() == codDocente)
				qtd += disci.getcHSemestral();
		}
		return qtd;
	}

	/**
	 * Método que retorna uma lista de Discente do Curso.
	 * 
	 * @return uma List<Discente> de Discente do Curso.
	 */
	public List<Discente> getDiscentes() {
		return this.discentes;
	}

	/**
	 * Método que retorna o código do Curso.
	 * 
	 * @return um int com o código do Curso.
	 */
	public int getCodigoCurso() {
		return this.codigo;
	}

	/**
	 * Método que retorna uma lista de Disciplina do Curso.
	 * 
	 * @return um List<Disciplina> de Disciplina do Curso.
	 */
	public List<Disciplina> getDisciplinas() {
		return this.disciplinas;
	}

	/**
	 * Método que adiciona uma Disciplina ao Curso.
	 * 
	 * @param disciplina é a Disciplina a ser adicionada.
	 */
	public void adicionaDisciplinaNoCurso(Disciplina disciplina) {
		this.disciplinas.add(disciplina);
	}

	/**
	 * Método que adiciona um Discente ao Curso.
	 * 
	 * @param discente é o Discente a ser adicionado.
	 */
	public void adicionaDiscenteNoCurso(Discente discente) {
		discentes.add(discente);
	}

	/**
	 * Método que compara um Curso a outro Curso.
	 * @param outroCurso é o outro Curso que vai ser comparado.
	 * @return um int que é menor, maior ou igual a zero se o nome do Curso fonte for antes, depois ou igual ao nome do
	 * 		   outro Curso segundo a ordem alfabética.
	 */
	public int compareTo(Curso outroCurso) {
		Locale loc = new Locale("pt", "BR");
		Collator col = Collator.getInstance(loc);
		return col.compare(this.getNome(), outroCurso.getNome());
	}

}
