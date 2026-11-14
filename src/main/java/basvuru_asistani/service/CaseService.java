package basvuru_asistani.service;

import basvuru_asistani.domain.CaseStatus;
import basvuru_asistani.domain.ConsumerCase;
import basvuru_asistani.request.CreateCaseRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class CaseService {
    private final Map<Long, ConsumerCase> cases = new HashMap<>();
    private long nextId = 1;

    public ConsumerCase createCase(CreateCaseRequest request) {

        ConsumerCase newCase = new ConsumerCase(nextId, request.getDescription(), request.getRequestedRemedy());

        cases.put(newCase.getId(), newCase);

        nextId++;

        return newCase;
    }

    public List<ConsumerCase> getAllCases() {

        return new ArrayList<>(cases.values());
    }

    public ConsumerCase getCaseById(Long id) {
        return cases.get(id);
    }
}
