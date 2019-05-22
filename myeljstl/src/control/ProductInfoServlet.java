package control;

import java.io.IOException;
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

@WebServlet("/productinfo")
public class ProductInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    	
	private ProductService productService;
	public ProductInfoServlet() {
		productService = new ProductService();
	}
			
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String prodNo = request.getParameter("no");
		//System.out.println("prod_no =" + prodNo);
		Product product = productService.findByNo(prodNo);
	
		 //System.out.println(product); 
		request.setAttribute("product", product);
		
		String path = "/productinforesult.jsp";
		/* String path = "/content.jsp"; */
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		}
	}

