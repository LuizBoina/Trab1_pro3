package trabalho1Prog3;

import java.util.List;
import java.util.ArrayList;

public class Universidade{
	private List<Departamento> departamentos;
	private Curso[] cursos;
	final int N_CURSOS;

	public Universidade(int qtdCursos) {
		this.N_CURSOS = qtdCursos;
		this.cursos = new Curso[qtdCursos];
		this.departamentos = new ArrayList<Departamento>();
	}

	public void criaDepartamentos(int numDocentes, String[][] planilhaDocentes) {
		Docente[] docentes = new Docente[numDocentes];
		for (int i = 0; i < numDocentes; i++) {
			docentes[i] = new Docente(planilhaDocentes[i]);
			int indexDepartamentoLista = this.getPosDepartamento(planilhaDocentes[i][2]);
			if (indexDepartamentoLista != -1)
				this.departamentos.get(indexDepartamentoLista).setNovoDocente(docentes[i]);
			else {
				Departamento novoDepartamento = new Departamento(planilhaDocentes[i][2]);
				novoDepartamento.setNovoDocente(docentes[i]);
				this.departamentos.add(novoDepartamento);
			}
		}
	}

	public void adicionaProdCientificaAosDocentes(int numProdCientifica, String[][] planilhaProdCientificas) {
		ArrayList<ProducaoCientifica> prodCientificas = new ArrayList<ProducaoCientifica>(numProdCientifica);
		for(int i = 0;i<numProdCientifica;i++)
			prodCientificas.add(new ProducaoCientifica(planilhaProdCientificas[i]));
		for(int i = 0;i<departamentos.size();i++)
			departamentos.get(i).adicionaProducaoCientificaADocente(prodCientificas);
		
	}

	public void adicionaCursos(String[][] planilhaCursos) {
		for(int i = 0; i<N_CURSOS;i++)
			cursos[i] = new Curso(planilhaCursos[i]);
	}

	private int getPosDepartamento(String departamento) {
		for (int i = 0; i < departamentos.size(); i++) {
			if (departamentos.get(i).getNomeDepartamento().equals(departamento))
				return i;
		}
		return -1;
	}
	
	public void adicionaDisciplinas(int numDisciplinas, String[][] planilhaDisciplinas) {
		ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>(numDisciplinas);
		for(int i = 0;i<numDisciplinas;i++) {
			disciplinas.add(new Disciplina(planilhaDisciplinas[i]));
			adicionaDisciplinaNoCurso(disciplinas.get(i));
			adicionaDisciplinaADocente(disciplinas.get(i));
		}
	}
	
	public void adicionaDisciplinaADocente(Disciplina disciplina) {
		for(int i = 0;i<departamentos.size();i++)
			if(departamentos.get(i).DisciplinaPertenceADocente(disciplina))
				break;
	}
	
	private void adicionaDisciplinaNoCurso(Disciplina disciplina) {
		for(int i = 0;i<N_CURSOS;i++) {
			if(cursos[i].getCodigoCurso() == disciplina.getCodigoCurso()) {
				cursos[i].adicionaDisciplinaNoCurso(disciplina);
				break;
			}
		}
		
	}
}