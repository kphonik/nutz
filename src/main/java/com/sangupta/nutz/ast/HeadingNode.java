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

package com.sangupta.nutz.ast;

import java.util.Map;

/**
 * 
 * @author sangupta
 *
 */
public class HeadingNode extends Node {
	
	private int headCount;
	
	private Node textNode;
	
	public HeadingNode(int headCount, Node text) {
		this.headCount = headCount;
		this.textNode = text;
	}
	
	@Override
	public void write(StringBuilder builder, boolean atRootNode, Map<String, AnchorNode> referenceLinks) {
		builder.append("<h");
		builder.append(this.headCount);
		builder.append(">");
		textNode.write(builder, false, referenceLinks);
		builder.append("</h");
		builder.append(this.headCount);
		builder.append(">");
		builder.append(NEW_LINE);
		builder.append(NEW_LINE);
	}

	@Override
	public String toString() {
		return "HEADING: " + textNode.toString();
	}

	/**
	 * @return the headCount
	 */
	public int getHeadCount() {
		return headCount;
	}

	/**
	 * @return the textNode
	 */
	public Node getTextNode() {
		return textNode;
	}

}