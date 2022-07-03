package com.example.density.service;

import com.example.density.model.PointOfInterest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TSVFileReaderImpl implements TSVFileReader {

    @Override
    public List<PointOfInterest> fetch() {
        try (var fis = new FileInputStream("src/main/resources/static/PointsOfInterest.tsv");
             var isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             var buffer = new BufferedReader(isr)) {

            return buffer.lines().skip(1).map(line -> List.of(line.split("\t")))
                    .filter(args -> args.size() == 3)
                    .map(args -> new PointOfInterest(args.get(0), Double.parseDouble(args.get(1)), Double.parseDouble(args.get(2))))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("Error reading file", e);
        }
        return Collections.emptyList();
    }
}
