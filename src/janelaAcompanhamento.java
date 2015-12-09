import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;


public class janelaAcompanhamento extends JFrame {
	private Container cnt;
	private JPanel borda1;
	private JPanel borda2;
	
	private JLabel lbPaciente;
	private JLabel lbData;
	private JLabel lbData2;
	private JLabel lbPD;
	private JLabel lbPS;
	private JLabel lbPeso;
	private JLabel lbBatimentos;
	
	private JCheckBox cxData;
	private JComboBox<String> boxPaciente;
	private JFormattedTextField campoData;
	
	private JTextField txPD;
	private JTextField txPS;
	private JTextField txPeso;
	private JTextField txBatimentos;
	
	private JButton bt_Cancelar;
	private JButton bt_Salvar;
	private JButton bt_Limpar;
	
	
	public janelaAcompanhamento(String titulo) {
		super(titulo);
		//janela
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/esfignamometro.jpg"));
		setSize(600, 500);
		setLocationRelativeTo(null);
		
		
		setVisible(true);
		
		//posicionamento
		 
		
		
		init();
		posiciona();
		init_event();
		
		
		
	}

	

	private void init() {
		try {
		cnt=this.getContentPane();
		
		cnt.setLayout(null);
		borda1 = new JPanel();
		borda1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		borda1.setForeground(Color.WHITE);
		borda1.setBackground(Color.orange);
		cnt.add(borda1);
		
		
		JLabel cnto = new JLabel("ACOMPANHAMENTO DE PACIENTES");
		cnto.setFont(new Font("Source Code Pro", Font.PLAIN, 20));
		borda1.add(cnto);
		
		borda2 = new JPanel();
		borda2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		borda2.setBackground(Color.orange);
		borda2.setForeground(Color.WHITE);
		cnt.add(borda2);
		
		lbPaciente = new JLabel("Paciente:");
		cnt.add(lbPaciente);
		
		
		
		lbData = new JLabel("Data da vrf:");
		lbData2 = new JLabel("Usar data de Hoje");
		lbPS = new JLabel("PD:");
		lbPD = new JLabel("PD:");
		lbPeso = new JLabel ("Peso:");
		lbBatimentos = new JLabel ("Batimentos:");
		boxPaciente = new JComboBox();
		boxPaciente.setModel(new DefaultComboBoxModel(new Vector(pesquisar())));  
		cxData = new JCheckBox("",true);
		
		txPD = new JTextField();
		txPS = new JTextField();
		txPeso = new JTextField();
		txBatimentos = new JTextField();
		
		bt_Salvar = new JButton("Salvar");
		cnt.add(bt_Salvar);
		
		bt_Limpar = new JButton("Limpar");
		cnt.add(bt_Limpar);
		
		bt_Cancelar = new JButton("Cancelar");
		cnt.add(bt_Cancelar);
		
		cnt.add(cxData);
		cnt.add(lbData);
		cnt.add(lbData2);
		cnt.add(boxPaciente);
		cnt.add(lbPD);
		cnt.add(lbPS);
		cnt.add(lbBatimentos);
		cnt.add(lbPeso);
		cnt.add(txPD);
		cnt.add(txPS);
		cnt.add(txPeso);
		cnt.add(txBatimentos);
		 
		
		
			MaskFormatter mascara = new MaskFormatter("##/##/####");
			mascara.setPlaceholderCharacter('_');
			 campoData = new JFormattedTextField(mascara);
			
			 cnt.add(campoData);
			 
			pegaData();
		
		        
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	public void posiciona(){
		cnt.setBounds(200,200,1000,1000);
		borda1.setBounds(10, 10, 564, 80);
		borda2.setBounds(10, 430, 565, 10);
		
		lbPaciente.setBounds(20,110,60,20);
		lbData.setBounds(20,140,100,20);
		lbData2.setBounds(200,140,120,20);
		boxPaciente.setBounds(90,110,470,20);
		campoData.setBounds(90,140,70,20);
		cxData.setBounds(180,140,20,20);
		lbPS.setBounds(20,170,40,20);
		lbPD.setBounds(180,170,40,20);
		lbBatimentos.setBounds(20,200,80,20);
		lbPeso.setBounds(180,200,40,20);
		txPS.setBounds(90,170,70,20);
		txPD.setBounds(220,170,70,20);
		txBatimentos.setBounds(90,200,70,20);
		txPeso.setBounds(220,200,70,20);
		
		bt_Salvar.setBounds(100,360,100,30);
		bt_Limpar.setBounds(220,360,100,30);
		bt_Cancelar.setBounds(340,360,100,30);
		}
	
	public void limpar(){
		txPS.setText("");
		txPD.setText("");
		txPeso.setText("");
		txBatimentos.setText("");
		pegaData();
		cxData.setSelected(true);
	}
	public void init_event(){
		bt_Limpar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event5) {
					
					limpar();
					
			}
		});
		
		
		bt_Cancelar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event5) {
				janelaAcompanhamento.this.setVisible(false);
					
			}
		});
		
		bt_Salvar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event5) {
				
				Paciente paciente = new Paciente();
				
				String nome = (String) boxPaciente.getSelectedItem();
				
				//busca id do paciente 
				ContatoDao dao = new ContatoDao();
				int id=0;
				for (Paciente c : dao.getContatoByName(nome)) {
					id=c.getId();
				}
				//formata a data
				String data= campoData.getText();
				String diaAcomp =(data.substring(6,10)+data.substring(3,5)+data.substring(0,2));
				
				 paciente.setId(id);
				 paciente.setDataAcompanhamento(diaAcomp);
				 paciente.setPressaoDiastolica(txPD.getText());
				 paciente.setPressaoSistolica(txPS.getText());
				 paciente.setPeso(Double.parseDouble(txPeso.getText()));
				 paciente.setBatimentos(Integer.parseInt(txBatimentos.getText()));
				
				
				dao.Acompanhamento(paciente);
				
				
				limpar();				
				}
				
				
			
		});
	
		cxData.addActionListener(new ActionListener() {
	
		public void actionPerformed(ActionEvent event5) {
		if(cxData.isSelected()) pegaData();
		else{
		campoData.setValue("");
		campoData.setEditable(true);}
		}
		
		
	
});  

	}
	void pegaData(){
		 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		 String data = sdf.format(new Date(System.currentTimeMillis()));  
	        campoData.setValue(data); 
	        campoData.setEditable(false);
	}
	
	 ArrayList<String> pesquisar() {
		 ArrayList<String> listaNomes = new ArrayList<String>();
		 
		ContatoDao dao = new ContatoDao();
		
		listaNomes.add("Selecione");
		for (Paciente c : dao.getContatos()) {
			listaNomes.add(c.getNome());
		}
	

		
		return  (listaNomes);
	}
}
