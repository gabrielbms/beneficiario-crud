package beneficiario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import beneficiario.entity.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    List<Documento> findByBeneficiarioId(Long beneficiarioId);
}