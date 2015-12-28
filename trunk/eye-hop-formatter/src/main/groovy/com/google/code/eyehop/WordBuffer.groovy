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

import java.util.List;

class WordBuffer {
	private Integer charsInBuffer = 0
	private final List<String> colBuffer = []
	
	def addWord(String word) {
		colBuffer.add word
		charsInBuffer += word.length()
	}
	
	def clear() {
		colBuffer.clear()
		charsInBuffer=0
	}
	
	def boolean isEmpty() {
		return charsInBuffer == 0
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder()
		for (String word : colBuffer) {
			sb.append word
			sb.append ' '
		}
		// remove trailing space
		return sb.toString().trim()
	}
	
	/**
	 * Enforce read only on non final field
	 * http://jira.codehaus.org/browse/GROOVY-3010
	 */
	private void setCharsInBuffer(Integer i) {
		throw new IllegalAccessException("charsInBuffer is read only")
	}
}
