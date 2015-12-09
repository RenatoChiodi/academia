
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.text.MaskFormatter;


public class cadastraPaciente extends JFrame {
	
	private Container cnt;
	private JLabel label_nmPaciente;
	private JLabel label_dtNascimento;
	private JLabel label_sexo;
	private JLabel cadastro_Paciente;
	private JLabel label_CPF;
	private JLabel label_endereco;
	private JLabel label_cidade;
	private JLabel label_estado;
	
	private JLabel label_estCivil;
	private JLabel label_profissao;
	private JLabel label_email;
	private JLabel label_celular;
	private JLabel label_telefone;
	private JLabel label_bairro;
	
	private JButton bt_Cancelar;
	private JButton bt_Salvar;
	private JButton bt_Limpar;
	
	private JTextField txb_nmPaciente;
	private JTextField txb_endereco;
	private JTextField txb_cidade;
	private JTextField txb_estado;
	private JTextField txb_prifissao;
	private JTextField txb_email;
	private JTextField txb_bairro;
	
	private JComboBox<?> cb_sexo ;
	private JComboBox<?> cb_estCivil;

	private JFormattedTextField campoData;
	private JFormattedTextField campoCPF;
	private JFormattedTextField campoTelefone;
	private JFormattedTextField campoCelular;
	

	private JPanel borda1;
	private JPanel borda2;
	
	
public void posiciona(){
	cnt.setBounds(200,200,1000,1000);
	borda1.setBounds(10, 10, 564, 80);
	borda2.setBounds(10, 400, 564, 10);
	
	label_nmPaciente.setBounds(20,100,50,20);
	txb_nmPaciente.setBounds(100,100,400,20);
	
	
	label_endereco.setBounds(20,130,80,20);
	txb_endereco.setBounds(100,130,180,20);
	label_bairro.setBounds(290,130,60,20);
	txb_bairro.setBounds(350,130,150,20);
	
	label_cidade.setBounds(20,160,60,20);
	txb_cidade.setBounds(100,160,180,20);
	label_estado.setBounds(290,160,60,20);
	txb_estado.setBounds(350,160,150,20);
	
	label_dtNascimento.setBounds(20,190,90,20);
	campoData.setBounds(100,190,80,20);
	label_sexo.setBounds(290,190,40,20);
	cb_sexo.setBounds(350,190,50,20);
	
	
	
	
	label_estCivil.setBounds(20,220,70,20);
	cb_estCivil.setBounds(100,220,100,20);
	label_CPF.setBounds(290,220,50,20);
	campoCPF.setBounds(350,220,110,20);
	
	label_profissao.setBounds(20,250,80,20);
	txb_prifissao.setBounds(100,250,180,20);	
	label_email.setBounds(290,250,60,20);
	txb_email.setBounds(350,250,150,20);
	
	label_telefone.setBounds(20,280,80,20);
	campoTelefone.setBounds(100,280,100,20);
	label_celular.setBounds(290,280,80,20);
	campoCelular.setBounds(350,280,100,20);
	
	bt_Salvar.setBounds(100,360,100,30);
	bt_Limpar.setBounds(220,360,100,30);
	bt_Cancelar.setBounds(340,360,100,30);
	
		
	}
	
