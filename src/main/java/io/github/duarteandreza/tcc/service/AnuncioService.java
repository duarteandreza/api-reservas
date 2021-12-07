package io.github.duarteandreza.tcc.service;

import io.github.duarteandreza.tcc.domain.Anuncio;
import io.github.duarteandreza.tcc.domain.Imovel;
import io.github.duarteandreza.tcc.domain.Usuario;
import io.github.duarteandreza.tcc.exception.IdAnuncioNaoEncontradoException;
import io.github.duarteandreza.tcc.exception.IdImovelNaoEncontradoException;
import io.github.duarteandreza.tcc.exception.IdProprietarioNaoCadastradoException;
import io.github.duarteandreza.tcc.exception.JaExisteAnuncioParaEsteImovelException;
import io.github.duarteandreza.tcc.repository.AnuncioRepository;
import io.github.duarteandreza.tcc.request.CadastrarAnuncioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;
    @Autowired
    private ImovelService imovelService;
    @Autowired
    private UsuarioService usuarioService;

    public Anuncio salvar(CadastrarAnuncioRequest cadastrarAnuncioRequest) {

        Imovel imovel = imovelService.buscarImovelPorId(cadastrarAnuncioRequest.getIdImovel());

        Usuario usuario = usuarioService.buscarUsuarioId(cadastrarAnuncioRequest.getIdAnunciante());

        if (cadastrarAnuncioRequest.getIdImovel() == null){
            throw new IdImovelNaoEncontradoException(imovel.getId());
        }
        if (cadastrarAnuncioRequest.getIdAnunciante() == null){
            throw new IdProprietarioNaoCadastradoException(usuario.getId());
        }

        if (anuncioRepository.findByImovelIdAndAtivoTrue(imovel.getId()) != null) {
            throw new JaExisteAnuncioParaEsteImovelException(imovel.getId());
        }

        final Anuncio anuncio = new Anuncio(cadastrarAnuncioRequest.getTipoAnuncio(),
                imovel, usuario, cadastrarAnuncioRequest.getValorDiaria(), cadastrarAnuncioRequest.getFormasAceitas(),
                cadastrarAnuncioRequest.getDescricao());


        return anuncioRepository.save(anuncio);
    }

    public Page<Anuncio> listar(Pageable pageable) {
        return anuncioRepository.findAllByAtivoTrue(pageable);
    }

    public Page<Anuncio> listarAnuncioPorIdAnunciante(Long idAnunciante, Pageable pageable) {

        return anuncioRepository.findByAnuncianteIdAndAtivoTrue(idAnunciante, pageable);

    }

    public void deletarAnuncio(Long idAnuncio) {
        Anuncio anuncio = anuncioRepository.findById(idAnuncio);

        if (anuncio == null){
            throw new IdAnuncioNaoEncontradoException(idAnuncio);
        }

        anuncio.setAtivo(false);

        anuncioRepository.save(anuncio);
    }

    public Anuncio buscarAnuncio(Long idAnuncio) {
        boolean anuncioEncontrado = anuncioRepository.existsById(idAnuncio);

        if (anuncioEncontrado) {
            return anuncioRepository.findById(idAnuncio);
        }
        throw new IdAnuncioNaoEncontradoException(idAnuncio);
    }

}
