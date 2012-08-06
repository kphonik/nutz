/**
 *
 * nutz - Markdown processor for JVM
 * Copyright (c) 2012, Sandeep Gupta
 * 
 * http://www.sangupta/projects/nutz
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 		http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package com.sangupta.nutz;

/**
 * Utility functions used when constructing the Abstract Syntax Tree
 * of the markup presented.
 * 
 * @author sangupta
 *
 */
public class MarkupUtils {

	/**
	 * Checks if the given text represents a valid email or not.
	 * 
	 * @param text the text to be tested
	 * 
	 * @return <code>true</code if the text represents a valid email, <code>false</code>
	 * otherwise
	 */
	public static boolean isEmail(String text) {
		if(text.contains("@") && text.contains(".")) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks if the given text is actually a hyperlink or not.
	 * 
	 * @param text the text to be tested
	 * 
	 * @return <code>true</code if the text represents a valid link, <code>false</code>
	 * otherwise
	 */
	public static boolean isHyperLink(String text) {
		if(text.contains("://") && text.contains(".")) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Extract title and link from the given link text
	 * 
	 * @param link the link text from which the actual link and the title
	 * need to be parsed and extracted
	 * 
	 * @return a string array of 2 objects, the first one being the parsed link and the
	 * 		second one being the title if present.
	 */
	public static String[] parseLinkAndTitle(String link) {
		String[] tokens = new String[2];

		int index = link.indexOf(Identifiers.SPACE);
		if(index == -1) {
			tokens[0] = link;
		} else {
			String text = link.substring(index).trim();
			if(text.charAt(0) == Identifiers.DOUBLE_QUOTES) {
				int pos = index;

				// find next quotes
				index = text.indexOf(Identifiers.DOUBLE_QUOTES, 1);
				if(index == -1) {
					// none found
					index = text.length(); 
				} else if((index + 1) != text.length()) {
					index = text.length();
				}
				
				tokens[0] = link.substring(0, pos);
				tokens[1] = text.substring(1, index);
			}
		}
		
		// make sure we remove the starting and closing angle brackets if present
		// this is a present for Daring Fireball test for 'Amps and angle encoding.text'
		// test
		String token = tokens[0];
		if(token.charAt(0) == '<' && token.charAt(token.length() - 1) == '>') {
			tokens[0] = token.substring(1, token.length() - 1);
		}
		
		return tokens; 
	}

}