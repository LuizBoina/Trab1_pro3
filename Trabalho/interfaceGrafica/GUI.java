package interfaceGrafica;

import java.awt.event.*;
import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import trabalho1Prog3.Entrada;

public class GUI {
	JButton btnDocen;
	JButton btnDiscen;
	JButton btnCur;
	JButton btnAulas;
	JButton btnOriGrad;
	JButton btnOriPos;
	JButton btnProd;
	JButton btnSair;
	JButton btnDirSaida;
	JButton btnGerarRela;
	JCheckBox writeOnly;
	JCheckBox readOnly;
	
	public GUI(Entrada input) throws FileNotFoundException{
		btnDocen = new JButton("docentes.csv");
		btnDocen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileFilter(new FileNameExtensionFilter("apenas csv", "csv"));
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
		btnDirSaida = new JButton("");
	}

	public void mostraJanela() {

	}
}
