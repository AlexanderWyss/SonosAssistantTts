package tech.wyss.tts;

import com.microsoft.cognitiveservices.speech.SpeechConfig;
import com.microsoft.cognitiveservices.speech.SpeechSynthesizer;
import com.microsoft.cognitiveservices.speech.audio.AudioConfig;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;

@RestController
@RequestMapping("/api")
public class Controller {
    @PostMapping("/tts")
    public TtsUri generate() {
        SpeechConfig speechConfig = SpeechConfig.fromSubscription(System.getenv("SPEECH_KEY"), System.getenv("SPEECH_REGION"));
        AudioConfig audioConfig = AudioConfig.fromWavFileOutput(Paths.get(System.getenv("AUDIO_PATH"),"test.wav").toString());

        SpeechSynthesizer synthesizer = new SpeechSynthesizer(speechConfig, audioConfig);
        synthesizer.SpeakText("A simple test to write to a file.");
        return new TtsUri("https://sonos.wyss.tech/kajlfdsjaölgds");
    }
}
