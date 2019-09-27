/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arep.webService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import spark.Response;
import spark.Request;
import static spark.Spark.get;
import static spark.Spark.port;

/**
 *
 * @author 2112076
 */
public class WebSparck {

    public static void main(String[] args) {

        port(getPort());

        get("/inputdata", (req, res) -> inputDataPage(req, res));
        get("/results", (req, res) -> results(req, res));
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static String inputDataPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h2>cuadrado </h2>"
                + "<form action=\"/results\">"
                + "  * Ingrese un numero para saber su cuadrado: <br>"
                + "  <input type=\"text\" name='numero'>"
                + "  <br>"
                + "  <br><br>"
                + "<input type=\"submit\" value=\"Submit\">"
                + "<p> found \"\"\"/results\".</p>"
                + "</form>"
                + "</body>"
                + "</html>";
        return pageContent;
    }

    private static String results(Request req, Response res) throws MalformedURLException, IOException {
        String ur = "https://asrwzf3p08.execute-api.us-east-1.amazonaws.com/Beta?value=";
        URL url = new URL(ur + req.queryParams("numero"));
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        String content = "";
        String linea = br.readLine();
        while (null != linea) {
         content += linea;
         linea = br.readLine();
        }
        
        String htm;
        htm = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<br:>" + "Cuadrado:" +content + "<br:>"
                + "</body>"
                + "</html>";
        return htm;
    }

}
