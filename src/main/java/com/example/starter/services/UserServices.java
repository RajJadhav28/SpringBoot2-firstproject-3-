package com.example.starter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.starter.models.Address;
import com.example.starter.models.Blog;
import com.example.starter.models.User;
import com.example.starter.projections.UserWithoutPassword;
import com.example.starter.repo.AddressRepo;
import com.example.starter.repo.BlogRepo;
import com.example.starter.repo.UserRepository;

@Service
public class UserServices {
	//HashMap<Long, User> users=new HashMap();//Not Needed
		//AtomicLong id=new AtomicLong();//Not Needed
		@Autowired
		UserRepository userRepository;
		
		@Autowired
		AddressRepo addressRepo;
		
		@Autowired
		BlogRepo blogRepo;
		
		 public List<UserWithoutPassword> getAll(){
			 //return this.users.values();
			 return this.userRepository.findAllProjectedBy();
		 }
		 public UserWithoutPassword getById(Long id) {
			// return this.users.get(id);
			 return this.userRepository.findProjectedById(id).orElseThrow(()->{
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with id not found");

			 });
		 }
		 public UserWithoutPassword getByEmail(String email) {
				// return this.users.get(id);
				 return this.userRepository.findByEmail(email).orElseThrow(()->{
						throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found");

				 });
			 }
		 public List<UserWithoutPassword> getAnyByEmail(String email){
			 
			 return this.userRepository.findAnyByEmailContaining(email);
		 }
		 
	 public List<UserWithoutPassword> getByName(String name){
			 
			 return this.userRepository.findAnyByNameContaining(name);
		 }
	 
	 public List<UserWithoutPassword> getByNameOrEmail(String name, String email){
		 
		 return this.userRepository.findAnyByNameContainingOrEmailContaining(name, email);
	 }
	 
		 public User create(User user) {
			return this.userRepository.save(user);
		 }
		 public User update(Long id,User user) {
			 this.userRepository.findById(id).orElseThrow(()->{
				 throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with specified id not found");
				 });
			 user.setId(id);
			 User userdata=this.userRepository.findById(id).orElseThrow(()->{
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with id not found");
			 });
			 user.setCreatedAt(userdata.getCreatedAt());
			 return this.userRepository.save(user);
			
		 }
		 public void delete(Long id) {
			 this.userRepository.findById(id).orElseThrow(()->{
				 throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with specified id"+id+" not found");
			 });
			 this.userRepository.deleteById(id);
		 }
		 
		 public User getUserWithPassword(Long id) {
			return this.userRepository.findById(id).orElseThrow(()->{
					throw new ResponseStatusException(HttpStatus.NOT_FOUND,"User with id not found");

			 });
		 }
		 public Address AddAddress(Long id, Address address) {
			 User user=this.getUserWithPassword(id);
			 Address saveAddress=this.addressRepo.save(address);
			 user.setAddress(saveAddress);
			 this.userRepository.save(user);
			 return saveAddress;
		 }
		 public Address getUserAddress(Long id) {
			 User user=this.getUserWithPassword(id);
			 return user.getAddress();
		 }
		public Blog AddBlog(Long id, Blog blog) {
			User user=this.getUserWithPassword(id);
			blog.setUser(user);
			this.blogRepo.save(blog);
			
			return blog;
		}
		public  List<Blog> getuserBlogs(Long id) {
			User user= this.getUserWithPassword(id);
			return user.getBlogs();
		}

}
