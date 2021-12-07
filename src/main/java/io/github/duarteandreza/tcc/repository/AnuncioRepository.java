package io.github.duarteandreza.tcc.repository;


import io.github.duarteandreza.tcc.domain.Anuncio;
import io.github.duarteandreza.tcc.domain.FormaPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Integer> {

    boolean existsById(Long id);

    Anuncio findById(Long id);

    Page<Anuncio> findByAnuncianteIdAndAtivoTrue(Long idAnunciante, Pageable pageable);

    boolean existsByFormasAceitas(FormaPagamento formaPagamento);

    Anuncio findByImovelIdAndAtivoTrue(Long id);

    Page<Anuncio> findAllByAtivoTrue(Pageable pageable);
}
