package ru.ddk.simplewebservice.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import ru.ddk.simplewebservice.configure.AppConfig;
import ru.ddk.simplewebservice.domain.User;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
public class HttpRestImpl implements HttpRest {

    private final AppConfig appConfig;
    private final String writeDataService;

    public HttpRestImpl(AppConfig appConfig) {
        this.appConfig = appConfig;
        writeDataService = appConfig.getWriteServiceUrl();
    }

    @Override
    public String deleteQuery(String userName) {
        return deleteQuery(writeDataService + "/v1/delete", new User(userName, "", "", "", ""), "DELETE");
    }

    @Override
    public List<JsonObject> findAll(User user) {
        return null;
    }

    @Override
    public JsonObject putSaveQuery(User user) {
        return saveQuery(writeDataService + "/v1/update", user, "PUT");
    }

    @Override
    public JsonObject postSaveQuery(User user) {
        return saveQuery(writeDataService + "/v1/add", user, "POST");
    }

    public JsonObject saveQuery(String url, User user, String method) {
        log.info("URL: " + url + " user: " + user);

        HttpURLConnection con = httpConnect(url, method, user);
        try {
            byte[] bytes = IOUtils.toByteArray(con.getInputStream());
            String result = IOUtils.toString(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes))));
            return result.isEmpty() ? new JsonObject() : JsonParser.parseString(result)
                    .getAsJsonObject();
        } catch (IOException e) {
            return new JsonObject();
        }
    }

    public String deleteQuery(String url, User user, String method) {
        log.info("URL: " + url + " user: " + user);

        HttpURLConnection con = httpConnect(url, method, user);
        try {
            byte[] bytes = IOUtils.toByteArray(con.getInputStream());
            return IOUtils.toString(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes))));
        } catch (IOException e) {
            return e.toString();
        }
    }

    private HttpURLConnection httpConnect(String urlString, String method, User user) {

        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
            con.setDoInput(true);
            con.setDoOutput(true);
            if (method.equals("DELETE")) {
                paramDeleteQuery(con, user.getUsername());
            } else {
                paramSaveQuery(con, user);
            }

            con.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return con;
    }

    private void paramSaveQuery(HttpURLConnection con, User user) {
        DataOutputStream out = null;
        try {

            Map<String, String> parameters = new HashMap<>();
            parameters.put("userName", user.getUsername());
            parameters.put("firstName", user.getFirstName());
            parameters.put("lastName", user.getLastName());
            parameters.put("email", user.getEmail());
            parameters.put("phone", user.getPhone());

            OutputStream os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getParamsString(parameters));
            writer.flush();
            writer.close();
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void paramDeleteQuery(HttpURLConnection con, String user) {
        DataOutputStream out = null;
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("userName", user);
            OutputStream os = con.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getParamsString(parameters));
            writer.flush();
            writer.close();
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getParamsString(Map<String, String> params)
            throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0
                ? resultString.substring(0, resultString.length() - 1)
                : resultString;
    }
}
