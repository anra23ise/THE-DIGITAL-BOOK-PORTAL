package com.user.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.DAO.BookDAOImpl;
import com.DAO.BookOrderImpl;
import com.DAO.CartDAOImpl;
import com.DB.DBConnect;
import com.entity.BookDtls;
import com.entity.Book_Order;
import com.entity.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		httpOrderHandler(req, resp);
	}

	private void httpOrderHandler(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		try {
			HttpSession session = req.getSession();

			int id = Integer.parseInt(req.getParameter("id"));
			String name = req.getParameter("username");
			String email = req.getParameter("email");
			String phno = req.getParameter("phno");
			String address = req.getParameter("address");
			String landmark = req.getParameter("landmark");
			String city = req.getParameter("city");
			String state = req.getParameter("state");
			String pincode = req.getParameter("pincode");
			String paymentType = req.getParameter("payment");
			String totalPrice = req.getParameter("totalPrice");

			if (paymentType == null || paymentType.equals("noselect")) {
				session.setAttribute("failedMsg", "Choose Payment Method");
				resp.sendRedirect("checkout.jsp");
				return;
			}

			String fullAdd = String.join(",", address, landmark, city, state, pincode);

			CartDAOImpl cartDao = new CartDAOImpl(DBConnect.getConn());
			BookDAOImpl bookDao = new BookDAOImpl(DBConnect.getConn());
			List<Cart> cartList = cartDao.getBookByUser(id);

			if (cartList.isEmpty()) {
				session.setAttribute("failedMsg", "Add Items to cart first.");
				resp.sendRedirect("checkout.jsp");
				return;
			}

			ArrayList<Book_Order> orderList = new ArrayList<>();
			Random r = new Random();

			for (Cart c : cartList) {
				BookDtls b = bookDao.getBookById(c.getBid());

				Book_Order order = new Book_Order();
				order.setOrderId("BOOK-ORD-00" + r.nextInt(1000));
				order.setUserName(name);
				order.setEmail(email);
				order.setPhno(phno);
				order.setFulladd(fullAdd);
				order.setBookName(c.getBookName());
				order.setAuthor(c.getAuthor());
				order.setPrice(String.valueOf(c.getPrice()));
				order.setPaymentType(paymentType);
				order.setIsbn(b.getIsbn());

				orderList.add(order);
			}

			if (paymentType.equals("COD")) {
				BookOrderImpl orderDao = new BookOrderImpl(DBConnect.getConn());
				boolean saved = orderDao.saveOrder(orderList);

				if (saved) {
					orderList.clear();
					resp.sendRedirect("order_success.jsp");
				} else {
					session.setAttribute("failedMsg", "Your Order Failed");
					resp.sendRedirect("checkout.jsp");
				}
			} else {
				resp.sendRedirect("cardpayment.jsp?un=" + name + "&&em=" + email + "&&ph=" + phno + "&&ad=" + fullAdd + "&&ta=" + totalPrice);
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.getSession().setAttribute("failedMsg", "Something went wrong.");
			resp.sendRedirect("checkout.jsp");
		}
	}
}
