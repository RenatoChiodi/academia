import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ContatoDao {
	private final String INSERT = "INSERT INTO tb_paciente (nm_paciente, sexo_paciente, dt_nasc_paciente, endPaciente, bairroPaciente,cidadePaciente,estadoPaciente,estCivilPaciente,cpfPaciente,profissaoPaciente,emailPaciente,telefonePaciente,celularPaciente) VALUES( ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private final String UPDATE = "UPDATE tb_paciente  SET nm_paciente=?, sexo_paciente=?, dt_nasc_paciente=?, endPaciente=?, bairroPaciente=?,cidadePaciente=?,estadoPaciente=?,estCivilPaciente=?,cpfPaciente=?,profissaoPaciente=?,emailPaciente=?,telefonePaciente=?,celularPaciente=? WHERE id_paciente =?";
	private final String DELETE = "DELETE FROM tb_paciente WHERE id_paciente =?";
	private final String LIST 	= "SELECT * FROM tb_paciente";
	private final String LISTBYNAME = "SELECT * FROM tb_paciente WHERE nm_paciente like ?";
	private final String LISTBYID 	= "SELECT * FROM tb_paciente WHERE id_paciente=?";
	private final String INSERT_ACOMP ="INSERT INTO tb_acompanhamento(idPaciente,dtAcompanhamentoPaciente,prDiastolicaPaciente,prSistolicaPaciente,pesoPaciente,batimentosPaciente)VALUES(?,?,?,?,?,?)";

	public void inserir(Paciente contato) {
		if (contato != null) {
			Connection conn = null;
			try {
				conn = FabricaConexao.getConexao();
				PreparedStatement pstm ;
				pstm = conn.prepareStatement(INSERT);
				
				pstm.setString(1,contato.getNome()); 
				pstm.setString(2,contato.getSexo()) ;
				pstm.setString(3,contato.getNascimento());
				pstm.setString(4,contato.getEndereco());
				pstm.setString(5,contato.getBairro());
				pstm.setString(6,contato.getCidade());
				pstm.setString(7,contato.getEstado());
				pstm.setString(8,contato.getEstCivil());
				pstm.setString(9,contato.getCpf());
				pstm.setString(10,contato.getProfissao());
				pstm.setString(11,contato.getEmail());				
				pstm.setString(12,contato.getTelefone());
				pstm.setString(13,contato.getCelular());
				pstm.execute();
				JOptionPane.showMessageDialog(null,
						"Contato cadastrado com sucesso");
				FabricaConexao.fechaConexao(conn, pstm);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(
						null,
						"Erro ao inserir contato no banco de" + "dados "
								+ e.getMessage());
			}
		} else {
			System.out.println("O contato enviado por parâmetro está vazio");
		}
	}

	public void atualizar(Paciente contato) {
		if (contato != null) {
			Connection conn = null;
			try {
				conn = FabricaConexao.getConexao();
				PreparedStatement pstm;
				pstm = conn.prepareStatement(UPDATE);
				
				pstm.setString(1,contato.getNome()); 
				pstm.setString(2,contato.getSexo()) ;
				pstm.setString(3,contato.getNascimento());
				pstm.setString(4,contato.getEndereco());
				pstm.setString(5,contato.getBairro());
				pstm.setString(6,contato.getCidade());
				pstm.setString(7,contato.getEstado());
				pstm.setString(8,contato.getEstCivil());
				pstm.setString(9,contato.getCpf());
				pstm.setString(10,contato.getProfissao());
				pstm.setString(11,contato.getEmail());				
				pstm.setString(12,contato.getTelefone());
				pstm.setString(13,contato.getCelular());
				pstm.setInt(14,contato.getId());
				pstm.execute();
				
				JOptionPane.showMessageDialog(null,	"Contato alterado com sucesso");
				FabricaConexao.fechaConexao(conn,pstm);
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(
						null,
						"Erro ao atualizar contato no banco de" + "dados "	+ e.getMessage());
			}
		} 
		else {
			JOptionPane.showMessageDialog(null,	"O contato enviado por parâmetro está vazio");
		}
	}

	public void remover(int id) {
		Connection conn = null;
		try {
			conn = FabricaConexao.getConexao();
			PreparedStatement pstm;
			pstm = conn.prepareStatement(DELETE);
			pstm.setInt(1, id);
			pstm.execute();
			FabricaConexao.fechaConexao(conn, pstm);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(
					null,
					"Erro ao excluir contato do banco de" + "dados "
							+ e.getMessage());
		}
	}

	public List<Paciente> getContatos() {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Paciente> contatos = new ArrayList<Paciente>();
		try {
			conn = FabricaConexao.getConexao();
			pstm = conn.prepareStatement(LIST);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Paciente contato = new Paciente();
				contato.setId(rs.getInt("id_paciente"));
				contato.setNome(rs.getString("nm_paciente"));
				contato.setNascimento(rs.getString("dt_nasc_paciente"));
				contatos.add(contato);
				
			}
			FabricaConexao.fechaConexao(conn, pstm, rs);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao listar contatos" + e.getMessage());
		}
		return contatos;
	}

	public List<Paciente> getContatoByName(String nome) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Paciente> contatos = new ArrayList<Paciente>();
		Paciente contato = new Paciente();
		try {
			conn = FabricaConexao.getConexao();
			pstm = conn.prepareStatement(LISTBYNAME);
			pstm.setString(1,"%"+nome+"%");
			rs = pstm.executeQuery();
			while (rs.next()) {
				contato.setId(rs.getInt("id_paciente"));
				contato.setNome(rs.getString("nm_paciente"));			
				contato.setNascimento(rs.getString("dt_nasc_paciente"));
				contatos.add(contato);
			}
			FabricaConexao.fechaConexao(conn, pstm, rs);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao listar contatos" + e.getMessage());
		}
		return contatos;
	}
	
	public  List<Paciente> getContatoById(int id) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		ArrayList<Paciente> contatos = new ArrayList<Paciente>();
		String data;
		Paciente contato = new Paciente();
		try {
			conn = FabricaConexao.getConexao();
			pstm = conn.prepareStatement(LISTBYID);
			pstm.setLong(1,id);
			rs = pstm.executeQuery();
			while (rs.next()) {
				contato.setNome(rs.getString("nm_paciente"));
				contato.setSexo(rs.getString("sexo_paciente")) ;
				data=(rs.getDate("dt_nasc_paciente")).toString();
				contato.setNascimento(data.substring(8,10)+"/"+data.substring(5,7)+"/"+data.substring(0,4));
				contato.setEndereco(rs.getString("endPaciente"));
				contato.setBairro(rs.getString("bairroPaciente"));
				contato.setCidade(rs.getString("cidadePaciente"));
				contato.setEstado(rs.getString("estadoPaciente"));
				contato.setEstCivil(rs.getString("estCivilPaciente"));
				contato.setCpf(rs.getString("cpfPaciente"));
				contato.setProfissao(rs.getString("profissaoPaciente"));
				contato.setEmail(rs.getString("emailPaciente"));	
				contato.setTelefone(rs.getString("telefonePaciente"));
				contato.setCelular(rs.getString("celularPaciente"));
				contatos.add(contato);
				
			}
			FabricaConexao.fechaConexao(conn, pstm, rs);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Erro ao listar contatos" + e.getMessage());
		}
	
		return contatos;
		
	}
	
	public void Acompanhamento(Paciente contato) {
		if (contato != null) {
			Connection conn = null;
			try {
				conn = FabricaConexao.getConexao();
				PreparedStatement pstm2 ;
				pstm2 = conn.prepareStatement(INSERT_ACOMP);
				
				pstm2.setInt(1,contato.getId()); 
				pstm2.setString(2,contato.getDataAcompanhamento());
				pstm2.setString(3,contato.getPressaoDiastolica());
				pstm2.setString(4,contato.getPressaoSistolica());
				pstm2.setDouble(5,contato.getPeso());
				pstm2.setInt(6,contato.getBatimentos());				
				pstm2.execute();
				
				JOptionPane.showMessageDialog(null,
						"Acompanhamento efetuado com sucesso");
				FabricaConexao.fechaConexao(conn, pstm2);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(
						null,
						"Erro ao inserir contato no banco de" + "dados "
								+ e.getMessage());
			}
		} else {
			System.out.println("O contato enviado por parâmetro está vazio");
		}
	}

	
}
