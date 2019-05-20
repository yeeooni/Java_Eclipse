package control;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.dto.Product;
import com.kitri.service.ProductService;

@WebServlet("/viewcart")
public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProductService productService;

	public ViewCartServlet() {
		productService = new ProductService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// System.out.println("들어오니?");

		// 1)세션 얻기
		HttpSession session = request.getSession();

		// 2)세션 속성얻기
		Map<Product, Integer> c = (Map<Product, Integer>) session.getAttribute("cart");
		Map<Product, Integer> rc = new HashMap<Product, Integer>();

		
		if (c != null) {
			Set<Product> keys = c.keySet();

			for (Product p : keys) {
				System.out.println("향상 포문");
//				for(Product p : c.keySet()) {

				String no = p.getProd_no();
					Product p1 = productService.findByNo(no);
					int quantity = c.get(p1);
					System.out.println("p no ="+p1.getProd_no());
					rc.put(p1, quantity);
					System.out.println("~~~~~!!!!" + quantity);

//					System.out.println("p: "+p);
//					System.out.println("p1: "+p1);
//					System.out.println("c: "+c);

			}
			System.out.println(c.size());
			request.setAttribute("rcart", rc);
			int size = rc.size();
			System.out.println(size);
			String path = "/viewcartresult.jsp";

			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}
	}
}
