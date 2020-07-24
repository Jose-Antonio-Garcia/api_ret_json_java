/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author JoseA
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments+
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String solicitud_url = "http://127.0.0.1/calculadora/public/api/Aceleracion";
        curl api = new curl(solicitud_url, "POST");
        
        JSONObject aceleracion = new JSONObject();
        aceleracion.put("d", 300);
        aceleracion.put("t", 2);
        
        JSONObject obj = api.apicall(aceleracion);
        
        int tiempo = Integer.parseInt(obj.get("tiempo").toString());
        System.out.println(tiempo);
        System.out.println(obj.get("distancia"));
        System.out.println(obj.get("aceleracion"));

    }

}
