package com.sip.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.sip.ams.controllers.ArticleController;
import com.sip.ams.controllers.ProviderController;

import java.io.File;

@SpringBootApplication
public class Ams1Application {

	public static void main(String[] args) {
		new File(ArticleController.uploadDirectory).mkdir();
		SpringApplication.run(Ams1Application.class, args);
	}

}
