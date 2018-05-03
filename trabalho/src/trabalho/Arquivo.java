package trabalho;

import java.io.*;

public class Arquivo {
	public String nome;
	public static Dado[] dados;
	
	public Arquivo(String nome) {
		this.nome = nome;
		this.abrir();
	}
	
	public void abrir() {
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(this.nome));
			this.coletarDados();
			buffRead.close();
		}catch(FileNotFoundException erro) {
			System.out.println(erro+" : arquivo "+this.nome+" nao foi encontrado!!!");
		}catch(IOException erro) {
			System.out.println(erro+" : arquivo "+this.nome+" nao foi fechado corretamente!!!");
		}
	}
	public void coletarDados() {
		
	}
}
