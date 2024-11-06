package com.example.starter.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.starter.models.User;
import com.example.starter.projections.UserWithoutPassword;

public interface UserRepository extends CrudRepository<User, Long>{
	
List<UserWithoutPassword> findAllProjectedBy();
	
	Optional<UserWithoutPassword>findProjectedById(Long id);
	
	Optional<UserWithoutPassword>findByEmail(String email);
	
	List<UserWithoutPassword>findAnyByEmailContaining(String email);
	
	List<UserWithoutPassword> findByName(String name);
	
	List<UserWithoutPassword>findAnyByNameContaining(String name);
	
	List<UserWithoutPassword>findAnyByNameContainingOrEmailContaining(String name, String email);

}
