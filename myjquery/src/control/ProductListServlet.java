package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kitri.dto.Product;
import com.kitri.service.ProductService;

@WebServlet("/productlist")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		// 서비스 객체를 한번만 호출하기 위해서
		private ProductService productService;
		public ProductListServlet() {
			productService = new ProductService();
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			List<Product> list =  productService.findAll();
//			System.out.println(list);
			request.setAttribute("list", list);
			
			// ServerSide WebContext 안쪽부터 
			String path = "/productlistresult.jsp"; 
		/* String path = "/content.jsp"; */
			
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			
	}
}
