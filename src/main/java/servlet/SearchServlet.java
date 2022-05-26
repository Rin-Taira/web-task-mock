package servlet;

import java.io.IOException;
import java.util.Collections;
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

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 文字化け対策
        request.setCharacterEncoding("UTF-8");
        
        HttpSession session = request.getSession(false);
        
        for (int i = 1; i < 7; i++) {
			session.removeAttribute("s" + i);
		}

        // キーワード取得
        String keyword = request.getParameter("keyword");
        
        
        ProductService productService = new ProductService();
        
        List<Product> productList = Collections.emptyList();
        
        if (ParamUtil.isNullOrEmpty(keyword)) {
        	productList = productService.find();
        } else {
        	productList = productService.findByKeyword(keyword);
        	if (productList.size() == 0) {
        		productList = productService.find();
        		request.setAttribute("msg", "検索結果がありません");
        	}
        }
        
        session.setAttribute("s", "selected");
        session.setAttribute("productList", productList);
        
        request.getRequestDispatcher("menu.jsp").forward(request, response);
    }
        
}