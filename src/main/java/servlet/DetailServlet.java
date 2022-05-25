package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Product;
import service.ProductService;

@WebServlet("/detail")
public class DetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		// productIdを取得
		String id = request.getParameter("id");

		ProductService productService = new ProductService();
		Product product = productService.findById(id);

		HttpSession session = request.getSession(false);
		session.setAttribute("product", product);

		request.getRequestDispatcher("detail.jsp").forward(request, response);

	}

}