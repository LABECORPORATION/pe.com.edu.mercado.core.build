package pe.com.edu.siaa.core.ejb.service.rest.impl;

import java.text.SimpleDateFormat;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;;

@Provider  
@Produces(MediaType.APPLICATION_JSON)  
public class JacksonConfig implements ContextResolver<ObjectMapper>  
{  
   protected final static String DATE_FORMAT_STR_ISO8601_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
   private ObjectMapper objectMapper;  
  
  
   public JacksonConfig() throws Exception  
   {  
      this.objectMapper = new ObjectMapper();//yyyy-MM-dd'T'HH:mm:ss'Z'//yyyy-MM-dd'T'HH:mm:ss.SSSX
      this.objectMapper.setDateFormat(new SimpleDateFormat(DATE_FORMAT_STR_ISO8601_Z));  
      this.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);  
   }  
  
  
   public ObjectMapper getContext(Class<?> objectType)  
   {  
      return objectMapper;  
   }  
}  