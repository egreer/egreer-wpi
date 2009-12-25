/*
 * Created on Nov 25, 2009
 * 
 * Copyright (C) 2008, Gary Pollice, Worcester Polytechnic Institute, gpollice@cs.wpi.edu. 
 *
 * The program is provided under the terms and conditions of
 * the Eclipse Public License Version 1.0 ("EPL"). A copy of the EPL
 * is available at http://www.eclipse.org/org/documents/epl-v10.php.
 */
package hw5;

import java.util.*;

/**
 * This bean provides the base image names.
 * 
 * @author gpollice
 * @author Eric Greer
 * @author Jason Codding
 * 
 */
public class PictureNameBean
{
	private Collection<String> baseNames;
	private String urlBase;
	private String picture;
	/**
	 * The constructor fills in the picture names. It could initialize these
	 * from a file, but for this example, it does this from a set set of strings.
	 */
	public PictureNameBean()
	{
		urlBase = "http://www.cartoonspot.net/looney-tunes/picture";
		baseNames = new ArrayList<String>();
		baseNames.add("daffy-duck-1");
		baseNames.add("daffy-duck-2");
		baseNames.add("daffy-duck-3");
		baseNames.add("daffy-duck-4");
		baseNames.add("daffy-duck-5");
		baseNames.add("bugs-bunny-1");
		baseNames.add("bugs-bunny-2");
		baseNames.add("bugs-bunny-3");
		baseNames.add("bugs-bunny-4");
		baseNames.add("bugs-bunny-5");
		baseNames.add("coyote-1");
		baseNames.add("coyote-2");
		baseNames.add("coyote-3");
		baseNames.add("coyote-4");
		baseNames.add("coyote-5");
		baseNames.add("coyote-6");
	}

	/**
	 * @return the value of baseNames
	 */
	public Collection<String> getBaseNames()
	{
		return baseNames;
	}

	/**
	 * Set baseNames to the value specified
	 * @param baseNames the new value of baseNames
	 */
	public void setBaseNames(Collection<String> baseNames)
	{
		this.baseNames = baseNames;
	}

	/**
	 * @return the value of urlBase
	 */
	public String getUrlBase()
	{
		return urlBase;
	}

	/**
	 * Set urlBase to the value specified
	 * @param urlBase the new value of urlBase
	 */
	public void setUrlBase(String urlBase)
	{
		this.urlBase = urlBase;
	}
	
	public String getIcoURL(){
		return urlBase + "/ico-" + picture + ".jpg"; 		
	}

	public String getLargeURL(){
		return urlBase + "/" + picture + ".jpg"; 		
	}
	
	public void setPicture(String picture){
		this.picture = picture;
	}
}
