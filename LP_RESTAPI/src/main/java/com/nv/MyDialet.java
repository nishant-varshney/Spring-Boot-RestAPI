package com.nv;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StringType;

public class MyDialet extends MySQL5Dialect {
	public MyDialet(){
		super();
		registerFunction("GROUP_CONCAT", new StandardSQLFunction("GROUP_CONCAT", new StringType()) );
	}

}
