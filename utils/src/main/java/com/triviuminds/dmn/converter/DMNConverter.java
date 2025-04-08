package com.triviuminds.dmn.converter;


import com.github.underscore.U;

/**
 * A utility to convert DMN Model XML artifact to JSON representation and vice versa.
 */
public class DMNConverter {

    /**
     * <p>Convert dmn model xml artifact to its json representation.</p>
     * @param xmlContent
     * @return json string
     */
    public static String convertToJson(String xmlContent) {
       return U.xmlToJson(xmlContent);
    }

    /**
     * <p>Convert dmn model json representation to its xml artifact.</p>
     * @param jsonContent
     * @return xml string
     */
    public static String convertToXml(String jsonContent) {
        return U.jsonToXml(jsonContent);
    }
}
