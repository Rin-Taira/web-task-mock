package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Category;
import entity.Product;
import entity.User;
import service.CategoryService;
import service.ProductService;
import service.UserService;
import util.ParamUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		// ログインID、パスワードを取得
		String id = request.getParameter("loginId");
		String pass = request.getParameter("pass");

		// 入力値のチェック
		if (ParamUtil.isNullOrEmpty(id) || ParamUtil.isNullOrEmpty(pass)) {
			if (ParamUtil.isNullOrEmpty(id)) {
				// メッセージ設定
				request.setAttribute("msg2", "IDは必須です ");
			}
			if (ParamUtil.isNullOrEmpty(pass)) {
				// メッセージ設定
				request.setAttribute("msg3", "PASSは必須です ");
			}
			// 次画面指定
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}


		// ログインチェック
		UserService userService = new UserService();
		User user = userService.authentication(id, pass);


		if (user == null) {
			// メッセージ設定
			request.setAttribute("msg1", "IDまたはパスワードが不正です");
			// 次画面指定
			request.getRequestDispatcher("index.jsp").forward(request, response);

		} else {
			// user情報をセッションに保存
			HttpSession session = request.getSession(false);
			session.setAttribute("user", user);

			// 表示用のproductsデータ取得
			ProductService productService = new ProductService();
			List<Product> productList = productService.find();

			session.setAttribute("productList", productList);

			// categoryデータ取得
			CategoryService categoryService = new CategoryService();
			List<Category> categoryList = categoryService.find();

			session.setAttribute("categoryList", categoryList);

			request.getRequestDispatcher("menu.jsp").forward(request, response);
		}
	}

}