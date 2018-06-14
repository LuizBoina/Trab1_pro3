package trabalho1Prog3;

import java.io.IOException;
import java.text.ParseException;

import dominio.Universidade;
import exceptions.ErroCodInvalCurEmDisci;
import exceptions.ErroCodInvalCursoEmOri;
import exceptions.ErroCodInvalDocenEmDisci;
import exceptions.ErroCodInvalDocenEmOri;
import exceptions.ErroDataNoFuturo;
import exceptions.ErroInconsisNivelCurso;
import exceptions.ErroMesmoCodigo;
import interfaceGrafica.GUI;
/**
 * Essa classe contém o método principal para o sistema de gerenciamento de atividades de docente da UFES, que cria os
 * principais objetos do sistema e trata suas excessões. 
 * 
 * <p> Essa classe é uma parte do pacote trabalho1Prog3</a>.
 * 
 * 
 * @author Luiz Felipe Boina
 * @author Fernando Bisi Vieira
 * @see Universidade
 * @see Entrada
 * @see Saida
 */
public class Main {
	
	/**
	 * Produz os arquivos de saída e os objetos de acordo com o modo de execução e trata as excessões.
	 * 
	 * <p>Os tipos de execução são especificados na forma de argumentos na linha de comando ao execuar o programa.Na 
	 * execução padrão(sem especificação para a execução), esse método lê os arquivos de entrada, organiza em objetos os
	 * dados obtidos e produz os arquivos de saída. Na execução "--read-only", esse método lê os arquivos de entrada e 
	 * produz objetos a partir deles, a diferença é que os objetos são serializados e armazenados em um arquivo 
	 * "dados.dat". Na execução "--write-only", que não recebe o caminho dos arquivos de entrada, o programa lê um 
	 * arquivo "dados.dat" armazenado na raiz do programa, deserializando os objetos nele contidos e gerando os arquivos
	 * de sáida.
	 * 
	 * <p>O método trata as exceções imprimindo uma mensagem de erro e finalizando o programa. 
	 * @param args o array de String que contem os caminhos dos arquivos de entrada e/ou o modo de execução.
	 */
	public static void main(String[] args) {
		Entrada input = new Entrada(args);
		Saida output = new Saida();
		Universidade ufes;
		try {
			if(input.getGUI()) {
				GUI inter = new GUI(input, output);
				inter.mostraJanela();
			}
				if (input.getSoEscrita())
					ufes = (Universidade) input.deserializandoDados();
				else {
					ufes = new Universidade(input.qtdLinhas(Entrada.CAMINHO_PL_CURSOS));
					ufes.preencheDadosUniversidade(input);
				}
				if (input.getSoLeitura())
					ufes.serializarDados();
				else
					ufes.gerarSaidas(output);
		} catch (IOException | NullPointerException erroAbertura) {
			System.out.println("Erro de I/O");
		} catch (NumberFormatException | ParseException erroParsing) {
			System.out.println("Erro de formatação");
		} catch (ClassNotFoundException erroDeserializacao) {
			System.out.println("Erro em deserializar dados");
		} catch (ErroMesmoCodigo msmCod) {
			System.out.println(msmCod.getMessage());
		} catch (ErroCodInvalDocenEmDisci CodDocenInvalidoDisciDocen) {
			System.out.println(CodDocenInvalidoDisciDocen.getMessage());
		} catch (ErroCodInvalCurEmDisci codCursoInvalidoDisciCurso) {
			System.out.println(codCursoInvalidoDisciCurso.getMessage());
		} catch (ErroCodInvalDocenEmOri codInvalidoOriDocen) {
			System.out.println(codInvalidoOriDocen.getMessage());
		} catch (ErroCodInvalCursoEmOri codInvalidoOriCurso) {
			System.out.println(codInvalidoOriCurso.getMessage());
		} catch (ErroInconsisNivelCurso niveisIguais) {
			System.out.println(niveisIguais.getMessage());
		} catch (ErroDataNoFuturo dataInvalida) {
			System.out.println(dataInvalida.getMessage());
		}
	}

}
