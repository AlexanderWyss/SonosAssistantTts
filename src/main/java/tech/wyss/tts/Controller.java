package tech.wyss.tts;

import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class Controller {
    @PostMapping("/tts")
    public TtsUri generate(@RequestBody TtsRequest body) {
        SpeechConfig speechConfig = SpeechConfig.fromSubscription(System.getenv("SPEECH_KEY"), System.getenv("SPEECH_REGION"));
        AudioConfig audioConfig = AudioConfig.fromWavFileOutput(Paths.get(System.getenv("AUDIO_PATH"),"test.wav").toString());
        speechConfig.setSpeechSynthesisLanguage("de-DE");
        SpeechSynthesizer synthesizer = new SpeechSynthesizer(speechConfig, audioConfig);
        synthesizer.SpeakText(body.getText());
        return new TtsUri("https://sonos.wyss.tech/tts/test.wav");
    }
}
