package org.astralcore.discord.utils;

import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.interactions.InteractionHook;

import static org.astralcore.discord.lang.L10N.SYSL;
import static org.astralcore.discord.lang.L10N.SYSLG;
import static org.astralcore.discord.utils.LogUtils.LogSlash;

public class ChannelUtils {

    public static boolean isChannelOfType(InteractionHook M, GuildChannel C, ChannelType type) {
        if (C == null && M != null) {
            M.editOriginal(SYSL(M, "missing-channel")).queue();
        } else if (C != null) {
            if (C.getType().equals(type)) return true;
            if (M != null) {
                M.editOriginal("**[" + C.getAsMention() + "]** " + SYSL(M, "wrong-channel-type-text")).queue();
            } else {
                LogSlash(C.getGuild(), "**[" + C.getAsMention() + "]** " + SYSLG(C.getGuild(), "wrong-channel-type-text"));
            }
        }
        return false;
    }

}
