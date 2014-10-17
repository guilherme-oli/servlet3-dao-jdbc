package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.bean.Contato;
import br.com.caelum.dao.ContatoDao;

@WebServlet("/adicionaContato")
public class AdicionaContatoServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		log("Inicializando a Servlet");
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		log("Destruindo a Servlet");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String email = request.getParameter("email");
		String data = request.getParameter("dataNascimento");
		
		
		Date date;
		Calendar cal = Calendar.getInstance();
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
			cal.setTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("Erro de conversão de data");
			return;
		}
		
		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setEndereco(endereco);
		contato.setDataNascimento(cal);
		
		try {
			new ContatoDao().adiciona(contato);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("Erro ao adicionar no BD!");
			return;
		}
		
		out.println("<html>");
        out.println("<body>");
        out.println("Contato " + contato.getNome() + " adicionado com sucesso");    
        out.println("</body>");
        out.println("</html>");
	}
}
