package com.qsq.ego.portal.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BigPicture {
    @JsonProperty("alt")
    private String alt;
    @JsonProperty("href")
    private String href;

    @JsonProperty("src")
    private String src;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("width")
    private Integer width;

    @JsonProperty("srcB")
    private String srcB;
    @JsonProperty("heightB")
    private Integer heightB;
    @JsonProperty("widthB")
    private Integer widthB;

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getSrcB() {
        return srcB;
    }

    public void setSrcB(String srcB) {
        this.srcB = srcB;
    }

    public Integer getHeightB() {
        return heightB;
    }

    public void setHeightB(Integer heightB) {
        this.heightB = heightB;
    }

    public Integer getWidthB() {
        return widthB;
    }

    public void setWidthB(Integer widthB) {
        this.widthB = widthB;
    }
}
