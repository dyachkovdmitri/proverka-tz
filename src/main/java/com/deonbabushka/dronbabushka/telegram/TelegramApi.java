package com.deonbabushka.dronbabushka.telegram;

import com.deonbabushka.dronbabushka.scenarios.FirstFilter;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.logging.FileHandler;


@Service
public class TelegramApi {
    private static String PROXY_HOST = "103.89.253.249" /* proxy host */;
    private static Integer PROXY_PORT = 3128 /* proxy port */;

    FirstFilter filter;
    @Autowired
    public TelegramApi(FirstFilter filter) {
        this.filter=filter;
        init();
    }

    private void init() {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        DefaultBotOptions botOptions = ApiContext.getInstance(DefaultBotOptions.class);
//        HttpHost httpHost = new HttpHost(PROXY_HOST, PROXY_PORT);
//
//        RequestConfig requestConfig = RequestConfig.custom().setProxy(httpHost).setAuthenticationEnabled(false).build();
//        botOptions.setRequestConfig(requestConfig);
//        botOptions.setProxyPort(PROXY_PORT);
//        botOptions.setProxyHost(PROXY_HOST);
        try {
            telegramBotsApi.registerBot(new Bot(botOptions, filter));
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
