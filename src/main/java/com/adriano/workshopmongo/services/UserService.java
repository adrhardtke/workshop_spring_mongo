package com.adriano.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adriano.workshopmongo.domain.User;
import com.adriano.workshopmongo.dto.UserDTO;
import com.adriano.workshopmongo.repository.UserRepository;
import com.adriano.workshopmongo.services.exception.ObjectNotFoundException;

import java.util.Optional;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	// Responsável por retornar todos os usuarios do banco de dados
	public List<User> findAll(){
		return repo.findAll();
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));
	}
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
}
