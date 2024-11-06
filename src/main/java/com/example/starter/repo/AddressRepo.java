package com.example.starter.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.starter.models.Address;

public interface AddressRepo extends CrudRepository<Address, Long>{

}
