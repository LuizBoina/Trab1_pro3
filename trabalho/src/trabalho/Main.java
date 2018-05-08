package trabalho;

public class Main {

	public static void main(String[] args) {
		Entrada input = new Entrada();
		input.lerLinhaComando(args);
		int numDoscentes = input.qtdLinhas(input.getIndexCaminhoArquivo(0));
		Pessoa refPessoas = new Pessoa();
		Docente[] Docentes = new Docente[numDoscentes];
		String[][] planilhaDocente = input.lePlanilha(0, 3);
		for (int i = 0; i < numDoscentes; i++)
			Docentes[i].preencheDocente(planilhaDocente[i]);
		input.limparPlanilha(planilhaDocente);
		int numDiscentes = input.qtdLinhas(input.getIndexCaminhoArquivo(1));
		Discente[] Discentes = new Discente[numDiscentes];
		String[][] planilhaDiscente = input.lePlanilha(1, 3);
		for (int i = 0; i < numDiscentes; i++)
			Discentes[i].preencheDiscente(planilhaDiscente[i]);
		input.limparPlanilha(planilhaDiscente);
		Docentes[0].qSort(Docentes, numDoscentes);
		
	}
}