	public cadastraPaciente(String titulo)  {
		super(titulo);
		//janela
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/esfignamometro.jpg"));
		setSize(600, 450);
		setLocationRelativeTo(null);
		
		
		setVisible(true);
		
		//posicionamento
		 
		
		
		init();
		posiciona();
		init_event();
		
	}
	public void init() {
		try{
		
		
		cnt=this.getContentPane();		
		cnt.setLayout(null);
		
		label_nmPaciente = new JLabel ("Nome:");
		cnt.add(label_nmPaciente);
		
		txb_nmPaciente = new JTextField("");
		cnt.add(txb_nmPaciente);
		
		label_sexo = new JLabel("Sexo:");
		cnt.add(label_sexo);
		
		String[] sexo = {"F","M"};
		cb_sexo = new JComboBox<Object>(sexo);
		cnt.add(cb_sexo);
		
		
	//====================================================================
		//dia de nascimento=======================
		label_dtNascimento  = new JLabel("Nascimento:");
		cnt.add(label_dtNascimento);
		
		MaskFormatter mascara = new MaskFormatter("##/##/####");
	    mascara.setPlaceholderCharacter('_');
	    campoData = new JFormattedTextField(mascara);
	    cnt.add(campoData);
		 
		
		//campo CPF============================
		
		label_CPF  = new JLabel("CPF:");
		cnt.add(label_CPF);
	
		MaskFormatter mascaraCPF = new MaskFormatter("###.###.###-##");
	    mascaraCPF.setPlaceholderCharacter('_');
	    campoCPF = new JFormattedTextField(mascaraCPF);
	    cnt.add(campoCPF);
		
		
		//endereco==================================
		label_endereco = new JLabel("Endereço:");
		cnt.add(label_endereco);
		label_cidade = new JLabel("Cidade:");
		cnt.add(label_cidade);
		label_estado = new JLabel("Estado:");
		cnt.add(label_estado);
		label_bairro = new JLabel("Bairro:");
		cnt.add(label_bairro);
		 
		txb_endereco = new JTextField();
		cnt.add(txb_endereco);
		txb_cidade = new JTextField();
		cnt.add(txb_cidade);
		txb_estado = new JTextField();
		cnt.add(txb_estado);
		txb_bairro = new JTextField();
		cnt.add(txb_bairro);
		
		
		label_estCivil =new JLabel ("Est. Civil:");
		cnt.add(label_estCivil);
		
		String[] civil ={"Solteiro","Casado","Viuvo","Divorciado","Amasiado"};
		cb_estCivil = new JComboBox<Object>(civil);
		cnt.add(cb_estCivil);
		
		label_profissao = new JLabel ("Profissão:");
		cnt.add(label_profissao);
		
		txb_prifissao= new JTextField();
		cnt.add(txb_prifissao);
		
		label_email = new JLabel("E-mail:");
		label_celular= new JLabel("Celular:");
		label_telefone= new JLabel("Telefone:");
		cnt.add(label_email);
		cnt.add(label_telefone);
		cnt.add(label_celular);
		
		txb_email = new JTextField();
		cnt.add(txb_email);
		
		MaskFormatter mascaraTelefone = new MaskFormatter("(##)####-####");
	    mascaraTelefone.setPlaceholderCharacter('_');
	    campoTelefone = new JFormattedTextField(mascaraTelefone);
	    cnt.add(campoTelefone);
	    
	    MaskFormatter mascaraCelular = new MaskFormatter("(##)####-####");
	    mascaraCelular.setPlaceholderCharacter('_');
	    campoCelular = new JFormattedTextField(mascaraCelular);
	    cnt.add(campoCelular);
	    
	// BUTTON =============================================
		bt_Salvar = new JButton("Salvar");
		cnt.add(bt_Salvar);
		
		bt_Limpar = new JButton("Limpar");
		cnt.add(bt_Limpar);
		
		bt_Cancelar = new JButton("Cancelar");
		cnt.add(bt_Cancelar);
		
	// bordas =================================================
		borda1 = new JPanel();
		borda1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		borda1.setForeground(Color.WHITE);
		borda1.setBackground(Color.RED);
		cnt.add(borda1);
		
		cadastro_Paciente = new JLabel("CADASTRO DE PACIENTES");
		cadastro_Paciente.setFont(new Font("Source Code Pro", Font.PLAIN, 30));
		borda1.add(cadastro_Paciente);
		
		
		
		borda2 = new JPanel();
		borda2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		borda2.setBackground(Color.RED);
		cnt.add(borda2);
		
		} catch (ParseException e) {
		    e.printStackTrace();
		  }
	}
	public void limpar(){
		txb_nmPaciente.setText("");
		txb_cidade.setText("");
		txb_endereco.setText("");
		txb_estado.setText("");
		campoCPF.setText("");
		campoData.setText("");
		campoCelular.setText("");
		campoTelefone.setText("");
		txb_bairro.setText("");
		txb_prifissao.setText("");
		txb_email.setText("");
	}
	public void init_event(){
		bt_Limpar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event5) {
					
					limpar();
					
			}
		});
		
		
		bt_Cancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event5) {
				cadastraPaciente.this.setVisible(false);
					
			}
		});
		
bt_Salvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event5) {
				String sexo=(String) cb_sexo.getSelectedItem();
				String stCivil= (String) cb_estCivil.getSelectedItem();
				String data= campoData.getText();
				String nasc;
			
				nasc=(data.substring(6,10)+data.substring(3,5)+data.substring(0,2));
				Paciente paciente = new Paciente();
				
				paciente.setNome(txb_nmPaciente.getText()); 
				paciente.setSexo(sexo) ;
				paciente.setNascimento(nasc);
				paciente.setEndereco(txb_endereco.getText());
				paciente.setBairro( txb_bairro.getText());
				paciente.setCidade( txb_cidade.getText());
				paciente.setEstado(txb_estado.getText());
				paciente.setEstCivil( stCivil);
				paciente.setCpf( campoCPF.getText());
				paciente.setProfissao( txb_prifissao.getText());
				paciente.setEmail( txb_email.getText());				
				paciente.setTelefone(campoTelefone.getText());
				paciente.setCelular(campoCelular.getText());
				ContatoDao dao = new ContatoDao();
				dao.inserir(paciente);
				
				
				limpar();				
				}
				
				
			
		});
		
		
	}


}
