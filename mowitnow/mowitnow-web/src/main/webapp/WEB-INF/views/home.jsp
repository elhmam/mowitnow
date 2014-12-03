<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
					<h3 class="panel-title">Instructions</h3>
				</div>
				<div class="panel-body">
					<form class="navbar-form navbar-left" method="POST" action="/">
						<div class="form-group">
							<textarea rows="10" cols="70" name="instructions" class="form-control"
								placeholder="Entrer le plan d'exécution">${instructions}</textarea>
						</div>
						<br> <br>
						<button type="submit" class="btn btn-default">Lancer les
							tondeuses</button>
					</form>
				</div>
			</div>
			<div class="block" id="tondeuse"></div>
		</div>

		<div class="col-lg-6">
			<c:if test="${!empty mowersMap}">
				<div class="panel panel-success">
					<div class="panel-heading">
						<h3 class="panel-title">La pelouse apr&egrave;s le passage
							des tondeuses</h3>
					</div>
					<div class="panel-body">
						<c:if test="${!empty mowersMap}">
							<div class="table-responsive">
								<table class="table table-bordered" id="tab">
									<tbody>
										<c:set var="color" value="#3c763d" />
										<c:forEach var="i" begin="0" end="${xlimit}">
											<c:set var="decr" value="${xlimit-i}" />
											<tr>
												<c:forEach var="j" begin="0" end="${ylimit}">
													<c:forEach items="${mowersMap}" var="mowerMap"
														varStatus="count">
														<c:forEach items="${mowerMap.value}" var="mower">
															<c:if test="${mower.x==j && mower.y==decr}">
																<c:set var="color" value="#d0e9c6" />
															</c:if>
														</c:forEach>
													</c:forEach>
													<td style="color:#3c763d;background-color:${color}">
														[${j}-${decr}]</td>
													<c:set var="color" value="#3c763d" />
												</c:forEach>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</c:if>

						<div class="accordion" id="accordion2">
							<c:forEach items="${mowersMap}" var="mowerMap">
						</div>
					</div>
					<div class="accordion-group">
						<div class="accordion-heading">
							<a class="accordion-toggle collapsed" data-toggle="collapse"
								data-parent="#accordion2" href="#collapseOne${mowerMap.key}"
								style="padding-left: 25px"> Trajectoire de la tondeuse n° :
								${mowerMap.key+1} </a>
						</div>
						<div id="collapseOne${mowerMap.key}"
							class="accordion-body collapse" style="padding-left: 35px">
							<c:forEach items="${mowerMap.value}" var="mower">
								<c:out value="[${mower.x} - ${mower.y} - ${mower.orientation}]" />
								<br>
							</c:forEach>
							</c:forEach>
						</div>
					</div>
					<br>
					<br>
				</div>
			</c:if>

			<c:if test="${!empty error}">
				<div class="alert alert-warning">
					<div class="panel-body">						
							<c:out value="${error}" />						
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