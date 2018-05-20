package trabalho1Prog3;

import java.util.List;
import java.util.ArrayList;

public class Departamento {
	private String nome;
	private List<Docente> docentes;

	public Departamento(String nome) {
		this.nome = nome;
		this.docentes = new ArrayList<Docente>();
	}

	public String getNomeDepartamento() {
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

	public boolean ehDisciplinaDadaPeloDocente(Disciplina disciplina) {
		for (int i = 0; i < docentes.size(); i++) {
			if (docentes.get(i).getCodigo() == disciplina.getCodigoDocente()) {
				docentes.get(i).adicionaDisciplinaALista(disciplina);
				return true;
			}
		}
		return false;
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

}
