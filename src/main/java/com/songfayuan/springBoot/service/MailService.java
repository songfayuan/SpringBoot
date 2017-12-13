/**
 * 项目名称：spring-boot
 * 项目包名：com.songfayuan.springBoot.service
 * 创建时间：2017年12月13日上午10:51:53
 * 创建者：Administrator-宋发元
 * 创建地点：杭州
 */
package com.songfayuan.springBoot.service;

/**
 * 描述：发送邮件接口
 * @author songfayuan
 * 2017年12月13日上午10:51:53
 */
public interface MailService {

	/**
	 * 描述：发送普通（文本）邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @author songfayuan
	 * 2017年12月13日上午11:08:12
	 */
	public void sendSimpleMail(String to, String subject, String content);
	
	/**
	 * 描述：发送html邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @author songfayuan
	 * 2017年12月13日上午11:07:57
	 */
	public void sendHtmlMail(String to, String subject, String content);
	
	/**
	 * 描述：发送附件邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @param filePath
	 * @author songfayuan
	 * 2017年12月13日上午11:07:41
	 */
	public void sendAttachmentMail(String to, String subject, String content, String filePath);
	
	/**
	 * 描述：发送正文中有静态资源（图片）的邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @param resourcePath
	 * @param contentId
	 * @author songfayuan
	 * 2017年12月13日上午11:25:31
	 */
	public void sendInlineResourceMail(String to, String subject, String content, String resourcePath, String contentId);
	
}
