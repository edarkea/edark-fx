package com.edarkea.edark.core.ui;

import com.edarkea.edark.core.enums.ActionEnum;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Steeven Ayui
 */

@Getter
@Setter
public class OptionUIModel {

    private ActionEnum type;
    private String name;
    private String action;
}
