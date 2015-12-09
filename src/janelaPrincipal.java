

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import javax.swing.SwingConstants;

import java.awt.Toolkit;



public class janelaPrincipal extends JFrame {
	
	private JLabel displayLabel;
	private JLabel displayLabel2;
		
	private JMenu programa;
		private JMenuItem sair;
		
	private JMenu ajuda;
		private JMenuItem creditos;
			
	private JMenu paciente;
		private JMenuItem CadastraPaciente;
		private JMenuItem BuscaPaciente;
		
	private JMenu acompanhar;
		private JMenuItem acompPaciente;
			
	private JMenuBar bar;
	

	public janelaPrincipal(String titulo) {
		
		super(titulo);
		//janela
		setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/esfignamometro.jpg"));
		setSize(1000, 700);
		setLocationRelativeTo(null);
		
		setVisible(true);
		
		//posicionamento
		 
		
		
		init();
		init_event();
	}
	
	public void init(){
		
		
		
		
		programa = new JMenu("Programa");
		programa.setMnemonic('P');
		programa.setBackground(Color.WHITE);
		
			sair = new JMenuItem("Sair");
			sair.setBackground(Color.WHITE);
			sair.setIcon(new ImageIcon("./src/exit.png"));
			programa.add(sair);
			sair.setMnemonic('S');
		
		ajuda = new JMenu("Ajuda");
		ajuda.setMnemonic('A');
		
			creditos = new JMenuItem("Creditos");
			creditos.setMnemonic('C');
			creditos.setBackground(Color.white);
			ajuda.add(creditos);
		
		paciente = new JMenu("Paciente");
		paciente.setMnemonic('P');
			CadastraPaciente = new JMenuItem("Cadastrar");
			CadastraPaciente.setBackground(Color.WHITE);
			CadastraPaciente.setMnemonic('C');
			CadastraPaciente.setIcon(new ImageIcon("./src/add.png"));
			paciente.add(CadastraPaciente);
			
			BuscaPaciente = new JMenuItem("Buscar");
			BuscaPaciente.setBackground(Color.WHITE);
			BuscaPaciente.setMnemonic('B');
			BuscaPaciente.setIcon(new ImageIcon("./src/lupa.png"));
			paciente.add(BuscaPaciente);
			
		acompanhar = new JMenu("Acompanhamento");
		acompanhar.setMnemonic('M');
			acompPaciente = new JMenuItem("Acompanhamento de Paciente");
			acompPaciente.setBackground(Color.white);
			acompPaciente.setIcon(new ImageIcon("./src/coracao.gif"));
			acompanhar.add(acompPaciente);
		
		bar = new JMenuBar();
		setJMenuBar(bar);
			bar.add(programa);
			bar.add(paciente);
			bar.add(acompanhar);
			bar.add(ajuda);
		
		
		displayLabel2 = new JLabel("", SwingConstants.CENTER);
		displayLabel2.setIcon(new ImageIcon("./src/hipertensao.jpg"));
		displayLabel = new JLabel("Denise", SwingConstants.CENTER);
		displayLabel2.setLabelFor(displayLabel);

		displayLabel2.setForeground(Color.white);
		
		displayLabel.setForeground(Color.white);
		displayLabel.setFont(new Font("Courier New", Font.BOLD, 18));

		// adiciona os textos na tela atual
		getContentPane().add(displayLabel2, BorderLayout.CENTER);
		getContentPane().add(displayLabel, BorderLayout.SOUTH);
		getContentPane().setBackground(Color.white);
		getContentPane().add(displayLabel2, BorderLayout.CENTER);
		getContentPane().setBackground(Color.white);
		displayLabel2.setFont(new Font("Arial New", Font.BOLD, 16));
		
		
	}
	
	public void init_event(){
		
		sair.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event5) {
					System.exit(0);
					
			}
		});
		
		
		creditos.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent event5) {
						creditos credito = new creditos("Creditos!");
						credito.setDefaultCloseOperation(JFrame.ABORT);
					      					  							
					}
				});
		
			CadastraPaciente.addActionListener(new ActionListener() {
			
					public void actionPerformed(ActionEvent event5) {
						 cadastraPaciente c = new cadastraPaciente("Cadastrar");
						c.setDefaultCloseOperation(JFrame.ABORT);
				
			}
		});
			
			
			BuscaPaciente.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent event5) {
					 buscaPaciente b = new buscaPaciente("Buscar");
					b.setDefaultCloseOperation(JFrame.ABORT);
				
		}
	});
			acompPaciente.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent event5) {
					janelaAcompanhamento ac = new janelaAcompanhamento("Acompanhamento");
					ac.setDefaultCloseOperation(JFrame.ABORT);
				
		}
	});
	}
	

}
