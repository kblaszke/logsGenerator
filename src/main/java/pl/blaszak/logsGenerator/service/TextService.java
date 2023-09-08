package pl.blaszak.logsGenerator.service;

public class TextService {

    private final String[] content;

    public TextService(String[] content) {
        this.content = content;
    }

    public String getLine(int number) {
        return content[number % content.length];
    }

    public int getContentLength() {
        return content.length;
    }
}
