package io.github.duarteandreza.tcc.repository;

import io.github.duarteandreza.tcc.domain.Imovel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends JpaRepository<Imovel, Integer> {

    Imovel findById(Long id);

    boolean existsById(Long id);

    Page<Imovel> findAllByProprietarioId(Long idProprietario, Pageable pageable);

    @Query("Select (count(a) > 0) from Anuncio a where a.imovel.id = ?1")
    boolean existsByAnuncio(Long idImovel);

}
