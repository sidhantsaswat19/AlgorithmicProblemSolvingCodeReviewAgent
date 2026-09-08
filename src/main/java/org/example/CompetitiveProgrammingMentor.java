package org.example;

import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
/*import dev.langchain4j.mcp.McpToolProvider;
import dev.langchain4j.mcp.client.DefaultMcpClient;
import dev.langchain4j.mcp.client.McpClient;
import dev.langchain4j.mcp.client.transport.stdio.StdioMcpTransport;*/
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.allminilml6v2.AllMiniLmL6V2EmbeddingModel;
//import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import dev.langchain4j.data.document.Document;

//import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class CompetitiveProgrammingMentor {
    public static void main(String[] args) {

        ChatModel llm = OpenAiChatModel.builder()
                .baseUrl("https://api.groq.com/openai/v1")
                .apiKey(System.getenv("GROQ_API_KEY"))
                .modelName("openai/gpt-oss-120b")
                .build();

        System.out.println("Building RAG Database...");

        Document stdDoc = FileSystemDocumentLoader.loadDocument("C:\\Users\\sures\\OneDrive\\Desktop\\CodingPractice.txt");

        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        EmbeddingStoreIngestor.builder()
                .embeddingModel(new AllMiniLmL6V2EmbeddingModel())
                .embeddingStore(embeddingStore)
                .build()
                .ingest(stdDoc);

        EmbeddingStoreContentRetriever retriever = EmbeddingStoreContentRetriever.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(new AllMiniLmL6V2EmbeddingModel())
                .maxResults(1)
                .build();

        CriticAgent criticAgent = AiServices.builder(CriticAgent.class)
                .chatModel(llm)
                .contentRetriever(retriever)
                .build();

        ComplexityAgent complexityAgent = AiServices.builder(ComplexityAgent.class)
                .chatModel(llm)
                .build();

        MentorAgent mentorAgent = AiServices.builder(MentorAgent.class)
                .chatModel(llm)
                .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                .build();

        String problem  = "Write a method to find the maximum element in an array of integers.";
        String studentCode = """
              public int findMax(int[] arr) {
                    int max = arr[0];
                    for (int i = 1; i < arr.length; i++) {
                        if (arr[i] > max) {
                        max = arr[i];
                        }
                    }
                return max;
              }
        """;

        System.out.println("Starting multi-agent analysis...\n");

        System.out.println("-> Critic is analyzing edge cases...");
        criticReport criticReport = criticAgent.analyzeEdgeCases(problem, studentCode);

        System.out.println("Did the code pass the critic's analysis? " + criticReport.passed());
        System.out.println("Critic's severity rating (1-10): " + criticReport.severity()+"/10");

        System.out.println("Critic's identified edge cases: " );
        for (String edgeCase : criticReport.edgeCases()) {
            System.out.println("- " + edgeCase);
        }
        //String criticNotes = String.valueOf(criticAgent.analyzeEdgeCases(problem, studentCode));

        System.out.println("-> Complexity is calculating time complexity...");
        String complexityAnalysis = complexityAgent.analyzeComplexity(problem, studentCode);

        System.out.println("--- Mentor has joined the chat ---");
        String mentorReply = mentorAgent.mentorStudent(problem, studentCode, complexityAnalysis, String.valueOf(criticReport));
        System.out.println("Mentor: " + mentorReply);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("You: ");
            String userText = scanner.nextLine();

            if(userText.equals("exit") || userText.equals("quit")) {
                System.out.println("Mentor: Great session! Keep practicing...");
                break;
            }

            System.out.println("\nMentor is typing...");
            String nextReply = mentorAgent.chat(userText);

            System.out.println("Mentor: " + nextReply);
        }
        scanner.close();
        /*System.out.println("-> Mentor is synthesizing feedback...\n");
        String finalFeedback = mentorAgent.mentorStudent(problem, studentCode, criticNotes, complexityAnalysis);

        System.out.println("--- Message From Mentor ---");
        System.out.println(finalFeedback);*/

    }
}