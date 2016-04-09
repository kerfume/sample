package kei.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionFilter implements Filter {
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
		
		try {
			HttpSession session = ((HttpServletRequest)request).getSession(false);
			if (session == null){
				((HttpServletResponse)response).sendRedirect("/QuizForStruts/Top.jsp");
			}
			chain.doFilter(request, response);
		} catch (ServletException se) {
		} catch (IOException e) {
		}
	}

	public void init(FilterConfig filterConfig) {
	}

	public void destroy() {
	}
}
