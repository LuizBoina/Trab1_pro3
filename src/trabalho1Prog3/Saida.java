package trabalho1Prog3;


import java.io.FileWriter;
import java.io.IOException;

public class Saida {
	private final String NOVA_LINHA;
	public static String CAMINHO_PASTA;
	public static String HEAD_PAD = "Docente;Departamento;Horas Semanais Aula;Horas Semestrais Aula;Horas Semanais Orientação;Produções Qualificadas;Produções Não Qualificadas";
	public static String HEAD_RHA = "Departamento;Docente;Cód. Curso;Curso;Horas Semestrais Aula";
	public static String HEAD_ALOCACAO = "Docente;Código;Nome;Carga Horária Semestral";
	public static String HEAD_PPG = "Nome do Programa;Data de Ingresso;Matrícula;Nome";
	FileWriter fw;

	public Saida(String caminhoPastaSaida) {
		CAMINHO_PASTA = caminhoPastaSaida;
		this.NOVA_LINHA = "\n";
		fw = null;
	}

	public void escreveString(String str) throws IOException {
		this.fw.append(str);
		this.fw.append(NOVA_LINHA);
	}

	public void abrirArquivoParaEscrita(String nomeArqSaida) throws IOException {
		this.fw = new FileWriter(CAMINHO_PASTA + nomeArqSaida);
	}

	public void fecharFw() throws IOException{
		fw.flush();
		fw.close();
	}
	
}
