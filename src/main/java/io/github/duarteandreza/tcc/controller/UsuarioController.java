package io.github.duarteandreza.tcc.controller;


import io.github.duarteandreza.tcc.domain.Usuario;
import io.github.duarteandreza.tcc.request.AtualizarUsuarioRequest;
import io.github.duarteandreza.tcc.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario){
        return usuarioService.salvar(usuario);
    }

    @GetMapping
    public Page<Usuario> listarUsuarios(Usuario usuario, @PageableDefault(sort = "nome") @ApiIgnore Pageable pageable){
        return usuarioService.listar(usuario, pageable);
    }

    @GetMapping(path = "/{idUsuario}")
    public Usuario buscarUsuarioId(@PathVariable Long idUsuario){
        return usuarioService.buscarUsuarioId(idUsuario);
    }

    @GetMapping(path = "/cpf/{cpf}")
    public Usuario buscarUsuarioCPF(@PathVariable String cpf) {
        return usuarioService.buscarUsuarioCPF(cpf);
    }

    @PutMapping(path = "/{id}")
    public Usuario atualizarUsuario(@PathVariable Long id,@RequestBody @Valid AtualizarUsuarioRequest atualizarUsuarioRequest) throws Exception {
        return usuarioService.atualizarUsuario(id, atualizarUsuarioRequest);
    }
}