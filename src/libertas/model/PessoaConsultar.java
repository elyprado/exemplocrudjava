package libertas.model;

import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import libertas.model.dao.PessoaDao;
import libertas.model.pojo.Pessoa;

public class PessoaConsultar implements Modelo {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			PessoaDao pdao = new PessoaDao();
			
			int idpessoa = Integer.parseInt(request.getParameter("idpessoa"));
			
			Pessoa p = pdao.consultar(idpessoa);
			Gson gson = new Gson();
			
			//imprime o resultado da consulta no banco de dados:
			PrintWriter writer = response.getWriter();
			writer.print(gson.toJson(p));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
