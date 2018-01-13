package ca.ocbl.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "hi-service")
public interface HiService {

	@RequestMapping("/hi") 
	public String hi();

}
