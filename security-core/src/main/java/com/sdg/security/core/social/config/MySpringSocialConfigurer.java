/**
 * 
 */
package com.sdg.security.core.social.config;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * @author zhailiang
 *
 */
public class MySpringSocialConfigurer extends SpringSocialConfigurer {
	
	private String filterProcessesUrl;
	
	public MySpringSocialConfigurer(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl(filterProcessesUrl);
		return (T) filter;
	}

}
