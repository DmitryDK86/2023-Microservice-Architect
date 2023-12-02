package ru.ddk.simplewebservice.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ddk.simplewebservice.configure.AppConfig;
import ru.ddk.simplewebservice.domain.LocalChanges;
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
public class HttpRestLocalStorageImpl implements HttpLocalChangesRest {

    private final AppConfig appConfig;
    private final String writeDataService;
    static final String urlPart = "/v1/lc";

    public HttpRestLocalStorageImpl(AppConfig appConfig) {
        this.appConfig = appConfig;
        writeDataService = appConfig.getWriteServiceUrl() + urlPart;
    }

    @Override
    public String deleteQuery(String userName) {
        return deleteQuery(writeDataService + "/delete", new LocalChanges(userName, false, false, "", "","","",""), "DELETE");
    }

    @Override
    public List<JsonObject> findAll() {
        return null;
    }

    @Override
    public JsonObject putSaveQuery(LocalChanges localChanges) {
        return saveQuery(writeDataService + "/update", localChanges, "PUT");
    }

    @Override
    public JsonObject postSaveQuery(LocalChanges localChanges) {
        return saveQuery(writeDataService + "/add", localChanges, "POST");
    }


    public JsonObject saveQuery(String url, LocalChanges localChanges, String method) {
        log.info("URL: " + url + " localChanges: " + localChanges);

        HttpURLConnection con = httpConnect(url, method, localChanges);
        try {
            byte[] bytes = IOUtils.toByteArray(con.getInputStream());
            String result = IOUtils.toString(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes))));
            return result.isEmpty() ? new JsonObject() : JsonParser.parseString(result)
                    .getAsJsonObject();
        } catch (IOException e) {
            return new JsonObject();
        }
    }

    public String deleteQuery(String url, LocalChanges localChanges, String method) {
        log.info("URL: " + url + " tran: " + localChanges);

        HttpURLConnection con = httpConnect(url, method, localChanges);
        try {
            byte[] bytes = IOUtils.toByteArray(con.getInputStream());
            return IOUtils.toString(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes))));
        } catch (IOException e) {
            return e.toString();
        }
    }

    private HttpURLConnection httpConnect(String urlString, String method, LocalChanges localChanges) {

        URL url = null;
        HttpURLConnection con = null;
        try {
            url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
            con.setDoInput(true);
            con.setDoOutput(true);
            if (method.equals("DELETE")) {
                paramDeleteQuery(con, localChanges.getUsername());
            } else {
                paramSaveQuery(con, localChanges);
            }

            con.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return con;
    }

    private void paramSaveQuery(HttpURLConnection con, LocalChanges localChanges) {
        DataOutputStream out = null;
        try {

            Map<String, String> parameters = new HashMap<>();
            parameters.put("userName", localChanges.getUsername());
            parameters.put("committed", localChanges.getCommitted().toString());
            parameters.put("aborted", localChanges.getAborted().toString());
            parameters.put("tranId", localChanges.getTranId());
            parameters.put("firstName", localChanges.getFirstName());
            parameters.put("lastName", localChanges.getLastName());
            parameters.put("email", localChanges.getEmail());
            parameters.put("phone", localChanges.getPhone());

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

    private void paramDeleteQuery(HttpURLConnection con, String tranId) {
        DataOutputStream out = null;
        try {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("tranId", tranId);
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
