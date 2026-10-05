package org.example.model;

import java.util.List;

public record CriticReport(
    boolean passed,
    int severity,//1 to 10
    List<String> edgeCases,
    List<String> potentialExceptions
) {}
