package pe.com.edu.siaa.core.ui.filter;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pe.com.edu.siaa.core.ejb.util.log.Logger;
import pe.com.edu.siaa.core.ui.util.ConstantesParameterUtil;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class SecurityMapfreSecureFilter.
 *
 * @author ndavilal
 * @version 1.0 , 08/04/2015
 * @since SIAA-CORE 2.1
 */
public class SecuritySiaaSecureFilter implements Filter {

    /** La Constante debug. */
    private static final boolean DEBUG = true;
    
    /** El objeto filter config. */
    private FilterConfig filterConfig = null;
    
    /** El log. */
    private static Logger log = Logger.getLogger(SecuritySiaaSecureFilter.class);

    /**
     * Instancia un nuevo seace secure filter.
     */
    public SecuritySiaaSecureFilter() {
    }

    /**
     * Do before processing.
     *
     * @param request el request
     * @param response el response
     * @throws IOException Señales de que una excepción de E / S se ha producido.
     * @throws ServletException the servlet exception
     */
    private void doBeforeProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (DEBUG) {
           // log("MapfreSecureFilter:DoBeforeProcessing");
        }
    }

    /**
     * Do after processing.
     *
     * @param request el request
     * @param response el response
     * @throws IOException Señales de que una excepción de E / S se ha producido.
     * @throws ServletException the servlet exception
     */
    private void doAfterProcessing(ServletRequest request, ServletResponse response)
            throws IOException, ServletException {
        if (DEBUG) {
            //log("MapfreSecureFilter:DoAfterProcessing");
        }

    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        doBeforeProcessing(request, response);
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpSession session = httpRequest.getSession(false);
            if (session != null && !isSessionInvalid(httpRequest)){ 
            	Object temp = session.getAttribute(ConstantesParameterUtil.ATRIBUTE_SESSION_USUARIO);
                if (temp == null) {
                	 ((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() +"/errorIniciarSession.xhtml");
                        return;
                }  
            } 
            if (session == null) {
            	((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() +"/errorIniciarSession.xhtml");
                return;
            }
        }

        Throwable problem = null;
        try {
            chain.doFilter(request, response);
        } catch (Throwable t) {
            problem = t;
            log.error(problem.getMessage());
        }

        doAfterProcessing(request, response);

        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }
    
    
    
	/**
	 * Comprueba si es session invalid.
	 *
	 * @param httpRequest el http request
	 * @return true, si es session invalid
	 */
	private boolean isSessionInvalid(HttpServletRequest httpRequest) {
		boolean sessionValida = httpRequest.getRequestedSessionId() != null
				&& !httpRequest.isRequestedSessionIdValid();
		return sessionValida;
	}

	/**
	 * Obtiene filter config.
	 *
	 * @return filter config
	 */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Establece el filter config.
     *
     * @param filterConfig el new filter config
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {
    }

    /* (non-Javadoc)
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (DEBUG) {
              //  log("MapfreSecureFilter:Initializing filter");
            }
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MapfreSecureFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    /**
     * Send processing error.
     *
     * @param t el t
     * @param response el response
     */
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);

        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
                pw.print(stackTrace);
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
                log.error(ex);
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
                log.error(ex);
            }
        }
    }

    /**
     * Obtiene stack trace.
     *
     * @param t el t
     * @return stack trace
     */
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
            log.error(ex);
        }
        return stackTrace;
    }

    /**
     * Log.
     *
     * @param msg el msg
     */
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }
}
