package io.github.duarteandreza.tcc.repository;

import io.github.duarteandreza.tcc.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    boolean existsByEmail(String email);

    boolean existsByCpf(String cpf);

    boolean existsById(Long id);

    Usuario findById(Long id);

    Usuario findByCpf(String cpf);


}
