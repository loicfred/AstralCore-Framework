package org.astralcore.discord.core;

import net.dv8tion.jda.api.components.ModalTopLevelComponent;
import net.dv8tion.jda.api.components.buttons.Button;
import net.dv8tion.jda.api.components.selections.EntitySelectMenu;
import net.dv8tion.jda.api.components.selections.SelectOption;
import net.dv8tion.jda.api.components.selections.StringSelectMenu;
import net.dv8tion.jda.api.interactions.Interaction;
import net.dv8tion.jda.api.modals.Modal;

import java.util.List;

public class CMD {
    protected Interaction IT;

    protected String TL(String key, Object... var) {
        return org.astralcore.discord.lang.L10N.TL(IT, key, var);
    }
    protected String TLG(String key, Object... var) {
        return org.astralcore.discord.lang.L10N.TLG(IT.getGuild(), key, var);
    }

    protected Button makeButton(Class<? extends ButtonCMD> button, Interaction event, String... metadata) {
        try {
            ButtonCMD BTN = button.getDeclaredConstructor().newInstance();
            BTN.IT = event;
            String id = BTN.getData().id() + "/" + String.join("/", metadata);
            if (id.length() > Button.ID_MAX_LENGTH) throw new RuntimeException("Button ID is too long for " + id);
            return Button.primary(id, TL(BTN.getData().label())).withStyle(BTN.getData().style());
        } catch (Exception ignored) {
            return null;
        }
    }

    protected Modal makeModal(Class<? extends ModalCMD> modal, Interaction event, List<ModalTopLevelComponent> components, String... metadata) {
        try {
            ModalCMD Mdl = modal.getDeclaredConstructor().newInstance();
            Mdl.IT = event;
            String id = Mdl.getData().id() + "/" + String.join("/", metadata);
            if (id.length() > Button.ID_MAX_LENGTH) throw new RuntimeException("Modal ID is too long for " + id);
            return Modal.create(id, TL(Mdl.getData().title()))
                    .addComponents(components).build();
        } catch (Exception ignored) {
            return null;
        }
    }

    protected StringSelectMenu makeStringSelectMenu(Class<? extends StringSelectCMD> select, Interaction event, List<SelectOption> options, String... metadata) {
        try {
            StringSelectCMD Menu = select.getDeclaredConstructor().newInstance();
            Menu.IT = event;
            String id = Menu.getData().id() + "/" + String.join("/", metadata);
            if (id.length() > Button.ID_MAX_LENGTH) throw new RuntimeException("String Select ID is too long for " + id);
            return StringSelectMenu.create(id)
                    .setPlaceholder(TL(Menu.getData().placeholder()))
                    .setRequiredRange(Menu.getData().minValues(), Menu.getData().maxValues()).addOptions(options)
                    .setRequired(Menu.getData().required()).build();

        } catch (Exception ignored) {
            return null;
        }
    }

    protected EntitySelectMenu makeUserSelectMenu(Class<? extends EntitySelectCMD> select, Interaction event, String... metadata) {
        return makeEntitySelectMenu(select, event, EntitySelectMenu.SelectTarget.USER, metadata);
    }
    protected EntitySelectMenu makeChannelSelectMenu(Class<? extends EntitySelectCMD> select, Interaction event, String... metadata) {
        return makeEntitySelectMenu(select, event, EntitySelectMenu.SelectTarget.CHANNEL, metadata);
    }
    protected EntitySelectMenu makeRoleSelectMenu(Class<? extends EntitySelectCMD> select, Interaction event, String... metadata) {
        return makeEntitySelectMenu(select, event, EntitySelectMenu.SelectTarget.ROLE, metadata);
    }

    private EntitySelectMenu makeEntitySelectMenu(Class<? extends EntitySelectCMD> select, Interaction event, EntitySelectMenu.SelectTarget target, String... metadata) {
        try {
            EntitySelectCMD Menu = select.getDeclaredConstructor().newInstance();
            Menu.IT = event;
            String id = Menu.getData().id() + "/" + String.join("/", metadata);
            if (id.length() > Button.ID_MAX_LENGTH) throw new RuntimeException("Entity Select ID is too long for " + id);
            return EntitySelectMenu.create(id, target)
                    .setPlaceholder(TL(Menu.getData().placeholder()))
                    .setRequiredRange(Menu.getData().minValues(), Menu.getData().maxValues())
                    .setRequired(Menu.getData().required()).build();
        } catch (Exception ignored) {
            return null;
        }
    }
}
