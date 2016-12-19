package com.academicservice.query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Base64;
import java.security.*;



/**
 *
 * @author pgeor
 */
public class WebServiceClient  {
    
    private String username;
    private String password;
    private String WebApplicationServerURL;
    private String JSONobject;
    private static HttpURLConnection connectionURL;
    
    public WebServiceClient() {
      this.username="";
      this.password="";
      this.JSONobject="";
      this.WebApplicationServerURL="";
      WebServiceClient.connectionURL=null;
    }
    
    public WebServiceClient(String user,String pass,String Host) {
        this.username=user;
        this.password=pass;
        this.JSONobject="";
        this.WebApplicationServerURL=Host;
        WebServiceClient.connectionURL=null;
    }
    
    public WebServiceClient(String user,String pass,String Host,String jsondata) {
        this.username=user;
        this.password=pass;
        this.WebApplicationServerURL=Host;
        this.JSONobject=jsondata;
        WebServiceClient.connectionURL=null;
    }
    
    
    public void setUsername(String user) {
        this.username=user;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setPassword(String pass) {
        this.password=pass;
    }
    
    public String getPassword() {
        return this.password;
    }
    public void setWebAPPURLName(String Host) {
        this.WebApplicationServerURL=Host;
    }
    public String getWebAPPURLName() {
        return this.WebApplicationServerURL;
    }
    
    public void setJSONObject(String object) {
        this.JSONobject=object;
    }
    public String getJSONObject() {
        return this.JSONobject;
    }
    
    public static String createJSONbody(String data) {
        return "{\n" +"\"SubmissionCode\":"+data+"\n"+"}";
    }
    
  public static String clientConnectGetJson(String serverUrl,String host,String bodydata,String username,String password){

    StringBuilder sb = new StringBuilder();

    String http = serverUrl;

    //HttpURLConnection urlConnection = null;
    
    try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes("UTF-8"));
        byte[] HashedPasswordBytes = md.digest();
        StringBuffer stringBuffer = new StringBuffer();
        for (byte bytes : HashedPasswordBytes) {
           stringBuffer.append(String.format("%02x", bytes & 0xff));
        }
      //  String hashedPassword=md.digest(bytesOfPassword).toString();
        //JOptionPane.showMessageDialog(null,"" + stringBuffer.toString(),
//"WARNING MESSAGE",JOptionPane.WARNING_MESSAGE,null);
        String userpass=username+":"+stringBuffer.toString();
       // System.out.println(md.toString());
        String basicAuth = "Basic "+ new String(Base64.getEncoder().encode(userpass.getBytes()));
 //       JOptionPane.showMessageDialog(null,"" + basicAuth,
//"WARNING MESSAGE",JOptionPane.WARNING_MESSAGE,null);
        URL url = new URL(http);
        WebServiceClient.connectionURL = (HttpURLConnection) url.openConnection();
        WebServiceClient.connectionURL.setDoOutput(true);
        WebServiceClient.connectionURL.setRequestMethod("POST");
        WebServiceClient.connectionURL.setRequestProperty("Authorization",basicAuth);
        WebServiceClient.connectionURL.setUseCaches(false);
        WebServiceClient.connectionURL.setConnectTimeout(50000);
        WebServiceClient.connectionURL.setReadTimeout(50000);
        WebServiceClient.connectionURL.setRequestProperty("Content-Type", "application/json");
        WebServiceClient.connectionURL.setRequestProperty("Host", host);
        WebServiceClient.connectionURL.setRequestProperty("User-Agent","AcademicIDClient/v1.0 panosgeorgak@gmail.com");
        WebServiceClient.connectionURL.connect();
         
        OutputStreamWriter out = new OutputStreamWriter(WebServiceClient.connectionURL.getOutputStream());
        out.write(WebServiceClient.createJSONbody(bodydata));
        out.close();
        int HttpResult = WebServiceClient.connectionURL.getResponseCode();
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(
            		WebServiceClient.connectionURL.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
        //    Log.e("new Test", "" + sb.toString());
   //        JOptionPane.showMessageDialog(null,"1:"+sb.toString(),
//"WARNING MESSAGE",JOptionPane.WARNING_MESSAGE,null);
            return sb.toString();
        } else {
          //  Log.e(" ", "" + urlConnection.getResponseMessage());
        //  JOptionPane.showMessageDialog(null,"" + urlConnection.getResponseMessage(),
//"WARNING MESSAGE",JOptionPane.WARNING_MESSAGE,null);
          return "WebServiceClient connection error:"+WebServiceClient.connectionURL.getResponseMessage().toString();
        }
    } catch (MalformedURLException e) {
        e.printStackTrace();
        return "WebServiceClient MalformedURLException error:"+e.toString();
    } catch (IOException e) {
        e.printStackTrace();
        return "WebServiceClient IOException error:"+e.toString();
    } catch (Exception ex) {
     //  JOptionPane.showMessageDialog(null,"1:"+ex.toString(),
//"WARNING MESSAGE",JOptionPane.WARNING_MESSAGE,null);  
    	return "WebServiceClient Exception error:"+ex.toString();
    }
     finally {
        if (WebServiceClient.connectionURL != null)
        	WebServiceClient.connectionURL.disconnect();
    }
   // return null;
}
     
   
  
}
