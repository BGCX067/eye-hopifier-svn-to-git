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

import org.junit.Test;

import com.google.code.eyehop.EyeHopPair;

import static org.junit.Assert.*;

class EyeHopPairTest {
	
	def EyeHopPair classUnderTest;
	@Test
	def void singleColumnPair(){
		def col1="the"
		classUnderTest=new EyeHopPair(col1)
		assertNotNull(classUnderTest)
		assertEquals(col1, classUnderTest.column1)
		assertEquals(1, classUnderTest.columnCount)
	}
	
	@Test
	def void doubleColumnPair() {
		def col1="the"
		def col2 = "quick"
		classUnderTest=new EyeHopPair(col1,col2)
		assertNotNull(classUnderTest)
		assertEquals(col1,classUnderTest.column1)
		assertEquals(col2,classUnderTest.column2)
		assertEquals(2, classUnderTest.columnCount)
	}
}
