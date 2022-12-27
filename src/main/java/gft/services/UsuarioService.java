package gft.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import gft.entities.Usuario;
import gft.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	
	private final PasswordEncoder passwordEncoder;
	
	public Usuario salvarUsuario(Usuario usuario) {
		usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuario> listarTodosOsUsuarios() {
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarUsuario(Long id) {
		Optional<Usuario> optional = usuarioRepository.findById(id);
		
		return optional.orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
	}
}
