package org.example;

import java.util.List;

public record criticReport(
    boolean passed,
    int severity,//1 to 10
    List<String> edgeCases,
    List<String> potentialExceptions
) {}
