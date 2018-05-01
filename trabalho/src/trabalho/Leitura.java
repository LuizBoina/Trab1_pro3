package trabalho;

public class Leitura {
	private String[] arquivos;
	/* 0 - Planilha de docentes -d
	 * 1 - Planilha de discentes -a
	 * 2 - Planilha de produções científicas -p
	 * 3 - Planilha de cursos -c
	 * 4 - Planilha de disciplinas(atividades didático-aulas) -r
	 * 5 - Planilha de orientação de discentes da graduação -o
	 * 6 - Planilha de orientação de discentes da pós-graduação
	 */
	private boolean soLeitura;
	private boolean soEscrita;
	public Leitura() {
		arquivos = new String[6];
		soLeitura = false;
		soEscrita = false;
	}
	public void lerLinhaComando(String[] linhaDeComando) {
		for(int i = 0; i<linhaDeComando.length;i++) {
			if(linhaDeComando[i] == "-d")
				arquivos[0] = linhaDeComando[++i];
			else if(linhaDeComando[i] == "-a")
				arquivos[1] = linhaDeComando[++i];
			else if(linhaDeComando[i] == "-p")
				arquivos[2] = linhaDeComando[++i];
			else if(linhaDeComando[i] == "-c")
				arquivos[3] = linhaDeComando[++i];
			else if(linhaDeComando[i] == "-r")
				arquivos[4] = linhaDeComando[++i];
			else if(linhaDeComando[i] == "-o")
				arquivos[5] = linhaDeComando[++i];
			else if(linhaDeComando[i] == "NAOEXPECIFICADO")
				arquivos[6] = linhaDeComando[++i];
			else if(linhaDeComando[i] == "--ready-only")
				soLeitura = true;
			else if(linhaDeComando[i] == "--write-only")
				soEscrita = true;
		}
	}
	
}
