package basvuru_asistani.service;

import basvuru_asistani.domain.ConsumerCase;
import basvuru_asistani.repository.ConsumerCaseRepository;
import basvuru_asistani.request.CreateCaseRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseService {

    private static final Long DEMO_USER_ID = 1L;

    private final ConsumerCaseRepository caseRepository;

    public CaseService(ConsumerCaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public ConsumerCase createCase(CreateCaseRequest request){
        ConsumerCase newCase  = new ConsumerCase(DEMO_USER_ID, request.getDescription(), request.getRequestedRemedy(),null);
        return caseRepository.save(newCase);
    }

    public List<ConsumerCase> getAllCases(){
        return caseRepository.findAll();
    }

    public ConsumerCase getCaseById(Long id){
        return caseRepository.findById(id).orElse(null);
    }
}