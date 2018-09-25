package com.example.bauwensn.appollutiontest.models.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connection {

    private String requestResult = null;
    private HttpURLConnection connection = null;

    public String connection(String requestURL) {

        try {
            URL url = new URL(requestURL);

            // region --Connexion à l'API
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }
            //endregion

            //region -- Lecture de l'input de l'API et création du String à renvoyer
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);

            StringBuilder data = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
            reader.close();
            streamReader.close();

            requestResult = data.toString();

            //endregion

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return requestResult;
    }
}
