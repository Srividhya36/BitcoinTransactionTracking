

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Servlet implementation class KafkaProducerServlet
 */
@WebServlet("/KafkaProducerServlet")
public class KafkaProducerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KafkaProducerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ToRedis redis = new ToRedis();
		redis.abc();
		String abc = request.getParameter("cntr");
		String abc2= request.getParameter("time");
		System.out.println("cntr="+abc);
		System.out.println("time="+abc2);
		Timer timer = new Timer();
		timer.schedule(new clearDB(), 0, 5000);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
