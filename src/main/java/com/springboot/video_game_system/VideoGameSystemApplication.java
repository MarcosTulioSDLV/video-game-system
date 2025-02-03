package com.springboot.video_game_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VideoGameSystemApplication implements CommandLineRunner {

	private static final Logger LOG= LoggerFactory.getLogger(VideoGameSystemApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VideoGameSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOG.info("Hello World");
	}
	
}
