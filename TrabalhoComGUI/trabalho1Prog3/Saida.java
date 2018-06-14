package trabalho1Prog3;

import java.io.FileWriter;
import java.io.IOException;
/**
 * Essa classe armazena o cabeçalho dos arquivos de saída para o sistema e possui métodos para escrever esses arquivos.  
 * 
 * <p> Essa classe é uma parte do pacote trabalho1Prog3</a>.
 * 
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 *
 */
public class Saida {
	//Atributos privados
	/**Atributo que armazena o caractere de quebra de linha desejado*/
	private final String NOVA_LINHA;
	/**Atributo que é utilizado como leitor de arquivos*/
	private FileWriter fw;
	
	public static String CAMINHO_SAIDA;
	
	//Atributos publicos
	/**Atributo que armazena o cabeçalho do relatório pad.csv*/
	public static String HEAD_PAD = "Docente;Departamento;Horas Semanais Aula;Horas Semestrais Aula;Horas Semanais "
									+ "Orientação;Produções Qualificadas;Produções Não Qualificadas";
	/**Atributo que armazena o cabeçalho do relatório rha.csv*/
	public static String HEAD_RHA = "Departamento;Docente;Cód. Curso;Curso;Horas Semestrais Aula";
	/**Atributo que armazena o cabeçalho do relatório alocacao.csv*/
	public static String HEAD_ALOCACAO = "Docente;Código;Nome;Carga Horária Semestral";
	/**Atributo que armazena o cabeçalho do relatório ppg.csv*/
	public static String HEAD_PPG = "Nome do Programa;Data de Ingresso;Matrícula;Nome";
	
	/**
	 * Construtor padrão de saída.
	 */
	public Saida() {
		this.NOVA_LINHA = "\n";
		fw = null;
		CAMINHO_SAIDA = new String();
	}
	
	/**
	 * Método que adiciona uma String no arquivo terminada em quebra de linha.
	 * @param str e a String a ser escrita no arquivo.
	 * @throws IOException caso não tenha aberto o arquivo para escrevê-lo.
	 */
	public void escreveString(String str) throws IOException {
		this.fw.append(str);
		this.fw.append(NOVA_LINHA);
	}
	
	/**
	 * Método que abre o arquivo para escrita.
	 * @param nomeArqSaida é o caminho do arquivo.
	 * @throws IOException caso o arquivo não exista no caminho dado.
	 */
	public void abrirArquivoParaEscrita(String nomeArqSaida) throws IOException {
		this.fw = new FileWriter(nomeArqSaida);
	}
	
	/**
	 * Método que fecha o arquivo e salva as alterações nele feitas.
	 * @throws IOException caso o arquivo não estivesse aberto.
	 */
	public void fecharFw() throws IOException {
		fw.flush();
		fw.close();
	}
	
	public void setCaminhoSaida(String caminho) {
		CAMINHO_SAIDA = caminho+"/";
	}

}
