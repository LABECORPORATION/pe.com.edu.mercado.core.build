/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package pe.com.edu.siaa.core.ui.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.util.Log;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import pe.com.edu.siaa.core.ejb.util.jasper.ConstanteGenerarReporteUtil;
import pe.com.edu.siaa.core.model.vo.FileVO;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: HtmlServlet.java 6731 2013-11-12 12:20:12Z teodord $
 */
public class HtmlServlet extends HttpServlet {
	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
//		try {
			String nombreArchivo = request.getParameter("fileName");
			String context = request.getContextPath();
			FileVO fileVO = (FileVO) request.getSession().getAttribute(nombreArchivo);
			JasperPrint jasperPrint = (JasperPrint)fileVO.getReporteGeneradoMap().get(ConstanteGenerarReporteUtil.JASPER_PRINT);

			StringBuffer sbuffer = new StringBuffer();	
			if  (jasperPrint != null) {
				HtmlExporter exporter = new HtmlExporter();
				try {
					exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
					exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, sbuffer);
					exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "" + context + "/resourcesweb/reporte/images/logo_mapfre_general.png?");
					exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
					exporter.setParameter(JRHtmlExporterParameter.BETWEEN_PAGES_HTML, "");
					exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
					exporter.exportReport();
				} catch(Exception e) {
					Log.error(e);
				}
			}
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>MyTron</title>");
			out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"../stylesheet.css\" title=\"Style\">");
			out.print(" <style type=\"text/css\">");
			out.print("	img {");
			out.print(" width: 100%!important;");
			out.print(" } ");
			out.print(" </style> ");
			out.println("</head>");
			out.println("<body ><center>");
			out.print( sbuffer.toString() );
			out.println("</center><body >");
			out.println("</body>");
			out.println("</html>");
			

	}

}
