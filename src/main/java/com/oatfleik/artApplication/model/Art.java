package com.oatfleik.artApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Art {

    @Id
    private long id;
    private boolean isHighlight = false;
    private boolean isTimelineWork;
    private boolean isPublicDomain;
    @Column(length=1000)
    private String objectName;
    @Column(length=1000)
    private String title;
    @Column(length=1000)
    private String linkResource;
    @Column(length=10000)
    private String artistDisplayName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean getIsHighlight() {
        return isHighlight;
    }

    public void setHighlight(boolean highlight) {
        isHighlight = highlight;
    }

    public boolean getIsTimelineWork() {
        return isTimelineWork;
    }

    public void setTimelineWork(boolean timelineWork) {
        isTimelineWork = timelineWork;
    }

    public boolean getIsPublicDomain() {
        return isPublicDomain;
    }

    public void setPublicDomain(boolean publicDomain) {
        isPublicDomain = publicDomain;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtistDisplayName() {
        return artistDisplayName;
    }

    public void setArtistDisplayName(String artistDisplayName) {
        this.artistDisplayName = artistDisplayName;
    }

    public String getLinkResource() {
        return linkResource;
    }

    public void setLinkResource(String linkResource) {
        this.linkResource = linkResource;
    }
}
