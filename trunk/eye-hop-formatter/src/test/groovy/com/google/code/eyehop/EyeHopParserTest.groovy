/**
 * Copyright (C) 2010 Tim Myerscough
 *
 * This file is part of eye-hop-formatter - http://code.google.com/p/eye-hopifier/
 *
 * eye-hop-formatter is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.google.code.eyehop;


import org.junit.Before;
import org.junit.Test;

import com.google.code.eyehop.EyeHopParser;

import static org.junit.Assert.*;

class EyeHopParserTest {
	
	def EyeHopParser classUnderTest = null;
	def Collection<String> output
	
	@Before
	def void before() {
		def columns = 2;
		classUnderTest = new EyeHopParser(columns);
		
		assertNotNull(classUnderTest);
		assertEquals(columns, classUnderTest.wordsPerColumn);
	}
	
	@Test
	def void splitColumnSonAverageWordLength() {
		expectTheFollowingResult("the")
	}
	
	@Test
	def void splitColumnSonAverageWordLength2() {
		expectTheFollowingResult("the quick")
	}
	
	@Test
	def void splitColumnSonAverageWordLength3() {
		expectTheFollowingResult("large words","seperate columns")
	}
	
	@Test
	def void seraratePunctuation() {
		expectTheFollowingResult("the quick brown","fox jumped." ,"Over the lazy" ,"dog")
	}
	
	@Test
	def void seraratePunctuation2() {
		expectTheFollowingResult("small words","are still." ,"Split by punctuation." ,"It's only.", "Scentences","that split.");
	}
	@Test
	def void seraratePunctuation3() {
		expectTheFollowingResult("Prefer splitting","scentences." ,"It is better." ,"Than keeping", "to the word count.");
	}
	
	@Test
	def void seraratePunctuationBelowAverageWordLength() {
		expectTheFollowingResult("123456789.","123456789.","123456789.")
	}
	@Test
	def void seraratePunctuationAboveAverageWordLength() {
		expectTheFollowingResult("123 45678901.","123 45678901.","123 45678901.")
	}
	@Test
	def void seraratePunctuationBelowAverageWordLength2() {
		expectTheFollowingResult("12345678.","12345678.","12345678.")
	}
	@Test
	def void seraratePunctuationAverageWordLengthMultiCol4() {
		def columns=4
		classUnderTest = new EyeHopParser(columns)
		expectTheFollowingResult("1234567890123456789.","1234567890123456789.","1234567890123456789.")
	}
	@Test
	def void seraratePunctuationAverageWordLengthMultiCol3() {
		def columns=3
		classUnderTest = new EyeHopParser(columns)
		expectTheFollowingResult("12345678901234.","12345678901234.","12345678901234.")
	}
	
	
	
	@Test
	def void seraratePunctuationTinyWords() {
		expectTheFollowingResult("a a a a a.","A.","A a a a a a a a a.","A a a a a a a a a a.", "A")
	}
	@Test
	def void seraratePunctuation6() {
		// TODO Only scentence punctuation splits - abbreviation using '.' doesn't
		expectTheFollowingResult("a b c etc. a b c", "a b c, a b c, a b", "a B c.", "D e f g h.");
	}
	
	@Test
	def void tinyWordsStickTogether() {
		expectTheFollowingResult("a a a a a a a a a a","a a a a a a a a a a","a a a a a a a a a a")
	}
	
	@Test
	def void tinyWordsStickTogetherMultiCol() {
		def columns = 3;
		classUnderTest = new EyeHopParser(columns);
		expectTheFollowingResult("a a a a a a a a a a a a a a a","a a a a a a a a a a a a a a a","a a a a a a a a a a a a a a a")
	}
	
	@Test
	def void shortWordsStickTogether() {
		expectTheFollowingResult("words shorter", "than the average", "word length", "are kept together.", "If the current", "column length", "times the hop", "length is less", "than the average", "word length", "times the hop", "length then", "add another", "word to the column.")
	}
	
	@Test
	def void longWordsAreSplitUp() {
		expectTheFollowingResult("Seperation", "depends upon", "word length.");
	}
	
	@Test
	def void longWordsAreSplitUpAverageWordLength() {
		expectTheFollowingResult("1234567890","1234567890","1234567890")
	}
	@Test
	def void longWordsAreSplitUpAverageWordLengthMultiCol4() {
		def columns=4
		classUnderTest = new EyeHopParser(columns)
		expectTheFollowingResult("12345678901234567890","12345678901234567890","12345678901234567890")
	}
	@Test
	def void longWordsAreSplitUpAverageWordLengthMultiCol3() {
		def columns=3
		classUnderTest = new EyeHopParser(columns)
		expectTheFollowingResult("123456789012345","123456789012345","123456789012345")
	}
	
	@Test
	def void longWordsAreSplitUpAboveAverageWordLength() {
		expectTheFollowingResult("12345678901","12345678901","12345678901")
	}
	@Test
	def void longWordsAreSplitUpBelowAverageWordLength() {
		expectTheFollowingResult("123456789 123456789","123456789 123456789","123456789 123456789")
	}
	
	@Test
	def void longWordsAreSplitUpBelowAverageWordLengthMultiCol3() {
		def columns=3
		classUnderTest = new EyeHopParser(columns)
		expectTheFollowingResult("12345678901234 12345678901234","12345678901234 12345678901234","12345678901234 12345678901234")
	}
	@Test
	def void longWordsAreSplitUpBelowAverageWordLengthMultiCol5() {
		def columns=5
		classUnderTest = new EyeHopParser(columns)
		expectTheFollowingResult("123456789012345678901234 123456789012345678901234","123456789012345678901234 123456789012345678901234","123456789012345678901234 123456789012345678901234")
	}
	
	@Test
	def void nonBreakingPunctuationIsCountedWhenSplittingWords() {
	}
	
	@Test
	def void longWordsAreSplitUpMultiColumn() {
		def columns = 5;
		classUnderTest = new EyeHopParser(columns);
		// 25 chars per column
		expectTheFollowingResult("Extradordinarily long words", "ensure restrictions reduce the", "word count per column.");
	}
	
	private def void expectTheFollowingResult(String... input ) {
		StringBuilder sb = new StringBuilder()
		for (String string : input) {
			sb.append string
			sb.append ' '
		}
		
		def wholeText = sb.toString().trim()
		classUnderTest.parse(wholeText);
		output = classUnderTest.hopifiedContent;
		
		assertNotNull(output)
		System.out.println(output);
		assertEquals(input.length, output.size)
		
		int i=0
		for (String column : output) {
			assertEquals(input[i++], column)
		}
	}
}
