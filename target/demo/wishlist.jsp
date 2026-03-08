<%@page import="com.entity.Wishlist" %>
    <%@page import="java.util.List" %>
        <%@page import="com.DAO.WishlistDAOImpl" %>
            <%@page import="com.DB.DBConnect" %>
                <%@page import="com.entity.User" %>
                    <%@ include file="all_component/navbar.jsp" %>
                        <%@page isELIgnored="false" %>

                            <!DOCTYPE html>
                            <html>

                            <head>
                                <meta charset="ISO-8859-1">
                                <title>All Recent Book</title>
                                <%@include file="all_component/allCss.jsp" %>
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
                                <% User user=(User) session.getAttribute("userobj"); if (user==null) {
                                    response.sendRedirect("login.jsp"); return; } WishlistDAOImpl dao=new
                                    WishlistDAOImpl(DBConnect.getConn()); List<Wishlist> list =
                                    dao.getWishlistByUser(user.getId());
                                    %>

                                    <div class="container mt-4">
                                        <h3 class="text-center">My Wishlist</h3>
                                        <div class="row">
                                            <% if (list !=null && !list.isEmpty()) { for (Wishlist w : list) { %>
                                                <div class="col-md-3 mb-4">
                                                    <div class="card">
                                                        <img src="book/<%= w.getBook().getPhotoName() %>"
                                                            class="card-img-top" alt="Book Image"
                                                            style="height: 200px; object-fit: cover;">
                                                        <div class="card-body">
                                                            <h5 class="card-title">
                                                                <%= w.getBook().getBookName() %>
                                                            </h5>
                                                            <p class="card-text">Author: <%= w.getBook().getAuthor() %>
                                                            </p>
                                                            <p class="card-text">Price: â¹<%= w.getBook().getPrice() %>
                                                            </p>

                                                            <!-- Form for removing a book from the wishlist -->
                                                            <form action="remove_from_wishlist" method="post">
                                                                <input type="hidden" name="bookId"
                                                                    value="<%= w.getBook().getBookId() %>">
                                                                <button type="submit"
                                                                    class="btn btn-danger btn-sm">Remove</button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                                <% } } else { %>
                                                    <h5 class="text-center col-12 text-muted">No books in wishlist.</h5>
                                                    <% } %>
                                        </div>
                                    </div>

                            </body>

                            </html>