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


class Hopifier {
	
	static main(args) {
		def cli = new CliBuilder(usage:'Hopifier: [options] target...',header:'Options:')
		cli.o(longOpt:'output-file', args:1, argName:'dest', 'Output file name')
		cli.w(longOpt:'words', args:1, argName:'count', 'Desired words per column')
		
		def options = cli.parse(args)
		
		if(options.arguments == []) {
			cli.usage
			System.exit()
		}
		
		def outFilePath = options.o
		
		def wordCount = options.w == null ? 2 : Integer.parseInt(options.w)
		
		def inFilePath = options.arguments()[0]
		def inFile = new File(inFilePath)
		
		StringBuffer sb = new StringBuffer()
		inFile.eachLine{sb.append(it) }
		
		EyeHopParser parser = new EyeHopParser(wordCount)
		def content = parser.parse(sb.toString()).hopifiedContent
		def formatter = new EyeHopFormatter(wordCount)
		def formattedContent = formatter.format(content)
		
		if(outFilePath != null) {
			def outFile = new File(outFilePath)
			if(outFile.exists()) {
				outFile.write('') // clear existing content
			}
			
			
			formattedContent.each { outFile << it+'\n' }
		}else {
			println formattedContent
		}
	}
}
