<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import= "model.JavaBeans" %>
    <%@page import= "java.util.ArrayList" %>
    
   <% 
   
   ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("contatos");
  %>
  
   <%--for (int i =0; i<lista.size(); i++){
	   out.println(lista.get(i).getIdcon());
	   out.println(lista.get(i).getNome());
	   out.println(lista.get(i).getFone());
	   out.println(lista.get(i).getEmail());
   }
		    
   --%> 
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda de Contatos</title>
<link rel="stylesheet" type="text/css" href="style.css"><link>
<link rel="icon" href="imagens/favicon.png">
</head>
<body>
	<h1>Agenda de Contatos</h1>
	<a href="novo.html" class="Botao1" >Novo Contato</a>
	<a href="report" class="Botao2" >Relatório</a>
	
	<table class ="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Fone</th>
				<th>E-mail</th>
				<th>Opções</th>
				
			</tr>	
		</thead>
		<tbody>
			<%for (int i = 0; i < lista.size(); i++){ %>
				<tr>
					<td><%=lista.get(i).getIdcon()  %></td>
					<td><%=lista.get(i).getNome() %></td>
					<td><%=lista.get(i).getFone() %></td>
					<td><%=lista.get(i).getEmail() %></td>
					<td><a href="select?idcon=<%=lista.get(i).getIdcon()%>" class="Botao1">Editar</a>
						<a href="javascript:confirmar(<%=lista.get(i).getIdcon()%>)" class="Botao2">Excluir</a>
					</td>
					
				</tr>
			 <% }%>
		</tbody>			
	</table>
	<script type="text/javascript" src="scripts/confirmador.js"></script>
	
</body>
</html>