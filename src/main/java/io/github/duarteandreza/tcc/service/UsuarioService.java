package io.github.duarteandreza.tcc.service;

import io.github.duarteandreza.tcc.domain.Usuario;
import io.github.duarteandreza.tcc.repository.AvatarRepository;
import io.github.duarteandreza.tcc.repository.UsuarioRepository;
import io.github.duarteandreza.tcc.request.AtualizarUsuarioRequest;
import io.github.duarteandreza.tcc.exception.CpfDeUsuarioJaCadastradoException;
import io.github.duarteandreza.tcc.exception.CpfUsuarioNaoEncontradoException;
import io.github.duarteandreza.tcc.exception.EmailDeUsuarioJaCadastradoException;
import io.github.duarteandreza.tcc.exception.IdUsuarioNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AvatarRepository avatarRepository;

    public Usuario salvar(Usuario usuario) {
        boolean emailDeUsuarioJaCadastrado = usuarioRepository.existsByEmail(usuario.getEmail());

        if (emailDeUsuarioJaCadastrado){
            throw new EmailDeUsuarioJaCadastradoException(usuario.getEmail());
        }

        boolean cpfDeUsuarioJaCadastrado = usuarioRepository.existsByCpf(usuario.getCpf());

        if (cpfDeUsuarioJaCadastrado){
            throw new CpfDeUsuarioJaCadastradoException(usuario.getCpf());
        }

        String avatar = avatarRepository.getAvatar().toString();

        usuario.setImagemAvatar(avatar);

        return usuarioRepository.save(usuario);
    }

    public Page<Usuario> listar(Usuario usuario, Pageable pageable) {
        return (Page<Usuario>) this.usuarioRepository.findAll(pageable);
    }

    public Usuario buscarUsuarioId(Long idUsuario) {
        boolean usuarioEncontrado = usuarioRepository.existsById(idUsuario);

        if (usuarioEncontrado) {
            return usuarioRepository.findById(idUsuario);
        }
        throw new IdUsuarioNaoEncontradaException(idUsuario);

    }

    public Usuario buscarUsuarioCPF(String cpf) {
        boolean usuarioEncontrado = usuarioRepository.existsByCpf(cpf);

        if (usuarioEncontrado) {
            return usuarioRepository.findByCpf(cpf);
        }
        throw new CpfUsuarioNaoEncontradoException(cpf);
    }

    public Usuario atualizarUsuario(Long id, AtualizarUsuarioRequest atualizarUsuarioRequest) throws Exception {
        Usuario usuario = buscarUsuarioId(id);

        if (usuarioRepository.existsById(id)) {
            usuario.setNome(atualizarUsuarioRequest.getNome());
            usuario.setDataNascimento(atualizarUsuarioRequest.getDataNascimento());
            usuario.setSenha(atualizarUsuarioRequest.getSenha());
            if (usuario.getEmail().equals(atualizarUsuarioRequest.getEmail())) {
                usuario.getEmail();
            } else {
                if (usuarioRepository.existsByEmail(atualizarUsuarioRequest.getEmail())){
                    throw new EmailDeUsuarioJaCadastradoException(atualizarUsuarioRequest.getEmail());
                } else {
                    usuario.setEmail(atualizarUsuarioRequest.getEmail());
                }
            }
            usuario.getEndereco().setBairro(atualizarUsuarioRequest.getEndereco().getBairro());
            usuario.getEndereco().setCep(atualizarUsuarioRequest.getEndereco().getCep());
            usuario.getEndereco().setCidade(atualizarUsuarioRequest.getEndereco().getCidade());
            usuario.getEndereco().setComplemento(atualizarUsuarioRequest.getEndereco().getComplemento());
            usuario.getEndereco().setEstado(atualizarUsuarioRequest.getEndereco().getEstado());
            usuario.getEndereco().setLogradouro(atualizarUsuarioRequest.getEndereco().getLogradouro());
            usuario.getEndereco().setNumero(atualizarUsuarioRequest.getEndereco().getNumero());

            usuarioRepository.save(usuario);

        } else {
            throw new IdUsuarioNaoEncontradaException(id);
        }

        return usuarioRepository.save(usuario);

    }

}