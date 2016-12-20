package gr.gov.minedu.osteam.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Base64;
import java.security.*;

public class WebServiceClient  {

  public static String clientConnectGetJson() {
    StringBuilder sb = new StringBuilder();

    try {
        String resourceEndpoint = "https://wso2.local.dev:8243/identity/1.0.0/details";
        String JSONobject = "{ \"id\": \"0123\", \"fields\": \"id,fullname,organisation\" }";
        String auth = "Bearer " + "<token>";

        URL url = new URL(resourceEndpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", auth);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("User-Agent", "WSO2 sample java caller");
        connection.setUseCaches(false);
        connection.setConnectTimeout(50000);
        connection.setReadTimeout(50000);
        connection.connect();

        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
        out.write(JSONobject); // send the payload
        out.close();

        int HttpResult = connection.getResponseCode();
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            return sb.toString();
        } else {
          return "Client connection error:" + connection.getResponseMessage().toString();
        }
    } catch (MalformedURLException e) {
        e.printStackTrace();
        return "WebServiceClient MalformedURLException error:"+e.toString();
    } catch (IOException e) {
        e.printStackTrace();
        return "WebServiceClient IOException error:"+e.toString();
    } catch (Exception ex) {
        return "WebServiceClient Exception error:"+ex.toString();
    } finally {
        if (WebServiceClient.connectionURL != null) {
            WebServiceClient.connectionURL.disconnect();
        }
    }
    return null;
  }
}

