package com.delivery.dao;

import com.delivery.entity.Region;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RegionDao {
    List<Region> getRegions();

    List<Region> getRegionsByDim(String q);

}
