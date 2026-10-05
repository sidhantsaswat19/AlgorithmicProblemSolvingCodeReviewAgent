package org.example.agents;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface ComplexityAgent {
    @SystemMessage("""
            Your are a theoretical computer scientist.
            Analyze the Big-O Time & Space complexity of the provided Java code.
            Explain exactly which lines cause this complexity (e.g 'The nested for-loop on line 4 causes O(N^2) time').
            """)
    @UserMessage("Analyze this Java code: \n {{code}}")
    String analyzeComplexity(@V("code") String code, @V("studentCode") String studentCode);
}
