package br.activemq.sender.rest;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.jms.JMSException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.activemq.sender.mod.MyFilesUpload;
import br.activemq.sender.service.SyncMessageSenderService;
import br.activemq.sender.service.SyncObjectSenderService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	SyncMessageSenderService syncMessageSenderService;

	@Autowired
	SyncObjectSenderService syncObjectSenderService;

	@ExceptionHandler
	public String exceptionHandler(Exception ex) {
		return ex.getMessage();

	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/syncMessage", method = RequestMethod.GET, produces = "text/plain")
	@ResponseBody
	public String syncMessage(@RequestParam String message) throws JMSException {
		logger.info("Sending message: " + message);

		syncMessageSenderService.sendMessage(message);

		return "Simple Sync message sent !!!";
	}

	@RequestMapping(value = "/objectMessage", method = RequestMethod.GET, produces = "text/plain")
	@ResponseBody
	public String objectMessage() throws JMSException {
		logger.info("Sending Object message...");

		syncObjectSenderService.sendMessage();

		return "Object message sent !!!";
	}

	// File upload
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	@ResponseBody
	// File on the request should be same as declared on MyFilesUpload.files - i
	// mean, should be 'files' in this case
	public String uploadFile(MyFilesUpload uploadFiles, HttpServletRequest request) throws IOException {

		List<MultipartFile> multipartFileList = uploadFiles.getFiles();

		//System.getProperty("java.io.tmpdir")+ 
		for (MultipartFile mpFile : multipartFileList) {
			logger.info("######## FILE NAME: " + mpFile.getName());
		
			logger.info("######## ORIGINAL FILE NAME: " + mpFile.getOriginalFilename());
			File destFile = new File(System.getProperty("catalina.base") + "\\"+ mpFile.getOriginalFilename());
			logger.info("######## PATH: " + destFile.getAbsolutePath());
			logger.info("######## PATH: " + destFile.getCanonicalPath());
			logger.info("######## PATH: " + destFile.getPath());
			syncMessageSenderService.saveFileSendMessage(mpFile.getInputStream(), destFile);
		}

		return "File Uploaded...";

	}

	@RequestMapping(value = "/getFile", method = RequestMethod.GET)
	@ResponseBody
	public void getFile(HttpServletResponse response, @RequestParam String fileName) {

		try {
			File destFile = new File(System.getProperty("java.io.tmpdir") + fileName);
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			IOUtils.copy(new FileInputStream(destFile), response.getOutputStream());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
