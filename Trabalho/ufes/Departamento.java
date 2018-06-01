package ufes;

import java.util.List;
import java.util.Locale;
import java.io.Serializable;
import java.text.Collator;
import java.util.ArrayList;

public class Departamento implements Comparable<Departamento>, Serializable {
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

	public String getNome() {
		return this.nome;
	}

	public void setNovoDocente(Docente novoDocente) {
		this.docentes.add(novoDocente);
	}

	public int getQuantidadeDocentes() {
		return this.docentes.size();
	}

	public List<Docente> getDocentes() {
		return docentes;
	}

	public int compareTo(Departamento outroDepartamento) {
		Locale loc = new Locale("pt", "BR");
		Collator col = Collator.getInstance(loc);
		return col.compare(this.getNome(), outroDepartamento.getNome());
	}

	public boolean achouDocente(ProducaoCientifica prod) {
		for (Docente docen : docentes) {
			if (docen.getCodigo() == prod.getCodigoDocente()) {
				docen.adicionaProdCientifica(prod);
				return true;
			}
		}
		return false;
	}

	public Docente achouDocenteNoDepartamento(Orientacao orienta) {
		for (Docente docen : this.docentes) {
			if (docen.getCodigo() == orienta.getCodigoDocente())
				return docen;
		}
		return null;
	}

	public void adicionaOrientacaoAoDocente(int posDocente, Orientacao orienta) {
		docentes.get(posDocente).adicionaOrientacaoALista(orienta);
	}

}
