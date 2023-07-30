import java.io.*;
import java.net.*;
import java.net.http.*;
import java.util.Scanner;

import org.json.*;

public class Main {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException, JSONException{
		
		//String testCountry = "Finland";
		//String testCountry = "Belarus";
		String testCountry = null;
		
		//program logic
		Scanner scEntry = new Scanner(System.in);
		while (true) {
			//ask customer to input the country name
            System.out.println("Enter the country name or enter 'exit' to stop the program, please.");
            testCountry = scEntry.nextLine();
            
            //exit from program
            if (testCountry.equalsIgnoreCase("exit")) {
                break;
            }
            //output for correct country
            else if (testCountry != null) {
            	try {
            		//get response from API
                    HttpResponse<String> response = getHTTPResponse(testCountry);
        		
                    //get capital city from API response
                    String capital = getCapital(response);
                    System.out.println("The capital city of the " + testCountry + " is: " + capital);
            	}
            	catch(Exception e) {
            		System.out.println("Wrong entry");
            	}
            	
            }            
        }		
	}
	
	 //method to get response from API
	static HttpResponse<String> getHTTPResponse(String country){
		
		HttpResponse<String> response = null;
		
		//declare url
        String url = "https://restcountries.com/v3.1/name/" + country;

        //build request
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        
        //send and return response -> body
        try {
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return response;
						
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		
	//method to get capital from response JSON
	static String getCapital(HttpResponse<String> response) {
			
			String capital = null;
			JSONArray jsonArray = null;
			
			//transform response -> JSON
			try {
				jsonArray = new JSONArray(response.body());
				JSONObject jsonObject = jsonArray.getJSONObject(0);
	            JSONArray capitalArray = jsonObject.getJSONArray("capital");
	            
	            //get capital city from response;
	            capital = capitalArray.getString(0);
	            return capital;
				
			} catch (JSONException e) {
				//output for wrong entry
				System.out.println("This country does not exist!");
				return null;
			}
		}
}
