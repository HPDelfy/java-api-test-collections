package com.collections.assignment.models.art;

import lombok.Data;

@Data
public class Image {
    private String guid;
    private int offsetPercentageX;
    private int offsetPercentageY;
    private int width;
    private int height;
    private String url;
}
