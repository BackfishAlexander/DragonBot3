package Utility;

import net.dv8tion.jda.api.interactions.commands.OptionType;

public class Option {
    OptionType type;
    String name;
    String description;
    Boolean isRequired;

    public Option(OptionType type, String name, String description, Boolean isRequired) {
        this.type = type;
        this.name = name;
        this.description = description;
        this.isRequired = isRequired;
    }

    public OptionType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getRequired() {
        return isRequired;
    }
}
