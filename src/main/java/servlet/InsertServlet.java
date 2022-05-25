package servlet;

import java.io.IOException;
import java.sql.Timestamp;
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

@WebServlet("/insert")
public class InsertServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 文字化け対策
        request.setCharacterEncoding("UTF-8");

        // ログインID、パスワードを取得
        String productId = request.getParameter("product_id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String categoryId = request.getParameter("category_id");
        String description = request.getParameter("description");
        String imgPath = request.getParameter("img_path");
        
        
        // 入力値のチェック
        if (ParamUtil.isNullOrEmpty(productId) || ParamUtil.isNullOrEmpty(name) || ParamUtil.isNullOrEmpty(price) || ParamUtil.isNullOrEmpty(categoryId)) {
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
            if (ParamUtil.isNullOrEmpty(categoryId)) {
                // メッセージ設定
                request.setAttribute("msg5", "カテゴリは必須です ");
            }
            // 次画面指定
            request.getRequestDispatcher("insert.jsp").forward(request, response);
        }
        
      
        ProductService productService = new ProductService();
        Product product = productService.findById(productId);

        
        if (product != null) {
        	// メッセージ設定
            request.setAttribute("msg1", "商品IDが重複しています");
            // 次画面指定
            request.getRequestDispatcher("insert.jsp").forward(request, response);
            
        } else {
        	// 入力されたデータをもとに insert するためのProductのインスタンス生成
        	
        	Product newProduct = new Product(Integer.parseInt(productId), Integer.parseInt(categoryId), null, name, Integer.parseInt(price), description, new Timestamp(System.currentTimeMillis()), null);
        	
        	productService.insert(newProduct);
        	
        	request.setAttribute("msg", "登録が完了しました");
        	
        	
        	// 表示用のproductsデータ取得
        	List<Product> productList = productService.find();
        	
        	HttpSession session = request.getSession(false);
        	session.setAttribute("productList", productList);
        	
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }
    }

}