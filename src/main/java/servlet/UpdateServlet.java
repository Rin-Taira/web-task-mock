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

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		// 各データを取得
		String productId = request.getParameter("product_id");
		String name = request.getParameter("product_name");
		String price = request.getParameter("price");
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		

		ProductService productService = new ProductService();
		int result = productService.UpdateById(productId, name, price, category, description);

		if (result == -1) {
			request.setAttribute("msg", "更新時にエラーが発生しました");
			request.getRequestDispatcher("detail.jsp").forward(request, response);
		}

		request.setAttribute("msg", "更新処理が完了しました");
		
		List<Product> productList = productService.find();
		
		HttpSession session = request.getSession(false);

		session.setAttribute("productList", productList);
		request.getRequestDispatcher("menu.jsp").forward(request, response);

	}

}