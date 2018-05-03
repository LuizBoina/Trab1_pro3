package trabalho;

import java.util.Objects;

public class Main {
	private static final int N_PLANILHAS = 6;
	private static boolean soLeitura = false;
	private static boolean soEscrita = false;
	public static Docente[] docentes;
	public static Discente[] discentes;

	public static void main(String[] args) {
		String[] planilhas = Main.lerLinhaComando(args);
		//Main.imprimirNomesArquivos(planilhas);
		
	}
	
	public static String[] lerLinhaComando(String[] linhaDeComando) {
		String[] arquivos = new String[N_PLANILHAS];
		int length = linhaDeComando.length;
		for(int i = 0; i < length ;i++) {
			if(Objects.equals(linhaDeComando[i], "-d"))
				arquivos[0] = linhaDeComando[++i];
			else if(Objects.equals(linhaDeComando[i], "-a"))
				arquivos[1] = linhaDeComando[++i];
			else if(Objects.equals(linhaDeComando[i], "-p"))
				arquivos[2] = linhaDeComando[++i];
			else if(Objects.equals(linhaDeComando[i], "-c"))
				arquivos[3] = linhaDeComando[++i];
			else if(Objects.equals(linhaDeComando[i], "-r"))
				arquivos[4] = linhaDeComando[++i];
			else if(Objects.equals(linhaDeComando[i], "-o"))
				arquivos[5] = linhaDeComando[++i];
			else if(Objects.equals(linhaDeComando[i], "NAOESPECIFICADO"))
				arquivos[6] = linhaDeComando[++i];
			else if(Objects.equals(linhaDeComando[i], "--read-only"))
				soLeitura = true;
			else if(Objects.equals(linhaDeComando[i], "--write-only"))
				soEscrita = true;
		}
		return arquivos;
	}
	public static void imprimirNomesArquivos(String[] arquivos) {
		int length = arquivos.length;
		for(int i = 0 ; i < length ; i++) 
			System.out.println(arquivos[i]);
	}
	
	
}
