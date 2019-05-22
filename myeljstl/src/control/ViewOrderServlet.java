package control;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.kitri.dto.OrderInfo;
import com.kitri.service.OrderService;

@WebServlet("/vieworder")
public class ViewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private OrderService orderService;
	
	public ViewOrderServlet() {
		orderService = new OrderService();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 세션을 불러옴
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("loginInfo");
		
		// OrderList에 DAO에서 찾아온 id를 넣는다.
		List<OrderInfo> orderList = orderService.findById(id);
		// Dao에서 찾아온 id의 속성을 지정 해준다. (jsp에서 불러올 속성 정보)
		request.setAttribute("orderlist", orderList);
		
		// 해당결과를 jsp(view)에 전달해준다.
		String path = "/vieworderresult.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}

}
