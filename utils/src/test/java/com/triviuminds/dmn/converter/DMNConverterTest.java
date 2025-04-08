package com.triviuminds.dmn.converter;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.kie.dmn.api.core.DMNMessage;
import org.kie.dmn.core.compiler.DMNProfile;
import org.kie.dmn.core.compiler.profiles.ExtendedDMNProfile;
import org.kie.dmn.validation.DMNValidator;
import org.kie.dmn.validation.DMNValidatorFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static com.jayway.jsonpath.matchers.JsonPathMatchers.hasJsonPath;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DMNConverterTest {
    private static final String resourcePath = System.getProperty("user.dir") + "/src/test/resources/";
    private static final DMNValidator validator = DMNValidatorFactory.newValidator(List.of((DMNProfile) new ExtendedDMNProfile()));

    @Test
    public void covertToJson() throws IOException {
        String json = DMNConverter.convertToJson(read("sum.dmn"));
        assertThat(json, hasJsonPath("$.dmn:definitions.-name", is("sum")));
    }

    @Test
    public void convertToXml() throws IOException {
        String json = DMNConverter.convertToJson(read("sum.dmn"));
        String xml = DMNConverter.convertToXml(json);
        List<DMNMessage> validationMessages = validator.validate(new StringReader(xml),
                DMNValidator.Validation.VALIDATE_SCHEMA,
                DMNValidator.Validation.VALIDATE_MODEL,
                DMNValidator.Validation.VALIDATE_COMPILATION,
                DMNValidator.Validation.ANALYZE_DECISION_TABLE);
        assertEquals(validationMessages.size(), 0);
    }

    private static String read(String dmnfile) throws IOException {
        InputStream fileStream = new FileInputStream(resourcePath + dmnfile);
        return IOUtils.toString(fileStream, StandardCharsets.UTF_8);
    }
}
