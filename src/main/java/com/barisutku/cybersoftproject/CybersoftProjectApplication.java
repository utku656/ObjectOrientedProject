package com.barisutku.cybersoftproject;

import com.barisutku.cybersoftproject.IOOperations.CreateOutput;
import com.barisutku.cybersoftproject.IOOperations.IOFileOperations;
import com.barisutku.cybersoftproject.IOOperations.InputParsing;
import com.barisutku.cybersoftproject.models.Hero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CybersoftProjectApplication implements CommandLineRunner{

	@Autowired
	private InputParsing parser;

	@Autowired
	private IOFileOperations operation;

	@Autowired
	private CreateOutput createOutput;

	public static void main(String[] args)  {

		SpringApplication.run(CybersoftProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		String inputStr = operation.readInput();
		Hero hero = parser.getHero(inputStr);
		hero.setOutput(createOutput);
		hero.start(parser.getRoute(inputStr));

	}
}
