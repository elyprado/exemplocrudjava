package libertas.model;

import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import libertas.model.dao.PessoaDao;
import libertas.model.pojo.Pessoa;

public class PessoaSalvar implements Modelo {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			PessoaDao pdao = new PessoaDao();
			
			
			Pessoa p = new Pessoa();
			p.setIdpessoa(Integer.parseInt(request.getParameter("idpessoa")));
			p.setNome(request.getParameter("nome"));
			p.setTelefone(request.getParameter("telefone"));
			
			if (p.getIdpessoa()==0) {
				pdao.inserir(p);
			} else {
				pdao.alterar(p);
			}
			//imprime o resultado da consulta no banco de dados:
			PrintWriter writer = response.getWriter();
			Gson gson = new Gson();
			writer.print(gson.toJson("ok"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
