package com.zettamine.mi;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class MaterialInspectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaterialInspectionApplication.class, args);
	}

	
//	@EventListener(ApplicationReadyEvent.class)
	private void openBrowser()
	{
		
		try {
			Runtime.getRuntime().exec("rundll32 url.dll FileProtocolHandler http:localhost:8080/material/login");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
}
