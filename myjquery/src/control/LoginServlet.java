package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kitri.dto.Customer;
import com.kitri.service.CustomerService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		CustomerService customerService = new CustomerService();
		
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		
		String result = customerService.login(id, pass);
		String path = "/loginresult.jsp";
		
		HttpSession session = request.getSession();
		session.removeAttribute("loginInfo");
		
			if(result.equals("1")) {
				session.setAttribute("loginInfo", id);
			}
		
		request.setAttribute("result", result);
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
