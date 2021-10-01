package de.hswhameln.isbnvalidator.dto;

import java.util.ArrayList;
import java.util.List;

public class DeleteRequest {

    List<String> isbns;

    public DeleteRequest() {
        this.isbns = new ArrayList<>();
    }

    public List<String> getIsbns() {
        return isbns;
    }

    public void setIsbns(List<String> isbns) {
        this.isbns = isbns;
    }
}
