package control;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.dto.Product;

@WebServlet("/addcart")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("서블릿들어옴");
		String no = request.getParameter("no");
		String quantity = request.getParameter("quantity");
		
		HttpSession session = request.getSession();
	
		Map<Product, Integer> c = (Map<Product, Integer>) session.getAttribute("cart");
		//System.out.println(c);
		
			if(c == null) {
				c = new HashMap<Product, Integer>();
				session.setAttribute("cart", c);
			}
			
			Product p = new Product();
			p.setProd_no(no);

			int intquantity = Integer.parseInt(quantity);
			
			// 장바구니에 해당상품이 존재하는지 확인한다.
			Integer inte = c.get(p);
			if(inte != null) { // 존재한다면 수량을 합친다.
					intquantity += inte.intValue();
			//System.out.println("intquantity =" + intquantity);
			}			
			c.put(p,  intquantity); // 장바구니 상품 추가 
			
			String path = "/addcartresult.jsp"; 
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
			
		/*
		 * System.out.println("=== 장바구니 내용 ==="); Set<Product> keys = c.keySet();
		 * for(Product key : keys) { int q = c.get(key); System.out.println("상품번호 " + key.getProd_no() + ", 수량 " + q); }
		 */
		
		
	}
}
