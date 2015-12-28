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

class EyeHopPair {
	
	def String column1
	def String column2
	int columnCount
	def EyeHopPair(String column1) {
		this.column1=column1
		columnCount++
	}
	
	def EyeHopPair(String column1,String column2) {
		this(column1)
		this.column2=column2
		columnCount++
	}
}
