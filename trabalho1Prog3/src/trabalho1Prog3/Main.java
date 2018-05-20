package trabalho1Prog3;

//falta tratar erros em geral e a questao do read e write only
public class Main {

	public static void main(String[] args) {
		Entrada input = new Entrada();
		input.lerLinhaComando(args);
		if (input.getSoEscrita()) {

		} else {
			Universidade ufes = new Universidade(input.qtdLinhas(input.getIndexCaminhoArquivo(3)));
			ufes.criaDepartamentos(input.qtdLinhas(input.getIndexCaminhoArquivo(0)), input.lePlanilha(0, 3));
			ufes.adicionaCursos(input.lePlanilha(3, 4));
			ufes.adicionaProdCientificaAosDocentes(input.qtdLinhas(input.getIndexCaminhoArquivo(2)),
					input.lePlanilha(2, 3));
			ufes.adicionaDisciplinas(input.qtdLinhas(input.getIndexCaminhoArquivo(4)), input.lePlanilha(4, 6));
			ufes.adicionaDiscentesAosCursos(input.qtdLinhas(input.getIndexCaminhoArquivo(1)), input.lePlanilha(1, 3));
			ufes.adicionaOrientacaoGradAosDocentes(input.qtdLinhas(input.getIndexCaminhoArquivo(5)),
					input.lePlanilha(5, 4));
			ufes.adicionaOrientacaoPosGradAosDocentes(input.qtdLinhas(input.getIndexCaminhoArquivo(6)),
					input.lePlanilha(6, 5));
			ufes.adicionaCursosADocentes();
		}
		if (input.getSoLeitura()) {

		} else {
			Saida output = new Saida(input.getCaminhoDosArquivos());
			ufes.gerarPad(output);
			ufes.gerarRha(output);
			ufes.gerarAlocacao(output);
			ufes.gerarPpg(output);
		}
	}

}
