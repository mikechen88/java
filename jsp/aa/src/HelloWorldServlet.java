import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloWorldServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		System.out.println("do get");
		response.getWriter().write(
				"<a href='http://www.bjsxt.com'>gobbbbvvvv</a>");
	}
}