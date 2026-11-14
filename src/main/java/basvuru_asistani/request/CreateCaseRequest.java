package basvuru_asistani.request;

import basvuru_asistani.domain.RequestedRemedy;

public class CreateCaseRequest {
    private String description;
    private RequestedRemedy requestedRemedy;

    public RequestedRemedy getRequestedRemedy() {
        return requestedRemedy;
    }

    public void setRequestedRemedy(RequestedRemedy requestedRemedy) {
        this.requestedRemedy = requestedRemedy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
