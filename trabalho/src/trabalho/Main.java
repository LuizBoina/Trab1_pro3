package trabalho;

public class Main {

	public static void main(String[] args) {
		Entrada input = new Entrada();
		input.lerLinhaComando(args);
		int numDoscentes = input.qtdLinhas(input.getIndexCaminhoArquivo(1));
		Docente[] Docentes = new Docente[numDoscentes];
		Docentes.preencheDocentes(input.getIndexCaminhoArquivo(1)); // fazer um for talvez...

	}
}
