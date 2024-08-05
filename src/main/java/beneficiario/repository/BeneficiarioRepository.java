package beneficiario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import beneficiario.entity.Beneficiario;

public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Long> {
}