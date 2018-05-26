package trabalho1Prog3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Entrada {
	private boolean GUI;
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

	public boolean getGUI() {
		return this.GUI;
	}

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
			else if (linhaDeComando[i].equals("--read-only")) {
				soLeitura = true;
				i++;
			} else if (linhaDeComando[i].equals("GUI")) {
				this.GUI = true;
				i++;
			} else if (linhaDeComando[i].equals("--write-only")) {
				this.soEscrita = true;
				i++;
			} else {
				System.out.println("argumento invalido");
				i++;
			}
		}
	}

	public int qtdLinhas(String caminho) {
		int linhas = 0;
		BufferedReader leitor = null; // tratar se a entrada n for .csv
		try {
			leitor = new BufferedReader(new FileReader(caminho));
			while (leitor.readLine() != null)
				linhas++;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (leitor != null)
					leitor.close();
				if (leitor != null)
					leitor.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return (linhas - 1); // total de linhas menos a especif da coluna
	}

	public String[][] lePlanilha(String caminhoArq, int qtdCelulas) {
		int numLinhas = qtdLinhas(caminhoArq);
		String[][] planilha = new String[numLinhas][qtdCelulas];
		BufferedReader leitor = null;
		String linhaLida = "";
		try {
			leitor = new BufferedReader(new FileReader(caminhoArq));
			linhaLida = leitor.readLine(); // para desconsiderar a especific da coluna
			for (int i = 0; (linhaLida = leitor.readLine()) != null; i++) {
				planilha[i] = linhaLida.split(";");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (leitor != null) {
				try {
					leitor.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return planilha;
	}

	public String getCaminhoDosArquivos() {
		return CAMINHO_PL_ORIENTAPOS.substring(0, CAMINHO_PL_ORIENTAPOS.lastIndexOf("/"));
	}

}
