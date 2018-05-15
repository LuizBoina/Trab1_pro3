package trabalho1Prog3;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Universidade implements Comparable{
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
			int indexDepartamentoLista = this.getPosDepartamento(planilhaDocentes[i][3]);
			if (indexDepartamentoLista != -1)
				this.departamentos.get(i).setNovoDocente(docentes[i]);
			else {
				Departamento novoDepartamento = new Departamento(planilhaDocentes[i][3]);
				novoDepartamento.setNovoDocente(docentes[i]);
				this.departamentos.add(novoDepartamento);
			}
		}
	}

	public void adicionaProdCientificaAosDocentes(int numProdCientifica, String[][] planilhaProdCientificas) {
		ArrayList<ProducaoCientifica> prodCientificas = new ArrayList<ProducaoCientifica>(numProdCientifica);
		for(int i = 0;i<numProdCientifica;i++)
			prodCientificas.add(new ProducaoCientifica(planilhaProdCientificas[i]));
		Collections.sort(prodCientificas, new Comparator<ProducoesCientificas>() {
			@Override
			public int compare(ProducaoCientifica prod1, ProducaoCientifica prod2) {
				return prod1.getCodigoDocente()-prod2.getCodigoDocente;
			}
		});
		for(int i = 0;i<departamentos.size();i++) {
			//da pra fazer de outro jeito
			
		}
		
	}

	public void adicionaCursos(String[][] planilhaCursos) {
		for(int i = 0; i<this.N_CURSOS;i++)
			cursos[i] = new Curso(planilhaCursos[i]);
	}

	public int getPosDepartamento(String departamento) {
		for (int i = 0; i < departamentos.size(); i++) {
			if (departamentos.get(i).getNomeDepartamento().equals(departamento))
				return i;
		}
		return -1;
	}

	public int getPosCurso(int codigo) {
		for(int i = 0;i<this.N_CURSOS;i++) {
			if(codigo==cursos[i].getCodigoCurso())
				return i;
		}
		return -1;
	}
}