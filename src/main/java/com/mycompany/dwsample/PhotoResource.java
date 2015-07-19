package com.mycompany.dwsample;

import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.WeakHashMap;

@Path("/Photo")
@Produces(MediaType.APPLICATION_JSON)
public class PhotoResource{
    private final String name;
    private String photoName;
    private int id;

    public PhotoResource(String nameInput) {
        this.name = nameInput;
       }

    @GET
    public Response getPhotoName(@QueryParam("photoName") String photoName) throws Exception {

    	 Class.forName("org.h2.Driver");
	        Connection conn = DriverManager.
	            getConnection("jdbc:h2:~/test", "sa", "");
	        Statement stat = conn.createStatement();

	        // this line would initialize the database
	        // from the SQL script file 'init.sql'
	        // stat.execute("runscript from 'init.sql'");
	        //stat.execute("create table test(id int primary key, name varchar(255))");
	        ResultSet rs;
	        rs = stat.executeQuery("select * from test where name='"+photoName+"'");
	        
	        while (rs.next()) {
	            photoName = rs.getString("name");
	            id = rs.getInt("id");
	        }
	        stat.close();
	        conn.close();

    	Map<String, Object> outObjectMap = new WeakHashMap<>();
    	
    	outObjectMap.put("id", id);
    	outObjectMap.put("name", name);
    	outObjectMap.put("photoName", photoName);
        JSONObject json = new JSONObject(outObjectMap);
		String orderListJson = json.toString();
		return Response.ok().entity(orderListJson).build();
    }
    
    @POST
    public void putPhotoName(@QueryParam("photoName") String photoName) throws Exception{
    	 Class.forName("org.h2.Driver");
	        Connection conn = DriverManager.
	            getConnection("jdbc:h2:~/test", "sa", "");
	        Statement stat = conn.createStatement();

	        // this line would initialize the database
	        // from the SQL script file 'init.sql'
	        // stat.execute("runscript from 'init.sql'");
	        //stat.execute("create table test(id int primary key, name varchar(255))");
	        int pkey = new java.util.Random().nextInt();
	        stat.execute("insert into test values("+pkey+", '"+photoName+"')");
	        conn.commit();
	        ResultSet rs;
	        rs = stat.executeQuery("select * from test");
	        
	        while (rs.next()) {
	            System.out.println(rs.getString("name"));
	        }
	        stat.close();
	        conn.close();
		
    }
}