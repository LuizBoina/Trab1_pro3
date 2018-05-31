package trabalho1Prog3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Objects;

public class Entrada {
	// private boolean GUI;
	private boolean soLeitura;
	private boolean soEscrita;
	public static String CAMINHO_PL_DOCENTE;
	public static String CAMINHO_PL_DISCENTE;
	public static String CAMINHO_PL_PRODCIENTIFICA;
	public static String CAMINHO_PL_CURSOS;
	public static String CAMINHO_PL_DISCIPLINAS;
	public static String CAMINHO_PL_ORIENTAGRAD;
	public static String CAMINHO_PL_ORIENTAPOS;

	public Entrada(String[] args) {
		this.lerLinhaComando(args);
	}

	public boolean getSoLeitura() {
		return this.soLeitura;
	}

	public boolean getSoEscrita() {
		return this.soEscrita;
	}

	/*
	 * public boolean getGUI() { return this.GUI; }
	 */

	public void lerLinhaComando(String[] linhaDeComando) {
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
			/*
			 * else if (linhaDeComando[i].equals("--GUI")) { this.GUI = true; i++; }
			 */
			else if (linhaDeComando[i].equals("--write-only"))
				this.soEscrita = true;
			i++;
		}
	}

	public int qtdLinhas(String caminho) throws IOException {
		int linhas = 0;
		BufferedReader leitor = null;
		leitor = new BufferedReader(new FileReader(caminho));
		while (leitor.readLine() != null)
			linhas++;
		leitor.close();
		return (linhas - 1);
	}

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

	public String getCaminhoDosArquivos() {
		return CAMINHO_PL_ORIENTAPOS.substring(0, CAMINHO_PL_ORIENTAPOS.lastIndexOf("/"));
	}

	public Universidade deserializandoDados() throws IOException, ClassNotFoundException {
		FileInputStream fin = new FileInputStream("dados.dat");
		ObjectInputStream ois = new ObjectInputStream(fin);
		Universidade uni = (Universidade) ois.readObject();
		ois.close();
		return uni;
	}

}
