package pe.com.edu.siaa.core.ui.servlets;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.com.edu.siaa.core.model.util.ConstanteConfigUtil;

/**
 * <ul>
 * <li>Copyright 2017 ndavilal. Todos los derechos reservados.</li>
 * </ul> 
 * 
 * La Class FileServlet.
 *
 * @author ndavilal
 * @version 1.0 , 07/04/2015
 * @since SIAA-CORE 2.1
 */
public class FileServlet extends HttpServlet {

    // Constants ----------------------------------------------------------------------------------

    /** La Constante serialVersionUID. */
	private static final long serialVersionUID = -6428163993771269535L;
	
	/** La Constante DEFAULT_BUFFER_SIZE. */
	private static final int DEFAULT_BUFFER_SIZE = 10240; // ..bytes = 10KB.
    
    /** La Constante DEFAULT_EXPIRE_TIME. */
    private static final long DEFAULT_EXPIRE_TIME = 604800000L; // ..ms = 1 week.
    
    /** La Constante MULTIPART_BOUNDARY. */
    private static final String MULTIPART_BOUNDARY = "MULTIPART_BYTERANGES";

    // Properties ---------------------------------------------------------------------------------

    /** El base path. */
    private String basePath;

    // Actions ------------------------------------------------------------------------------------

   
    /* (non-Javadoc)
     * @see javax.servlet.GenericServlet#init()
     */
    @Override
    public void init() throws ServletException {

    	// Obtiene la ruta base (camino de conseguir todos los recursos de) como par�metro init.
        this.basePath = ConstanteConfigUtil.RUTA_RECURSOS;//getInitParameter("basePath");

        // Validar ruta de la base.
        if (this.basePath == null) {
            throw new ServletException("'BasePath' parametro FileServlet init es necesario.");
        } else {
            File path = new File(this.basePath);
            if (!path.exists()) {
                throw new ServletException("Valor 'basePath' parametro FileServlet init"
                    + this.basePath + " no existe realmente en el sistema de archivos..");
            } else if (!path.isDirectory()) {
                throw new ServletException("Valor 'basePath' parametro FileServlet init"
                    + this.basePath + "' en realidad no es un directorio en el sistema de archivos.");
            } else if (!path.canRead()) {
                throw new ServletException("Parametro FileServlet init 'basePath' valor '"
                    + this.basePath + "' en realidad no es legible en el sistema de archivos.");
            }
        }
    }

  
    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doHead(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Process request without content.
        processRequest(request, response, false);
    }

   
    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Process request with content.
        processRequest(request, response, true);
    }

   
    /**
     * Process request.
     *
     * @param request el request
     * @param response el response
     * @param content el content
     * @throws IOException Se�ales de que una excepci�n de E / S se ha producido.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response, boolean content) throws IOException {
    	// Validar el archivo solicitado -------------------------------------------- ----------------

    	// Obtener archivo solicitado por la informaci�n de la ruta.
        String requestedFile = request.getPathInfo();
         // Comprobar si el archivo se presta con la URL de la solicitud.
        if (requestedFile == null) {
        	// Haga lo suyo si el archivo no se suministra a la URL de la solicitud.
        	// Lanzar una excepci�n, o enviar 404, o mostrar default / p�gina de advertencia, o simplemente lo ignoran.
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // URL decodificar el nombre del archivo (puede contener espacios y sobre) y preparar objeto de archivo.
        File file = new File(basePath, URLDecoder.decode(requestedFile, "UTF-8"));

        // Comprobar si el archivo existe realmente en sistema de archivos.
        if (!file.exists()) {
        	// Haga lo suyo si el archivo que parece ser que no existe.
        	// Lanzar una excepci�n, o enviar 404, o mostrar default / p�gina de advertencia, o simplemente lo ignoran.
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // Preparar algunas variables. El ETag es un identificador �nico del archivo.
        String fileName = file.getName();
        long length = file.length();
        long lastModified = file.lastModified();
        String eTag = fileName + "_" + length + "_" + lastModified;


        // Validar encabezados de solicitud para el almacenamiento en cach� ------------------------------------------- --------

        // If-None-Match cabecera debe contener "*" o ETag. Si es as�, entonces volver 304.
        String ifNoneMatch = request.getHeader("If-None-Match");
        if (ifNoneMatch != null && matches(ifNoneMatch, eTag)) {
            response.setHeader("ETag", eTag); // Necesario en 304.
            response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
            return;
        }

        // If-Modified-Since encabezado debe ser mayor que LastModified. Si es as�, entonces volver 304.
        // Esta cabecera se ignora si cualquier encabezado If-None-Match se especifica.
        long ifModifiedSince = request.getDateHeader("If-Modified-Since");
        if (ifNoneMatch == null && ifModifiedSince != -1 && ifModifiedSince + 1000 > lastModified) {
            response.setHeader("ETag", eTag); // Required in 304.
            response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
            return;
        }


        // Validar encabezados de solicitud para reanudar ------------------------------------------- ---------

        // If-Match cabecera debe contener "*" o ETag. Si no, entonces volver 412.
        String ifMatch = request.getHeader("If-Match");
        if (ifMatch != null && !matches(ifMatch, eTag)) {
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
            return;
        }

        // Si-no modificada-Since encabezado debe ser mayor que LastModified. Si no, entonces volver 412.
        long ifUnmodifiedSince = request.getDateHeader("If-Unmodified-Since");
        if (ifUnmodifiedSince != -1 && ifUnmodifiedSince + 1000 <= lastModified) {
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED);
            return;
        }


        // Validar y procesar rango -------------------------------------------- -----------------

        // Preparar algunas variables. La gama completa representa el archivo completo.
        Range full = new Range(0, length - 1, length);
        List<Range> ranges = new ArrayList<Range>();

        // Validar y procesar los encabezados de Rango y Si-Range.
        String range = request.getHeader("Range");
        if (range != null) {

        	// Rango de cabecera debe coincidir con el formato "bytes = nn, nn, nn ...". Si no, entonces volver 416.
            if (!range.matches("^bytes=\\d*-\\d*(,\\d*-\\d*)*$")) {
                response.setHeader("Content-Range", "bytes */" + length); // Necesario en 416.
                response.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                return;
            }

            // Si-Range encabezado ETag deben coincidir o ser mayor LastModified. Si no,
            // Entonces volver archivo completo.
            String ifRange = request.getHeader("If-Range");
            if (ifRange != null && !ifRange.equals(eTag)) {
                try {
                    long ifRangeTime = request.getDateHeader("If-Range"); // Throws IAE if invalid.
                    if (ifRangeTime != -1 && ifRangeTime + 1000 < lastModified) {
                        ranges.add(full);
                    }
                } catch (IllegalArgumentException ignore) {
                    ranges.add(full);
                }
            }

            // Si alguna validez si-Range cabecera, luego procesar cada parte de la gama de bytes.
            if (ranges.isEmpty()) {
                for (String part : range.substring(6).split(",")) {
                	// Suponiendo un archivo con longitud de 100, los siguientes ejemplos devuelve bytes en:
                    // 50-80 (50 a 80), 40- (40 a length=100), -20 (length-20=80 a length=100).
                    long start = sublong(part, 0, part.indexOf("-"));
                    long end = sublong(part, part.indexOf("-") + 1, part.length());

                    if (start == -1) {
                        start = length - end;
                        end = length - 1;
                    } else if (end == -1 || end > length - 1) {
                        end = length - 1;
                    }

                    // Comprobar si el rango es sint�cticamente v�lida. Si no, entonces volver 416.
                    if (start > end) {
                        response.setHeader("Content-Range", "bytes */" + length); // Necesario en 416.
                        response.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                        return;
                    }

                    // Anhade rango.
                    ranges.add(new Range(start, end, length));
                }
            }
        }


        // Preparar e inicializar respuesta -------------------------------------------- ------------

        // Obtiene el tipo de contenido por nombre de archivo y la configuraci�n predeterminada de apoyo GZIP y disposici�n del contenido.
        String contentType = getServletContext().getMimeType(fileName);
        boolean acceptsGzip = false;
        String disposition = "inline";

        // Si el tipo de contenido se desconoce, a continuaci�n, establezca el valor predeterminado.
        // Para todos los tipos de contenido, consulte: http://www.w3schools.com/media/media_mimeref.asp
        // Para agregar nuevos tipos de contenido, agregar nuevo mime-mapping entrada en web.xml.
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        // Si el tipo de contenido es texto, a continuaci�n, determinar si la codificaci�n GZIP contenido es compatible con
        // El navegador y expandir tipo de contenido con la codificaci�n de caracteres y un derecho.
        if (contentType.startsWith("text")) {
            String acceptEncoding = request.getHeader("Accept-Encoding");
            acceptsGzip = acceptEncoding != null && accepts(acceptEncoding, "gzip");
            contentType += ";charset=UTF-8";
         } 
        // De lo contrario, le espera para las im�genes, determinar la disposici�n del contenido. Si el tipo de contenido es compatible con
        // El navegador, ajuste la unidad en l�nea, otro accesorio que se abrir� un 'guardar como' di�logo.
        else if (!contentType.startsWith("image")) {
            String accept = request.getHeader("Accept");
            disposition = accept != null && accepts(accept, contentType) ? "inline" : "attachment";
        }

        // Inicializar respuesta.
        response.reset();
        response.setBufferSize(DEFAULT_BUFFER_SIZE);
        response.setHeader("Content-Disposition", disposition + ";filename=\"" + fileName + "\"");
        response.setHeader("Accept-Ranges", "bytes");
        response.setHeader("ETag", eTag);
        response.setDateHeader("Last-Modified", lastModified);
        response.setDateHeader("Expires", System.currentTimeMillis() + DEFAULT_EXPIRE_TIME);


        // Enviar archivo solicitado (pieza (s)) al cliente ------------------------------------- -----------

        // Preparar los streams.
        RandomAccessFile input = null;
        OutputStream output = null;

        try {
        	// streams abiertos.
            input = new RandomAccessFile(file, "r");
            output = response.getOutputStream();

            if (ranges.isEmpty() || ranges.get(0) == full) {

            	// Devolver el archivo completo.
                Range r = full;
                response.setContentType(contentType);
                response.setHeader("Content-Range", "bytes " + r.start + "-" + r.end + "/" + r.total);

                if (content) {
                    if (acceptsGzip) {
                    	// El navegador acepta GZIP, por lo que el contenido de GZIP.
                        response.setHeader("Content-Encoding", "gzip");
                        output = new GZIPOutputStream(output, DEFAULT_BUFFER_SIZE);
                    } else {
                    	// La longitud del contenido no es directamente previsible en caso de GZIP.
                    	// Por lo que s�lo anadirlo si no hay medios de GZIP, otro navegador se bloquear�.
                        response.setHeader("Content-Length", String.valueOf(r.length));
                    }

                    // Copiar gama completa.
                    copy(input, output, r.start, r.length);
                }

            } else if (ranges.size() == 1) {

            	// Devolver sola parte del archivo.
                Range r = ranges.get(0);
                response.setContentType(contentType);
                response.setHeader("Content-Range", "bytes " + r.start + "-" + r.end + "/" + r.total);
                response.setHeader("Content-Length", String.valueOf(r.length));
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT); // 206.

                if (content) {
                	// Copiar gama �nica pieza.
                    copy(input, output, r.start, r.length);
                }

            } else {

            	// Devolver m�ltiples partes del archivo.
                response.setContentType("multipart/byteranges; boundary=" + MULTIPART_BOUNDARY);
                response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT); // 206.

                if (content) {
                	// Lanzar de nuevo a ServletOutputStream para obtener los m�todos println f�ciles.
                    ServletOutputStream sos = (ServletOutputStream) output;

                    // Copiar rango de varias partes.
                    for (Range r : ranges) {
                    	// Anadir frontera multipart y los campos de cabecera para cada rango.
                        sos.println();
                        sos.println("--" + MULTIPART_BOUNDARY);
                        sos.println("Content-Type: " + contentType);
                        sos.println("Content-Range: bytes " + r.start + "-" + r.end + "/" + r.total);

                        // Copiar gama �nica parte de la gama de varias partes.
                        copy(input, output, r.start, r.length);
                    }

                    // Fin con l�mite de varias partes.
                    sos.println();
                    sos.println("--" + MULTIPART_BOUNDARY + "--");
                }
            }
        } finally {
        	// Cierre suavemente los streams.
            close(output);
            close(input);
        }
    }

    // Asistentes (puede ser redisenado para clase de utilidad p�blica) -------------------------------------- -
   
    /**
     * Accepts.
     *
     * @param acceptHeader el accept header
     * @param toAccept el to accept
     * @return true, en caso de exito
     */
    private static boolean accepts(String acceptHeader, String toAccept) {
        String[] acceptValues = acceptHeader.split("\\s*(,|;)\\s*");
        Arrays.sort(acceptValues);
        return Arrays.binarySearch(acceptValues, toAccept) > -1
            || Arrays.binarySearch(acceptValues, toAccept.replaceAll("/.*$", "/*")) > -1
            || Arrays.binarySearch(acceptValues, "*/*") > -1;
    }

  
    /**
     * Matches.
     *
     * @param matchHeader el match header
     * @param toMatch el to match
     * @return true, en caso de exito
     */
    private static boolean matches(String matchHeader, String toMatch) {
        String[] matchValues = matchHeader.split("\\s*,\\s*");
        Arrays.sort(matchValues);
        return Arrays.binarySearch(matchValues, toMatch) > -1
            || Arrays.binarySearch(matchValues, "*") > -1;
    }

   
    /**
     * Sublong.
     *
     * @param value el value
     * @param beginIndex el begin index
     * @param endIndex el end index
     * @return the long
     */
    private static long sublong(String value, int beginIndex, int endIndex) {
        String substring = value.substring(beginIndex, endIndex);
        return (substring.length() > 0) ? Long.parseLong(substring) : -1;
    }

   
    /**
     * Copy.
     *
     * @param input el input
     * @param output el output
     * @param start el start
     * @param length el length
     * @throws IOException Se�ales de que una excepci�n de E / S se ha producido.
     */
    private static void copy(RandomAccessFile input, OutputStream output, long start, long length) throws IOException {
        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
        int read;

        if (input.length() == length) {
        	// Escribir gama completa.
            while ((read = input.read(buffer)) > 0) {
                output.write(buffer, 0, read);
            }
        } else {
        	// Escribir alcance parcial.
            input.seek(start);
            long toRead = length;

            while ((read = input.read(buffer)) > 0) {
                if ((toRead -= read) > 0) {
                    output.write(buffer, 0, read);
                } else {
                    output.write(buffer, 0, (int) toRead + read);
                    break;
                }
            }
        }
    }

  
    /**
     * Close.
     *
     * @param resource el resource
     */
    private static void close(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (IOException ignore) {
            	// Ignorar IOException. Si usted quiere manejar esto de todos modos, puede ser �til saber
            	// Que por lo general s�lo se produce cuando el cliente abort� la solicitud.
            }
        }
    }

    // Las clases internas ---------------------------------------------- --------------------------------
    
    /**
     * <ul>
     * <li>Copyright 2012 UNIVERSIDAD PRIVADA DE PUCALLPA - UPP. Todos los derechos reservados.</li>
     * </ul>
     * 
     * La Class Range.
     *
     * @author ndavilal and lpereirat.
     * @version 1.0 , 16/09/2012
     * @since SIAA 2.0
     */
    protected class Range {
        
        /** El start. */
       private long start;
        
        /** El end. */
       private long end;
        
        /** El length. */
       private long length;
        
        /** El total. */
       private long total;

        
        /**
         * Instancia un nuevo range.
         *
         * @param start el start
         * @param end el end
         * @param total el total
         */
        public Range(long start, long end, long total) {
            this.start = start;
            this.end = end;
            this.length = end - start + 1;
            this.total = total;
        }

    }

}