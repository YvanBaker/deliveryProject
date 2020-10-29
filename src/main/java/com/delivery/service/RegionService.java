package com.delivery.service;


import com.delivery.entity.Region;

import java.util.List;

public interface RegionService {
    List<Region> getRegions();

    List<Region> getRegionsByDim(String q);
}
