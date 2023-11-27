package Boundary;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import Entity.ProductDetail;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.net.http.HttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

public class ProductRepository {
    public List<ProductDetail> fetchData(String catalogNumber) {
        List<ProductDetail> productDetails =new ArrayList<>();
        try {
            System.out.println("catalogNumber : "+catalogNumber);
            var uri = URI.create("https://falconer2-71714182580c.herokuapp.com/get-products");
            String jsonData = "{\"category_id\":\""+catalogNumber+"\"}";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(uri)
                    .header("accept", "application/json")
                     .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                    .build();
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.statusCode());
//            System.out.println(response.body());
    
            if (response.statusCode() == 200){
                JSONArray jsonArray = new JSONArray(response.body().toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String productId = jsonObject.getString("product_id");
                    String productName = jsonObject.getString("product_name");
                    String productDescription = jsonObject.getString("product_description");
                    double quantity = jsonObject.getDouble("quantity");
                    double price = jsonObject.getDouble("price");
                    int category = jsonObject.getInt("category_id");

                    ProductDetail product = new ProductDetail(productId, productName,productDescription,quantity,price,category);
                    if (product.quantity > 0) {
                        productDetails.add(product);
                    }
                }
                return productDetails;
                
            }else {
                System.err.println("Error: Unable to fetch data");
                return productDetails;
    
            
            }}catch (Exception ex){
              System.out.println("error while fetching products : "+ex.getMessage());
            }
        return  productDetails;
    }
}