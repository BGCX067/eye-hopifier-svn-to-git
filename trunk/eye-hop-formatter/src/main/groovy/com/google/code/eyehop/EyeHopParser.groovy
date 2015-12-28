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

package com.google.code.eyehop

class EyeHopParser {
	
	public final int AVERAGE_WORD_LENGTH_ENGLISH = 5;
	
	private int wordsPerColumn;
	private final List<String> hopifiedContent = [];
	private final int preferredCharsPerColumn 
	
	private String[] words
	private final WordBuffer buffer = new WordBuffer()
	
	EyeHopParser(int wordsPerColumn) {
		this.wordsPerColumn = wordsPerColumn
		preferredCharsPerColumn = AVERAGE_WORD_LENGTH_ENGLISH * wordsPerColumn
	}
	
	def EyeHopParser parse(String input) {
		words = input.split(' ')
		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			
			buffer.addWord word
			
			if(isEndOfColumn(i)) {
				createColumnFromBuffer()
			}
		}
		
		if(!buffer.isEmpty()) {
			createColumnFromBuffer()
		}
		
		return this
	}
	
	private boolean isEndOfColumn(int currentIndex) {
		return isEnfOfScentence(currentIndex) || buffer.charsInBuffer >= preferredCharsPerColumn
	}
	
	private boolean isEnfOfScentence(int currentIndex) {
		//		assert(currentIndex >= 0)
		String currentWord = words[currentIndex]
		if(!currentWord.endsWith(".")) {
			return false
		}
		
		if(isEndOfInput(currentIndex)) {
			return true
		}else {
			
			def nextIndex = currentIndex+1;
			
			String nextWord = words[nextIndex]
			def startChar = nextWord.charAt(0)
			return startChar.isUpperCase() || startChar.isDigit()
		}
	}
	
	private boolean isEndOfInput(int currentIndex) {
		return !isInputRemaining(currentIndex)
	}
	
	private boolean isInputRemaining(int currentIndex) {
		return currentIndex+1 < words.length
	}
	
	private createColumnFromBuffer() {
		String column = buffer.toString()
		hopifiedContent.add column
		initialiseBuffer()
	}
	
	private initialiseBuffer() {
		buffer.clear()
	}
}
