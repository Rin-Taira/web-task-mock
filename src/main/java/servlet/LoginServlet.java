package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Product;
import entity.User;
import service.ProductService;
import service.UserService;
import util.ParamUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            return;
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
        	// 次画面指定
        	request.setAttribute("user", user);
        	
        	// 表示用のproductsデータ取得
        	ProductService productService = new ProductService();
        	List<Product> productList = productService.find();
        	
        	request.setAttribute("productList", productList);
        	
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }
    }

}