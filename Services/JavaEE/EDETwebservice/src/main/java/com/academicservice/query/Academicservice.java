package com.academicservice.query;

import javax.ws.rs.*;

/**
*
* @author pgeor
*/

@Path("/academicID")
public class Academicservice {
	
	@GET
    @Consumes("text/plain")
    @Produces("text/plain")
	@Path("/testServiceStatus/{id}")
	public String testServiceStatus1(@PathParam("id") String id) {
	     
		 id=id.trim();
		 String msg="StudentID sent was:"+id;
		// return Response.ok(msg).build();
        return msg;
   }
	
	@GET
    @Consumes("text/plain")
    @Produces("text/plain")
	@Path("/testServiceStatus")
	public String testServiceStatus2(@QueryParam("id") String id) {
	     
		 id=id.trim();
		 String msg="StudentID sent was:"+id;
		// return Response.ok(msg).build();
        return msg;
   }
	
	@GET
    @Consumes("text/plain")
    @Produces("text/json")
    @Path("/queryID")
    public String queryStudentID(@QueryParam("id") String id,
    		@QueryParam("username") String username,
    		@QueryParam("password") String password) {
     
		 id = id.trim();
		 username=username.trim();
		 password=password.trim();
		 
		 WebServiceClient edetConnect=new WebServiceClient(username,password,"https://academicidapp.grnet.gr/admin/web/ws/users/inspectAcademicID");
     
		 return WebServiceClient.clientConnectGetJson(edetConnect.getWebAPPURLName(),"wso2.minedu.gov.gr",id,edetConnect.getUsername(),edetConnect.getPassword());
     
    }
	
	
	@GET
    @Consumes("text/plain")
    @Produces("text/plain")
    @Path("/queryIDnoCD")
    public String queryStudentIDnocd(@QueryParam("id") String id) {
     
		 id = id.trim();
		 String JSONresponse="";
		 WebServiceClient edetConnect=new WebServiceClient("","","https://academicidapp.grnet.gr/admin/web/ws/users/inspectAcademicID");
		 JSONresponse=WebServiceClient.clientConnectGetJson(edetConnect.getWebAPPURLName(),"wso2.minedu.gov.gr",id,edetConnect.getUsername(),edetConnect.getPassword());
     
		 if (!JSONresponse.equals("")) {
		 String[] filter1=JSONresponse.split(",");
	        String[] filter2=null;
	        for (String message : filter1) {
	           if (message.contains("webServiceSuccess")) {
	               filter2=message.split(":");
	               break;
	           }
	        }
	       if (filter2 != null)
	    	return "isStudent:"+filter2[1];
	       else
	       return JSONresponse;
		 }
		 else return "EDET Web Service Unreacheable"; 
     
    }
	
	@POST
    @Consumes("text/plain")
    @Produces("text/json")
    @Path("/queryID")
    public String queryStudentID1(@QueryParam("id") String id,
    		@QueryParam("username") String username,
    		@QueryParam("password") String password) {
     
		 id = id.trim();
		 username=username.trim();
		 password=password.trim();
		 
		 WebServiceClient edetConnect=new WebServiceClient(username,password,"https://academicidapp.grnet.gr/admin/web/ws/users/inspectAcademicID");
     
		 return WebServiceClient.clientConnectGetJson(edetConnect.getWebAPPURLName(),"wso2.minedu.gov.gr",id,edetConnect.getUsername(),edetConnect.getPassword());
     
    }
	
	
	@POST
    @Consumes("text/plain")
    @Produces("text/plain")
    @Path("/queryID/noCD")
    public String queryStudentIDnocd1(@QueryParam("id") String id) {
     
		 id = id.trim();
		 String JSONresponse="";
		 WebServiceClient edetConnect=new WebServiceClient("kasvestas","boldtrick666","https://academicidapp.grnet.gr/admin/web/ws/users/inspectAcademicID");
		 JSONresponse=WebServiceClient.clientConnectGetJson(edetConnect.getWebAPPURLName(),"wso2.minedu.gov.gr",id,edetConnect.getUsername(),edetConnect.getPassword());
     
		 if (!JSONresponse.equals("")) {
		 String[] filter1=JSONresponse.split(",");
	        String[] filter2=null;
	        for (String message : filter1) {
	           if (message.contains("webServiceSuccess")) {
	               filter2=message.split(":");
	               break;
	           }
	        }
	       if (filter2 != null)
	    	return "isStudent:"+filter2[1];
	       else
	       return JSONresponse;
		 }
		 else return "EDET Web Service Unreacheable"; 
     
    }
   	
	


}
