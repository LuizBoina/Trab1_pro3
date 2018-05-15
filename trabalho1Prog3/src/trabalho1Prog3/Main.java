package trabalho1Prog3;

public class Main {

	public static void main(String[] args) {
		Entrada input = new Entrada(input.qtdLinhas(input.getIndexCaminhoArquivo(3)));
		input.lerLinhaComando(args);
		Universidade ufes = new Universidade();
		ufes.criaDepartamentos(input.qtdLinhas(input.getIndexCaminhoArquivo(0)), input.lePlanilha(0, 3));
		ufes.adicionaCursos(input.lePlanilha(3, 4));
		ufes.adicionaProdCientificaAosDocentes(input.qtdLinhas(input.getIndexCaminhoArquivo(2)), input.lePlanilha(2, 3));
		
	}

}
