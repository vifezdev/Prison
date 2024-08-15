package gg.convict.prison.util.command.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import gg.convict.prison.util.command.data.Data;

import java.util.List;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 03.06.2020 / 11:23
 * libraries / cc.invictusgames.libraries.commandapi.data
 */

@Getter
@AllArgsConstructor
public class FlagData implements Data {

    private final List<String> names;
    private final boolean defaultValue;
    private final String description;
    private final boolean hidden;
    private final int index;

}
