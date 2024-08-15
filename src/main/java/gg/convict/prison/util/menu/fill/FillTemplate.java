package gg.convict.prison.util.menu.fill;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import gg.convict.prison.util.menu.fill.impl.BorderFiller;
import gg.convict.prison.util.menu.fill.impl.FillFiller;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 17.12.2020 / 21:19
 * libraries / cc.invictusgames.libraries.menu.fill
 */

@RequiredArgsConstructor
@Getter
public enum FillTemplate {

    FILL(new FillFiller()),
    BORDER(new BorderFiller());

    private final IMenuFiller menuFiller;

}
