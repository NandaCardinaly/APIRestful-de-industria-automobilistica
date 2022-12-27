package gft.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gft.dto.usuario.ConsultaUsuarioDTO;
import gft.dto.usuario.RegistroUsuarioDTO;
import gft.dto.usuario.UsuarioMapper;
import gft.entities.Usuario;
import gft.services.UsuarioService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@PostMapping
	public ResponseEntity<ConsultaUsuarioDTO> salvarUsuario(@RequestBody RegistroUsuarioDTO dto) {
		Usuario usuario = usuarioService.salvarUsuario(UsuarioMapper.fromDTO(dto));
		return ResponseEntity.ok(UsuarioMapper.fromEntity(usuario));
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping
	public ResponseEntity<List<ConsultaUsuarioDTO>> buscarTodosOsUsuarios() {
		return ResponseEntity.ok(usuarioService.listarTodosOsUsuarios().stream().map(UsuarioMapper::fromEntity).collect(Collectors.toList()));
	}
	
	@PreAuthorize("hasAnyAuthority('PERFIL_ADMIN', 'PERFIL_USER')")
	@GetMapping("{id}")
	public ResponseEntity<ConsultaUsuarioDTO> buscarUsuario(@PathVariable Long id) {
		Usuario usuario = usuarioService.buscarUsuario(id);
		return ResponseEntity.ok(UsuarioMapper.fromEntity(usuario));
	}
}
