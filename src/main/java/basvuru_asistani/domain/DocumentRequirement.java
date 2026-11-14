package basvuru_asistani.domain;

import java.util.Objects;

public class DocumentRequirement {
    private DocumentType type;
    private boolean mandatory;

    public DocumentRequirement(DocumentType type, boolean mandatory){
        this.type = type;
        this.mandatory = mandatory;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof DocumentRequirement)){
            return false;
        }
        DocumentRequirement other = (DocumentRequirement) o;
        return this.type == other.type;
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(type);
    }
}
