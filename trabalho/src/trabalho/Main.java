package trabalho;

public class Main {

	public static void main(String[] args) {
		Entrada input = new Entrada();
		input.lerLinhaComando(args);
		int numDoscentes = input.qtdLinhas(input.getIndexCaminhoArquivo(0));
		Docente[] Docentes = new Docente[numDoscentes];	//talvez abrir o arq aq e fechar dps do for melhore o desempenho
		for(int i =0; i< numDoscentes;i++) {
			Docentes[i] = new Docente();
			String[] celulasLidas = input.retornaLinhaLida(0, 3, i);
			Docentes[i].preencheDocente(celulasLidas);
		}
		int numDiscentes = input.qtdLinhas(input.getIndexCaminhoArquivo(1));	//talvez de para botar esse metodo em uma so funcao
		Discente[] Discentes = new Discente[numDiscentes];
		for(int i = 0; i< numDiscentes; i++) {
			Discentes[i] = new Discente();
			String[] celulasLidas = input.retornaLinhaLida(1, 3, i);
			Discentes[i].preencheDiscente(celulasLidas);
		}
	}
}
