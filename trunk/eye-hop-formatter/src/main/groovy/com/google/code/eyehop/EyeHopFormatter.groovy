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

import java.util.Iterator;

class EyeHopFormatter {
	
	private static final int COL_SPACING = 8;
	private final wordsPerColumn
	
	public EyeHopFormatter(int wordsPerColumn) {
		this.wordsPerColumn=wordsPerColumn
	}
	
	public List<String> format(List<String> content) {
		
		def formattedContent = [];
		for (Iterator it = content.iterator(); it.hasNext();) {
			String col1 = (String) it.next();
			String col2 = it.hasNext() ? it.next() : ""
			
			int spaceBetweenCols = COL_SPACING - wordsPerColumn
			
			String spaces = "        "[1..spaceBetweenCols]
			int maxColWidth = content*.size().max()
			
			String col1Format = "%"+maxColWidth+"s"
			String spacesformat = "%s"
			String col2Format = "%-"+maxColWidth+"s"
			String contentFormat = col1Format+spacesformat+col2Format
			formattedContent.add( String.format(contentFormat, col1,spaces,col2))
		}		
		
		return formattedContent;
	}
}
