package trabalho1Prog3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) {
		Entrada input = new Entrada(args);
		Universidade ufes;
		try {
			if (input.getSoEscrita()) {
				FileInputStream objects = new FileInputStream("dados.dat");
				ObjectInputStream entrada = new ObjectInputStream(objects);

				// Ler os objetos no dados.dat

				ufes = (Universidade) entrada.readObject();

				entrada.close();
				objects.close();
			} else {
				ufes = new Universidade(input.qtdLinhas(Entrada.CAMINHO_PL_CURSOS));
				ufes.preencheDadosUniversidade(input);
			}
			if (input.getSoLeitura()) {
				FileOutputStream objects = new FileOutputStream("dados.dat");
				ObjectOutputStream saida = new ObjectOutputStream(objects);

				// Escrever os objetos no dados.dat

				saida.writeObject(ufes);

				saida.close();
				objects.close();
			} else {
				Saida output = new Saida(input.getCaminhoDosArquivos());
				ufes.gerarSaidas(output);
			}
		} catch (IOException e) {
			System.out.println("Erro de I/O");
		} catch (NumberFormatException n) {
			System.out.println("Erro de formatação");
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
