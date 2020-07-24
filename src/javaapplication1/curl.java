/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author JoseA
 */
public class curl {

    public String url;
    public String verbo;

    public curl(String _url, String _verbo) {
        url = _url;
        verbo = _verbo;
    }

    public JSONObject apicall(JSONObject _body) {
        String output = new String();
        JSONObject obj = new JSONObject();
        try {
            URL url = new URL(this.url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod(this.verbo);
            conn.setRequestProperty("Content-Type", "application/json");
            StringWriter out = new StringWriter();
            _body.writeJSONString(out);
            String jsonText = out.toString();

            OutputStream os = conn.getOutputStream();
            os.write(jsonText.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode() + "CODE: ");
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            String salida;
            while ((salida = br.readLine()) != null) {
                output = salida;
            }
            conn.disconnect();
            JSONParser parser = new JSONParser();
            obj = (JSONObject) parser.parse(output);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException pe) {
            pe.printStackTrace();
        }

        return obj;
    }
}
