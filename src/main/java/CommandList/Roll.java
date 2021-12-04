package CommandList;
import Utility.MessageFunctions;
import Utility.Option;
import com.bernardomg.tabletop.dice.history.RollHistory;
import com.bernardomg.tabletop.dice.history.RollResult;
import com.bernardomg.tabletop.dice.interpreter.DiceInterpreter;
import com.bernardomg.tabletop.dice.interpreter.DiceRoller;
import com.bernardomg.tabletop.dice.parser.DefaultDiceParser;
import com.bernardomg.tabletop.dice.parser.DiceParser;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyAction;

import java.awt.*;
import java.util.ArrayList;

public class Roll implements BlankCommand{

    String name = "roll";
    String description = "Rolls dice";
    SlashCommandEvent e;
    ArrayList<Option> parameters = new ArrayList<Option>();
    public static DiceParser parser = new DefaultDiceParser();

    public Roll() {
        this.parameters.add(new Option(
                OptionType.STRING,
                "roll",
                "What dice to roll",
                true));
    }

    public Roll(SlashCommandEvent e) {
        this.e = e;

        new Thread(this).start();
    }

    @Override
    public void run() {
        RollHistory rolls;
        DiceInterpreter<RollHistory> roller = new DiceRoller();
        e.deferReply().queue();
        try {
            rolls = parser.parse(e.getOption("roll").getAsString(), roller);
        } catch (Exception ex) {
            e.getHook().sendMessageEmbeds(MessageFunctions.makeEmbed(
                    "Error",
                    "Incorrect roll format.",
                    Color.red)).queue();
            return;
        }
        int c = 0;
        String s = "";
        for (RollResult r : rolls.getRollResults()) {
            if (c > 5)
                break;
            if (c == 0) {
                s = "`" + r.getTotalRoll().toString() + "` ";
            }
            else if (r.getTotalRoll() < 0) {
                s = s + "- `" + Integer.toString(Math.abs(r.getTotalRoll())) + "` ";

            }
            else {
                s = s + "+ `" + r.getTotalRoll().toString() + "` ";
            }
            c = c + 1;
        }
        if (c > 5 || c < 2) {
            e.getHook().sendMessageEmbeds(MessageFunctions.makeEmbed(
                    "Alex Rolled",
                    String.format("%s: `%s`", e.getOption("roll").getAsString(), rolls.getTotalRoll()),
                    Color.CYAN)).queue();

        } else {
            e.getHook().sendMessageEmbeds(MessageFunctions.makeEmbed(
                    "Alex Rolled",
                    String.format("%s: %s = `%s`", e.getOption("roll").getAsString(), s, rolls.getTotalRoll()),
                    Color.CYAN)).queue();
        }
    }

    @Override
    public void execute(SlashCommandEvent e) {
        new Roll(e);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public ArrayList<Option> getParameters() {
        return this.parameters;
    }
}
