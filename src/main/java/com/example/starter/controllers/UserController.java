package com.example.starter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.starter.helpers.ResponseWrapper;
import com.example.starter.models.Address;
import com.example.starter.models.Blog;
import com.example.starter.models.User;
import com.example.starter.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
		
		@Autowired
		UserServices userServices;
		
		@GetMapping("")
		public ResponseEntity getallUsers(){
			ResponseWrapper responseWrapper=new ResponseWrapper();
			responseWrapper.setMessage("All users.");
			responseWrapper.setData(this.userServices.getAll());
			return new ResponseEntity(responseWrapper,HttpStatus.OK);
			
		}
		@GetMapping("{id}")
		public ResponseEntity getUserById(@PathVariable("id")Long id) {
			
			
			ResponseWrapper responseWrapper=new ResponseWrapper();
			responseWrapper.setMessage("users.");
			responseWrapper.setData(this.userServices.getById(id));
			return new ResponseEntity(responseWrapper,HttpStatus.OK);
		}
		@GetMapping("/find-by")
		public ResponseEntity getUserByEmail(@RequestParam String email) {
			ResponseWrapper responseWrapper=new ResponseWrapper();
			responseWrapper.setMessage("users.");
			responseWrapper.setData(this.userServices.getByEmail(email));
			return new ResponseEntity(responseWrapper,HttpStatus.OK);
		}
		@GetMapping("/find-by-Any")
		public ResponseEntity getAnyUserByEmail(@RequestParam String email) {
			ResponseWrapper responseWrapper=new ResponseWrapper();
			responseWrapper.setMessage("users.");
			responseWrapper.setData(this.userServices.getAnyByEmail(email));
			return new ResponseEntity(responseWrapper,HttpStatus.OK);
		}
		@GetMapping("/find-by-Any-Name")
		public ResponseEntity getAnyUserByName(@RequestParam String name) {
			ResponseWrapper responseWrapper=new ResponseWrapper();
			responseWrapper.setMessage("users.");
			responseWrapper.setData(this.userServices.getByName(name));
			return new ResponseEntity(responseWrapper,HttpStatus.OK);
		}
		@GetMapping("/keyword")
		public ResponseEntity getAnyUserByNameorEmail(@RequestParam(required=false) String name, @RequestParam(required=false) String email) {
			ResponseWrapper responseWrapper=new ResponseWrapper();
			responseWrapper.setMessage("users.");
			responseWrapper.setData(this.userServices.getByNameOrEmail(name,email));
			return new ResponseEntity(responseWrapper,HttpStatus.OK);
		}
		
		
		@PostMapping("")
		public ResponseEntity createUser(@RequestBody @Valid User user) {
			User userdata=this.userServices.create(user);
			ResponseWrapper responseWrapper=new ResponseWrapper();
			responseWrapper.setMessage("User created Successfully");
			responseWrapper.setData(userdata);
			return new ResponseEntity(responseWrapper,HttpStatus.CREATED);
			
		}
		@PutMapping("{id}")
		public ResponseEntity updateUser(@PathVariable("id") Long id, @RequestBody User user) {
			
			User userdata=this.userServices.update(id, user);
			ResponseWrapper responseWrapper=new ResponseWrapper();
			responseWrapper.setMessage("User Updated successfully");
			responseWrapper.setData(userdata);
			return new ResponseEntity(responseWrapper,HttpStatus.OK);
		}
		@DeleteMapping("{id}")
		public ResponseEntity deleteUser(@PathVariable("id") Long id) {
			
			ResponseWrapper responseWrapper=new ResponseWrapper();
			responseWrapper.setMessage("User deleted successfully");
			this.userServices.delete(id);
			return new ResponseEntity(responseWrapper,HttpStatus.OK);

		}
		
		@PostMapping("{id}/address")
		public ResponseEntity addAddress(@PathVariable("id") Long id, @RequestBody Address address) {
			
			ResponseWrapper responseWrapper=new ResponseWrapper();
			responseWrapper.setMessage("Address created successfully");
			responseWrapper.setData(this.userServices.AddAddress(id, address));
			return new ResponseEntity(responseWrapper,HttpStatus.CREATED);

		}
		@GetMapping("{id}/address")
		public ResponseEntity addAddress(@PathVariable("id") Long id) {
			
			ResponseWrapper responseWrapper=new ResponseWrapper();
			responseWrapper.setMessage("User Address");
			responseWrapper.setData(this.userServices.getUserAddress(id));
			return new ResponseEntity(responseWrapper,HttpStatus.CREATED);

		}
		
		@PostMapping("{id}/blogs")
		public ResponseEntity addblog(@PathVariable("id") Long id, @RequestBody Blog blog) {
			
			ResponseWrapper responseWrapper=new ResponseWrapper();
			responseWrapper.setMessage("Blog created successfully");
			responseWrapper.setData(this.userServices.AddBlog(id, blog));
			return new ResponseEntity(responseWrapper,HttpStatus.CREATED);

		}
		@GetMapping("{id}/blogs")
		public ResponseEntity addblogs(@PathVariable("id") Long id) {
			
			ResponseWrapper responseWrapper=new ResponseWrapper();
			responseWrapper.setMessage("All Blogs");
			responseWrapper.setData(this.userServices.getuserBlogs(id));
			return new ResponseEntity(responseWrapper,HttpStatus.CREATED);

		}
}
