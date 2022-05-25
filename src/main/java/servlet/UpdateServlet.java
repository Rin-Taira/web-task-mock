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
import util.ParamUtil;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);

		// 各データを取得
		String productId = request.getParameter("product_id");
		String name = request.getParameter("product_name");
		String price = request.getParameter("price");
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		
		if (ParamUtil.isNullOrEmpty(productId) || ParamUtil.isNullOrEmpty(name) || ParamUtil.isNullOrEmpty(price)) {
			if (ParamUtil.isNullOrEmpty(productId)) {
				// メッセージ設定
				request.setAttribute("msg2", "商品IDは必須です ");
			}
			if (ParamUtil.isNullOrEmpty(name)) {
				// メッセージ設定
				request.setAttribute("msg3", "商品名は必須です ");
			}
			if (ParamUtil.isNullOrEmpty(price)) {
				// メッセージ設定
				request.setAttribute("msg4", "単価は必須です ");
			}
			
			request.getRequestDispatcher("updateInput.jsp").forward(request, response);
			
		}
		
		
		ProductService productService = new ProductService();
		
		Product checkProduct = productService.findById(productId);
		
		Product updateProduct = (Product) session.getAttribute("product");
		
		if (checkProduct != null && checkProduct.getProductId() != updateProduct.getProductId()) {
			request.setAttribute("msg", "商品IDが重複しています");
			request.getRequestDispatcher("updateInput.jsp").forward(request, response);
		} else {
			int result = productService.UpdateById(productId, name, price, category, description, String.valueOf(updateProduct.getProductId()));
	
			if (result == -1) {
				request.setAttribute("msg", "更新時にエラーが発生しました");
				request.getRequestDispatcher("updateInput.jsp").forward(request, response);
			}
	
			request.setAttribute("msg", "更新処理が完了しました");
			
			List<Product> productList = productService.find();
	
			session.setAttribute("productList", productList);
			request.getRequestDispatcher("menu.jsp").forward(request, response);
	
		}
	}

}