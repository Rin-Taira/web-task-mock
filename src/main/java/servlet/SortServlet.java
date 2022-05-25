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

import compare.ProductCompareByCategory;
import compare.ProductCompareByDate1;
import compare.ProductCompareByDate2;
import compare.ProductCompareById;
import compare.ProductCompareByPrice1;
import compare.ProductCompareByPrice2;
import entity.Product;

@WebServlet("/sort")
public class SortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 文字化け対策
		request.setCharacterEncoding("UTF-8");

		// ログインID、パスワードを取得
		String sortNum = request.getParameter("sort");

		HttpSession session = request.getSession(false);
		List<Product> productList = (List<Product>) session.getAttribute("productList");

		switch (sortNum) {
		case "1":
			Collections.sort(productList, new ProductCompareById());
			break;

		case "2":
			Collections.sort(productList, new ProductCompareByCategory());
			break;

		case "3":
			Collections.sort(productList, new ProductCompareByPrice1());
			break;

		case "4":
			Collections.sort(productList, new ProductCompareByPrice2());
			break;

		case "5":
			Collections.sort(productList, new ProductCompareByDate1());
			break;

		case "6":
			Collections.sort(productList, new ProductCompareByDate2());
			break;
		}

		session.setAttribute("productList", productList);

		request.getRequestDispatcher("menu.jsp").forward(request, response);

	}

}