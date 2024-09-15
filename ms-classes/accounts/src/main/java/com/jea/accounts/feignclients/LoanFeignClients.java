package com.jea.accounts.feignclients;

import java.util.List;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.jea.accounts.dto.LoansDetailDto;

@FeignClient(name = "LOANS")
@LoadBalancerClient("LOANS")
public interface LoanFeignClients {
	
	@GetMapping("/api/v1/loans/{mobileNumber}")
	public List<LoansDetailDto> getAllLoansByMobileNumber(@PathVariable String mobileNumber);
}
