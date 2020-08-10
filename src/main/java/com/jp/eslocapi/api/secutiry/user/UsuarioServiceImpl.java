package com.jp.eslocapi.api.secutiry.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jp.eslocapi.api.dto.CredenciaisDto;
import com.jp.eslocapi.api.dto.UserDto;
import com.jp.eslocapi.api.entities.EnumPermissao;
import com.jp.eslocapi.api.entities.Persona;
import com.jp.eslocapi.api.exceptions.InvalidPasswordException;
import com.jp.eslocapi.api.repositories.ProdutorRepository;
import com.jp.eslocapi.api.secutiry.exceptions.UserNameNotFoundException;
import com.jp.eslocapi.exceptions.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UserDetailsService{
	
	@Autowired
	ProdutorRepository repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Persona usuario = repository.findByCpf(username)
					.orElseThrow(()-> new UserNameNotFoundException());
		return User.builder()
				.username(usuario.getCpf())
				.password(usuario.getPassword())
				.roles(usuario.getPermissao().toString())
				.build();
	}

	public Persona toPersona(UserDto dto) {
		EnumPermissao permissao = EnumPermissao.CONVIDADO;
		
		try {
			permissao = EnumPermissao.valueOf(dto.getRole());
			
		}catch(IllegalArgumentException ex) {
			
		}
		return Persona.builder()
				.nome(dto.getNome())
				.cpf(dto.getLogin())
				.password(dto.getPassword())
				.permissao(permissao)
				.build();
	}

	public UserDto toUserDto(Persona user) {
		return UserDto.builder()
				.nome(user.getNome())
				.login(user.getCpf())
				.password(user.getPassword())
				.role(user.getPermissao().toString())
				.build();
	}
	@Transactional
	public Persona save(Persona toSaved) {
		String senhaCriptografada = passwordEncoder.encode(toSaved.getPassword());
		toSaved.setPassword(senhaCriptografada);
		if(this.repository.existsByCpf(toSaved.getCpf())) {
			throw new BusinessException("Já existe um registro com este cpf.");
		}		
		return this.repository.save(toSaved);
	}

	public UserDetails autenticar(CredenciaisDto usuario) {
		UserDetails user = this.loadUserByUsername(usuario.getLogin());
		boolean senhaEIgual = passwordEncoder.matches(usuario.getSenha(), user.getPassword());
		if(senhaEIgual) {
			return user;
		}
		throw new InvalidPasswordException();
		
	}

}

