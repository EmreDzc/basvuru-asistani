package basvuru_asistani.service;

import basvuru_asistani.domain.ConsumerCase;
import basvuru_asistani.request.CreateCaseRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CaseService {
    private final Map<Long, ConsumerCase> cases = new ConcurrentHashMap<>();
    private final AtomicLong nextId = new AtomicLong(0);

    public ConsumerCase createCase(CreateCaseRequest request) {
        long id = nextId.incrementAndGet();
        ConsumerCase newCase = new ConsumerCase(
                id, request.getDescription(), request.getRequestedRemedy());
        cases.put(id, newCase);
        return newCase;
    }

    public List<ConsumerCase> getAllCases() {
        return new ArrayList<>(cases.values());
    }

    public ConsumerCase getCaseById(Long id) {
        return cases.get(id);
    }
}