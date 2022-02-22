<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true" %>

<!DOCTYPE html>
<html lang="en">
  <%@include file="pageHeader.jsp" %>

  <body>
	<%@include file="header.jsp" %>
	<%--
	<div class="welcome">
		<c:if test="${not empty req_msg}">
			<h1 style="text-align: center;padding-top: 150px;">${req_msg}</h1>
		</c:if>
		<c:if test="${empty req_msg}">
			<h1 style="text-align: center;padding-top: 150px;"></h1>
		</c:if>
		<c:remove var="req_msg" scope="session" /> 
	</div>
	--%>
	
	<!-- Slideshow container -->
	<div class="slideshow-container">
	<h1 style="text-align: center;padding-top: 50px;"></h1>

  	<!-- Full-width images with number and caption text -->
  	<div class="mySlides fade">
    	<img src="/resources/img/img1.jpg" style="width:100%">
  	</div>

  	<div class="mySlides fade">
    	<img src="/resources/img/img2.jpg" style="width:100%">
  	</div>

  	<div class="mySlides fade">
    	<img src="/resources/img/img3.jpg" style="width:100%">
  	</div>
  	
  	<div class="mySlides fade">
    	<img src="/resources/img/img4.jpg" style="width:100%">
  	</div>
  	
  	<div class="mySlides fade">
    	<img src="/resources/img/img5.jpg" style="width:100%">
  	</div>

  	<!-- Next and previous buttons -->
  	<a class="prev" onclick="plusSlides(-1)">&#10094;</a>
  	<a class="next" onclick="plusSlides(1)">&#10095;</a>
	</div>
	<br>

	<!-- The dots/circles -->
	<div style="text-align:center">
  	<span class="dot" onclick="currentSlide(1)"></span>
  	<span class="dot" onclick="currentSlide(2)"></span>
  	<span class="dot" onclick="currentSlide(3)"></span>
  	<span class="dot" onclick="currentSlide(4)"></span>
  	<span class="dot" onclick="currentSlide(5)"></span>
	</div>

	<!-- jQuery and line numbering JavaScript -->
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.11.3.js" />"></script>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/welcome.js" />"></script>
  </body>
</html>
