package trabalho1Prog3;

import java.io.IOException;
import java.text.ParseException;

import exceptions.ErroCodInvalCurEmDisci;
import exceptions.ErroCodInvalCursoEmOri;
import exceptions.ErroCodInvalDocenEmDisci;
import exceptions.ErroCodInvalDocenEmOri;
import exceptions.ErroDataNoFuturo;
import exceptions.ErroInconsisNivelCurso;
import exceptions.ErroMesmoCodigo;
import ufes.Universidade;

public class Main {

	public static void main(String[] args) {
		Entrada input = new Entrada(args);
		Universidade ufes;
		try {
			Saida output = new Saida(input.getCaminhoDosArquivos());
			if (input.getSoEscrita()) {
				ufes = (Universidade) input.deserializandoDados();
			} else {
				ufes = new Universidade(input.qtdLinhas(Entrada.CAMINHO_PL_CURSOS));
				ufes.preencheDadosUniversidade(input);
			}
			if (input.getSoLeitura()) {
				ufes.serializarDados(Saida.CAMINHO_PASTA);
			} else
				ufes.gerarSaidas(output);
		} catch (IOException | NullPointerException erroAbertura) {
			System.out.println("Erro de I/O");
		} catch (NumberFormatException | ParseException erroParsing) {
			System.out.print("Erro de formatação");
		} catch (ClassNotFoundException erroDeserializacao) {
			System.out.println("Erro em deserializar dados");
		} catch (ErroMesmoCodigo msmCod) {
			msmCod.getMessage();
		} catch (ErroCodInvalDocenEmDisci CodDocenInvalidoDisciDocen) {
			CodDocenInvalidoDisciDocen.getMessage();
		} catch (ErroCodInvalCurEmDisci codCursoInvalidoDisciCurso) {
			codCursoInvalidoDisciCurso.getMessage();
		} catch (ErroCodInvalDocenEmOri codInvalidoOriDocen) {
			codInvalidoOriDocen.getMessage();
		} catch (ErroCodInvalCursoEmOri codInvalidoOriCurso) {
			codInvalidoOriCurso.getMessage();
		} catch (ErroInconsisNivelCurso niveisIguais) {
			niveisIguais.getMessage();
		} catch (ErroDataNoFuturo dataInvalida) {
			dataInvalida.getMessage();
		}
	}

}
