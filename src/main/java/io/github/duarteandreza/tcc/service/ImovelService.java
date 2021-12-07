package io.github.duarteandreza.tcc.service;

import io.github.duarteandreza.tcc.domain.Imovel;
import io.github.duarteandreza.tcc.domain.Usuario;
import io.github.duarteandreza.tcc.exception.IdImovelNaoEncontradoException;
import io.github.duarteandreza.tcc.exception.IdProprietarioNaoCadastradoException;
import io.github.duarteandreza.tcc.exception.NaoEPossivelExluirImoveComAnuncioException;
import io.github.duarteandreza.tcc.repository.ImovelRepository;
import io.github.duarteandreza.tcc.request.CadastrarImovelRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
    private UsuarioService usuarioService;


    public Imovel salvar(CadastrarImovelRequest cadastrarImovelRequest) throws Exception {

        final Usuario usuario = usuarioService.buscarUsuarioId(cadastrarImovelRequest.getIdProprietario());

        final Imovel imovel = new Imovel(cadastrarImovelRequest.getIdentificacao(), cadastrarImovelRequest.getTipoImovel(),
                cadastrarImovelRequest.getEndereco(),cadastrarImovelRequest.getcaracteristicas(), usuario);

        if (cadastrarImovelRequest.getIdProprietario() == null){
            throw new IdProprietarioNaoCadastradoException(usuario.getId());
        }

        return imovelRepository.save(imovel);

    }


    public Page<Imovel> listar(Imovel imovel, Pageable pageable) {

        return (Page<Imovel>) this.imovelRepository.findAll(pageable);
    }

    public Page<Imovel> listarImovelIdProprietario(Long idProprietario, Pageable pageable) {

        return (Page<Imovel>) this.imovelRepository.findAllByProprietarioId(idProprietario, pageable);

    }

    public Imovel buscarImovelPorId(Long idImovel) {
        boolean imovelEncontrado = imovelRepository.existsById(idImovel);

        if (imovelEncontrado) {
            return imovelRepository.findById(idImovel);
        }
        throw new IdImovelNaoEncontradoException(idImovel);
    }

    public void deletarImovel(Long idImovel) {
        Imovel imovel = imovelRepository.findById(idImovel);

        if (imovel == null || imovel.getAtivo() == false){
            throw new IdImovelNaoEncontradoException(idImovel);
        }
        if (imovelRepository.existsByAnuncio(idImovel)){
            throw new NaoEPossivelExluirImoveComAnuncioException(imovel);
        }

        imovel.setAtivo(false);

        imovelRepository.save(imovel);

    }
}
