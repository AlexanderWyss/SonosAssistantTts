package tech.wyss.tts;

public class TtsRequest {
    private String text;

    public TtsRequest(String text) {
        this.text = text;
    }

    public TtsRequest() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
