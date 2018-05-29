package trabalho1Prog3;

import java.io.IOException;
import java.text.ParseException;

public class Main {

	public static void main(String[] args) {
		Entrada input = new Entrada(args);
		Universidade ufes;
		try {
			ufes = new Universidade(input.qtdLinhas(Entrada.CAMINHO_PL_CURSOS));
			ufes.preencheDadosUniversidade(input);
			Saida output = new Saida(input.getCaminhoDosArquivos());
			ufes.gerarSaidas(output);
		} catch (IOException erroAbertura) {
			System.out.println("Erro de I/O");
		} catch (NumberFormatException | ParseException erroParsing) {
			System.out.print("Erro de formatação");
		}
	}

}
