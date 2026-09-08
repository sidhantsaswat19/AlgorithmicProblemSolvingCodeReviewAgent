### Multi-Agent Agentic Code Reviewer & Socratic Tutor 🤖🎓
An advanced Multi-Agent System (MAS) built with Java and LangChain4j, designed to provide interactive, Socratic facilitation for algorithmic problem solving.

Unlike zero-shot LLM implementations that default to an "answer-generation" anti-pattern (which bypasses student learning), this project implements strict system constraints and agentic routing. It orchestrates a specialized topology of AI agents that perform background code analysis, cross-reference local coding standards via RAG, and guide the user toward an optimal solution using context-aware dialogue.

### 🧠 System Architecture & Agent Topology
## 🧐 The Critic Agent (Domain-Grounded Static Analysis):
Performs strict edge-case and vulnerability analysis on the user's Java submission.

RAG Integration: Utilizes Retrieval-Augmented Generation (RAG) to dynamically fetch and inject university/enterprise coding standards into the prompt context via semantic search.

Schema Compliance: Constrained to output telemetry strictly as a JSON payload, mapped directly to immutable Java Records for deterministic downstream processing.

## 📊 The Complexity Agent (Asymptotic Analyzer):
Functions as a theoretical computer science evaluator. It isolates code segments to calculate and verify the deterministic Time (Big-O) and Space complexity.

## 🎓 The Mentor Agent (Stateful Orchestrator):
The user-facing facilitator. It synthesizes the hidden telemetry from the Critic and Complexity agents, maintaining stateful conversational context using MessageWindowChatMemory. It utilizes this injected context to execute a Socratic dialogue pattern, prompting the user to discover their own logical flaws.

### 🛠️ Technology Stack
Language: Java 21 (utilizing Records and modern language features)

AI Orchestration: LangChain4j (for multi-agent routing, RAG pipelines, and conversational memory)

Vector Embeddings: AllMiniLM-L6-v2 via In-Memory Embedding Store for localized, offline document vectorization.

LLM Inference: Groq API (Utilizing Llama-3.1-70B for high-throughput, low-latency reasoning)

Build System: Maven

### 🚀 Technical Implementation Highlights
Agentic Routing & Parallel Context Injection: The system intercepts user input and routes it to specialized analysis agents. Their structured outputs are synthesized into a hidden internal prompt payload, giving the Mentor Agent a comprehensive understanding of the code's deficiencies prior to generation.

Retrieval-Augmented Generation (RAG): Implements a full embedding pipeline (Document Loading ➔ Text Chunking/Segmentation ➔ Vectorization ➔ Semantic Retrieval) to ground the Critic Agent's logic in local, proprietary text files (e.g., coding_standards.txt).

Structured Outputs: Mitigates LLM hallucination by forcing the Critic Agent to adhere to a strict JSON schema, parsed directly into Java objects for type-safe execution.

### ⚙️ Quick Start
Clone the repository.

Ensure you have a free Groq API Key.

Add your key to your environment variables: GROQ_API_KEY=gsk_your_key_here

Create a coding_standards.txt file on your local machine and update the FileSystemDocumentLoader path in the source code.

Execute the CompetitiveProgrammingMentor.main() method in your IDE to initialize the RAG database and start the interactive terminal session.

### 🗺️ Roadmap (Future Scope)
Model Context Protocol (MCP) Integration: Upgrading the system to autonomously read and write .java files directly from the local file system or a live GitHub repository.
