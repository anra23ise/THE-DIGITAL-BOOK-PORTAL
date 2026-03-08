<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Digital Book Portal | Login</title>
<%@ include file="all_component/allCss.jsp" %>
<style type="text/css">
.paint-card {
	box-shadow: 0 0 6px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body style="background-color: #f0f1f2;">
	<%@ include file="all_component/navbar.jsp" %>

	<div class="container">
		<div class="row mt-5">
			<div class="col-md-4 offset-md-4">
				<div class="card paint-card">
					<div class="card-body">
						<h3 class="text-center mb-4">Login to Your Account</h3>

						<%-- ✅ Success Message First --%>
						<c:if test="${not empty succMsg}">
							<div class="alert alert-success text-center" role="alert">
								${succMsg}
							</div>
							<c:remove var="succMsg" scope="session" />
						</c:if>

						<%-- 🔴 Error Message Second --%>
						<c:if test="${not empty failedMsg}">
							<div class="alert alert-danger text-center" role="alert">
								${failedMsg}
							</div>
							<c:remove var="failedMsg" scope="session" />
						</c:if>

						<form action="login" method="post">
							<div class="form-group">
								<label for="emailInput">Email address</label>
								<input type="email" class="form-control" id="emailInput"
									name="email" required="required" placeholder="Enter your email">
							</div>

							<div class="form-group">
								<label for="passwordInput">Password</label>
								<input type="password" class="form-control" id="passwordInput"
									name="password" required="required" placeholder="Enter your password">
							</div>

							<div class="text-center mt-3">
								<button type="submit" class="btn btn-primary w-100">Login</button>
							</div>

							<div class="mt-3 text-center">
								<a href="forgot.jsp" style="text-decoration: none;">Forgot Password?</a><br>
								<a href="register.jsp" style="text-decoration: none;">Create New Account</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
