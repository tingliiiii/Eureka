package com.example.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RateLimiterTest {

	@Test
	void test() throws Exception {

		int i;
		// 先發送 10 次正常請求
		for (i = 1; i <= 10; i++) {
			System.out.printf("發送第 %d 次 => ", i);
			String responseMessage = getEmployee();
			System.out.println("Response Message: " + responseMessage);
		}
		// 第 11 次
		System.err.printf("發送第 %d 次 => ", i);
		String responseMessage = getEmployee();
		System.err.println("Response Message: " + responseMessage);

		// 停 1 秒鐘
		Thread.sleep(10);
		System.out.println("停 1 秒鐘");
		Thread.sleep(1000);
		i++;

		// 第 12 次
		System.out.printf("發送第 %d 次 => ", i);
		responseMessage = getEmployee();
		System.out.println("Response Message: " + responseMessage);

	}

	private String getEmployee() throws IOException {

		URL url = new URL("http://localhost:6060/employee/ratelimiter/1");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		try (BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())))) {
			return br.lines().collect(Collectors.joining());
		}
	}

}
