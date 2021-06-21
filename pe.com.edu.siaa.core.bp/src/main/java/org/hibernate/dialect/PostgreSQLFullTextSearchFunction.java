package org.hibernate.dialect;
 
import java.util.List;

import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction; 
import org.hibernate.type.BooleanType;
import org.hibernate.type.Type;
  
/** 
 * @author lysming 
 * 
 */  
public class PostgreSQLFullTextSearchFunction implements SQLFunction  {  
	 @SuppressWarnings("unchecked")
	   public String render(Type firstArgumentType,List args, org.hibernate.engine.spi.SessionFactoryImplementor factory) {
	      if (args.size() != 4) {
	         throw new IllegalArgumentException(
	               "The function must be passed 3 arguments");
	      }

	      String ftsConfig = (String) args.get(0);
	      String field = (String) args.get(1);
	      String field2 = (String) args.get(2);
	      //String field3 = (String) args.get(3);
	      String value = (String) args.get(3);  
 
	      String fragment = null;
	      if (ftsConfig == null) {
	         fragment = "to_tsvector(" + field + ") || to_tsvector(" + field2 + ") @@ " + "to_tsquery('"+ value + "')";
	      } else  {
	         fragment = "to_tsvector(" + ftsConfig + "::regconfig, " + field + ") || to_tsvector(" + ftsConfig + "::regconfig, " + field2 + ") @@ "
	      + "to_tsquery(" + ftsConfig + ", " + value +")";
	      }
	      return fragment;

	   }

	   @Override
	   public Type getReturnType(Type columnType, org.hibernate.engine.spi.Mapping mapping)
	         throws QueryException {
	      return new BooleanType();
	   }

	   @Override
	   public boolean hasArguments() {
	      return true;
	   }

	   @Override
	   public boolean hasParenthesesIfNoArguments() {
	      return false;
	   }
 
 
}  
