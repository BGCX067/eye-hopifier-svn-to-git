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

import java.io.File;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

class HopifierTest {
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void outputToFile() {
		def inFilePath = "src/test/resources/input.txt"
		def outFilePath = "target/HopifierTest.txt"
		Hopifier.main( "-o",outFilePath, "-w","2", inFilePath );
		
		File outFile = new File(outFilePath)
		assertTrue(outFile.exists())
		
		def contentsOut=outFile.readLines()
		
		def expectedOut = new File('src/test/resources/expectedOut-2word.txt').readLines()
		assertTrue(expectedOut == contentsOut)
	}
}
