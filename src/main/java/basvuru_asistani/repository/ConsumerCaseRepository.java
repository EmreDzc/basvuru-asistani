package basvuru_asistani.repository;

import basvuru_asistani.domain.ConsumerCase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumerCaseRepository extends JpaRepository<ConsumerCase, Long> {
}