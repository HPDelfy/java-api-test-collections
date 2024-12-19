package com.collections.assignment.models;

import com.collections.assignment.models.art.ArtObjects;
import com.collections.assignment.models.facets.CountFacets;
import com.collections.assignment.models.facets.FacetsObject;
import lombok.Data;

import java.util.List;

@Data
public class Collections {
    private int elapsedMilliseconds;
    private int count;
    private CountFacets countFacets;
    private List<ArtObjects> artObjects;
    private List<FacetsObject> facets;
}
