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
import groovy.lang.ReadOnlyPropertyException;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.google.code.eyehop.WordBuffer;

class WordBufferTest {
	
	WordBuffer classUnderTest
	
	@Before
	public void setUp() throws Exception {
		classUnderTest=new WordBuffer()
	}
	
	@Test
	public void testAddWordToBuffer() {
		classUnderTest.addWord("word")
		assertEquals(4, classUnderTest.charsInBuffer)
		assertFalse(classUnderTest.isEmpty())
	}
	
	@Test
	public void testClear(){
		classUnderTest.addWord("word");
		classUnderTest.clear();
		
		assertEquals(0,classUnderTest.charsInBuffer)
		assertEquals("", classUnderTest.toString())
		assertTrue(classUnderTest.isEmpty())
	}
	
	@Test(expected=IllegalAccessException.class)
	public void charsInBufferIsReadOnly() {
		classUnderTest.charsInBuffer = 2
		fail("Able to set charsInBuffer property directly: " + classUnderTest.charsInBuffer + ", buffer: '" + classUnderTest.toString()+"'")
	}
	
	@Test(expected=ReadOnlyPropertyException.class)
	public void BufferIsReadOnly() {
		classUnderTest.colBuffer = ["Thing"]
		fail("Able to set values in colBuffer directly: " + classUnderTest.colBuffer + ", charsInBuffer: " + classUnderTest.charsInBuffer)
	}
	
	@Test
	def void initialiseAsempty() {
		assertTrue(classUnderTest.isEmpty())
	}
}

