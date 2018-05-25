package trabalho1Prog3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//falta tratar erros em geral e a questao do read e write only
public class Main {

	public static void main(String[] args) {
		Entrada input = new Entrada();
		input.lerLinhaComando(args);
		Universidade ufes;
		try {
			if (input.getSoEscrita()) {
				FileInputStream objects = new FileInputStream("dados.dat");
				ObjectInputStream entrada = new ObjectInputStream(objects);
			
				//Ler os objetos no dados.dat
				
				ufes = (Universidade) entrada.readObject();
			
				entrada.close();
				objects.close();
			} else {
				ufes = new Universidade(input.qtdLinhas(input.getIndexCaminhoArquivo(3)));;
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
				FileOutputStream objects = new FileOutputStream("dados.dat");
				ObjectOutputStream saida = new ObjectOutputStream(objects);
			
				//Escrever os objetos no dados.dat
				
				saida.writeObject(ufes);
			
				saida.close();
				objects.close();
			} else {
				Saida output = new Saida(input.getCaminhoDosArquivos());
				ufes.gerarPad(output);
				ufes.gerarRha(output);
				ufes.gerarAlocacao(output);
				ufes.gerarPpg(output);
			}
		}catch(IOException e) {
			System.out.println("Erro de I/O");
		}catch(NumberFormatException n) {
			System.out.println("Erro de formatação");
		}catch(ClassNotFoundException c) {
			c.printStackTrace();
		}catch(Exception ex) {
			System.out.println(ex);
		}
	}

}
