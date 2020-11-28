package tech.wyss.tts;

import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class Controller {
    private static int counter = 0;
    private static final int MAX_FILES = 5;

    @PostMapping("/tts")
    public TtsUri generate(@RequestBody TtsRequest body) {
        String fileName = getNextFileName();
        SpeechConfig speechConfig = SpeechConfig.fromSubscription(System.getenv("SPEECH_KEY"), System.getenv("SPEECH_REGION"));
        AudioConfig audioConfig = AudioConfig.fromWavFileOutput(Paths.get(System.getenv("AUDIO_PATH"), fileName).toString());
        speechConfig.setSpeechSynthesisLanguage("de-DE");
        SpeechSynthesizer synthesizer = new SpeechSynthesizer(speechConfig, audioConfig);
        synthesizer.SpeakText(body.getText());
        return new TtsUri("https://sonos.wyss.tech/tts/" + fileName);
    }

    private String getNextFileName() {
        if (counter == MAX_FILES) {
            counter = 0;
        }
        return "tts-" + counter++ + ".wav";
    }
}
