package io.github.duarteandreza.tcc.repository;


import io.github.duarteandreza.tcc.domain.Reserva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Integer> {

    boolean existsById(Long idSolicitante);

    Reserva findById(Long idReserva);

    Page<Reserva> findAllBySolicitanteIdEqualsAndPeriodoDataHoraInicialGreaterThanEqualAndPeriodoDataHoraFinalLessThanEqual(
            Long idSolicitante,
            LocalDateTime dataHoraInicial,
            LocalDateTime dataHoraFinal,
            Pageable pageable);

    Page<Reserva> findAllByAnuncioAnuncianteId(Long idAnunciante, Pageable pageable);

    Page<Reserva> findAllBySolicitanteId(Long idSolicitante, Pageable pageable);


    @Query("SELECT r FROM Reserva AS r " +
            "WHERE (" +
            "r.periodo.dataHoraInicial BETWEEN :dataChegada AND :dataSaida " +
            "OR r.periodo.dataHoraFinal BETWEEN :dataChegada AND :dataSaida " +
            "OR :dataChegada BETWEEN r.periodo.dataHoraInicial AND r.periodo.dataHoraFinal " +
            ") " +
            "AND r.anuncio.imovel.id = :imovelId " +
            "AND ( r.pagamento.status = io.github.duarteandreza.tcc.domain.StatusPagamento.PAGO " +
            "OR r.pagamento.status = io.github.duarteandreza.tcc.domain.StatusPagamento.PENDENTE )")
    List<Reserva> findAllValidReservasComConflitosDeDatas(@Param("imovelId") Long imovelId,
                                                            @Param("dataChegada") LocalDateTime dataChegada,
                                                            @Param("dataSaida") LocalDateTime dataSaida);



}
