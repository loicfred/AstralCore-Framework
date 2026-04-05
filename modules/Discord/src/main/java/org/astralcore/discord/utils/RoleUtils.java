package org.astralcore.discord.utils;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.middleman.GuildChannel;
import net.dv8tion.jda.api.interactions.InteractionHook;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.astralcore.discord.lang.L10N.SYSL;
import static org.astralcore.discord.lang.L10N.SYSLG;
import static org.astralcore.discord.utils.LogUtils.LogSlash;

public class RoleUtils {

    public static boolean isAdmin(Member member) {
        return Objects.requireNonNull(member).hasPermission(Permission.ADMINISTRATOR);
    }

    public static boolean isAdmin(InteractionHook M, Member member) {
        if (isAdmin(member)) return true;
        M.editOriginal(SYSL(M, "reply-failed-not-enough-permission-you", "ADMINISTRATOR")).queue();
        return false;
    }

    public static boolean hasPermissionOverRole(InteractionHook M, Role R) {
        if (R == null && M != null) {
            M.editOriginal(SYSL(M, "missing-role")).queue();
        } else if (R != null) {
            Member U = R.getGuild().getSelfMember();
            if (U.canInteract(R)) return true;
            if (M != null) {
                M.editOriginal(SYSL(M, "role-access-interact-fail", R.getName())).queue();
            } else {
                LogSlash(R.getGuild(), SYSLG(R.getGuild(), "role-access-interact-fail", R.getName()));
            }
        }
        return false;
    }
    public static boolean hasPermissionInChannel(InteractionHook M, GuildChannel C, Permission... Perm) {
        if (C == null && M != null) {
            M.editOriginal(SYSL(M, "missing-channel")).queue();
        } else if (C != null) {
            Member U = C.getGuild().getSelfMember();
            if (U.hasPermission(C, Perm)) return true;
            List<Permission> MissingPerms = Arrays.stream(Perm).filter(P -> !U.hasPermission(C, P)).toList();
            if (M != null) {
                M.editOriginal("**[" + C.getAsMention() + "]** " + SYSL(M, "missing-perm") + "\n" + MissingPerms.stream().map(P -> "> - " + P.getName()).collect(Collectors.joining("\n"))).queue();
            } else {
                LogSlash(C.getGuild(), "**[" + C.getAsMention() + "]** " + SYSLG(C.getGuild(), "missing-perm") + "\n" + MissingPerms.stream().map(P -> "> - " + P.getName()).collect(Collectors.joining("\n")));
            }
        }
        return false;
    }
    public static boolean hasPermissionInChannelNoLog(GuildChannel C, Permission... Perm) {
        return C != null && C.getGuild().getSelfMember().hasPermission(C, Perm);
    }
}
