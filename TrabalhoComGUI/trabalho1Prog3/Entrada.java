package trabalho1Prog3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Objects;

import dominio.Universidade;

/**
 * Essa classe armazena o caminho dos arquivos de entrada, possui métodos para
 * extração de dados desses arquivos e determina o modo de execução do sistema.
 * 
 * <p>
 * Essa classe é uma parte do pacote trabalho1Prog3</a>.
 * 
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 * @see BufferedReader
 * @see FileReader
 * @see Exception
 */
public class Entrada {

	// Declaração dos atributos privados
	/** Atributo que determina se o método de execução é "--read-only" */
	private boolean soLeitura;
	/** Atributo que determina se o método de execução é "--write-only" */
	private boolean soEscrita;
	/**
	 * Atributo que determina se o programa deve ser rodado com interface gráfica
	 */
	private boolean GUI;
	// Declaração dos atributos publicos
	/** Atributo que armazina o caminho da planilha de docentes */
	public static String CAMINHO_PL_DOCENTE;
	/** Atributo que armazina o caminho da planilha de discentes */
	public static String CAMINHO_PL_DISCENTE;
	/** Atributo que armazina o caminho da planilha de produções científicas */
	public static String CAMINHO_PL_PRODCIENTIFICA;
	/** Atributo que armazina o caminho da planilha de cursos */
	public static String CAMINHO_PL_CURSOS;
	/** Atributo que armazina o caminho da planilha de disciplinas */
	public static String CAMINHO_PL_DISCIPLINAS;

	/**
	 * Atributo que armazina o caminho da planilha de orientações de discentes da
	 * graduação
	 */
	public static String CAMINHO_PL_ORIENTAGRAD;
	/**
	 * Atributo que armazina o caminho da planilha de orientações de discentes da
	 * pós-graduação
	 */
	public static String CAMINHO_PL_ORIENTAPOS;

	/**
	 * Contrutor de Entrada que pega as informações de um array de String para
	 * formar uma Entrada, onde as Strings podem ser caminho de arquivo, flags que
	 * determinam o tipo do arquivo cujo caminho está logo em seguida no array e
	 * flags que determinam o modo de execução do arquivo.
	 * 
	 * @param args
	 *            é o array de String que contém as informações para a construção de
	 *            Entrada
	 */
	public Entrada(String[] args) {
		this.lerLinhaComando(args);
	}

	/**
	 * Método que retorna se o modo de execução é "--read-only"
	 * 
	 * @return um boolean, true se for modo execução "--read-only" e false caso não
	 *         for.
	 */
	public boolean getSoLeitura() {
		return this.soLeitura;
	}

	public void setSoLeitura(boolean leitura) {
		this.soLeitura = leitura;
	}

	public void setSoEscrita(boolean escrita) {
		this.soEscrita = escrita;
	}

	/**
	 * Método que retorna se o modo de execução é "--write-only"
	 * 
	 * @return um boolean, true se for modo execução "--write-only" e false caso não
	 *         for.
	 */
	public boolean getSoEscrita() {
		return this.soEscrita;
	}

	public boolean getGUI() {
		return this.GUI;
	}

	/**
	 * Método que extrai as informações de um array de String para armazenar em
	 * Entrada.
	 * 
	 * @param linhaDeComando
	 *            é o array de String que contém as informações a serem armazenadas.
	 */
	private void lerLinhaComando(String[] linhaDeComando) {
		int i = 0;
		while (i < linhaDeComando.length) {
			if (Objects.equals(linhaDeComando[i], "-d"))
				CAMINHO_PL_DOCENTE = linhaDeComando[++i];
			else if (linhaDeComando[i].equals("-a"))
				CAMINHO_PL_DISCENTE = linhaDeComando[++i];
			else if (linhaDeComando[i].equals("-p"))
				CAMINHO_PL_PRODCIENTIFICA = linhaDeComando[++i];
			else if (linhaDeComando[i].equals("-c"))
				CAMINHO_PL_CURSOS = linhaDeComando[++i];
			else if (linhaDeComando[i].equals("-r"))
				CAMINHO_PL_DISCIPLINAS = linhaDeComando[++i];
			else if (linhaDeComando[i].equals("-og"))
				CAMINHO_PL_ORIENTAGRAD = linhaDeComando[++i];
			else if (linhaDeComando[i].equals("-op"))
				CAMINHO_PL_ORIENTAPOS = linhaDeComando[++i];
			else if (linhaDeComando[i].equals("--read-only"))
				soLeitura = true;
			else if (linhaDeComando[i].equals("--write-only"))
				this.soEscrita = true;
			else if (linhaDeComando[i].equals("--GUI"))
				this.GUI = true;
			i++;
		}
	}

