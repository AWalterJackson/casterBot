package Utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public final class HttpsHandler {
	private static final String USER_AGENT = "CasterBot/0.1 (By Nantangitan on e621)";
	private static final boolean debug = false;

	public static HTTPResponse httpget(String url) {
	    try {
            // Open connection
            URL resourcelocator = new URL(url);
            HttpURLConnection con = (HttpURLConnection) resourcelocator.openConnection();

            // Set Request Type
            con.setRequestMethod("GET");

            // Set Header Details
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responsecode = con.getResponseCode();
            if (debug) {
                System.out.println("GET sent to: " + url);
                System.out.println("Response code: " + responsecode);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputline;
            StringBuffer response = new StringBuffer();

            while ((inputline = in.readLine()) != null) {
                response.append(inputline);
            }
            in.close();

            return new HTTPResponse(responsecode, HTTPResponse.Protocol.HTTP, response.toString());
        } catch (MalformedURLException e) {
	        return new HTTPResponse(600, HTTPResponse.Protocol.HTTP, "Malformed URL");
        } catch (IOException e) {
            return new HTTPResponse(601, HTTPResponse.Protocol.HTTP, e.toString());
        }
	}

	public static HTTPResponse httpsget(String url) {
	    try {
            // Open connection
            URL resourcelocator = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) resourcelocator.openConnection();

            // Set Request Type
            con.setRequestMethod("GET");

            // Set Header Details
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responsecode = con.getResponseCode();
            if (debug) {
                System.out.println("GET sent to: " + url);
                System.out.println("Response code: " + responsecode);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputline;
            StringBuffer response = new StringBuffer();

            while ((inputline = in.readLine()) != null) {
                response.append(inputline);
            }
            in.close();

            return new HTTPResponse(responsecode, HTTPResponse.Protocol.HTTPS, response.toString());
        } catch (MalformedURLException e){
            return new HTTPResponse(600, HTTPResponse.Protocol.HTTPS, "Malformed URL");
        } catch (IOException e) {
	        e.printStackTrace();
	        return new HTTPResponse(601, HTTPResponse.Protocol.HTTPS, e.toString());
        }
	}

	public static HTTPResponse httpspost(String url, String type, String data) {
	    try {
            // Open Connection
            URL resourcelocator = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) resourcelocator.openConnection();

            // Set request type
            con.setRequestMethod("POST");

            // Set Header Details
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Content-type", type);

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(data);
            wr.flush();
            wr.close();

            int responsecode = con.getResponseCode();
            if (debug) {
                System.out.println(responsecode);
            }
            // Debugdata
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputline;
            StringBuffer response = new StringBuffer();

            while ((inputline = in.readLine()) != null) {
                response.append(inputline);
            }
            in.close();
            if (debug) {
                System.out.println(response.toString());
            }
            return new HTTPResponse(responsecode, HTTPResponse.Protocol.HTTPS, response.toString());
        } catch (MalformedURLException e){
            return new HTTPResponse(600, HTTPResponse.Protocol.HTTPS, "Malformed URL");
        } catch (IOException e) {
            return new HTTPResponse(601, HTTPResponse.Protocol.HTTPS, e.toString());
        }
	}
	
	public static HTTPResponse httpsgetcookie(String url, String cookiedata) {
	    try {
            // Open connection
            URL resourcelocator = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) resourcelocator.openConnection();

            // Set Request Type
            con.setRequestMethod("GET");

            // Set Header Details
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Cookie", cookiedata);

            int responsecode = con.getResponseCode();
            if (debug) {
                System.out.println("GET sent to: " + url);
                System.out.println("Response code: " + responsecode);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputline;
            StringBuffer response = new StringBuffer();

            while ((inputline = in.readLine()) != null) {
                response.append(inputline);
            }
            in.close();

            return new HTTPResponse(responsecode, HTTPResponse.Protocol.HTTPS, response.toString());
        } catch (MalformedURLException e) {
            return new HTTPResponse(600, HTTPResponse.Protocol.HTTPS, "Malformed URL");
        } catch (IOException e) {
            return new HTTPResponse(601, HTTPResponse.Protocol.HTTPS, e.toString());
        }
	}
}
