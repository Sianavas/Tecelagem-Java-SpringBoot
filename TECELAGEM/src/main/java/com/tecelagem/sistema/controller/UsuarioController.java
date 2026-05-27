package com.tecelagem.sistema.controller;

import com.tecelagem.sistema.model.Usuario;
import com.tecelagem.sistema.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    @GetMapping
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }
    @GetMapping("/total")
        public long totalUsuarios() {
    return usuarioRepository.count();
    }

    @PostMapping
    public Usuario cadastrar(@RequestBody Usuario usuario) {
        usuario.setSenha(
                encoder.encode(usuario.getSenha())
        );

        return usuarioRepository.save(usuario);
    }

    @PutMapping("/{id}")
    public Usuario alterar(
            @PathVariable Long id,
            @RequestBody Usuario usuario) {

        Usuario usuarioExistente =
                usuarioRepository.findById(id)
                        .orElseThrow();

        usuarioExistente.setUsuario(
                usuario.getUsuario()
        );

        if (usuario.getSenha() != null &&
                !usuario.getSenha().isBlank()) {

            usuarioExistente.setSenha(
                    encoder.encode(usuario.getSenha())
            );
        }

        return usuarioRepository.save(usuarioExistente);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        usuarioRepository.deleteById(id);
    }
}