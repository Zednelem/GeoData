package com.example.density.service;

import com.example.density.model.PointOfInterest;

import java.util.List;

public interface TSVFileReader {
    List<PointOfInterest> fetch();
}
