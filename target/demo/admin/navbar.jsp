<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>

<!-- Top Stripe -->
<div class="container-fluid" style="height: 5px; background-color: #303f9f"></div>

<!-- Header Bar -->
<div class="container-fluid p-3 bg-light">
  <div class="row align-items-center">
    <div class="col-md-6 text-success">
      <h3>
        <i class="fas fa-book"></i> E-Books
      </h3>
    </div>

    <div class="col-md-6 text-right">
      <!-- Logged-in User -->
      <c:if test="${not empty userobj}">
        <span class="mr-2 text-success font-weight-bold">
          <i class="fas fa-user"></i> Welcome, ${userobj.name}
        </span>
        <button class="btn btn-danger" data-toggle="modal" data-target="#logoutModal">
          <i class="fas fa-sign-out-alt"></i> Logout
        </button>
      </c:if>

      <!-- Guest User -->
      <c:if test="${empty userobj}">
        <a href="../login.jsp" class="btn btn-success">
          <i class="fas fa-sign-in-alt"></i> Login
        </a>
        <a href="../register.jsp" class="btn btn-primary text-white">
          <i class="fas fa-user-plus"></i> Register
        </a>
      </c:if>
    </div>
  </div>
</div>

<!-- Logout Modal -->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="logoutModalCenterTitle" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">

      <div class="modal-header">
        <h5 class="modal-title">Confirm Logout</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>

      <div class="modal-body text-center">
        <h4>Do you want to logout?</h4>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
        <a href="../logout" class="btn btn-primary text-white">Logout</a>
      </div>

    </div>
  </div>
</div>

<!-- Navigation Bar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-custom">
<a class="navbar-brand" href="home.jsp"><i class="fas fa-home"></i> Home</a>
<button class="navbar-toggler" type="button" data-toggle="collapse"
    data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
    aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
</button>

<div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav ml-auto">
    <!-- Additional nav items can go here -->
    </ul>
</div>
</nav>
