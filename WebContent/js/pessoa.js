/**
 * 
 */
 
 
 //declara uma variável global contendo o ID do registro atual
 var idpessoaAtual = 0;
 
 //assim que carrega o arquivo pessoa.js, executa o método listarPessoa
 listarPessoa();
 
 
 function listarPessoa() {
 	document.getElementById("listapessoas").innerHTML = "<tr><td>aguarde, carregando...</td></tr>";
	
	fetch("Controller?service=PessoaListar").then(function(response) {
	    response.json().then(function(dados) {

			//carrega os dados na tabela
	  		var tab = "<th>Nome</th>"
	  		          + "<th>Telefone</th>"
	  		          + "<th width='10px'></th>"
	  		          + "<th width='10px'></th>";
	  		for (var i in dados) {
	  			tab += "<tr>"
	  			    + "<td>" + dados[i].nome + "</td>"
	  			    + "<td>" + dados[i].telefone + "</td>"
	  			    + "<td><button type='button' class='btn btn-primary' onclick='alterarPessoa("+dados[i].idpessoa+")'>Alterar</button></td>"
	  			    + "<td><button type='button' class='btn btn-danger' onclick='excluirPessoa("+dados[i].idpessoa+")'>Excluir</button></td>"
	  			    + "</tr>"
	  		}
	  		document.getElementById("listapessoas").innerHTML = tab;

	    });
	});
	
 	
 }
 
 function salvarPessoa() {
	

	const params = new URLSearchParams({
	  	idpessoa:idpessoaAtual, 
    	nome:document.getElementById("nome").value,
    	telefone: document.getElementById("telefone").value
	});

	var url ="Controller?service=PessoaSalvar&"+params.toString();
	
	fetch(url).then(function(response) {
	    response.json().then(function(dados) {
			if (dados=="ok") {
				listarPessoa();
				$('#formpessoa').modal('hide');
			} else {
				alert("Falha ao salvar os dados");
			} 
		});
	});

 }
 
 function novaPessoa() {
     idpessoaAtual = 0;
 	 document.getElementById("nome").value = "";
 	 document.getElementById("telefone").value = "";
 	 
 	 $('#formpessoa').modal('show');
 }
 
 function excluirPessoa(idpessoa) {
 	
	fetch("Controller?service=PessoaExcluir&idpessoa="+idpessoa)
	.then(function(response) {
	    response.json().then(function(dados) {
			if (dados=="ok") {
				listarPessoa();
			} else {
				alert("Falha ao salvar os dados");
			}
		}); 
	});
 }
 
 function alterarPessoa(idpessoa) {
 	
 	fetch("Controller?service=PessoaConsultar&idpessoa="+idpessoa)
 	.then(function(response) {
	    response.json().then(function(dados) {

		 idpessoaAtual = dados.idpessoa;
	 	 document.getElementById("nome").value = dados.nome;
	 	 document.getElementById("telefone").value = dados.telefone;
	 	 
	 	 $('#formpessoa').modal('show');

	    });
	});
 }
 