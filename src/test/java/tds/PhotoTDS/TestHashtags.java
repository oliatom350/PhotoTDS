package tds.PhotoTDS;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;


public class TestHashtags {

	@Test
	public void test() {
		ArrayList<String> hashtags = new ArrayList<String>();
		ArrayList<String> hashtagsCorrectos = new ArrayList<String>();
		hashtagsCorrectos.add("Esta"); hashtagsCorrectos.add("hashtags"); hashtagsCorrectos.add("obtencion"); hashtagsCorrectos.add("prueba");
		hashtags = Publicacion.getHashtags("#Esta es una #prueba de #obtencion de #hashtags");
		Collections.sort(hashtags);
		assertEquals("", hashtags, hashtagsCorrectos);
	}

}
