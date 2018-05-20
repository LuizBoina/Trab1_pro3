package trabalho1Prog3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Entrada {
	private String[] caminhoArquivos;
	private final int N_PLANILHAS;
	private boolean soLeitura = false;
	private boolean soEscrita = false;

	public Entrada() {
		N_PLANILHAS = 7;
		caminhoArquivos = new String[N_PLANILHAS];
		soLeitura = soEscrita = false;
	}

	public String getIndexCaminhoArquivo(int pos) {
		return this.caminhoArquivos[pos];
	}
	
	public boolean getSoLeitura() {
		return this.soLeitura;
	}
	
	public boolean getSoEscrita() {
		return this.soEscrita;
	}

	public void lerLinhaComando(String[] linhaDeComando) {
		int i = 0;
		while (i < linhaDeComando.length) {
			if (Objects.equals(linhaDeComando[i], "-d"))
				caminhoArquivos[0] = linhaDeComando[++i];
			else if (linhaDeComando[i].equals("-a"))
				caminhoArquivos[1] = linhaDeComando[++i];
			else if (linhaDeComando[i].equals("-p"))
				caminhoArquivos[2] = linhaDeComando[++i];
			else if (linhaDeComando[i].equals("-c"))
				caminhoArquivos[3] = linhaDeComando[++i];
			else if (linhaDeComando[i].equals("-r"))
				caminhoArquivos[4] = linhaDeComando[++i];
			else if (linhaDeComando[i].equals("-og"))
				caminhoArquivos[5] = linhaDeComando[++i];
			else if (linhaDeComando[i].equals("-op"))
				caminhoArquivos[6] = linhaDeComando[++i];
			else if (linhaDeComando[i].equals("--read-only")) {
				soLeitura = true;
				i++;
			} else if (linhaDeComando[i].equals("--write-only")) {
				soEscrita = true;
				i++;
			} else
				System.out.println("argumento invalido");
			i++;
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

	public String[][] lePlanilha(int posCaminhoArquivos, int qtdCelulas) {
		String caminhoArq = getIndexCaminhoArquivo(posCaminhoArquivos);
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
	
	public String getCaminhoDosArquivos(){
		return caminhoArquivos[0].substring(0, caminhoArquivos[0].lastIndexOf("/"));
	}

}
