package ufes;

import java.util.Date;
import java.util.Locale;

import exceptions.ErroDataNoFuturo;

import java.io.Serializable;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Essa classe contém as informações de uma Orientação de Pós-Graduação e os métodos necessários para sua manipulação.
 * 
 * <p>Essa classe extende a classe Orientacao.
 * 
 * <p>Essa classe é membro do pacote ufes</a>.
 * 
 * @see Orientacao
 * @see Date
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 */
public class OrientaPos extends Orientacao implements Comparable<OrientaPos>, Serializable {
	
	//Atributos privados
	/**Atributo que possui a versão de serialização de OrientacaoPos*/
	private static final long serialVersionUID = 1L;
	/**Atributo que possui a data de ingresso do discente no programa de OrientacaoPos*/
	private Date dataIngresso;
	/**Atributo que possui o nome do programa de pós-graduação*/
	private String programa;

	/**
	 * Construtor de OrientaPos que recebe um array de String e um discente.
	 * 
	 * @param celulas é um array de String que contém as informações de OrientaPos de uma tabela de orientação de pós-
	 * 		  graduação.
	 * @param dis é o Discente orientado.
	 * @throws NumberFormatException se não for possível converter uma String em um int.
	 * @throws ParseException se não for possível converter uma String em um Date.
	 * @throws ErroDataNoFuturo se a data do ingresso do discente no programa de pós-graduação for depois da data de 
	 * 							execução do programa.
	 */
	public OrientaPos(String[] celulas, Discente dis) throws NumberFormatException, ParseException, ErroDataNoFuturo {
		super(celulas[0], celulas[4], dis);
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		this.dataIngresso = formato.parse(celulas[2]);
		Date dataAtual = new Date();
		if (this.dataIngresso.after(dataAtual))
			throw new ErroDataNoFuturo(super.getNomeDiscente(), this.getDateString());
		this.programa = celulas[3];
	}

	/**
	 * Método que retorna o nome do programa de pós-graduação.
	 * 
	 * @return uma String com o nome do programa de pós-graduação.
	 */
	public String getPrograma() {
		return this.programa;
	}

	/**
	 * Método que retorna a matrícula do discente orientado.
	 * 
	 * @return um int com a matrícula do discente orientado.
	 */
	public int getMatriculaDiscente() {
		return super.discente.getMatricula();
	}

	/**
	 * Método que retorna a data de ingresso do discente orientado no programa de pós-graduação.
	 * 
	 * @return uma String com a data de ingresso do discente orientado no programa de pós-graduação.
	 */
	public String getDateString() {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		return formato.format(this.dataIngresso);
	}

	/**
	 * Método que compara uma OrientaPos a outra OrientaPos.
	 * 
	 * @param outraOrientaPos é a outra OrientaPos que vai ser comparada.
	 * @return um int que é menor ou maior que zero ao se a OrientaPos fonte for antes ou depois da outra OrientaPos,
	 * 		   sendo ambas comparadas usando como primeiro critério o nome do programa de pós-graduação, segundo 
	 * 		   critério a data de ingresso do discente orientado no programa de pós-graduação e como terceiro critério o 
	 *         nome do discente orientado. 
	 */
	public int compareTo(OrientaPos outraOrientaPos) {
		Locale loc = new Locale("pt", "BR");
		Collator col = Collator.getInstance(loc);
		int result = col.compare(this.getPrograma(), outraOrientaPos.getPrograma());
		if (result > 0)
			return 1;
		else if (result < 0)
			return -1;
		else {
			if (this.dataIngresso.compareTo(outraOrientaPos.dataIngresso) > 0)
				return 1;
			else if (this.dataIngresso.compareTo(outraOrientaPos.dataIngresso) < 0)
				return -1;
			else {
				int resultDiscente = col.compare(super.discente.getNome(), outraOrientaPos.discente.getNome());
				if (resultDiscente >= 0)
					return 1;
				else
					return -1;
			}
		}
	}
}
