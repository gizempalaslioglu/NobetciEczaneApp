package com.gizempalaslioglu.spinnerlrnekjson;
import android.os.AsyncTask;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;
import org.json.JSONObject;
class JSONTaker extends AsyncTask<String, Void,Integer> {

    public String getListData(String il,String ilce) throws UnirestException {
        String json_stu1;
        HttpClient httpClient = HttpClients.custom()
                .disableCookieManagement()
                .build();
        String response  = "https://api.collectapi.com/health/dutyPharmacy?ilce="+ilce+"&il="+il;
        Unirest.setHttpClient(httpClient);
        String finalresponse = Unirest.get(response)
                .header("content-type", "application/json")
                .header("authorization", "apikey 1MzRQXFQCDjE11KdkTQlCb:037ACxo7Z8RgIaoSqGkow6")
                .asString()
                .getBody();
        return finalresponse;
    }
    @Override
    protected Integer doInBackground(String... strings) {
        return null;
    }
}