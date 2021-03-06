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

import junit.framework.Assert;

import org.junit.Test;

/**
 * 
 * @author sangupta
 *
 */
public class MarkupUtilsTest {
	
	/**
	 * Tests for {@link MarkupUtils#isEmail(String)}
	 */
	@Test
	public void testIsEmail() {
		Assert.assertTrue(MarkupUtils.isEmail("sangupta@sangupta.com"));
		Assert.assertFalse(MarkupUtils.isEmail("sangupta"));
	}
	
	/**
	 * Tests for {@link MarkupUtils#isHyperLink(String)}
	 */
	@Test
	public void testIsHyperlink() {
		Assert.assertTrue(MarkupUtils.isHyperLink("http://www.google.com"));
		Assert.assertFalse(MarkupUtils.isHyperLink("sangupta"));
	}
	
	/**
	 * Tests for {@link MarkupUtils#parseLinkAndTitle(String)}
	 */
	@Test
	public void testParseLinkAndTitle() {
		String link = "http://att.com/  \"AT&T\"";
		String[] tokens = MarkupUtils.parseLinkAndTitle(link);
		
		Assert.assertEquals("http://att.com/", tokens[0]);
		Assert.assertEquals("AT&T", tokens[1]);
	}
	
	@Test
	public void testFindLeadingSpaces() {
		int[] tokens = MarkupUtils.findLeadingSpaces("abc");
		Assert.assertEquals(0, tokens[0]);
		Assert.assertEquals(0, tokens[1]);

		tokens = MarkupUtils.findLeadingSpaces(" abc");
		Assert.assertEquals(1, tokens[0]);
		Assert.assertEquals(1, tokens[1]);

		tokens = MarkupUtils.findLeadingSpaces("  abc");
		Assert.assertEquals(2, tokens[0]);
		Assert.assertEquals(2, tokens[1]);

		tokens = MarkupUtils.findLeadingSpaces("\tabc");
		Assert.assertEquals(1, tokens[0]);
		Assert.assertEquals(4, tokens[1]);

		tokens = MarkupUtils.findLeadingSpaces("\t\tabc");
		Assert.assertEquals(2, tokens[0]);
		Assert.assertEquals(8, tokens[1]);

		tokens = MarkupUtils.findLeadingSpaces(" \t abc");
		Assert.assertEquals(3, tokens[0]);
		Assert.assertEquals(6, tokens[1]);
	}
	
	@Test
	public void testIndexOfSkippingForPairCharacter() {
		Assert.assertEquals(7, MarkupUtils.indexOfSkippingForPairCharacter("(parens)", ')', '(', 1));
		Assert.assertEquals(9, MarkupUtils.indexOfSkippingForPairCharacter("(p(aren)s)", ')', '(', 1));
		
	}
	
	@Test
	public void testFindEndingTagPosition() {
		Assert.assertEquals(6, MarkupUtils.findEndingTagPosition("<hr />sandeep", 3, "hr"));
		Assert.assertEquals(7, MarkupUtils.findEndingTagPosition("<p></p>", 3, "p"));
		Assert.assertEquals(14, MarkupUtils.findEndingTagPosition("<p><p></p></p>", 3, "p"));
		Assert.assertEquals(18, MarkupUtils.findEndingTagPosition("<p><p><br></p></p>", 3, "p"));
		Assert.assertEquals(23, MarkupUtils.findEndingTagPosition("<p><p><br></p><br/></p>", 3, "p"));
		Assert.assertEquals(27, MarkupUtils.findEndingTagPosition("<p><p><br><hr></p><br/></p>", 3, "p"));
	}

}
