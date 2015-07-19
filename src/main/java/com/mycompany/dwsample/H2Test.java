package com.mycompany.dwsample;
import java.sql.*;
public class H2Test {

	    public static void main(String[] a)
	            throws Exception {
	        Class.forName("org.h2.Driver");
	        Connection conn = DriverManager.
	            getConnection("jdbc:h2:~/test", "sa", "");
	        Statement stat = conn.createStatement();
	        String photoName="jetty";

	        // this line would initialize the database
	        // from the SQL script file 'init.sql'
	        // stat.execute("runscript from 'init.sql'");
	        //stat.execute("create table test(id int primary key, name varchar(255))");

	       // stat.execute("insert into test values(5, 'Test3')");
	        //conn.commit();
	        ResultSet rs;
	        rs = stat.executeQuery("select * from test");
	        //rs = stat.executeQuery("select * from test where name='"+photoName+"'");
	        
	        while (rs.next()) {
	            System.out.println(rs.getString("id"));
	            System.out.println(rs.getString("name"));
	        }
	        stat.close();
	        conn.close();
	    }
	}


