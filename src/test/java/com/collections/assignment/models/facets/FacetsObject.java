package com.collections.assignment.models.facets;

import lombok.Data;

import java.util.List;

@Data
public class FacetsObject {
    private List<Facets> facets;
    private String name;
    private int otherTerms;
    private int prettyName;
}
