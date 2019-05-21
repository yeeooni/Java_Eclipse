package control;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.dto.*;
import com.kitri.exception.AddException;
import com.kitri.service.OrderService;


@WebServlet("/addorder")
public class AddOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	private OrderService orderService;
    public AddOrderServlet() {
       orderService = new OrderService();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 장바구니 정보가 주문 테이블에 저장
		OrderInfo info = new OrderInfo();
		
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("loginInfo");
		Customer c = new Customer();
		c.setId(id);
		info.setCustomer(c); // 주문자 아이디 설정
		
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
		List<OrderLine> lines = new ArrayList<OrderLine>();
		
		if(cart != null) {
			for(Product p : cart.keySet()) {
				//String no = p.getProd_no();
				int quantity = (Integer) cart.get(p);
				OrderLine line = new OrderLine();
				// 상품번호, 수량 >> OrderLine에 설정
				line.setProduct(p);
				line.setOrder_quantity(quantity);
				//line.setOrder_prod_no(order_prod_no);
				lines.add(line);
			}
			info.setLines(lines);
		
			orderService.addOrder(info);
			//  주문이 성공되면 장바구니 내용을 비운다.
			session.removeAttribute("cart");
			String result = "1";
			
			String path = "/addorderresult.jsp";
			request.setAttribute("result", result);
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}
	}

}
