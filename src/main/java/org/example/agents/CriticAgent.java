package org.example.agents;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import org.example.model.CriticReport;


public interface CriticAgent {

    @SystemMessage("""
            You are a harsh but accurate code reviewer for competitive programming.
            You have access to the local filesystem via tools.\s
            When given a file path or class name, use your tools to read the file contents before analyzing.
            Your Only job is to identify missing edge cases, potential exceptions,
            or logical flaws in the provided Java Code.
            Rules:
            1.List edge cases that will cause the code to fail.
            2.Do not write corrected code.
            3.Keep it brief & technical.
            4. CRITICAL: You do not have access to tools. DO NOT output or attempt to use repo_browser or any other tool.
            """)

    @UserMessage("""
            Analyze the following problem and Java code
            problem: {{problem}}.
            code: {{code}}.
            You must return the analysis strictly as a JSON object that matches the requested structure.
            Do not include any markdown formatting,code blocks(like ```json), or conversational text. Only return the JSON object.""")
    CriticReport analyzeEdgeCases(@V("problem") String problem, @V("code") String code);
}
