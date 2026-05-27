package com.tecelagem.sistema.controller;

import com.tecelagem.sistema.model.Usuario;
import com.tecelagem.sistema.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

@PostMapping("/login")
public Map<String, Object> login(@RequestBody Usuario dadosLogin) {

    Map<String, Object> resposta = new HashMap<>();

    Optional<Usuario> usuarioEncontrado =
            usuarioRepository.findByUsuario(dadosLogin.getUsuario());

    if (usuarioEncontrado.isPresent()) {
        Usuario usuario = usuarioEncontrado.get();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (encoder.matches(dadosLogin.getSenha(), usuario.getSenha())) {
            resposta.put("ok", true);
            resposta.put("mensagem", "Login realizado com sucesso");
            resposta.put("usuario", usuario.getUsuario());
            return resposta;
        }
    }

    resposta.put("ok", false);
    resposta.put("mensagem", "Usuário ou senha inválidos");
    return resposta;
}
}