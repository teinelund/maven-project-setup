package org.teinelund.console.application.mps.validation;

import org.junit.jupiter.api.Test;
import org.teinelund.console.application.mps.argumentparser.ArgumentsVO;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class ArgumentValidatorTest {

    @Test
    void validateArgumentsWhereHelpIsSetTrue() throws IOException {
        // Initialize
        ArgumentsVO arguments = new ArgumentsVO.Builder().setIsHelp(true).setIsVersion(false).build();
        ArgumentValidator sut = new ArgumentValidator();
        // Test
        sut.validateArguments(arguments);
        // Verify
    }

    @Test
    void validateArgumentsWhereVersionIsSetTrue() throws IOException {
        // Initialize
        ArgumentsVO arguments = new ArgumentsVO.Builder().setIsHelp(false).setIsVersion(true).build();
        ArgumentValidator sut = new ArgumentValidator();
        // Test
        sut.validateArguments(arguments);
        // Verify
    }
}