package org.astralcore.discord.utils;

import net.dv8tion.jda.api.entities.Guild;

import java.util.function.Consumer;

import static org.astralcore.discord.core.BotBuilder.LogChannel;
import static org.astralcore.core.util.TimeUtils.getNow;

public class LogUtils {

    public static void LogSlash(Guild guild, String log) {
        if (guild != null) {
            log = log.replaceAll("(\\*_`)", "");
            System.out.println("[" + getNow("HH:mm:ss") + "]" + log);
            if (LogChannel != null) LogChannel.sendMessage(log).queue();
        } else {
            LogSlash(log);
        }
    }
    public static void LogSlash(String log) {
        log = log.replaceAll("(\\*_)", "");
        System.out.println("[" + getNow("HH:mm:ss") + "] " + log);
        if (LogChannel != null) LogChannel.sendMessage(log).queue();
    }

}
