<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Digital Book Portal | Register</title>
    <%@ include file="all_component/allCss.jsp" %>
    <style type="text/css">
        .paint-card {
            box-shadow: 0 0 6px 0 rgba(0, 0, 0, 0.3);
        }
    </style>
    <script>
        function validateForm() {
            const pass = document.getElementById("password").value;
            const confPass = document.getElementById("confirmPassword").value;

            if (pass !== confPass) {
                alert("Passwords do not match!");
                return false;
            }
            return true;
        }

        // Optional: Live password match validation
        window.onload = () => {
            const password = document.getElementById("password");
            const confirm = document.getElementById("confirmPassword");

            confirm.oninput = () => {
                if (password.value !== confirm.value) {
                    confirm.setCustomValidity("Passwords do not match");
                } else {
                    confirm.setCustomValidity("");
                }
            };
        };
    </script>
</head>
<body style="background-color: #f0f1f2;">
    <%@ include file="all_component/navbar.jsp" %>

    <div class="container">
        <div class="row mt-5">
            <div class="col-md-4 offset-md-4">
                <div class="card paint-card">
                    <div class="card-body">
                        <h3 class="text-center mb-3">Create an Account</h3>

                        <c:if test="${not empty succMsg}">
                            <div class="alert alert-success text-center" role="alert">${succMsg}</div>
                            <c:remove var="succMsg" scope="session" />
                        </c:if>

                        <c:if test="${not empty failedMsg}">
                            <div class="alert alert-danger text-center" role="alert">${failedMsg}</div>
                            <c:remove var="failedMsg" scope="session" />
                        </c:if>

                        <form action="register" method="post" onsubmit="return validateForm()">
                            <div class="form-group">
                                <label for="name">Name</label>
                                <input type="text" class="form-control" name="name" id="name" required autocomplete="name" placeholder="Your full name">
                            </div>

                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" class="form-control" name="email" id="email" required autocomplete="email" placeholder="example@email.com">
                            </div>

                            <div class="form-group">
                                <label for="phno">Phone Number</label>
                                <input type="text" class="form-control" name="phno" id="phno" pattern="[0-9]{10}" required autocomplete="tel" placeholder="e.g. 9876543210">
                            </div>

                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" name="password" id="password" required autocomplete="new-password" placeholder="Create password">
                            </div>

                            <div class="form-group">
                                <label for="confirmPassword">Confirm Password</label>
                                <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" required autocomplete="new-password" placeholder="Re-enter password">
                            </div>

                            <div class="text-center mt-3">
                                <button type="submit" class="btn btn-success w-100">Register</button>
                            </div>

                            <div class="mt-3 text-center">
                                <a href="login.jsp" style="text-decoration: none;">Already have an account? Login</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
