package pl.blaszak.logsGenerator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.assertj.core.api.Assertions.assertThat;

class TextServiceTest {

    private TextService service;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        service = new TextService(LogsGeneratorUtils.loadResourceFile("loremIpsum.txt"));
    }

    @Test
    void shouldReturnFirstTextLine() {
        String line = service.getLine(0);
        assertThat(line).isNotNull().isNotEmpty();
    }

    @Test
    void shouldReturnFirstLineAsLastLinePlusOne() {
        // given
        String firstLine = service.getLine(0);
        int contentLength = service.getContentLength();
        // when
        String line = service.getLine(contentLength);
        // then
        assertThat(line).isEqualTo(firstLine);
    }
}