package dominio;

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

/**
 * Essa classe contém as informações de uma Universidade e os métodos necessários para sua manipulação.
 * 
 * <p>Essa classe é membro do pacote ufes</a>.
 * 
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 */
public class Universidade implements Serializable {
	
	//Atributos privados
	/**Atributo que possui a versão de serialização de Universidade*/
	private static final long serialVersionUID = 1L;
	/**Atributo que possui a lista de departamentos da Universidade*/
	private List<Departamento> departamentos;
	/**Atributo que possui um array de cursos da Universidade*/
	private Curso[] cursos;

	/**
	 * Construtor de Universidade que recebe um int.
	 * 
	 * @param qtdCursos é o int que possui a quantidade de cursos da Universidade.
	 */
	public Universidade(int qtdCursos) {
		this.cursos = new Curso[qtdCursos];
		this.departamentos = new ArrayList<Departamento>();
	}

	/**
	 * Método que preenche e organiza os dados da Universidade recebendo uma Entrada.
	 * 
	 * @param input é a Entrada que fornece os dados dos arquivos de entrada.
	 * @throws IOException se não for possível manipular algum arquivo.
	 * @throws NumberFormatException se não for possível converter uma String em um número.
	 * @throws ParseException se uma conversão de classes não funcionar.
	 * @throws ErroMesmoCodigo se dois docentes,curso ou disciplina possuirem o mesmo código ou se dois discentes
	 * 		   possuírem mesma matricula.  
	 * @throws ErroCodInvalDocenEmDisci se o código de docente de uma disciplina for inválido.
	 * @throws ErroCodInvalCurEmDisci se o código de curso de uma disciplina for inválido.
	 * @throws ErroCodInvalDocenEmOri se o código de docente de uma orientação for inválido.
	 * @throws ErroCodInvalCursoEmOri se o código de curso de uma orientação for inválido.
	 * @throws ErroInconsisNivelCurso se um curso não for de graduação nem de pós-graduação ou for de ambos.
	 * @throws ErroDataNoFuturo se a data de ingresso de um discente num programa de pós-graduação for no futuro.
	 */
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

	/**
	 * Método que preenche a lista de departamentos e seus respectivos docentes.
	 * 
	 * @param planilhaDocentes é uma matriz(array de arrays) de String que contém os dados de uma planilha de docentes
	 * 		  organizados.
	 * @throws NumberFormatException se não for possível converter uma String em um número.
	 * @throws ErroMesmoCodigo se dois docentes possuirem o mesmo código.	 
	 */
	private void criaDepartamentos(String[][] planilhaDocentes) throws NumberFormatException, ErroMesmoCodigo {
		ArrayList<Docente> docentes = new ArrayList<Docente>(planilhaDocentes.length);
		for (String[] str : planilhaDocentes) {
			Departamento depa = this.getDepartamento(str[2]);
			Docente docen = new Docente(str);
			for (Docente docens : docentes) {
				if (docens.getCodigo() == docen.getCodigo())
					throw new ErroMesmoCodigo("docente", docen.getCodigo());
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

	/**
	 * Método que adiciona produções científicas aos respectivos docentes nos departamentos da Universidade.
	 * 
	 * @param planilhaProdCientificas é uma matriz(array de arrays) de String que contém os dados de uma planilha de
	 *        produções científicas organizados.
	 * @throws NumberFormatException se não for possível converter uma String em um número.
	 */
	private void adicionaProdCientificaAosDocentes(String[][] planilhaProdCientificas) throws NumberFormatException {
		for (String[] str : planilhaProdCientificas) {
			percorreDepa(new ProducaoCientifica(str));
		}
	}

	/**
	 * Método que percorre os departamentos da Universidae e adiciona uma produção científica ao docente correspondente.
	 * 
	 * @param prod é a ProducaoCientifica a ser adicionada.
	 */
	private void percorreDepa(ProducaoCientifica prod) {
		for (Departamento depa : departamentos) {
			if (depa.achouDocente(prod))
				break;
		}
	}

	/**
	 * Método que adiciona um curso aos cursos da Universidade.
	 * 
	 * @param planilhaCursos é uma matriz(array de arrays) de String que contém os dados de uma planilha de cursos 
	 * 		  organizados.
	 * @throws NumberFormatException se não for possível converter uma String em um número.
	 * @throws ErroMesmoCodigo se dois cursos possuirem o mesmo código.
	 * @throws ErroInconsisNivelCurso se um curso não for de graduação nem de pós-graduação ou for de ambos.
	 */
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
					throw new ErroMesmoCodigo("curso", cur.getCodigoCurso());
			}
			this.cursos[i++] = cur;
		}
	}

