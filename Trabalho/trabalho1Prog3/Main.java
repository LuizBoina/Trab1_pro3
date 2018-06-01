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
		Saida output = new Saida();
		try {
			if (input.getSoEscrita()) {
				ufes = (Universidade) input.deserializandoDados();
			} else {
				ufes = new Universidade(input.qtdLinhas(Entrada.CAMINHO_PL_CURSOS));
				ufes.preencheDadosUniversidade(input);
			}
			if (input.getSoLeitura()) {
				ufes.serializarDados();
			} else
				ufes.gerarSaidas(output);
		} catch (IOException | NullPointerException erroAbertura) {
			System.out.println("Erro de I/O");
		} catch (NumberFormatException | ParseException erroParsing) {
			System.out.println("Erro de formatação");
		} catch (ClassNotFoundException erroDeserializacao) {
			System.out.println("Erro em deserializar dados");
		} catch (ErroMesmoCodigo msmCod) {
			System.out.println(msmCod.getMessage());
		} catch (ErroCodInvalDocenEmDisci CodDocenInvalidoDisciDocen) {
			System.out.println(CodDocenInvalidoDisciDocen.getMessage());
		} catch (ErroCodInvalCurEmDisci codCursoInvalidoDisciCurso) {
			System.out.println(codCursoInvalidoDisciCurso.getMessage());
		} catch (ErroCodInvalDocenEmOri codInvalidoOriDocen) {
			System.out.println(codInvalidoOriDocen.getMessage());
		} catch (ErroCodInvalCursoEmOri codInvalidoOriCurso) {
			System.out.println(codInvalidoOriCurso.getMessage());
		} catch (ErroInconsisNivelCurso niveisIguais) {
			System.out.println(niveisIguais.getMessage());
		} catch (ErroDataNoFuturo dataInvalida) {
			System.out.println(dataInvalida.getMessage());
		}
	}

}
