package ufes;

import java.util.List;
import java.util.Locale;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;

/**
 * Essa classe contém as informações de um departamento e os métodos necessários para sua manipulação.
 * 
 * <p>Essa classe é membro do pacote ufes</a>.
 * 
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 */
public class Departamento implements Comparable<Departamento>, Serializable {
	
	//Atributos privados
	/**Atributo que possui a versão de serialização de Departamento*/
	private static final long serialVersionUID = 1L;
	/**Atributo que possui o nome do Departamento*/
	private String nome;
	/**Atributo que armazena uma lista de docentes do Departamento*/
	private List<Docente> docentes;

	/**
	 * Construtor de Departamento que recebe o nome do Departamento como parâmetro. 
	 * 
	 * @param nome é o nome do Departamento.
	 */
	public Departamento(String nome) {
		this.nome = nome;
		this.docentes = new ArrayList<Docente>();
	}

	/**
	 * Método que retorna o nome do Departamento.
	 * 
	 * @return uma String com o nome do Departamento.
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Método que adiciona um Docente à lista de docentes do Departamento
	 * @param novoDocente é o Docente a ser adicionado.
	 */
	public void setNovoDocente(Docente novoDocente) {
		this.docentes.add(novoDocente);
	}

	/**
	 * Método que retorna a quantidade de docentes no departamento.
	 * 
	 * @return um int com a quantidade de docentes no departamento.
	 */
	public int getQuantidadeDocentes() {
		return this.docentes.size();
	}

	/**
	 * Método que retorna uma lista de docentes do Departamento.
	 * 
	 * @return um List<Docente> de Docente do Departamento.
	 */
	public List<Docente> getDocentes() {
		return docentes;
	}

	/**
	 * Método que compara um Departamento com outro Departamento.
	 * @param outroDepartamento é o Departamento que irá comparar com o Departamento fonte.
	 * @return um int que é menor, maior ou igual a zero se o nome do Departamento fonte for antes, depois ou igual ao 
	 * 		   nome do outro Departamento segundo a ordem alfabética.
	 */
	public int compareTo(Departamento outroDepartamento) {
		Locale loc = new Locale("pt", "BR");
		Collator col = Collator.getInstance(loc);
		return col.compare(this.getNome(), outroDepartamento.getNome());
	}

	/**
	 * Método que tenta adicionar uma produção cinetífica a um docente do Departamento e retorna se conseguiu ou não.
	 * 
	 * @param prod é a ProducaoCientifica que vai ser adicionada.
	 * @return um boolean, true se conseguir adicionar a produção a um docente do Departamento, false caso não consiga.
	 */
	public boolean achouDocente(ProducaoCientifica prod) {
		for (Docente docen : docentes) {
			if (docen.getCodigo() == prod.getCodigoDocente()) {
				docen.adicionaProdCientifica(prod);
				return true;
			}
		}
		return false;
	}

	/**
	 * Método que procura um docente no Departamento usando uma orientação e retorna o docente encontrado.
	 * 
	 * @param orienta é a Orientacao que é utilizada na busca pelo Docente no Departamento.
	 * @return o Docente encontrado ou null caso não encontre o Docente.
	 */
	public Docente achouDocenteNoDepartamento(Orientacao orienta) {
		for (Docente docen : this.docentes) {
			if (docen.getCodigo() == orienta.getCodigoDocente())
				return docen;
		}
		return null;
	}

	/**
	 * Método que adiciona uma orientação a um docente do Departamento.
	 * 
	 * @param posDocente é a posição do Docente na lista do Departamento.
	 * @param orienta é a Orientação a ser adicionada ao Docente do Departamento.
	 */
	public void adicionaOrientacaoAoDocente(int posDocente, Orientacao orienta) {
		docentes.get(posDocente).adicionaOrientacaoALista(orienta);
	}

}
