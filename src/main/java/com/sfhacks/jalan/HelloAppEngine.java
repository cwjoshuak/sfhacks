package com.sfhacks.jalan;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import com.google.maps.*;
import com.google.maps.GeoApiContext.Builder;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;



@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
	 
	    
	    String parameterLocation = request.getParameter("city");
	    
	    //Initialize GeoApiContext.Builder
	    GeoApiContext.Builder builder = new GeoApiContext.Builder().apiKey("AIzaSyCzHGKHl75gHvXEtXiGckmonfP0-00Iwow").queryRateLimit(500); //new Builder();
	    //builder.apiKey("AIzaSyCzHGKHl75gHvXEtXiGckmonfP0-00Iwow");
	    GeoApiContext gac = builder.build();
	   
	    //Iniatilize TextSearchRequest
	    TextSearchRequest tsr = new TextSearchRequest(gac);
	    System.out.println(parameterLocation);
	    TextSearchRequest toOutput = tsr.query("hotels+in+" + parameterLocation);
	    try
	    {
    	    PlacesSearchResponse resp = toOutput.await();
    	    PlacesSearchResult[] result = resp.results;
    	    //System.out.println(result);
    	    //JSONObject output = new JSONObject(result); //If doesn't work, this line probably causes the error
    	    //String message = "";
    	      request.setAttribute("hotel1", result[0].name); // This will be available as ${message}
    	      request.setAttribute("hotel2", result[1].name);
    	      request.setAttribute("hotel3", result[2].name);
    	      
    	      request.setAttribute("description1", result[0].formattedAddress); // This will be available as ${message}
    	      request.setAttribute("description2", result[1].formattedAddress);
    	      request.setAttribute("description3", result[2].formattedAddress);
    	      
    	      request.getRequestDispatcher("/WEB-INF/output.jsp").forward(request, response);
	    }
	    catch(InterruptedException | ApiException | ServletException e)
	    {
	    	
	    }
	  
	  
    //response.setContentType("text/plain");
    //response.setCharacterEncoding("UTF-8");
    //response.getWriter().print("Hello App Engine!\r\n");

  }
}