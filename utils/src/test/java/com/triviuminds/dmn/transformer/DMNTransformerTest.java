package com.triviuminds.dmn.transformer;

import com.triviuminds.dmn.converter.DMNConverter;
import com.triviuminds.dmn.model.DMNWrapper;
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

public class DMNTransformerTest {
    private static final String resourcePath = System.getProperty("user.dir") + "/src/test/resources/";
    private static final DMNValidator validator = DMNValidatorFactory.newValidator(List.of((DMNProfile) new ExtendedDMNProfile()));

    @Test
    public void split() throws IOException {
        DMNWrapper dmnWrapper = DMNTransformer.split(read("sum-wrapper.json"));
        //validate metadata
        assertThat(dmnWrapper.getMetaData(), hasJsonPath("$.status", is("production")));

        //validate dmn
        String xml = DMNConverter.convertToXml(dmnWrapper.getDmnModel());
        List<DMNMessage> validationMessages = validator.validate(new StringReader(xml),
                DMNValidator.Validation.VALIDATE_SCHEMA,
                DMNValidator.Validation.VALIDATE_MODEL,
                DMNValidator.Validation.VALIDATE_COMPILATION,
                DMNValidator.Validation.ANALYZE_DECISION_TABLE);
        assertEquals(validationMessages.size(), 0);
    }

    @Test
    public void concatenate() throws IOException {
        String metadata = read("dmn-metadata.json");
        String dmn = DMNConverter.convertToJson(read("sum.dmn"));
        DMNWrapper dmnWrapper = DMNWrapper.builder()
                .metaData(metadata)
                .dmnModel(dmn)
                .build();
        String json = DMNTransformer.concatenate(dmnWrapper);
        assertThat(json, hasJsonPath("$.metadata.version", is("v4.5")));
    }

    private static String read(String dmnfile) throws IOException {
        InputStream fileStream = new FileInputStream(resourcePath + dmnfile);
        return IOUtils.toString(fileStream, StandardCharsets.UTF_8);
    }
}