	/**
	 * Método que retorna um departamento da Universidade pelo nome.
	 * 
	 * @param departamento é o nome do Departamento a ser procurado.
	 * @return um Departamento se o Departamento for encontrado dentro da Universidadem, senão retorna null.
	 */
	private Departamento getDepartamento(String departamento) {
		for (Departamento depa : departamentos) {
			if (depa.getNome().equals(departamento))
				return depa;
		}
		return null;
	}

	/**
	 * Método que adiciona as disciplinas aos cursos da Universida e os cursos da Universidade aos docentes.
	 * 
	 * @param plDisci é uma matriz(array de arrays) de String que contém os dados de uma planilha de disciplinas 
	 * 		  organizados.
	 * @throws NumberFormatException se não for possível converter uma String em um número.
	 * @throws ErroMesmoCodigo se duas disciplinas possuirem a mesma matrícula.
	 * @throws ErroCodInvalDocenEmDisci se o código de docente de uma disciplina for inválido.
	 * @throws ErroCodInvalCurEmDisci se o código de curso de uma disciplina for inválido.
	 */
	private void adicionaDisciplinasCursosECursosADocentes(String[][] plDisci) throws NumberFormatException,
			ErroMesmoCodigo, ErroCodInvalDocenEmDisci, ErroCodInvalCurEmDisci {
		ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>(plDisci.length);
		for (String[] str : plDisci) {
			Disciplina disci = new Disciplina(str);
			for (Disciplina dis : disciplinas) {
				if (dis.getCodigo().equals(disci.getCodigo()))
					throw new ErroMesmoCodigo("disciplina", disci.getCodigo());
			}
			Curso curso = adicionaDisciplinaNoCursoERetornaCurso(disci);
			if (curso != null) {
				Docente docen = getDocentePeloCodigo(disci.getCodigoDocente());
				if (docen != null) {
					if (!docen.docenteJaPossuiCurso(curso.getCodigoCurso()))
						docen.adicionaCurso(curso);
				} else
					throw new ErroCodInvalDocenEmDisci(disci.getNome(), disci.getCodigoDocente());
			} else
				throw new ErroCodInvalCurEmDisci(disci.getNome(), disci.getCodigoCurso());
		}
	}

	/**
	 * Método que adiciona uma disciplina no curso respectivo da Universidade e retorna o curso.
	 * 
	 * @param disciplina é a Disciplina a ser adicionada.
	 * @return o Curso da Disciplina se ele for encontrado, caso contrário retorna null.
	 */
	private Curso adicionaDisciplinaNoCursoERetornaCurso(Disciplina disciplina) {
		for (Curso curso : cursos) {
			if (curso.getCodigoCurso() == disciplina.getCodigoCurso()) {
				curso.adicionaDisciplinaNoCurso(disciplina);
				return curso;
			}
		}
		return null;
	}

	/**
	 * Método que retorna o docente correspondente ao código.
	 * 
	 * @param codDocente é o código do Docente que vai ser procurado dentro dos departamentos de Universidade.
	 * @return o Docente se ele for encontrado, senão retorna null.
	 */
	private Docente getDocentePeloCodigo(int codDocente) {
		for (Departamento depa : departamentos) {
			for (Docente docen : depa.getDocentes()) {
				if (docen.getCodigo() == codDocente)
					return docen;
			}
		}
		return null;
	}

