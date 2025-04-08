package com.triviuminds.dmn.model;

import lombok.Builder;
import lombok.Data;

/* *
 * A wrapper around DMN JSON representation of DMN XML artifact.
 */
@Data
@Builder
public class DMNWrapper {
    private String metaData;
    private String dmnModel;
}
