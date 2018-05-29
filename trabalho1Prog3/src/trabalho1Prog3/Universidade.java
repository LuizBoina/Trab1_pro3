package trabalho1Prog3;

import java.util.List;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;

public class Universidade implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Departamento> departamentos;
	private Curso[] cursos;

	public Universidade(int qtdCursos) {
		this.cursos = new Curso[qtdCursos];
		this.departamentos = new ArrayList<Departamento>();
	}

	public void preencheDadosUniversidade(Entrada input) throws IOException, NumberFormatException, ParseException {
		criaDepartamentos(input.lePlanilha(Entrada.CAMINHO_PL_DOCENTE, 3));
		adicionaCursos(input.lePlanilha(Entrada.CAMINHO_PL_CURSOS, 4));
		adicionaProdCientificaAosDocentes(input.lePlanilha(Entrada.CAMINHO_PL_PRODCIENTIFICA, 3));
		adicionaDisciplinasCursosECursosADocentes(input.lePlanilha(Entrada.CAMINHO_PL_DISCIPLINAS, 6));
		adicionaDiscentesAosCursos(input.lePlanilha(Entrada.CAMINHO_PL_DISCENTE, 3));
		adicionaOrientacaoGradAosDocentes(input.lePlanilha(Entrada.CAMINHO_PL_ORIENTAGRAD, 4));
		adicionaOrientacaoPosGradAosDocentes(input.lePlanilha(Entrada.CAMINHO_PL_ORIENTAPOS, 5));
	}

	private void criaDepartamentos(String[][] planilhaDocentes) throws NumberFormatException {
		for (String[] str : planilhaDocentes) {
			Departamento depa = this.getDepartamento(str[2]);
			if (depa != null)
				depa.setNovoDocente(new Docente(str));
			else {
				Departamento novoDepartamento = new Departamento(str[2]);
				novoDepartamento.setNovoDocente(new Docente(str));
				this.departamentos.add(novoDepartamento);
			}
		}
	}

	private void adicionaProdCientificaAosDocentes(String[][] planilhaProdCientificas) throws NumberFormatException {
		for (String[] str : planilhaProdCientificas) {
			percorreDepa(new ProducaoCientifica(str));
		}
	}

	private void percorreDepa(ProducaoCientifica prod) {
		for (Departamento depa : departamentos) {
			if (depa.achouDocente(prod))
				break;
		}
	}

	private void adicionaCursos(String[][] planilhaCursos) throws NumberFormatException {
		int i = 0;
		for (String[] linha : planilhaCursos)
			cursos[i++] = new Curso(linha);
	}

	private Departamento getDepartamento(String departamento) {
		for (Departamento depa : departamentos) {
			if (depa.getNome().equals(departamento))
				return depa;
		}
		return null;
	}

	private void adicionaDisciplinasCursosECursosADocentes(String[][] planilhaDisciplinas)
			throws NumberFormatException {
		for (String[] str : planilhaDisciplinas) {
			Disciplina dis = new Disciplina(str);
			Curso curso = adicionaDisciplinaNoCursoERetornaCurso(dis);
			if (curso != null) {
				Docente docen = getDocentePelaDisciplina(dis.getCodigoDocente());
				if (docen != null && !docen.docenteJaPossuiCurso(curso.getCodigoCurso()))
					docen.adicionaCurso(curso);
			}
		}
	}

	private Curso adicionaDisciplinaNoCursoERetornaCurso(Disciplina disciplina) {
		for (Curso curso : cursos) {
			if (curso.getCodigoCurso() == disciplina.getCodigoCurso()) {
				curso.adicionaDisciplinaNoCurso(disciplina);
				return curso;
			}
		}
		return null;
	}

	private Docente getDocentePelaDisciplina(int codDocente) {
		for (Departamento depa : departamentos) {
			for (Docente docen : depa.getDocentes()) {
				if (docen.getCodigo() == codDocente)
					return docen;
			}
		}
		return null;
	}

	private void adicionaDiscentesAosCursos(String[][] planilhaDiscentes) throws NumberFormatException {
		for (String[] str : planilhaDiscentes) {
			Discente dis = new Discente(str);
			for (Curso cur : cursos) {
				if (cur.getCodigoCurso() == dis.getCodigoCurso()) {
					cur.adicionaDiscenteNoCurso(dis);
					break;
				}
			}
		}
	}

	private void adicionaOrientacaoGradAosDocentes(String[][] planilhaOrientacoes) throws NumberFormatException {
		for (String[] str : planilhaOrientacoes) {
			int matDiscente = Integer.parseInt(str[1]); // ver como tratar erro de parsing aq
			Discente dis = getDiscentePeloCurso(matDiscente); // se pa fazer de um jeito melhor
			OrientaGrad ori = new OrientaGrad(str, dis);
			for (Departamento depa : departamentos) {
				int posDocenteNoDepartamento = depa.achouDocenteNoDepartamento(ori);
				if (posDocenteNoDepartamento != -1) {
					depa.adicionaOrientacaoAoDocente(posDocenteNoDepartamento, ori);
					break;
				}
			}
		}
	}

	private Discente getDiscentePeloCurso(int matDiscente) {
		for (Curso curso : cursos) {
			for (Discente dis : curso.getDiscentes()) {
				if (dis.getMatricula() == matDiscente)
					return dis;
			}
		}
		return null;
	}

	private void adicionaOrientacaoPosGradAosDocentes(String[][] plOri) throws NumberFormatException, ParseException {
		for (String[] str : plOri) {
			int matDiscente = Integer.parseInt(str[1]);
			Discente dis = getDiscentePeloCurso(matDiscente);
			OrientaPos ori = new OrientaPos(str, dis);
			for (Departamento depa : departamentos) {
				int posDocenteNoDepartamento = depa.achouDocenteNoDepartamento(ori);
				if (posDocenteNoDepartamento != -1) {
					depa.adicionaOrientacaoAoDocente(posDocenteNoDepartamento, ori);
					break;
				}
			}
		}
	}

	private int quantidadeTotalDocentes() {
		int qtd = 0;
		for (Departamento dep : departamentos)
			qtd += dep.getQuantidadeDocentes();
		return qtd;
	}

	public void gerarSaidas(Saida output) {
		gerarPad(output);
		gerarRha(output);
		gerarAlocacao(output);
		gerarPpg(output);
	}

	private void gerarPad(Saida output) {
		int tHSemanais;
		int tHSemestrais;
		if (output.abrirArquivoParaEscrita("/1-pad.csv")) {
			output.escreverCabecalho("HEAD_PAD");
			ArrayList<Docente> docentesUfes = new ArrayList<Docente>(quantidadeTotalDocentes());
			for (Departamento depa : departamentos)
				docentesUfes.addAll(depa.getDocentes());
			Collections.sort(docentesUfes);
			for (Docente docen : docentesUfes) {
				List<Disciplina> disci = docen.getDisciplinasDadas();
				tHSemanais = tHSemestrais = 0;
				for (Disciplina dis : disci) {
					tHSemanais += dis.getcHSemanal();
					tHSemestrais += dis.getcHSemestral();
				}
				String saida = docen.getNome() + ";" + docen.getDepartamento() + ";" + String.valueOf(tHSemanais) + ";"
						+ String.valueOf(tHSemestrais) + ";" + String.valueOf(docen.getTHSemanaisOrientacao()) + ";"
						+ String.valueOf(docen.getQtdProdCientificasQualificadas()) + ";"
						+ String.valueOf(docen.getQtdProdCientificasNQualificadas());
				output.escreveString(saida);
			}
			output.fecharFw();
		}
	}

	private void gerarRha(Saida output) {
		if (output.abrirArquivoParaEscrita("/2-rha.csv")) {
			output.escreverCabecalho("HEAD_RHA");
			Collections.sort(departamentos);
			for (Departamento depa : departamentos) {
				Collections.sort(depa.getDocentes());
				for (Docente docen : depa.getDocentes()) {
					Collections.sort(docen.getCursos());
					for (Curso curso : docen.getCursos()) {
						String saida = docen.getDepartamento() + ";" + docen.getNome() + ";"
								+ String.valueOf(curso.getCodigoCurso()) + ";" + curso.getNome() + ";"
								+ String.valueOf(curso.TotalHorasDeDocente(docen.getCodigo()));
						output.escreveString(saida);
					}
				}
			}
			output.fecharFw();
		}
	}

	private void gerarAlocacao(Saida output) {
		if (output.abrirArquivoParaEscrita("/3-alocacao.csv")) {
			output.escreverCabecalho("HEAD_ALOCACAO");
			ArrayList<Docente> docentesUfes = new ArrayList<Docente>(quantidadeTotalDocentes());
			for (Departamento depa : departamentos)
				docentesUfes.addAll(depa.getDocentes());
			Collections.sort(docentesUfes);
			for (Docente docen : docentesUfes) {
				List<Disciplina> disciplinas = docen.getDisciplinasDadas();
				Collections.sort(disciplinas);
				for (Disciplina dis : disciplinas) {
					String saida = docen.getNome() + ";" + String.valueOf(dis.getCodigo()) + ";" + dis.getNome() + ";"
							+ String.valueOf(dis.getcHSemestral());
					output.escreveString(saida);
				}
			}
			output.fecharFw();

		}
	}

	private void gerarPpg(Saida output) {
		if (output.abrirArquivoParaEscrita("/4-ppg.csv")) {
			output.escreverCabecalho("HEAD_PPG");
			ArrayList<OrientaPos> ori = new ArrayList<OrientaPos>();
			for (Departamento dep : departamentos) {
				for (Docente doc : dep.getDocentes())
					ori.addAll(doc.getOrientaPos());
			}
			Collections.sort(ori);
			for (OrientaPos o : ori) {
				String saida = o.getPrograma() + ";" + o.getDateString() + ";"
						+ String.valueOf(o.getMatriculaDiscente()) + ";" + o.getNomeDiscente();
				output.escreveString(saida);
			}
			output.fecharFw();
		}
	}
}