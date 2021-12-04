package CommandList;

import Utility.Option;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

public interface BlankCommand extends Runnable {
    String name = null;
    String description = null;

    void execute(SlashCommandEvent e);

    public String getName();
    public String getDescription();
    public ArrayList<Option> getParameters();
}
