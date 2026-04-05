package org.astralcore.ai.spring;

import org.jspecify.annotations.Nullable;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.chat.prompt.Prompt;

import java.util.List;
import java.util.Map;

import static org.astralcore.ai.spring.AIService.aiService;

public class Conversation {

    private final List<Message> msgs = new java.util.ArrayList<>();
    private Object[] toolsObjects;

    protected Conversation(String myName, SystemMessage systemMessage, Object... toolsObjects) {
        this.toolsObjects = toolsObjects;
        msgs.add(systemMessage != null ? systemMessage : SystemMessage.builder().text("""
                You are an AI model designed to answer have a conversation with %s.
                Keep answers clear and short paragraphs, avoid switching lines too much.
                Avoid answering in list format unless the user asks for it.
                """.formatted(myName != null ? myName : "a user")).build());
    }

    public String talk(String yourMessage) {
        return chat(yourMessage, toolsObjects, aiService.Conversationist);
    }
    public String askForInformation(String yourMessage) {
        return chat(yourMessage, toolsObjects, aiService.Informationist);
    }
    public String askToChooseBetweenItems(String yourMessage) {
        return chat(yourMessage, toolsObjects, aiService.ItemChooser);
    }

    private @Nullable String chat(String yourMessage, Object[] toolsObjects, ChatOptions options) {
        msgs.add(UserMessage.builder().metadata(Map.of("timestamp", System.currentTimeMillis())).text(yourMessage).build());
        msgs.add(aiService.doPrompt(Prompt.builder().messages(msgs).chatOptions(options).build(), toolsObjects));
        return msgs.getLast().getText();
    }

    public List<Message> getMessages() {
        return msgs;
    }
    public void add(Message msg) {
        msgs.add(msg);
    }

}
