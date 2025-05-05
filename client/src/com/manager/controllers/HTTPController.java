package com.manager.controllers;

import com.manager.models.Session;
import com.manager.models.AuthenticationResponse;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.InputStream;
import java.util.Scanner;


public class HTTPController {

    private static final String baseURL = "http://192.168.1.89:7500/api/v1"; // incluir .env


    public AuthenticationResponse signIn(String username, String password) {
        try {
            URL url = new URL(baseURL + "/authentication/sign-in");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            // Armar el body JSON
            String jsonBody = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", username, password);

            // Enviar el body
            try (OutputStream os = con.getOutputStream()) {
                os.write(jsonBody.getBytes());
                os.flush();
            }

            // Verificar estado HTTP
            int httpStatusCode = con.getResponseCode();
            if (httpStatusCode != 200) {
                return new AuthenticationResponse(null, httpStatusCode);
            }

            //  Leer la respuesta
            InputStream responseStream = con.getInputStream();
            Scanner scanner = new Scanner(responseStream).useDelimiter("\\A");
            String response = scanner.hasNext() ? scanner.next() : "";


            //  Extraer token simple sin librería JSON (brígido pero funcional)
            String accessToken = response.split("\"access_token\":\"")[1].split("\"")[0];

            return new AuthenticationResponse(accessToken, httpStatusCode);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static void verifySession(String accessToken) {
        try {
            URL url = new URL(baseURL + "/authentication/verify/session");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            // Authorization: String

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setAccessToken(String accessToken) {
        Session.setAccessToken(accessToken);
    }

}
