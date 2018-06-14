package interfaceGrafica;

import java.awt.event.*;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import exceptions.ErroCodInvalCurEmDisci;
import exceptions.ErroCodInvalCursoEmOri;
import exceptions.ErroCodInvalDocenEmDisci;
import exceptions.ErroCodInvalDocenEmOri;
import exceptions.ErroDataNoFuturo;
import exceptions.ErroInconsisNivelCurso;
import exceptions.ErroMesmoCodigo;
import trabalho1Prog3.Entrada;
import trabalho1Prog3.Saida;

public class GUI {
	JButton btnDocen;
	JButton btnDiscen;
	JButton btnCur;
	JButton btnAulas;
	JButton btnOriGrad;
	JButton btnOriPos;
	JButton btnProd;
	// JButton btnData;
	JButton btnSair;
	JButton btnDirSaida;
	// JCheckBox writeOnly;
	// JCheckBox readOnly;

	public GUI(Entrada input, Saida output) throws IOException, NumberFormatException, ParseException,
	ErroMesmoCodigo, ErroCodInvalDocenEmDisci, ErroCodInvalCurEmDisci, ErroCodInvalDocenEmOri,
	ErroCodInvalCursoEmOri, ErroInconsisNivelCurso, ErroDataNoFuturo {
		/*
		 * btnData = new JButton("data.dat"); btnData.addActionListener(new
		 * ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { JFileChooser chooser =
		 * new JFileChooser(); chooser.setFileFilter(new
		 * FileNameExtensionFilter("apenas dat", "dat")); int retorno =
		 * chooser.showOpenDialog(null); if (retorno == JFileChooser.APPROVE_OPTION)
		 * input.setIndexData(chooser.getSelectedFile().getPath()); } });
		 */
		btnDocen = new JButton("docentes.csv");
		btnDocen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("apenas csv", "csv"));
				chooser.setAcceptAllFileFilterUsed(false);
				int retorno = chooser.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION)
					input.setIndexDocen(chooser.getSelectedFile().getPath());
			}
		});
		btnDiscen = new JButton("discentes.csv");
		btnDiscen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("apenas csv", "csv"));
				chooser.setAcceptAllFileFilterUsed(false);
				int retorno = chooser.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION)
					input.setIndexDiscen(chooser.getSelectedFile().getPath());
			}
		});
		btnCur = new JButton("cursos.csv");
		btnCur.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("apenas csv", "csv"));
				chooser.setAcceptAllFileFilterUsed(false);
				int retorno = chooser.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION)
					input.setIndexCur(chooser.getSelectedFile().getPath());
			}
		});
		btnAulas = new JButton("aulas.csv");
		btnAulas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("apenas csv", "csv"));
				chooser.setAcceptAllFileFilterUsed(false);
				int retorno = chooser.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION)
					input.setIndexAulas(chooser.getSelectedFile().getPath());
			}
		});
		btnOriGrad = new JButton("orientagrad.csv");
		btnOriGrad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("apenas csv", "csv"));
				chooser.setAcceptAllFileFilterUsed(false);
				int retorno = chooser.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION)
					input.setIndexOriGrad(chooser.getSelectedFile().getPath());
			}
		});
		btnOriPos = new JButton("orientapos.csv");
		btnOriPos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("apenas csv", "csv"));
				chooser.setAcceptAllFileFilterUsed(false);
				int retorno = chooser.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION)
					input.setIndexOriPos(chooser.getSelectedFile().getPath());
			}
		});
		btnProd = new JButton("producoes.csv");
		btnProd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("apenas csv", "csv"));
				chooser.setAcceptAllFileFilterUsed(false);
				int retorno = chooser.showOpenDialog(null);
				if (retorno == JFileChooser.APPROVE_OPTION)
					input.setIndexProd(chooser.getSelectedFile().getPath());
			}
		});
		btnSair = new JButton("sair");
		btnSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnDirSaida = new JButton("Diretório de saida");
		btnDirSaida.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int res = fc.showOpenDialog(null);
				if (res == JFileChooser.APPROVE_OPTION)
					output.setCaminhoSaida(fc.getSelectedFile().getPath());
			}
		});
		/*
		 * writeOnly = new JCheckBox("Write only"); readOnly = new
		 * JCheckBox("Read only");
		 */	
	}

	public void mostraJanela() {
		JPanel painel = new JPanel();
		painel.add(this.btnDocen);
		painel.add(this.btnDiscen);
		painel.add(this.btnCur);
		painel.add(this.btnAulas);
		painel.add(this.btnOriGrad);
		painel.add(this.btnOriPos);
		painel.add(this.btnProd);
		painel.add(this.btnDirSaida);
		JFrame janela = new JFrame("Trab1_Prog3");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton btnGerarRela = new JButton("Gerar Relatórios");
		btnGerarRela.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				janela.dispose();
			}
		});
		painel.add(btnGerarRela);
		painel.add(this.btnSair);
		janela.add(painel);
		janela.setSize(800,400);
		//janela.pack();
		janela.setVisible(true);
	}
}
