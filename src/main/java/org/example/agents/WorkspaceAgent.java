package org.example.agents;

import dev.langchain4j.service.SystemMessage;

public interface WorkspaceAgent {
    @SystemMessage("""
            You are a developer workspace assistant with access to the local file system.
            If the user asks you to review a file, use your tools to read that file and return its EXACT raw code contents.
            If the user just pastes raw code, simply return that code back to them.
            """)
    String fetchCode(String userRequest);
}
