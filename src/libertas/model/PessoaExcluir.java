package libertas.model;

import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import libertas.model.dao.PessoaDao;
import libertas.model.pojo.Pessoa;

public class PessoaExcluir implements Modelo {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			PessoaDao pdao = new PessoaDao();
			
			Pessoa p = new Pessoa();
			p.setIdpessoa(Integer.parseInt(request.getParameter("idpessoa")));
			
			pdao.excluir(p);
			
			//imprime o resultado da consulta no banco de dados:
			PrintWriter writer = response.getWriter();
			Gson gson = new Gson();
			writer.print(gson.toJson("ok"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
