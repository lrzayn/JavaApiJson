import static org.junit.jupiter.api.Assertions.*;

import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class MainTest {

	@Test
	void testGetHTTPResponsePositive() throws Exception{
		HttpResponse<String> response = Main.getHTTPResponse("USA");
		int statusCode = response.statusCode();
        assertEquals(200, statusCode);
	}
	
	@Test
	void testGetHTTPResponseNegativeWrongCountry() throws Exception{
		HttpResponse<String> response = Main.getHTTPResponse("asdfgh");
		int statusCode = response.statusCode();
        assertEquals(404, statusCode);
	}
	@Test
	void testGetHTTPResponseNegativeNullCountry() throws Exception{
		HttpResponse<String> response = Main.getHTTPResponse(null);
		int statusCode = response.statusCode();
        assertEquals(404, statusCode);
	}
	@Test
	void testGetCapitalPositive() throws Exception{
		//get response
		HttpResponse<String> response = Main.getHTTPResponse("USA");
        //get capital city from API response
        String capital = Main.getCapital(response);
        assertEquals("Washington, D.C.", capital);
	}
	@Test
	void testGetCapitalNegativeNull() throws Exception{
		//get response
		HttpResponse<String> response = Main.getHTTPResponse(null);
        //get null capital city from API response
        String capital = Main.getCapital(response);
        assertNull(capital);
	}
}
