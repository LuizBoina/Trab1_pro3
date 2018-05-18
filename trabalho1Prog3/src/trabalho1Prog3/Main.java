package trabalho1Prog3;
//falta tratar erros em geral e a questao do read e write only
public class Main {

	public static void main(String[] args) {
		Entrada input = new Entrada();
		input.lerLinhaComando(args);
		Universidade ufes = new Universidade(input.qtdLinhas(input.getIndexCaminhoArquivo(3)));
		ufes.criaDepartamentos(input.qtdLinhas(input.getIndexCaminhoArquivo(0)), input.lePlanilha(0, 3));
		ufes.adicionaCursos(input.lePlanilha(3, 4));
		ufes.adicionaProdCientificaAosDocentes(input.qtdLinhas(input.getIndexCaminhoArquivo(2)), input.lePlanilha(2, 3));
		ufes.adicionaDisciplinas(input.qtdLinhas(input.getIndexCaminhoArquivo(4)), input.lePlanilha(4, 6));
		System.out.println("vc e foda");
	}

}
