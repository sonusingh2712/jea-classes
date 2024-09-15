package com.jea.accounts.feignclients;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jea.accounts.dto.CardsDetailDto;

@FeignClient(name = "CARDS")
@LoadBalancerClient("CARDS")
public interface CardsFeignClients {
	
	@GetMapping("/api/v1/cards/{mobileNumber}")
	public CardsDetailDto getCardsInfoByMobileNumber(@PathVariable String mobileNumber);
}
