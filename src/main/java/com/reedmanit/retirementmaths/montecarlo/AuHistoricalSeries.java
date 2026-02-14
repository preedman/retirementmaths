package com.reedmanit.retirementmaths.montecarlo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuHistoricalSeries {
    private final List<ReturnInflation> samples;

    public AuHistoricalSeries() {
        this.samples = loadFromCsv("data/au_return_inflation.csv");
        if (samples.isEmpty()) {
            throw new IllegalStateException("No AU historical samples loaded (CSV empty).");
        }
    }

    public List<ReturnInflation> samples() {
        return samples;
    }

    private static List<ReturnInflation> loadFromCsv(String classpathLocation) {
        try {
            ClassPathResource resource = new ClassPathResource(classpathLocation);
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)
            )) {
                List<ReturnInflation> out = new ArrayList<>();
                String line;
                boolean first = true;

                while ((line = br.readLine()) != null) {
                    line = line.trim();
                    if (line.isEmpty()) continue;

                    if (first) { // header
                        first = false;
                        continue;
                    }

                    String[] parts = line.split(",", -1);
                    if (parts.length < 3) continue;

                    // parts[0] = year (unused for now)
                    double r = Double.parseDouble(parts[1].trim());
                    double i = Double.parseDouble(parts[2].trim());
                    out.add(new ReturnInflation(r, i));
                }

                return List.copyOf(out);
            }
        } catch (Exception e) {
            throw new IllegalStateException("Failed to load AU historical CSV: " + classpathLocation, e);
        }
    }

}
