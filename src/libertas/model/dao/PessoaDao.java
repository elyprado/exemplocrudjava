package libertas.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import libertas.model.pojo.Pessoa;

public class PessoaDao {

	public void inserir(Pessoa p) {
		Conexao con = new Conexao();	
		try {
		
			String sql = "INSERT INTO pessoa (nome, telefone) VALUES (?,?)";
			PreparedStatement prep = con.getConexao().prepareStatement(sql);
			prep.setString(1, p.getNome());
			prep.setString(2, p.getTelefone());
			prep.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconecta();
	}
	
	public void alterar(Pessoa p) {
		Conexao con = new Conexao();	
		try {
		
			String sql = "UPDATE pessoa "
					+ " SET nome = ?, "
					+ " telefone = ? "
					+ " WHERE idpessoa = ?";
			PreparedStatement prep = con.getConexao().prepareStatement(sql);
			prep.setString(1, p.getNome());
			prep.setString(2, p.getTelefone());
			prep.setInt(3, p.getIdpessoa());
			prep.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconecta();
	}
	
	public void excluir(Pessoa p) {
		Conexao con = new Conexao();	
		try {
		
			String sql = "DELETE FROM pessoa "
					+ " WHERE idpessoa = ?";
			PreparedStatement prep = con.getConexao().prepareStatement(sql);
			prep.setInt(1, p.getIdpessoa());
			prep.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconecta();
	}
	
	public Pessoa consultar(int idpessoa) {
		Conexao con = new Conexao();	
		Pessoa p = new Pessoa();
		try {
		
			String sql = "SELECT * FROM pessoa "
					+ " WHERE idpessoa = "+idpessoa;
			Statement sta = con.getConexao().createStatement();
			ResultSet res = sta.executeQuery(sql);
			if (res.next()) {
				p.setIdpessoa(res.getInt("idpessoa"));
				p.setNome(res.getString("nome"));
				p.setTelefone(res.getString("telefone"));
			}
			res.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconecta();
		return p;
	}
	
	public LinkedList<Pessoa> listar() {
		Conexao con = new Conexao();	
		LinkedList<Pessoa> lista = new LinkedList<Pessoa>();
		try {
		
			String sql = "SELECT * FROM pessoa "
					+ " ORDER BY nome";
			Statement sta = con.getConexao().createStatement();
			ResultSet res = sta.executeQuery(sql);
			while (res.next()) {
				Pessoa p = new Pessoa();
				p.setIdpessoa(res.getInt("idpessoa"));
				p.setNome(res.getString("nome"));
				p.setTelefone(res.getString("telefone"));
				lista.add(p);
			}
			res.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		con.desconecta();
		return lista;
	}
	
	
}
 