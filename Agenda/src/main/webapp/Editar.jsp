<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agenda de Contatos</title>
<link rel="icon" href="imagens/favicon.png">
<link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
	<h1>Editar Contato</h1>
	<form name= "frmContato" action="update">
		<table>
			<tr>
				<td><input type="text" name="idcon" class="caixa3" readonly value="<%= request.getAttribute("idcon") %>"><td/>
			</tr>
			<tr>
				<td><input type="text" name="nome" class="caixa1" value="<%= request.getAttribute("nome") %>"></td>
			</tr>
			<tr>
				<td><input type="text" name="fone" class="caixa2" value="<%= request.getAttribute("fone") %>"></td>
			</tr>
			<tr>
				<td><input type="text" name="email" class="caixa1" value="<%= request.getAttribute("email") %>"></td>
			</tr>
		</table>
		<input type="submit" value="Salvar" class="Botao1" onclick="validar()">
	</form>
	<script type="text/javascript" src="scripts/validacao.js"></script>
	
</body>
</html>