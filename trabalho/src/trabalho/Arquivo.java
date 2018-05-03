package trabalho;

import java.io.*;

public class Arquivo {
	public String nome;
	
	public Arquivo(String nome) {
		this.nome = nome;
	}
	
	public void abrir() {
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader(this.nome));
			this.ler();
			buffRead.close();
		}catch(FileNotFoundException erro) {
			System.out.println(erro+" : arquivo "+this.nome+" nao foi encontrado!!!");
		}catch(IOException erro) {
			System.out.println(erro+" : arquivo "+this.nome+" nao foi fechado corretamente!!!");
		}
	}
	public void ler() {
		
	}
}

public class Docente extends Arquivo{
	public int codigo;
	public String departamento;
	
	
	public Docente(String nome){
		this.nome = nome;
	}
}
