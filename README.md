## Multi-Agent Competitive Programming Tutor 🤖🎓
A multi-agent AI system built with Java and LangChain4j that acts as a Socratic tutor for algorithmic problem solving.

Unlike standard LLMs (like ChatGPT) that immediately generate the final code, this project constrains AI to act as an educational facilitator. It orchestrates three distinct AI personas that analyze code in the background and guide the user toward the correct solution using hints and back-and-forth dialogue.

🧠 The Multi-Agent Workflow
🧐 The Critic Agent: Runs static analysis on the user's Java submission. It is strictly prompted not to fix the code, but only to identify missing edge cases (e.g., null arrays, out-of-bounds risks) and return its findings as strongly typed Java Records.

📊 The Complexity Agent: Acts as a theoretical computer scientist. It evaluates the exact lines of code contributing to the Time (Big-O) and Space complexity.

🎓 The Mentor Agent: The Orchestrator. It reads the hidden notes from the Critic and Complexity agents, holds conversational context using Chat Memory, and engages the user in a Socratic dialogue. It asks guiding questions to help the user discover their own bugs.

## 🛠️ Tech Stack
Language: Java

AI Framework: LangChain4j (for agent orchestration, memory management, and structured output parsing)

LLM Provider: Groq API (Utilizing Llama-3.1-70B for ultra-fast, free agentic reasoning)

Build Tool: Maven

## 🚀 How It Works Under the Hood
This project demonstrates advanced prompt engineering and LLM routing. The Orchestrator takes the user's input and passes it simultaneously to the Critic and Complexity agents. Their outputs are synthesized and injected as "hidden internal notes" into the Mentor Agent's system prompt, giving the Mentor a complete understanding of the student's flaws before the chat even begins.

## ⚙️ Quick Start
Clone the repository.

Ensure you have your free Groq API Key.

Add your key to your environment variables: GROQ_API_KEY=gsk_your_key_here

Run the CompetitiveProgrammingMentor.main() method in your IDE to start the interactive console chat.
