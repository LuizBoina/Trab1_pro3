package trabalho;

public class Main {

	public static void main(String[] args) {
		Entrada input = new Entrada();
		input.lerLinhaComando(args);
		Faculdade faculdade = new Faculdade();
		faculdade.criaDepartamentos(input.qtdLinhas(input.getIndexCaminhoArquivo(0)), input.lePlanilha(0, 3));
		int numDiscentes = input.qtdLinhas(input.getIndexCaminhoArquivo(1));
		Discente[] Discentes = new Discente[numDiscentes];
		planilhaLida = input.lePlanilha(1, 3);
		for (int i = 0; i < numDiscentes; i++) {
			Discentes[i] = new Discente();
			Discentes[i].preencheDiscente(planilhaLida[i]);
		}
		int qtdProdCientifica = input.qtdLinhas(input.getIndexCaminhoArquivo(2));
		ProdCientifica[] ProdCientificas = new ProdCientifica[qtdProdCientifica];
		planilhaLida = input.lePlanilha(2, 3);
		for (int i = 0; i < numDiscentes; i++) {
			ProdCientificas[i] = new ProdCientifica();
			ProdCientificas[i].preencheProdCientifica(planilhaLida[i]);
		}

	}
}
