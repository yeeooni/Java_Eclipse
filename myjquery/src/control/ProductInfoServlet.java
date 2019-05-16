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

@WebServlet("/productInfo")
public class ProductInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService ps = new ProductService();
		Product prodNo = ps.findByNo();
		
		request.setAttribute("prod_No", prodNo);
//		System.out.println(prodNo);
		
		String path = "/content.jsp";
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
		}
	}