	/**
	 * Método que descobre a quantidade de linhas de uma arquivo.
	 * 
	 * @param caminho
	 *            é a String que contém o caminho do arquivo.
	 * @return um int com o número de linhas do arquivo.
	 * @throws IOException
	 *             se o arquivo não existir no caminho dado.
	 */
	public int qtdLinhas(String caminho) throws IOException {
		int linhas = 0;
		BufferedReader leitor = null;
		leitor = new BufferedReader(new FileReader(caminho));
		while (leitor.readLine() != null)
			linhas++;
		leitor.close();
		return (linhas - 1);
	}

	/**
	 * Método que lê uma planilha, mantendo sua organização em células e colunas.
	 * 
	 * @param caminhoArq
	 *            é o caminho da planilha a ser lida.
	 * @param qtdCelulas
	 *            é a quantidade de células da planilha.
	 * @return uma matriz de String, que contém as informações da planilha
	 *         organizadas.
	 * @throws IOException
	 *             se a planilha não existir no caminho dado.
	 */
	public String[][] lePlanilha(String caminhoArq, int qtdCelulas) throws IOException {
		int numLinhas = qtdLinhas(caminhoArq);
		String[][] planilha = new String[numLinhas][qtdCelulas];
		BufferedReader leitor = null;
		String linhaLida = "";
		leitor = new BufferedReader(new FileReader(caminhoArq));
		linhaLida = leitor.readLine();
		for (int i = 0; (linhaLida = leitor.readLine()) != null; i++)
			planilha[i] = linhaLida.split(";");
		leitor.close();
		return planilha;
	}

	/**
	 * Método que deserializa os dados contidos num arquivo "dados.dat" na raiz do
	 * código fonte.
	 * 
	 * @return um Universidade que contem os dados de "dados.dat"
	 * @throws IOException
	 *             se o arquivo "dados.dat" não estiver no diretório correto.
	 * @throws ClassNotFoundException
	 *             se os dados dentro de "dados.dat" não estiverem no formato de
	 *             Universidade serializada.
	 */
	public Universidade deserializandoDados() throws IOException, ClassNotFoundException {
		FileInputStream fin = new FileInputStream("dados.dat");
		ObjectInputStream ois = new ObjectInputStream(fin);
		Universidade uni = (Universidade) ois.readObject();
		ois.close();
		return uni;
	}

	public void setIndexDocen(String caminho) {
		CAMINHO_PL_DOCENTE = caminho;
	}

	public void setIndexDiscen(String caminho) {
		CAMINHO_PL_DISCENTE = caminho;
	}

	public void setIndexCur(String caminho) {
		CAMINHO_PL_CURSOS = caminho;
	}

	public void setIndexAulas(String caminho) {
		CAMINHO_PL_DISCIPLINAS = caminho;
	}

	public void setIndexOriGrad(String caminho) {
		CAMINHO_PL_ORIENTAGRAD = caminho;
	}

	public void setIndexOriPos(String caminho) {
		CAMINHO_PL_ORIENTAPOS = caminho;
	}

	public void setIndexProd(String caminho) {
		CAMINHO_PL_PRODCIENTIFICA = caminho;
	}
	

}
