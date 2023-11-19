package Boundary;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import model.ProductDetail;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.net.http.HttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProductFetcher {
    public List<ProductDetail> fetchdata() {
        List<ProductDetail> productDetails =new ArrayList<>();
        try {
            var uri = URI.create("http://localhost:5000/get-product-catalog");
            //String jsonData = "{\"username\":\""+userId+"\",\"password\":\""+password+"-\"}";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(uri)
                    .header("accept", "application/json")
                     .POST(HttpRequest.BodyPublishers.ofString("{}"))
                    .build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.statusCode());
//            System.out.println(response.body());
    
            if (response.statusCode() == 200){
                JSONArray jsonArray = new JSONArray(response.body().toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String productId = jsonObject.getString("id");
                    String productName = jsonObject.getString("name");
                    String productDescription = jsonObject.getString("product_description");
                    double quantity = jsonObject.getDouble("quantity");
                    double price = jsonObject.getDouble("price");
                    int category = jsonObject.getInt("category_id");

                    ProductDetail product = new ProductDetail(productId, productName,productDescription,quantity,price,category);
                    productDetails.add(product);
                }
                return productDetails;
                
            }else {
                System.err.println("Error: Unable to fetch data");
                return productDetails;
    
            
            }}catch (Exception ex){
              
            }
        return  productDetails;
    }
}