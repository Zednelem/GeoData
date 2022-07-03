package com.example.density.controllers;

import com.example.density.dto.ZoneDto;
import com.example.density.service.ZoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class DensityController {

    private final ZoneService zoneService;

    public DensityController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @GetMapping(path = "/api/public/zones", params = {"size"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ZoneDto>> getZones(@RequestParam("size") int size) {
        return new ResponseEntity<>(
                zoneService.getMostDenseZonesWithSize(size).stream().map(ZoneDto::new)
                        .collect(Collectors.toSet())
                , HttpStatus.OK);
    }

}
