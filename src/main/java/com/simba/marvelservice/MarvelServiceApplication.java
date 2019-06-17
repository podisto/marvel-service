package com.simba.marvelservice;

import com.simba.marvelservice.service.GithubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MarvelServiceApplication {

	private final GithubService githubService;

	public MarvelServiceApplication(GithubService githubService) {
		this.githubService = githubService;
	}

	public static void main(String[] args) {
		SpringApplication.run(MarvelServiceApplication.class, args);
	}

}
