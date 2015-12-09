import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;




public class creditos extends JFrame {
	
	private Container cnt;
	private JTextArea texto;
	private JScrollPane scrollPane;
	private JButton botaoOk;
	
	
	
public void posiciona(){
	cnt.setBounds(200,200,600,400);
	
	scrollPane.setBounds(20,20,550,290);
	botaoOk.setBounds(260,320,60,30);
	
		
	}
	
	public creditos(String titulo) {
		super(titulo);
		//janela
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("./src/esfignamometro.jpg"));
		setSize(600, 400);
		setLocationRelativeTo(null);
		
		
		setVisible(true);
		
		//posicionamento
		 
		
		
		init();
		posiciona();
		init_event();
		
	}
	public void init(){
		
		cnt=this.getContentPane();
		
		cnt.setLayout(null);
		texto = new JTextArea();		
		texto.setEditable(false);
		 scrollPane = new JScrollPane( texto );  
       	 cnt.add(scrollPane);
       
		
		
	             
	   // carrega txt===================================      

		 	String mostra="";
	        String nomeArq="arquivo.txt"; 
	        String linha="";
	        File arq = new File(nomeArq);
	        
	     
	            try{
	         
	            
	            FileReader reader = new FileReader(nomeArq);
	            
	            BufferedReader leitor = new BufferedReader(reader);
	            while(true){
	              linha=leitor.readLine();
	              if(linha==null)
	                break;
	              mostra+=linha+"\n";
	            }
	            texto.setText(mostra);
	          }
	          catch(Exception erro) {}	          
	        
	        	texto.setCaretPosition(0); 
	    
	            botaoOk = new JButton("OK");
	            cnt.add(botaoOk);
	            
	}
	      
	   
	
	public void init_event(){
botaoOk.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event5) {
				creditos.this.setVisible(false);
					
			}
		});
	}

}
