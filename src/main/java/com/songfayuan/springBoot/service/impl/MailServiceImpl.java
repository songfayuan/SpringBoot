/**
 * 项目名称：spring-boot
 * 项目包名：com.songfayuan.springBoot.service.impl
 * 创建时间：2017年12月13日上午11:28:56
 * 创建者：Administrator-宋发元
 * 创建地点：杭州
 */
package com.songfayuan.springBoot.service.impl;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.songfayuan.springBoot.service.MailService;

/**
 * 描述：发送邮件具体实现类
 * @author songfayuan
 * 2017年12月13日上午11:28:56
 */
@Service
public class MailServiceImpl implements MailService {
	
	private final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${mail.fromMail.address}")
	private String from;

	@Override
	public void sendSimpleMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		try {
			mailSender.send(message);
			logger.info("发送普通邮件成功...");
		} catch (Exception e) {
			logger.error("发送普通邮件时发生异常...", e);
		}
	}

	@Override
	public void sendHtmlMail(String to, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true); //true表示需要创建一个multipart message
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			mailSender.send(message);
			logger.info("发送html邮件成功...");
		} catch (Exception e) {
			logger.error("发送html邮件时发生异常...", e);
		}
	}

	@Override
	public void sendAttachmentMail(String to, String subject, String content, String filePath) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			helper.addAttachment(fileName, file);
			mailSender.send(message);
			logger.info("发送附件邮件成功...");
		} catch (Exception e) {
			logger.error("发送附件邮件时发生异常...", e);
		}
	}

	@Override
	public void sendInlineResourceMail(String to, String subject, String content, String resourcePath, String contentId) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			FileSystemResource resource = new  FileSystemResource(new File(resourcePath));
			helper.addInline(contentId, resource);
			mailSender.send(message);
			logger.info("发送嵌入静态资源的邮件成功...");
		} catch (Exception e) {
			logger.error("发送嵌入静态资源的邮件时发生异常...", e);
		}
	}

}
