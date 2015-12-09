import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;


public class buscaPaciente extends JFrame {
	
	private Container cnt;
	private JLabel busca_Paciente;
	private JLabel lbNome;
	
	private JTextField tfNome;
	
	private JButton btBuscar;
	private JButton btEditar;
	private JButton btDelete;
	private JButton atualizar;

	private JPanel borda1;
	private JPanel borda2;
	
	private JTable tabela;
    private JScrollPane barraRolagem;
    private DefaultTableModel modelo = new DefaultTableModel();
    static SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
	
	
public void posiciona(){
	cnt.setBounds(200,200,1000,1000);
	
	
	borda1.setBounds(10, 10, 564, 80);
	borda2.setBounds(10, 440, 565, 10);
	lbNome.setBounds(15,100,50,20);
	tfNome.setBounds(60,100,400,25);
	btBuscar.setBounds(470,100,100,25);
	barraRolagem.setBounds(20,130,550,200);
	btEditar.setBounds(150,400,100,25);
	btDelete.setBounds(270,400,100,25);
	atualizar.setBounds(390,400,100,25);
		
	}
	
	public buscaPaciente(String titulo) {
		super(titulo);
		//janela
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/esfignamometro.jpg"));
		setSize(600, 500);
		setLocationRelativeTo(null);
		
		
		setVisible(true);
		
		//posicionamento
		 
		
		criaJTable();
		init();
		posiciona();
		init_event();
		
		
	}
	public void init(){
		
		
		cnt=this.getContentPane();
		
		cnt.setLayout(null);
		
		
		barraRolagem = new JScrollPane(tabela);
		cnt.add(barraRolagem);
		
		busca_Paciente = new JLabel ("Nome:");
		cnt.add(busca_Paciente);
		
		lbNome = new JLabel("Nome:");
		cnt.add(lbNome);
		
		tfNome = new JTextField();
		cnt.add(tfNome);
		
		btBuscar = new JButton("Buscar:");
		cnt.add(btBuscar);
		btEditar = new JButton("Editar:");
		cnt.add(btEditar);
		btDelete = new JButton("Deletar:");
		cnt.add(btDelete);
		atualizar = new JButton("Atualizar:");
		cnt.add(atualizar);
		
		
	
		

		borda1 = new JPanel();
		borda1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		borda1.setForeground(Color.WHITE);
		borda1.setBackground(Color.magenta);
		cnt.add(borda1);
		
		busca_Paciente = new JLabel("BUSCA DE PACIENTES");
		busca_Paciente.setFont(new Font("Source Code Pro", Font.PLAIN, 30));
		borda1.add(busca_Paciente);
		
		
		
		borda2 = new JPanel();
		borda2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		borda2.setBackground(Color.magenta);
		cnt.add(borda2);
		
		
	}
	
	private void criaJTable() {
		tabela = new JTable(modelo);
		tabela.setBackground(Color.WHITE);
		modelo.addColumn("Id");
		modelo.addColumn("Nome");
		modelo.addColumn("Idade");
		tabela.getColumnModel().getColumn(0).setPreferredWidth(30);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(350);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(350);
		
		pesquisar(modelo);
	}

	public void init_event(){
		
		btBuscar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event5) {
				Paciente paciente = new Paciente();
				paciente.setNome(tfNome.getText());
				
				
				buscar(modelo);
				
			}
		});
			btEditar.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent event5) {
					int x = 0;
					x = (int) tabela.getModel().getValueAt(  tabela.getSelectedRow() ,0);

					if(x==0){
						
						JOptionPane.showMessageDialog(null, "Selecione um Paciente!");
						
					}
					else{
					editaPaciente editar = new editaPaciente("Editar",x);
					editar.setDefaultCloseOperation(JFrame.ABORT);
					}
					
					
				}
			});
			btDelete.addActionListener(new ActionListener() {
		
				public void actionPerformed(ActionEvent event5) {
			
					int x = 0;
					x = (int) tabela.getModel().getValueAt(  tabela.getSelectedRow() ,0);

					if(x==0){
						
						JOptionPane.showMessageDialog(null, "Selecione um Paciente!");
						
					}
					else{
					
					 int dialogButton = JOptionPane.YES_NO_OPTION;
		                dialogButton=JOptionPane.showConfirmDialog (null, "Deseja realmente excluir?","EXCLUIR",dialogButton);

		                if(dialogButton == JOptionPane.YES_OPTION){ 
		                	
		                	ContatoDao contato = new ContatoDao();
		                	contato.remover(x);
		                	pesquisar(modelo);
		                	
		                	
		                }
		                if(dialogButton == JOptionPane.NO_OPTION){
		                	JOptionPane.showMessageDialog(null, "apagar cancelado");
		                }
					
					}
			
		}
});
			
			atualizar.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent event5) {
					pesquisar(modelo);
					
				}
			});
				
	}

	//busca todos
	public static void pesquisar(DefaultTableModel modelo) {
		modelo.setNumRows(0);
		ContatoDao dao = new ContatoDao();
		for (Paciente c : dao.getContatos()) {
			try {
				
				String data=c.getNascimento().substring(8,10)+"/"+c.getNascimento().substring(5,7)+"/"+c.getNascimento().substring(0,4);
				Date nasc = new java.sql.Date(format.parse(data).getTime());
				
				
			modelo.addRow(new Object[] { c.getId(), c.getNome(),calculaIdade(nasc)+" Anos" });
			
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null,
						"deu pau!!!" + e.getMessage());
		} 
		}
	}
	
	//busca por nome
	public void buscar(DefaultTableModel modelo) {

		modelo.setNumRows(0);
		ContatoDao dao = new ContatoDao();
		for (Paciente c : dao.getContatoByName(tfNome.getText())) {
			 
			try {
				String data=c.getNascimento().substring(8,10)+"/"+c.getNascimento().substring(5,7)+"/"+c.getNascimento().substring(0,4);
				Date nasc = new java.sql.Date(format.parse(data).getTime());
								
				modelo.addRow(new Object[] { c.getId(), c.getNome(),calculaIdade(nasc)+" Anos" });
			
			} catch (ParseException e) {
				JOptionPane.showMessageDialog(null,
						"deu pau!!!" + e.getMessage());
		} 
		
		}

	}
	


	public static int calculaIdade(Date dataNasc){

	Calendar aniversario = new GregorianCalendar();

	aniversario.setTime(dataNasc);

	Calendar hoje = Calendar.getInstance();

	 

	// Obtém a idade baseado no ano

	int idade = hoje.get(Calendar.YEAR) - aniversario.get(Calendar.YEAR);

	 

	aniversario.add(Calendar.YEAR, idade);

	 

	//se a data de hoje é antes da data de Nascimento, então diminui 1(um)

	if (hoje.before(aniversario)) {

	idade--;

	}

	return idade;

	}



}	

	

