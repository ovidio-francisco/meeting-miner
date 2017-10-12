package segmenter.algorithms;

import java.util.ArrayList;

import segmenter.AbstractSegmenter;

public class SentencesSegmenter extends AbstractSegmenter {

	@Override
	public ArrayList<String> getSegments(String text) {
		text = getPreprocess().cleanTextMeating(text);
		text = getPreprocess().identifyEOS(text, EOS_MARK);
		
		String[] sentences = text.split(EOS_MARK);
		
		ArrayList<String> result = new ArrayList<>();
		
		for(String s : sentences) {
			result.add(s);
		}
		
		return result;
	}

	@Override
	public String preprocessToString() {
		return "";
	}

	@Override
	public String getAlgorithmName() {
		return "SentencesSegmenter";
	}

	@Override
	public String paramsToString() {
		return "No Params";
	}

}
