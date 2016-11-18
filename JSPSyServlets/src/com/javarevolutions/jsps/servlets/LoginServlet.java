package com.javarevolutions.jsps.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
/*Cuando se ejecute web.xml invoca al servlet y se mete en un metodo, en este 
 * caso se mete al "POST", porque ya lo predefinimosen el login.html
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//aca va lo que queremos que hago cuando entra por metodo get
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {//aca va lo que queremos que hago cuando entra por metodo post
		// Como se hace para cachear todo lo que me mande el cliente
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		System.out.println("Usuario: "+ usuario);
		System.out.println("Password: "+ password);
		if(usuario.equals("Sheila") && password.equals("Machado")){
		System.out.println("Welcom...");	
		} else {
			System.out.println("Error en los datos de acceso...");
		}
		
	}

}
