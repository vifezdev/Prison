package gg.convict.prison.util.command.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import gg.convict.prison.util.command.data.Data;
import gg.convict.prison.util.command.parameter.ParameterType;

import java.util.List;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 03.06.2020 / 10:29
 * libraries / cc.invictusgames.libraries.commandapi.parameter
 */

@Getter
@AllArgsConstructor
public class ParameterData implements Data {

    private final String name;
    private final String defaultValue;
    private final Class<?> type;
    private final boolean wildCard;
    private final ParameterType parameterType;
    private final List<String> completionFlags;
    private final int index;

}
