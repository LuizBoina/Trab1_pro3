package trabalho1Prog3;

import java.io.IOException;
import java.text.ParseException;

public class Main {

	public static void main(String[] args) {
		Entrada input = new Entrada(args);
		Saida output = new Saida(input.getCaminhoDosArquivos());
		Universidade ufes;
		try {
			if (input.getSoEscrita()) {
				ufes = (Universidade) input.deserializandoDados();
			} else {
				ufes = new Universidade(input.qtdLinhas(Entrada.CAMINHO_PL_CURSOS));
				ufes.preencheDadosUniversidade(input);
			}
			if (input.getSoLeitura()) {
				ufes.serializarDados(Saida.CAMINHO_PASTA);
			} else
				ufes.gerarSaidas(output);
		} catch (IOException erroAbertura) {
			System.out.println("Erro de I/O");
		} catch (NumberFormatException | ParseException erroParsing) {
			System.out.print("Erro de formatação");
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
