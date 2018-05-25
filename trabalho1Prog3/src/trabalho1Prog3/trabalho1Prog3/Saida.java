package trabalho1Prog3;

import java.io.FileWriter;
import java.io.IOException;

public class Saida {
	private final String caminhoPastaSaida;
	private final String NOVA_LINHA;
	private final String DIV_CELULAS;
	private final String HEAD_PAD = "Docente;Departamento;Horas Semanais Aula;Horas Semestrais Aula;Horas Semanais Orientação;Horas Semestrais Orientação;Produções Qualificadas;Produções Não Qualificadas";
	private final String HEAD_RHA = "Departamento;Docente;Cód. Curso;Curso;Horas Semestrais Aula";
	private final String HEAD_ALOCACAO = "Docente;Código;Nome;Carga Horara Semestral";
	private final String HEAD_PPG = "Nome do Programa;Data de Ingresso;Matricula;Nome";
	FileWriter fw;

	public Saida(String caminhoPastaSaida) {
		this.caminhoPastaSaida = caminhoPastaSaida;
		this.NOVA_LINHA = "\n";
		this.DIV_CELULAS = ";";
		fw = null;
	}

	public void escreverCabecalho(String cabecalho) {
		try {
			switch (cabecalho) {
			case "HEAD_PAD":
				this.fw.append(HEAD_PAD.toString());
				this.fw.append(NOVA_LINHA);
				break;
			case "HEAD_RHA":
				this.fw.append(HEAD_RHA.toString());
				this.fw.append(NOVA_LINHA);
				break;
			case "HEAD_ALOCACAO":
				this.fw.append(HEAD_ALOCACAO.toString());
				this.fw.append(NOVA_LINHA);
				break;
			case "HEAD_PPG":
				this.fw.append(HEAD_PPG.toString());
				this.fw.append(NOVA_LINHA);
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void escreveString(String str) {
		try {
			this.fw.append(str);
			this.fw.append(NOVA_LINHA);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean abrirArquivoParaEscrita(String nomeArqSaida) {
		try {
			this.fw = new FileWriter(this.caminhoPastaSaida + nomeArqSaida);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void fecharFw() {
		try {
			fw.flush();
			fw.close();
		} catch (IOException er) {
			er.printStackTrace();
		}
	}
}
