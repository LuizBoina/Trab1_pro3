package trabalho1Prog3;

public class Disciplina {
	private int codigo;
	private String nome;
	private int codigoDocente;
	private int cHSemanal;
	private int cHSemestral;
	private int codigoCurso;

	public Disciplina(String[] celulas) {
		try {
			this.codigo = Integer.parseInt(celulas[0])
		}
	}catch(NumberFormatException e)
	{
		System.out.println("Numero com formato errado!");
	}
}}
