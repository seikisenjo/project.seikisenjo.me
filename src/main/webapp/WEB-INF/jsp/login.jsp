<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "java.util.Date,java.text.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <link rel="icon" href="<c:url value="/resources/img/icon.ico" />">

    <title>Project</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />">
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.3.js" />"></script>

    <!-- Custom styles for this template -->
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap-theme.min.css" />">
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/default.css" />">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
  </head>

  <body>
	<header class="project-template">
	    <nav class="navbar navbar-inverse navbar-fixed-top">
	      <div class="container">
	        <div class="navbar-header">
	          <button id="navbar-button" type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	          </button>
	        </div>
	        <div id="navbar" class="collapse navbar-collapse">
	          <ul class="nav navbar-nav">
            	<li><a href="register">Register</a></li>
	            <%--<c:if test="${not empty authenticatedUser}">--%>
	            <c:if test="${not empty UserloggedIn or not empty ClientloggedIn}">
	            	<li><a href="logout">Logout</a></li>
	            </c:if>
	            <li><a href="welcome">Home</a></li>
	          </ul>
	        </div><!--/.nav-collapse -->
	      </div>
	    </nav>
	</header>

	<!-- Login form -->
	<c:if test="${empty authenticatedUser}">
		<main class="mainContent" role="main">
			<div>
				<h2 style="text-align: center">Login</h2>
			<div class="containter loginForm">
				<form id="loginForm" action="login" method="post">
					<div id="messageBox" class="hidden"></div>
					<c:if test="${not empty req_error}">
						<div id="errorMsg">
							<p class="text-danger">${req_error}</p>
						</div>
					</c:if>
					<c:remove var="req_error" scope="session" /> 
					<div id="input-group-username" class="form-group">
						<label for="username" class="control-label">User name</label>
						<input type="text" class="form-control" id="username" name="username" placeholder="User name">
					</div>
					<div id="input-group-password" class="form-group">
						<label for="password" class="control-label">Password</label>
						<input type="password" class="form-control" id="password" name="password" placeholder="Password">
					</div>
					<p><input type="radio" name="identity" value="client" checked> Client</p>
            		<p><input type="radio" name="identity" value="staff"> Staff</p>
					<button id="loginButton" type="submit" class="btn btn-default">Sign in</button>
				</form>
			</div>
			</div>
		</main>
		<table border="1" width="100%">	
	<tr>
	<!-- <td><c:out value="${dispatcher.req}"/></td> -->
	<!-- <td><c:out value="${row.secn}"/></td> -->
	</tr>
</table>		
	</c:if>
    <!-- jQuery and line numbering JavaScript -->
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.3.js" />"></script>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/login.js" />"></script>
  </body>
</html>
