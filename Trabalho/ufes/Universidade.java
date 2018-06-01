package ufes;

import java.util.List;

import exceptions.ErroCodInvalCurEmDisci;
import exceptions.ErroCodInvalCursoEmOri;
import exceptions.ErroCodInvalDocenEmDisci;
import exceptions.ErroCodInvalDocenEmOri;
import exceptions.ErroDataNoFuturo;
import exceptions.ErroInconsisNivelCurso;
import exceptions.ErroMesmoCodigo;
import trabalho1Prog3.Entrada;
import trabalho1Prog3.Saida;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

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

	public void preencheDadosUniversidade(Entrada input) throws IOException, NumberFormatException, ParseException,
			ErroMesmoCodigo, ErroCodInvalDocenEmDisci, ErroCodInvalCurEmDisci, ErroCodInvalDocenEmOri,
			ErroCodInvalCursoEmOri, ErroInconsisNivelCurso, ErroDataNoFuturo {
		criaDepartamentos(input.lePlanilha(Entrada.CAMINHO_PL_DOCENTE, 3));
		adicionaCursos(input.lePlanilha(Entrada.CAMINHO_PL_CURSOS, 4));
		adicionaProdCientificaAosDocentes(input.lePlanilha(Entrada.CAMINHO_PL_PRODCIENTIFICA, 3));
		adicionaDisciplinasCursosECursosADocentes(input.lePlanilha(Entrada.CAMINHO_PL_DISCIPLINAS, 6));
		adicionaDiscentesAosCursos(input.lePlanilha(Entrada.CAMINHO_PL_DISCENTE, 3));
		adicionaOrientacaoGradAosDocentes(input.lePlanilha(Entrada.CAMINHO_PL_ORIENTAGRAD, 4));
		adicionaOrientacaoPosAosDocentes(input.lePlanilha(Entrada.CAMINHO_PL_ORIENTAPOS, 5));
	}

	private void criaDepartamentos(String[][] planilhaDocentes) throws NumberFormatException, ErroMesmoCodigo {
		ArrayList<Docente> docentes = new ArrayList<Docente>(planilhaDocentes.length);
		for (String[] str : planilhaDocentes) {
			Departamento depa = this.getDepartamento(str[2]);
			Docente docen = new Docente(str);
			for (Docente docens : docentes) {
				if (docens.getCodigo() == docen.getCodigo())
					throw new ErroMesmoCodigo(docen, docen.getCodigo());
			}
			docentes.add(docen);
			if (depa != null)
				depa.setNovoDocente(docen);
			else {
				Departamento novoDepa = new Departamento(str[2]);
				novoDepa.setNovoDocente(docen);
				this.departamentos.add(novoDepa);
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

	private void adicionaCursos(String[][] planilhaCursos)
			throws NumberFormatException, ErroMesmoCodigo, ErroInconsisNivelCurso {
		int i = 0;
		for (String[] str : planilhaCursos) {
			Curso cur = new Curso(str);
			if (cur.getEhGrad() == cur.getEhPos())
				throw new ErroInconsisNivelCurso(cur.getCodigoCurso(), cur.getNome());
			for (Curso curso : this.cursos) {
				if (curso == null)
					break;
				if (curso.getCodigoCurso() == cur.getCodigoCurso())
					throw new ErroMesmoCodigo(cur, cur.getCodigoCurso());
			}
			this.cursos[i++] = cur;
		}
	}

	private Departamento getDepartamento(String departamento) {
		for (Departamento depa : departamentos) {
			if (depa.getNome().equals(departamento))
				return depa;
		}
		return null;
	}

	private void adicionaDisciplinasCursosECursosADocentes(String[][] plDisci) throws NumberFormatException,
			ErroMesmoCodigo, ErroCodInvalDocenEmDisci, ErroCodInvalCurEmDisci, ErroCodInvalDocenEmOri {
		ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>(plDisci.length);
		for (String[] str : plDisci) {
			Disciplina disci = new Disciplina(str);
			for (Disciplina dis : disciplinas) {
				if (dis.getCodigo() == disci.getCodigo())
					throw new ErroMesmoCodigo(disci, disci.getCodigo());
			}
			Curso curso = adicionaDisciplinaNoCursoERetornaCurso(disci);
			if (curso != null) {
				Docente docen = getDocentePelaDisciplina(disci.getCodigoDocente());
				if (docen != null) {
					if (!docen.docenteJaPossuiCurso(curso.getCodigoCurso()))
						docen.adicionaCurso(curso);
				} else
					throw new ErroCodInvalDocenEmDisci(disci.getNome(), disci.getCodigoDocente());
			} else
				throw new ErroCodInvalCurEmDisci(disci.getNome(), disci.getCodigoCurso());
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

	private void adicionaDiscentesAosCursos(String[][] planilhaDiscentes)
			throws NumberFormatException, ErroMesmoCodigo {
		ArrayList<Discente> discentes = new ArrayList<Discente>(planilhaDiscentes.length);
		for (String[] str : planilhaDiscentes) {
			Discente discen = new Discente(str);
			for (Discente dis : discentes) {
				if (dis.getMatricula() == discen.getMatricula())
					throw new ErroMesmoCodigo(discen, discen.getMatricula());
			}
			discentes.add(discen);
			for (Curso cur : cursos) {
				if (cur.getCodigoCurso() == discen.getCodigoCurso()) {
					cur.adicionaDiscenteNoCurso(discen);
					break;
				}
			}
		}
	}

	private void adicionaOrientacaoGradAosDocentes(String[][] plOri)
			throws NumberFormatException, ErroCodInvalDocenEmOri, ErroCodInvalCursoEmOri {
		for (String[] str : plOri) {
			int matDiscente = Integer.parseInt(str[1]);
			Discente dis = getDiscentePeloCurso(matDiscente);
			OrientaGrad ori = new OrientaGrad(str, dis);
			if (codCursoInvalido(ori.getCodigoCurso()))
				throw new ErroCodInvalCursoEmOri(dis.getNome(), ori.getCodigoCurso());
			if (!adicionouOriDocen(ori))
				throw new ErroCodInvalDocenEmOri(dis.getNome(), ori.getCodigoDocente());
		}
	}

	private boolean codCursoInvalido(int codCursoOrientacao) {
		for (Curso cur : this.cursos) {
			if (cur.getCodigoCurso() == codCursoOrientacao)
				return false;
		}
		return true;
	}

	private boolean adicionouOriDocen(Orientacao ori) {
		for (Departamento depa : this.departamentos) {
			for (Docente docen : depa.getDocentes())
				if (docen.getCodigo() == ori.getCodigoDocente()) {
					docen.adicionaOrientacaoALista(ori);
					return true;
				}
		}
		return false;
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

	private void adicionaOrientacaoPosAosDocentes(String[][] plOri)
			throws NumberFormatException, ParseException, ErroCodInvalDocenEmOri, ErroDataNoFuturo {
		for (String[] str : plOri) {
			int matDiscente = Integer.parseInt(str[1]);
			Discente dis = getDiscentePeloCurso(matDiscente);
			OrientaPos ori = new OrientaPos(str, dis);
			if (!adicionouOriDocen(ori))
				throw new ErroCodInvalDocenEmOri(dis.getNome(), ori.getCodigoDocente());
		}
	}

	private int quantidadeTotalDocentes() {
		int qtd = 0;
		for (Departamento dep : departamentos)
			qtd += dep.getQuantidadeDocentes();
		return qtd;
	}

	public void serializarDados(String caminhoSaida) throws IOException {
		FileOutputStream fout = new FileOutputStream(caminhoSaida + "/dados.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(this);
		oos.close();
	}

	public void gerarSaidas(Saida output) throws IOException {
		gerarPad(output);
		gerarRha(output);
		gerarAlocacao(output);
		gerarPpg(output);
	}

	private void gerarPad(Saida output) throws IOException {
		int tHSemanais;
		int tHSemestrais;
		output.abrirArquivoParaEscrita("/1-pad.csv");
		output.escreveString(Saida.HEAD_PAD);
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

	private void gerarRha(Saida output) throws IOException {
		output.abrirArquivoParaEscrita("/2-rha.csv");
		output.escreveString(Saida.HEAD_RHA);
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

	private void gerarAlocacao(Saida output) throws IOException {
		output.abrirArquivoParaEscrita("/3-alocacao.csv");
		output.escreveString(Saida.HEAD_ALOCACAO);
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

	private void gerarPpg(Saida output) throws IOException {
		output.abrirArquivoParaEscrita("/4-ppg.csv");
		output.escreveString(Saida.HEAD_PPG);
		ArrayList<OrientaPos> ori = new ArrayList<OrientaPos>();
		for (Departamento dep : departamentos) {
			for (Docente doc : dep.getDocentes())
				ori.addAll(doc.getOrientaPos());
		}
		Collections.sort(ori);
		for (OrientaPos o : ori) {
			String saida = o.getPrograma() + ";" + o.getDateString() + ";" + String.valueOf(o.getMatriculaDiscente())
					+ ";" + o.getNomeDiscente();
			output.escreveString(saida);
		}
		output.fecharFw();
	}
}