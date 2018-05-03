package trabalho;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class Entrada {
	private String[] caminhoArquivos;
	private final int N_PLANILHAS;
	private boolean soLeitura = false;
	private boolean soEscrita = false;
	/*
	 * 0 - Planilha de docentes -d 1 - Planilha de discentes -a 2 - Planilha de
	 * produções científicas -p 3 - Planilha de cursos -c 4 - Planilha de
	 * disciplinas(atividades didático-aulas) -r 5 - Planilha de orientação de
	 * discentes da graduação -o 6 - Planilha de orientação de discentes da
	 * pós-graduação
	 */

	public Entrada() {
		N_PLANILHAS = 6;
		caminhoArquivos = new String[N_PLANILHAS];
		soLeitura = soEscrita = false;
	}

	public String getIndexCaminhoArquivo(int pos) {
		return this.caminhoArquivos[pos];
	}

	public void lerLinhaComando(String[] linhaDeComando) {
		for (int i = 0; i < linhaDeComando.length; i++) {
			if (Objects.equals(linhaDeComando[i], "-d"))
				caminhoArquivos[0] = linhaDeComando[++i];
			else if (Objects.equals(linhaDeComando[i], "-a"))
				caminhoArquivos[1] = linhaDeComando[++i];
			else if (Objects.equals(linhaDeComando[i], "-p"))
				caminhoArquivos[2] = linhaDeComando[++i];
			else if (Objects.equals(linhaDeComando[i], "-c"))
				caminhoArquivos[3] = linhaDeComando[++i];
			else if (Objects.equals(linhaDeComando[i], "-r"))
				caminhoArquivos[4] = linhaDeComando[++i];
			else if (Objects.equals(linhaDeComando[i], "-o"))
				caminhoArquivos[5] = linhaDeComando[++i];
			else if (Objects.equals(linhaDeComando[i], "--read-only"))
				soLeitura = true;
			else if (Objects.equals(linhaDeComando[i], "--write-only"))
				soEscrita = true;
		}
	}

	public int qtdLinhas(String caminho) {
		int qtdDoscentes = 0;
		BufferedReader leitor = null; // tratar se a entrada n for .csv
		try {
			leitor = new BufferedReader(new FileReader(caminho));
			while (leitor.readLine() != null)
				qtdDoscentes++;
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
		return qtdDoscentes;
	}

	public void lePlanilha(String caminho) {

	}

}
