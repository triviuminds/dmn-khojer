package com.triviuminds.dmn.transformer;

import com.github.underscore.U;
import com.triviuminds.dmn.model.DMNWrapper;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A utility to split/concatenate DMN JSON representation to DMNWrapper
 */
@SuppressWarnings("unchecked")
public class DMNTransformer {
    public static final String KEY_META_DATA = "metadata";
    public static final String KEY_DMN_MODEL = "dmn";

    /**
     * <p>Split a dmn json {"metadata":....., "dmnMode":...} to a DmnWrapper.</p>
     * @param jsonContent
     * @return the DmnWrapper object
     */
    public static DMNWrapper split(String jsonContent) {
        Map<String, Object> map = U.fromJsonMap(jsonContent);
        return DMNWrapper.builder()
                .metaData(U.toJson((Map<String, Object>) map.get(KEY_META_DATA)))
                .dmnModel(U.toJson((Map<String, Object>) map.get(KEY_DMN_MODEL)))
                .build();
    }

    /**
     * <p>Concatenate a DmnWrapper to a dmn json string {"metadata":....., "dmn":...}.</p>
     * @param dmnWrapper
     * @return the json string
     */
    public static String concatenate(DMNWrapper dmnWrapper) {
        Map<String, Object> contentMap = new LinkedHashMap<>();
        contentMap.put(KEY_META_DATA, U.fromJsonMap(dmnWrapper.getMetaData()));
        contentMap.put(KEY_DMN_MODEL, U.fromJsonMap(dmnWrapper.getDmnModel()));
        return U.toJson(contentMap);
    }
}
