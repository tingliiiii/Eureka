package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private EurekaClient eurekaClient;
	
	@GetMapping("/down")
	public String down() {
		InstanceInfo info = eurekaClient.getApplicationInfoManager().getInfo();
		info.setStatus(InstanceInfo.InstanceStatus.DOWN);
		return "DOWN";
	}
	
	@GetMapping("/up")
	public String up() {
		InstanceInfo info = eurekaClient.getApplicationInfoManager().getInfo();
		info.setStatus(InstanceInfo.InstanceStatus.UP);
		return "UP";
	}
	
	
	
}
