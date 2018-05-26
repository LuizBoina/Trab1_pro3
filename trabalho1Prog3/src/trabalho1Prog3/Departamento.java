package trabalho1Prog3;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public class Departamento implements Comparable<Departamento>, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome;
	private List<Docente> docentes;

	public Departamento(String nome) {
		this.nome = nome;
		this.docentes = new ArrayList<Docente>();
	}

	public String getNomeDepartamento() {
		return this.nome;
	}

	public void setNovoDocente(Docente novoDocente) throws Exception {
		int indexDocente = this.getPosDocente(novoDocente);
		if(indexDocente == -1) {
			throw new Exception("Código repetido para docente: "+novoDocente.getCodigo());
		}this.docentes.add(novoDocente);
	}

	public int getQuantidadeDocentes() {
		return this.docentes.size();
	}

	public List<Docente> getDocentes() {
		return docentes;
	}
	
	public int compareTo(Departamento outroDepartamento) {
		return this.nome.compareTo(outroDepartamento.nome);
	}

	public void adicionaProducaoCientificaADocente(ArrayList<ProducaoCientifica> prodCientificas) {
		for (int i = 0; i < docentes.size(); i++) {
			for (int j = 0; j < prodCientificas.size(); j++) {
				if (docentes.get(i).getCodigo() == prodCientificas.get(j).getCodigoDocente()) {
					docentes.get(i).adicionaProdCientificaALista(prodCientificas.get(j));
					prodCientificas.remove(j);
				}
			}
		}
	}


	public int achouDocenteNoDepartamento(Orientacao orienta) {
		for (int i = 0; i < docentes.size(); i++) {
			if (docentes.get(i).getCodigo() == orienta.getCodigoDocente())
				return i;
		}
		return -1;
	}

	public void adicionaOrientacaoAoDocente(int posDocente, Orientacao orienta) {
		docentes.get(posDocente).adicionaOrientacaoALista(orienta);
	}
	
	private int getPosDocente(Docente doc) {
		return this.docentes.indexOf(doc);
	}
}
