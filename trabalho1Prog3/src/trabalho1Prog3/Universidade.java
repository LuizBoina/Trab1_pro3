package trabalho1Prog3;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Universidade {
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
		for (int i = 0; i < numProdCientifica; i++)
			prodCientificas.add(new ProducaoCientifica(planilhaProdCientificas[i]));
		for (int i = 0; i < departamentos.size(); i++)
			departamentos.get(i).adicionaProducaoCientificaADocente(prodCientificas);

	}

	public void adicionaCursos(String[][] planilhaCursos) {
		for (int i = 0; i < N_CURSOS; i++)
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
		for (int i = 0; i < numDisciplinas; i++) {
			disciplinas.add(new Disciplina(planilhaDisciplinas[i]));
			adicionaDisciplinaNoCurso(disciplinas.get(i));
			adicionaDisciplinaADocente(disciplinas.get(i));
		}
	}

	public void adicionaDisciplinaADocente(Disciplina disciplina) {
		for (Departamento depa : departamentos)
			if (depa.ehDisciplinaDadaPeloDocente(disciplina))
				break;
	}
	
	public void adicionaCursosADocentes() {
		
	}

	private void adicionaDisciplinaNoCurso(Disciplina disciplina) {
		for (Curso curso : cursos) {
			if (curso.getCodigoCurso() == disciplina.getCodigoCurso()) {
				curso.adicionaDisciplinaNoCurso(disciplina);
				break;
			}
		}
	}

	public void adicionaDiscentesAosCursos(int numDiscentes, String[][] planilhaDiscentes) {
		ArrayList<Discente> discentes = new ArrayList<Discente>(numDiscentes);
		for (int i = 0; i < numDiscentes; i++) {
			discentes.add(new Discente(planilhaDiscentes[i]));
			for (int j = 0; j < N_CURSOS; j++) {
				if (cursos[j].getCodigoCurso() == discentes.get(i).getCodigoCurso()) {
					cursos[j].adicionaDiscenteNoCurso(discentes.get(i));
					break;
				}
			}
		}
	}

	public void adicionaOrientacaoGradAosDocentes(int numOrientacao, String[][] planilhaOrientacoes) {
		ArrayList<OrientaGrad> orientaGrad = new ArrayList<OrientaGrad>(numOrientacao);
		for (int i = 0; i < numOrientacao; i++) {
			int matDiscente = Integer.parseInt(planilhaOrientacoes[i][1]);
			Discente dis = getDiscentePeloCurso(matDiscente);
			orientaGrad.add(new OrientaGrad(planilhaOrientacoes[i], dis));
			for (int j = 0; j < departamentos.size(); j++) {
				int posDocenteNoDepartamento = departamentos.get(j).achouDocenteNoDepartamento(orientaGrad.get(i));
				if (posDocenteNoDepartamento != -1) {
					departamentos.get(j).adicionaOrientacaoAoDocente(posDocenteNoDepartamento, orientaGrad.get(i));
					break;
				}
			}
		}
	}
	
	private Discente getDiscentePeloCurso(int matDiscente) {
		for(Curso curso : cursos) {
			for(Discente dis : curso.getDiscentes()) {
				if(dis.getMatricula() == matDiscente)
					return dis;
			}
		}
		return null;
	}

	public void adicionaOrientacaoPosGradAosDocentes(int numOrientacao, String[][] planilhaOrientacoes) {
		ArrayList<OrientaPos> orientaPos = new ArrayList<OrientaPos>(numOrientacao);
		for (int i = 0; i < numOrientacao; i++) {
			int matDiscente = Integer.parseInt(planilhaOrientacoes[i][1]);
			Discente dis = getDiscentePeloCurso(matDiscente);
			orientaPos.add(new OrientaPos(planilhaOrientacoes[i], dis));
			for (int j = 0; j < departamentos.size(); j++) {
				int posDocenteNoDepartamento = departamentos.get(j).achouDocenteNoDepartamento(orientaPos.get(i));
				if (posDocenteNoDepartamento != -1) {
					departamentos.get(j).adicionaOrientacaoAoDocente(posDocenteNoDepartamento, orientaPos.get(i));
					break;
				}
			}
		}
	}

	public int quantidadeTotalDocentes() {
		int qtd = 0;
		for (Departamento dep : departamentos)
			qtd += dep.getQuantidadeDocentes();
		return qtd;
	}

	public int getDepartamentoSize() {
		return this.departamentos.size();
	}

	public Departamento getDepartamento(int posDepartamentoNaLista) {
		return this.departamentos.get(posDepartamentoNaLista);
	}

	public void gerarPad(Saida output) {
		if (output.abrirArquivoParaEscrita("/1-pad.csv")) {
			output.escreverCabecalho("HEAD_PAD");
			ArrayList<Docente> docentesUfes = new ArrayList<Docente>(quantidadeTotalDocentes());
			for(Departamento depa : departamentos)
				docentesUfes.addAll(depa.getDocentes());
			Collections.sort(docentesUfes);
			for(Docente docen :docentesUfes)
				output.escreveString(docen.toStringParaPad());
			output.fecharFw();
		}
	}
	
	public void gerarRha(Saida output) {
		if(output.abrirArquivoParaEscrita("/2-rha.csv")) {
			output.escreverCabecalho("HEAD_RHA");
			ArrayList<Docente> docentesUfes = new ArrayList<Docente>(quantidadeTotalDocentes());
			Collections.sort(departamentos);
			for(Departamento depa : departamentos)
				docentesUfes.addAll(depa.getDocentes());
			Collections.sort(docentesUfes);
		}
	}
	
	public void gerarAlocacao(Saida output) {
		if(output.abrirArquivoParaEscrita("/3-alocacao.csv")) {
			output.escreverCabecalho("HEAD_ALOCACAO");
			ArrayList<Docente> docentesUfes = new ArrayList<Docente>(quantidadeTotalDocentes());
			for(Departamento depa : departamentos)
				docentesUfes.addAll(depa.getDocentes());
			Collections.sort(docentesUfes);
			for(Docente docen : docentesUfes) {
				List<Disciplina> disciplinas = docen.getDisciplinasDadas();
				Collections.sort(disciplinas);
				for(Disciplina dis:disciplinas) {
					String saida = docen.getNome()+";"+String.valueOf(dis.getCodigo())+";"+dis.getNome()+";"+String.valueOf(dis.getcHSemestral());
					output.escreveString(saida);
				}
			}
			output.fecharFw();
			
		}
	}
	
	public void gerarPpg(Saida output) {
		if(output.abrirArquivoParaEscrita("/4-ppg.csv")) {
			output.escreverCabecalho("HEAD_PPG");
			ArrayList<OrientaPos> ori = new ArrayList<OrientaPos>(); 
			for(Departamento dep: departamentos) {
				for(Docente doc: dep.getDocentes())
					ori.addAll(doc.getOrientaPos());
			}
			Collections.sort(ori);
			for(OrientaPos o : ori) {
				String saida = o.getPrograma()+";"+o.getDateString()+";"+String.valueOf(o.getMatriculaDiscente())+";"+o.getNomeDiscente();
				output.escreveString(saida);
			}
			output.fecharFw();
		}
	}
}