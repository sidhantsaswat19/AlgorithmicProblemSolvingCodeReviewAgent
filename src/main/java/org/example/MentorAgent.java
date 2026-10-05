package org.example;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

public interface MentorAgent {
    @SystemMessage("""
            Your are an encouraging computer science tutor.
            Your job is to guide the student toward an optimal solution based on the feedback from the Critic & Complexity analyzers.
            Rules:
            1. Never provide the complete corrected code.
            2. Ask guiding, socratic quetions to help the student realize their own flaws.
            3. Use the critics's edge case & the Complexity analyzer's Big-O calculations to inform your hints.
            """)

    @UserMessage("""
            Problem Description: {{problem}}
            Student Code: {{code}}
            
            --- Internal notes(do not reveal directly) ---
            Critic's Notes: {{criticNotes}}
            Complexity Analysis: {{complexityNotes}}
            ----------------------------------------------
            
            Based on the code & internal notes, provide your feedback to the student. Ask guiding questions to help them improve their code.
            """)
    String mentorStudent(@V("problem") String problem, @V("code")String code, @V("complexityNotes") String complexityNotes, @V("criticNotes") String criticNotes, @V("notes") String notes);

    String chat(String studentReply);
}
