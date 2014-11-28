import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.io.IOException;

/**
 * Created by dominic-althaus on 28.11.14.
 */
public class Testsphinx {
    private enum Language {
        de, en
    }

    private final static Language LANG = Language.de;

    public static void main(String[] args) throws IOException {
        Configuration configuration = new Configuration();

        switch (LANG) {
            case en:
                // Set path to acoustic model.
                configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/acoustic/wsj_8kHz");
                // Set path to dictionary.
                configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/acoustic/wsj_8kHz/dict/cmudict.0.6d");
                // Set language model.
                configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/language/en-us.lm.dmp");

                configuration.setGrammarName("test-en");
                configuration.setGrammarPath("res/");
                configuration.setUseGrammar(true);
                break;

            case de:
                // Set path to acoustic model.
                configuration.setAcousticModelPath("res/model-de/acoustic-model-de");
                // Set path to dictionary.
                configuration.setDictionaryPath("res/model-de/voxforge_de_sphinx.dic");
                // Set language model.
                configuration.setLanguageModelPath("res/model-de/voxforge_de_sphinx_lm");

                configuration.setGrammarName("test-de");
                configuration.setGrammarPath("res/");
                configuration.setUseGrammar(true);
                break;

            default:
                System.err.println("Language " + LANG + " is not supported.");
        }


        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);

        System.out.println("Start Recognition.");

        // Start recognition process pruning previously cached data.
        recognizer.startRecognition(true);
        SpeechResult result = recognizer.getResult();
        // Pause recognition process. It can be resumed then with startRecognition(false).
        recognizer.stopRecognition();

        System.out.println("Stopped Recognition.");

        System.out.println("Result: " + result.getHypothesis());
        System.out.println(result.getNbest(5).toString());
    }
}
