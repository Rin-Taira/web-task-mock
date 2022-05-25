package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Product;
import service.ProductService;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		// productIdを取得
		String id = request.getParameter("product_id");

		ProductService productService = new ProductService();
		int result = productService.deleteById(id);

		if (result == -1) {
			request.setAttribute("msg", "削除に失敗しました。");
			request.getRequestDispatcher("detail.jsp").forward(request, response);
		}

		request.setAttribute("msg", "削除に成功しました。");
		
		List<Product> productList = productService.find();
		
		HttpSession session = request.getSession(false);

		session.setAttribute("productList", productList);
		request.getRequestDispatcher("menu.jsp").forward(request, response);

	}

}