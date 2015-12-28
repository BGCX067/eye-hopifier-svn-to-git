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

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

class EyeHopFormatterTest {
	
	def classUnderTest = null
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void twoColFormatterHas6CharColSpacing() {
		run1RowTest (2, ["one","two"], "one      two")
	}
	@Test
	public void threeColFormatterHas5CharColSpacing() {
		run1RowTest (3, ["one","two"], "one     two")
	}
	@Test
	public void fourColFormatterHas4CharColSpacing() {
		run1RowTest (4, ["one","two"], "one    two")
	}
	@Test
	public void fiveColFormatterHas3CharColSpacing() {
		run1RowTest (5, ["one","two"], "one   two")
	}
	
	@Test
	public void multiRow() {
		classUnderTest=new EyeHopFormatter(2)
		def content = classUnderTest.format(["the quick","brown fox", "jumped over","the lazy","dog"] as List<String>)
		assertNotNull(content)
		def rows = 3
		assertEquals(rows, content.size())
		assertEquals("  the quick      brown fox  ", content[0]);
		assertEquals("jumped over      the lazy   ", content[1]);
		assertEquals("        dog                 ", content[2]);
	}
	
	private run1RowTest(int wordsPerCol, input, expected) {
		classUnderTest=new EyeHopFormatter(wordsPerCol)
		def content = classUnderTest.format(input as List<String>)
		assertNotNull(content)
		assertEquals(1, content.size())
		assertEquals(expected,content[0])
	}
}
