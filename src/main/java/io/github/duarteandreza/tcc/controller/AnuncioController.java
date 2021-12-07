package io.github.duarteandreza.tcc.controller;


import io.github.duarteandreza.tcc.domain.Anuncio;
import io.github.duarteandreza.tcc.request.CadastrarAnuncioRequest;
import io.github.duarteandreza.tcc.service.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Anuncio salvar(@RequestBody @Valid CadastrarAnuncioRequest anuncio) {
        return anuncioService.salvar(anuncio);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Anuncio> listarAnuncios(@PageableDefault(sort = "valorDiaria") @ApiIgnore Pageable pageable){
        return anuncioService.listar(pageable);
    }

    @GetMapping(path = "/anunciantes/{idAnunciante}")
    public Page<Anuncio> listarAnuncioPorIdAnunciante(@PathVariable Long idAnunciante, @PageableDefault(sort = "valorDiaria") @ApiIgnore Pageable pageable){
        return anuncioService.listarAnuncioPorIdAnunciante(idAnunciante, pageable);
    }

    @DeleteMapping(path = "/{idAnuncio}")
    @ResponseStatus(HttpStatus.OK)
    public void deletarAnuncio(@PathVariable Long idAnuncio){
        anuncioService.deletarAnuncio(idAnuncio);
    }

}
