package bq.spring.bean;

import java.net.URI;
import java.util.Map;

import org.junit.Test;
import org.springframework.web.util.UriTemplate;

public class UriVaribleOperationTest {

	@Test
	public void test2(){
		String fromUri = "offer/skus/2000";
		String fromUriTemplate = "offer/skus/{skuid}";
		
		UriTemplate ut = new UriTemplate(fromUriTemplate);
		Map<String, String> variables = ut.match(fromUri);
		
		String toUriTemplate = "v1/offer/skus/{skuid}";
		UriTemplate utTo = new UriTemplate(toUriTemplate);
		URI toUri = utTo.expand(variables);
		
		System.out.println(toUri.toString());
	}
	
}
