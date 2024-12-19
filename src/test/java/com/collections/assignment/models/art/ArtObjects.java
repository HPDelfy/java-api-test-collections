package com.collections.assignment.models.art;

import lombok.Data;

import java.util.List;

@Data
public class ArtObjects {
    private Links links;
    private String id;
    private String objectNumber;
    private String title;
    private Boolean hasImage;
    private String principalOrFirstMaker;
    private String longTitle;
    private Boolean showImage;
    private Boolean permitDownload;
    private Image webImage;
    private Image headerImage;
    private List<String> productionPlaces;
}
