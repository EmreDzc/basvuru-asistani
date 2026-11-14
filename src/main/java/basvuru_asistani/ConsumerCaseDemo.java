package basvuru_asistani;

import basvuru_asistani.domain.ConsumerCase;
import basvuru_asistani.domain.DocumentRequirement;
import basvuru_asistani.domain.DocumentType;
import basvuru_asistani.domain.RequestedRemedy;

import java.util.HashSet;
import java.util.Set;

public class ConsumerCaseDemo {
    public static void main(String[] args){
        DocumentRequirement d1 = new DocumentRequirement(DocumentType.INVOICE, true);
        DocumentRequirement d2 = new DocumentRequirement(DocumentType.INVOICE, true);

        Set<DocumentRequirement> requirements = new HashSet<>();
        requirements.add(d1);
        requirements.add(d2);

        System.out.println(requirements.size());
    }
}
