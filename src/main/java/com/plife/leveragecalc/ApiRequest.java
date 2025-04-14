package com.plife.leveragecalc;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;


public  class ApiRequest {
    public ArrayList<Item> apiReq(String symbol) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            String url = "https://api.bitget.com/api/v2/mix/market/symbol-price?productType=usdt-futures&symbol=" + symbol;
            HttpGet request = new HttpGet(url);

            CloseableHttpResponse response = httpClient.execute(request);

            // Get HttpResponse Status
            System.out.println(response.getProtocolVersion());              // HTTP/1.1
            System.out.println(response.getStatusLine().getStatusCode());   // 200
            System.out.println(response.getStatusLine().getReasonPhrase()); // OK
            System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

            try {

                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    JsonParser parser = new JsonParser();
                    JsonObject jsonObject = parser.parse(result).getAsJsonObject(); // Parse as JsonObject

                    JsonArray jsonArray = jsonObject.getAsJsonArray("data"); // Extract "data" array

                    //System.out.println(jsonArray);
                    ArrayList<Item> itemList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.size(); i++) {
                        JsonObject obj = jsonArray.get(i).getAsJsonObject();

                        // Create and add Item object
                        itemList.add(new Item(
                                obj.get("symbol").getAsString(),
                                obj.get("price").getAsString(),
                                obj.get("indexPrice").getAsString(),
                                obj.get("markPrice").getAsString(),
                                obj.get("ts").getAsString()
                        ));
                    }

                    return itemList; // Return the

                }

                return null;
            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
    }
}