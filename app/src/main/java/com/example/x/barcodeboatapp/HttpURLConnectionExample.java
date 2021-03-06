package com.example.x.barcodeboatapp;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
/**
 * Created by x on 04.04.2017.
 */

public class HttpURLConnectionExample {
    private final String USER_AGENT = "Mozilla/5.0";
    // HTTP POST request
        public void sendPost(String EAN, String artikelName) throws Exception {

            //String url = "https://api.outpan.com/v2/products/" + EAN + "/attribute?apikey=619f94150a9780d7ad905c10feffebca";
            String url = "https://api.outpan.com/v2/products/" + EAN + "/name?apikey=619f94150a9780d7ad905c10feffebca";
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

            String urlParameters = "name=name&value=" + artikelName;

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'POST' request to URL : " + url);
            System.out.println("Post parameters : " + urlParameters);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());

        }

    }

