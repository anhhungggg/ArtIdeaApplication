package com.oatfleik.artApplication.data;

import com.oatfleik.artApplication.model.Art;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.batch.item.ItemProcessor;

public class ArtProcessor implements ItemProcessor<ArtInput, Art> {

    private static final Logger log = LoggerFactory.getLogger(ArtProcessor.class);

    @Override
    public Art process(final ArtInput artInput) throws Exception {
        Art art = new Art();
        art.setId(Long.parseLong(artInput.getId()));
        art.setHighlight(Boolean.parseBoolean(artInput.getIsHighlight()));
        art.setTimelineWork(Boolean.parseBoolean(artInput.getIsTimelineWork()));
        art.setPublicDomain(Boolean.parseBoolean(artInput.getIsPublicDomain()));
        art.setObjectName(artInput.getObjectName());
        art.setTitle(artInput.getTitle());
        art.setArtistDisplayName(artInput.getArtistDisplayName());
        art.setLinkResource(artInput.getLinkResource());

        return art;
    }

}
