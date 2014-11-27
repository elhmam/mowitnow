<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="fr">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Mowitnow</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
   		<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
   		<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>
<body>

	<!-- Begin page content -->
	<div class="container">
		<div class="page-header">
			<h1>Mini-projet : Mowitnow</h1>
		</div>

		<div class="col-lg-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Plan d'exécution</h3>
				</div>
				<div class="panel-body">
					<form class="navbar-form navbar-left" method="POST" action="/home">
						<div class="form-group">
							<textarea rows="10" cols="50" name="data" class="form-control"
								placeholder="Entrer le plan d'exécution">${data}</textarea>
						</div>
						<br> <br>
						<button type="submit" class="btn btn-default">Lancer le
							plan d'execution</button>
					</form>
				</div>
			</div>
		</div>

		<div class="col-lg-6">
			<c:if test="${!empty results}">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title">Résultats</h3>
					</div>
					<div class="panel-body">
						<ul class="list-group">
							<c:forEach items="${results}" var="result">
								<li class="list-group-item"><c:out value="${result}" /></li>
							</c:forEach>
						</ul>
					</div>
				</div>

			</c:if>
			<c:if test="${!empty errors}">
				<div class="alert alert-warning" role="alert">
					<div class="panel-body">
						<c:forEach items="${errors}" var="error">
							<c:out value="${error}" />
							<br>
						</c:forEach>
					</div>
				</div>
			</c:if>


		</div>

	</div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
</body>
</html>