	/**
	 * Método que adiciona os discentes aos cursos da Universidade.
	 * 
	 * @param planilhaDiscentes é a matriz(array de arrays) de String que contém os dados de uma planilha de discentes
	 * 		  organizados.
	 * @throws NumberFormatException se não for possível converter uma String em um número.
	 * @throws ErroMesmoCodigo se dois discentes possuírem a mesma matrícula.
	 */
	private void adicionaDiscentesAosCursos(String[][] planilhaDiscentes)
			throws NumberFormatException, ErroMesmoCodigo {
		ArrayList<Discente> discentes = new ArrayList<Discente>(planilhaDiscentes.length);
		for (String[] str : planilhaDiscentes) {
			Discente discen = new Discente(str);
			for (Discente dis : discentes) {
				if (dis.getMatricula() == discen.getMatricula())
					throw new ErroMesmoCodigo("discente", ((Integer)discen.getMatricula()).toString());
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

	/**
	 * Método que adiciona as orientações de graduação aos docentes da Universidade.
	 * 
	 * @param plOri é a matriz(array de arrays) de String que contém os dados de uma planilha de orientações de 
	 * 		  graduação organizados.
	 * @throws NumberFormatException se não for possível converter uma String em um número.
	 * @throws ErroCodInvalDocenEmOri se o código de docente de uma orientação for inválido.
	 * @throws ErroCodInvalCursoEmOri se o código de curso de uma orientação for inválido.
	 */
	private void adicionaOrientacaoGradAosDocentes(String[][] plOri)
			throws NumberFormatException, ErroCodInvalDocenEmOri, ErroCodInvalCursoEmOri {
		for (String[] str : plOri) {
			int matDiscente = Integer.parseInt(str[1]);
			Discente dis = getDiscente(matDiscente);
			OrientaGrad ori = new OrientaGrad(str, dis);
			if (!cursoExiste(ori.getCodigoCurso()))
				throw new ErroCodInvalCursoEmOri(dis.getNome(), ori.getCodigoCurso());
			if (!adicionouOriDocen(ori))
				throw new ErroCodInvalDocenEmOri(dis.getNome(), ori.getCodigoDocente());
		}
	}

	/**
	 * Método que determina se um curso ja existe na Universidade.
	 * 
	 * @param codCursoOrientacao é o código do curso a ser verificado.
	 * @return um boolean, true se o curso existir e false caso o curso não exista.
	 */
	private boolean cursoExiste(int codCursoOrientacao) {
		for (Curso cur : this.cursos) {
			if (cur.getCodigoCurso() == codCursoOrientacao)
				return true;
		}
		return false;
	}

	/**
	 * Método que tenta adicionar uma orientação a um docente da Universidade com o código correspondente e retorna 
	 * se obteve sucesso.
	 * 
	 * @param ori é a orientação a ser adicionada.
	 * @return um boolean, true se adicionar a orientação a algum docente e false se não achar tal docente.
	 */
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

	/**
	 * Método que retorna um discente da Universidade pela matrícula.
	 * 
	 * @param matDiscente é a matrícula do discente.
	 * @return o Discente correspondente, ou null caso ele não exista dentro da Universidade.
	 */
	private Discente getDiscente(int matDiscente) {
		for (Curso curso : cursos) {
			for (Discente dis : curso.getDiscentes()) {
				if (dis.getMatricula() == matDiscente)
					return dis;
			}
		}
		return null;
	}

	/**
	 * Método que adiciona as orientações de pós-graduação aos docentes da Universidade.
	 * 
	 * @param plOri é uma matriz(array de arrays) de String que contém os dados de uma planilha de orientações de pós-
	 * 		  graduação organizados.
	 * @throws NumberFormatException se não for possível converter uma String em um número.
	 * @throws ParseException se uma conversão de classes não funcionar.
	 * @throws ErroCodInvalDocenEmOri se o código de docente de uma orientação for inválido.
	 * @throws ErroDataNoFuturo se a data de ingresso de um discente num programa de pós-graduação for no futuro.
	 */
	private void adicionaOrientacaoPosAosDocentes(String[][] plOri)
			throws NumberFormatException, ParseException, ErroCodInvalDocenEmOri, ErroDataNoFuturo {
		for (String[] str : plOri) {
			int matDiscente = Integer.parseInt(str[1]);
			Discente dis = getDiscente(matDiscente);
			OrientaPos ori = new OrientaPos(str, dis);
			if (!adicionouOriDocen(ori))
				throw new ErroCodInvalDocenEmOri(dis.getNome(), ori.getCodigoDocente());
		}
	}

	/**
	 * Método que calcula e retorna a quantidade total de docentes na Universidade.
	 * 
	 * @return um int com a quantidade total de docentes na Universidade.
	 */
	private int quantidadeTotalDocentes() {
		int qtd = 0;
		for (Departamento dep : departamentos)
			qtd += dep.getQuantidadeDocentes();
		return qtd;
	}

	/**
	 * Método que serializa os dados da Universidade num arquivo "dados.dat" na raiz do código fonte.
	 * 
	 * @throws IOException se não for possível criar e escrever um "dados.dat"
	 */
	public void serializarDados() throws IOException {
		FileOutputStream fout = new FileOutputStream("dados.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(this);
		oos.close();
	}

	/**
	 * Método que gera os relatórios de saída com as informações da Universidade.
	 * 
	 * @param output é a Saída que vai administrar a escrita dos relatórios.
	 * @throws IOException se algum relatório de saída não for criado corretamente.
	 */
	public void gerarSaidas(Saida output) throws IOException {
		gerarPad(output);
		gerarRha(output);
		gerarAlocacao(output);
		gerarPpg(output);
	}

	/**
	 * Método que gera o Plano Semestral das Atividades Docentes (pad.csv) na raiz do código fonte.
	 * 
	 * @param output é a Saída que vai administrar a escrita do pad.csv.
	 * @throws IOException se o pad.csv não for criado corretamente.
	 */
	private void gerarPad(Saida output) throws IOException {
		int tHSemanais;
		int tHSemestrais;
		output.abrirArquivoParaEscrita("1-pad.csv");
		try {
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
		}finally {
			output.fecharFw();
		}
	}

	/**
	 * Método que gera o Relatório de Horas Aula (rha.csv) na raiz do código fonte.
	 * 
	 * @param output é a Saída que vai administrar a escrita do rha.csv.
	 * @throws IOException se o rha.csv não for criado corretamente.
	 */
	private void gerarRha(Saida output) throws IOException {
		output.abrirArquivoParaEscrita("2-rha.csv");
		try {
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
		} finally {
			output.fecharFw();
		}
	}

	/**
	 * Método que gera a Tabela de alocação de disciplinas (alocacao.csv) na raiz do código fonte.
	 * 
	 * @param output é a Saída que vai administrar a escrita do alocacao.csv.
	 * @throws IOException se o alocacao.csv não for criado corretamente.
	 */
	private void gerarAlocacao(Saida output) throws IOException {
		output.abrirArquivoParaEscrita("3-alocacao.csv");
		try {
			output.escreveString(Saida.HEAD_ALOCACAO);
			ArrayList<Docente> docentesUfes = new ArrayList<Docente>(quantidadeTotalDocentes());
			for (Departamento depa : departamentos)
				docentesUfes.addAll(depa.getDocentes());
			Collections.sort(docentesUfes);
			for (Docente docen : docentesUfes) {
				List<Disciplina> disciplinas = docen.getDisciplinasDadas();
				Collections.sort(disciplinas);
				for (Disciplina dis : disciplinas) {
					String saida = docen.getNome() + ";" + dis.getCodigo() + ";" + dis.getNome() + ";"
							+ String.valueOf(dis.getcHSemestral());
					output.escreveString(saida);
				}
			}
		} finally {
			output.fecharFw();
		}
	}

	/**
	 * Método que gera a Lista de discentes de pós-graduação (ppg.csv) na raiz do código fonte.
	 * 
	 * @param output é a Saída que vai administrar a escrita do ppg.csv.
	 * @throws IOException se o ppg.csv não for criado corretamente.
	 */
	private void gerarPpg(Saida output) throws IOException {
		output.abrirArquivoParaEscrita("4-ppg.csv");
		try {
			output.escreveString(Saida.HEAD_PPG);
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
		} finally {
			output.fecharFw();
		}
	}
}