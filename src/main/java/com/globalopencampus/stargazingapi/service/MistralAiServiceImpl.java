package com.globalopencampus.stargazingapi.service;

import org.springframework.ai.mistralai.MistralAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MistralAiServiceImpl implements MistralAiService {

    @Autowired
    private MistralAiChatModel chat;

    @Override
    public String call(String prompt) {
        return chat.call(prompt);
    }
}
