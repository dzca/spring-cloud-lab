package ca.ocbl.auth.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestHelper {

	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private RestTemplate template;

	public String post(String url, String data) {

		log.info("post: usr={}, data={}", url, data);
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType
				.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		HttpEntity request = new HttpEntity(data, headers);
		String result = template.postForObject(url, request, String.class);
		log.info("result:{}", result);
		return result;
	}

	public String get(String url) {
		log.info("******************************************************");
		log.info("getUrl:{}", url);
		String result = template.getForObject(url, String.class);
		log.info("result:{}", result);
		log.info("******************************************************");
		return result;
	}
}
