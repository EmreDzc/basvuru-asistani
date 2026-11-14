package basvuru_asistani.controller;

import basvuru_asistani.domain.ConsumerCase;
import basvuru_asistani.request.CreateCaseRequest;
import basvuru_asistani.service.CaseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/cases")
public class CaseController {
    private final CaseService caseService;

    public CaseController(CaseService caseService) {
        this.caseService = caseService;
    }

    @PostMapping
    public ConsumerCase createCase(@RequestBody CreateCaseRequest request) {
        return caseService.createCase(request);
    }

    @GetMapping
    public List<ConsumerCase> getAllCases() {
        return caseService.getAllCases();
    }

    @GetMapping("/{id}")
    public ConsumerCase getCaseById(@PathVariable Long id) {
        ConsumerCase consumerCase = caseService.getCaseById(id);

        if (consumerCase == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Şikâyet bulunamadı");
        }

        return consumerCase;
    }
}
