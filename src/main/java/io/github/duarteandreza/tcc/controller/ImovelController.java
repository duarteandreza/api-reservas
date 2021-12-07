package io.github.duarteandreza.tcc.controller;


import io.github.duarteandreza.tcc.domain.Imovel;
import io.github.duarteandreza.tcc.request.CadastrarImovelRequest;
import io.github.duarteandreza.tcc.service.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

@RestController
@RequestMapping("/imoveis")
public class ImovelController {

    @Autowired
    private ImovelService imovelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Imovel salvar(@RequestBody  @Valid CadastrarImovelRequest imovel) throws Exception {
        return imovelService.salvar(imovel);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Imovel> listarImoveis(Imovel imovel, @PageableDefault(sort = "identificacao") @ApiIgnore Pageable pageable){
        return imovelService.listar(imovel, pageable);
    }

    @GetMapping(path = "/proprietarios/{idProprietario}")
    public Page<Imovel> listarImovelIdProprietario(@PathVariable Long idProprietario, @PageableDefault(sort = "identificacao") @ApiIgnore Pageable pageable){
        return imovelService.listarImovelIdProprietario(idProprietario, pageable);
    }

    @GetMapping(path = "{idImovel}")
    @ResponseStatus(HttpStatus.OK)
    public Imovel buscarImovelPorId(@PathVariable Long idImovel){
        return imovelService.buscarImovelPorId(idImovel);
    }

    @DeleteMapping(path = "{idImovel}")
    @ResponseStatus(HttpStatus.OK)
    @Valid
    public void deletarImovel(@PathVariable Long idImovel){
        imovelService.deletarImovel(idImovel);
    }

}