<%@page import="com.entity.User"%>
<%@page import="com.entity.BookDtls"%>
<%@page import="java.util.List"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.DAO.BookDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Old Books</title>
<%@include file="all_component/allCss.jsp"%>
<style type="text/css">
.crd-ho:hover {
    background-color: #fcf7f7;
}
.paint-card {
    box-shadow: 0 0 6px 0 rgba(0, 0, 0, 0.3);
}
</style>
</head>
<body>
<%
User u = (User) session.getAttribute("userobj");
%>
<%@include file="all_component/navbar.jsp"%>
<div class="container-fluid">
    <h3 class="text-center">All Old Books</h3>
    <div class="row p-3">
        <%
        BookDAOImpl dao2 = new BookDAOImpl(DBConnect.getConn());
        List<BookDtls> list2 = dao2.getAllOldBook();
        for (BookDtls b : list2) {
        %>
        <div class="col-md-3">
            <div class="card crd-ho mt-2 paint-card">
                <div class="card-body text-center">
                    <img alt="" src="book/<%=b.getPhotoName()%>"
                        style="width: 100px; height: 150px" class="img-thumblin">
                    <p><%=b.getBookName()%></p>
                    <p><%=b.getAuthor()%></p>
                    <p>Categories: <%=b.getBookCategory()%></p>
                    <div class="row justify-content-center">
                        <a href="view_books.jsp?bid=<%=b.getBookId()%>"
                            class="btn btn-success btn-sm m-1">View Details</a>
                        <a href="#" class="btn btn-danger btn-sm m-1">
                            <i class="fas fa-rupee-sign"></i> <%=b.getPrice()%>
                        </a>
                        <a href="demo/add_to_wishlist?bookId=<%=b.getBookId()%>" 
                            class="btn btn-warning btn-sm m-1">Add to Wishlist</a>
                    </div>
                </div>
            </div>
        </div>
        <%
        }
        %>
    </div>
</div>
</body>
</html>
