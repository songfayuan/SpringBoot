/**
 * 项目名称：spring-boot
 * 项目包名：com.songfayuan.springBoot.service
 * 创建时间：2017年12月13日下午2:39:52
 * 创建者：Administrator-宋发元
 * 创建地点：杭州
 */
package com.songfayuan.springBoot.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * 描述：邮件模板单元测试
 * @author songfayuan
 * 2017年12月13日下午2:39:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

	@Autowired
	private MailService mailService;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	//接受者账户
	private String to = "1414798079@qq.com"; 
	
	@Test
	public void testSimpleMail() throws Exception{
		String subject = "测试发送普通（文本）邮件";
		String content = "这是一封用于测试发送普通文本邮件的信息...";
		mailService.sendSimpleMail(to, subject, content);
	}
	
	@Test
	public void testHtmlMail() throws Exception{
		String subject = "测试发送html邮件";
		String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
		mailService.sendHtmlMail(to, subject, content);
	}
	
	@Test
	public void sendAttachmentMail() throws Exception{
		String subject = "测试发送带附件的邮件";
		String content = "这是一封用于测试发送带附件文件邮件的信息...";
		String filePath="D:\\Icon Manager.rar";
		mailService.sendAttachmentMail(to, subject, content, filePath);
	}
	
	@Test
	public void sendInlineResourceMail() throws Exception{
		String contentId = "demo01";
		String subject = "测试发送有图片的邮件";
		String content = "<html><body>这是一封用于测试发送有图片邮件的信息：<img src=\'cid:" + contentId + "\' ></body></html>";
		String resourcePath = "D:\\123456.png";
		mailService.sendInlineResourceMail(to, subject, content, resourcePath, contentId);
	}
	
	@Test 
	public void sendTemplateMail() throws Exception{
		String subject = "测试发送自定义模板邮件";
		Context context = new Context();
		context.setVariable("id", "demo002");
		String content = templateEngine.process("emailTemplate", context);
		mailService.sendHtmlMail(to, subject, content);
	}
	
}
