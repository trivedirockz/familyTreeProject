package com.ft.familyTree.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ft.familyTree.dto.HelloWorldDTO;

@RestController
@RequestMapping("/api/v1")
public class HelloWorldController {

	@GetMapping(path="/hello-family")
	public String helloWorld() {
		return "Welcome to Family Tree Project";
	}
	
	@GetMapping(path="/hello-family-dto")
	public HelloWorldDTO helloWorldDTO() {
		return new HelloWorldDTO("Welcome to Family Tree Project. I am from HelloWorldDTO.");
	}
	
	@GetMapping(path="/hello-family-dto/pathvariable/{name}")
	public HelloWorldDTO helloWorldDTOVariable(@PathVariable(name="name") String pName) {
		return new HelloWorldDTO("Welcome to Family Tree Project Mr."+pName+". I am from HelloWorldDTO.");
	}
	
}
