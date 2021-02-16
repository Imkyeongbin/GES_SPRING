package com.oracle.oBootMyBatis03.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@GetMapping(value = "upLoadFormStart")
	public String upLoadFormStart(Model model) {
		System.out.println("upLoadFormStart Start");
		return "upLoadFormStart";
	}
	
	@PostMapping(value = "uploadForm")
	public String uploadForm(HttpServletRequest request, MultipartFile file1, Model model)
		throws Exception{
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		System.out.println("uploadForm POST Start");
		logger.info("originalName: " + file1.getOriginalFilename());
		logger.info("size: " + file1.getSize());
		logger.info("contentType: " + file1.getContentType());
		String savedName = uploadFile(file1.getOriginalFilename(), file1.getBytes(), uploadPath);
		logger.info("savedName: " + savedName);
		model.addAttribute("savedName", savedName);

		return "uploadResult";
	}
	
	private String uploadFile(String originalName, byte[] fileData, String uploadPath)
		throws Exception{
		UUID uid = UUID.randomUUID();
		// requestPath = requestPath + "/resources/image";
		System.out.println("uploadPath->"+uploadPath);
		// Directory 생성
		File fileDirectory = new File(uploadPath);
		if(!fileDirectory.exists()) {
			fileDirectory.mkdirs();
			System.out.println("업로드용 폴더 생성 : " + uploadPath);
		}
		String savedName = uid.toString() + "_" + originalName;
//		String path1 = "c:\\spring\\spring3914\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\sMybatis\\resources\\image";
		File target = new File(uploadPath, savedName);
//		File target = new File(requestPath, savedName);
		FileCopyUtils.copy(fileData, target); 	// org.springframework.util.FileCopyUtils
		
		return savedName;
	}
	
	
	@RequestMapping(value="uploadFileDelete")
	public String uploadFileDelete(HttpServletRequest request, Model model)
		throws Exception{
		String uploadPath = request.getSession().getServletContext().getRealPath("/upload/");
		String deleteFile = uploadPath + "d84b8546-a717-46cd-8e60-a205e918e0ff_jung1.jpg";
		logger.info("deleteFile: " + deleteFile);
		System.out.println("uploadFileDelete POST Start");
		int delResult = upFileDelete(deleteFile);
		logger.info("deleteFile result-> " + delResult);
		model.addAttribute("deleteFile", deleteFile);
		model.addAttribute("delResult", delResult);
		return "uploadResult";
	}
	
	private int upFileDelete(String deleteFileName) throws Exception{
		int result = 0;
		logger.info("upFileDelete result-> " + deleteFileName);
		File file = new File(deleteFileName);
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("파일삭제 성공");
				result = 1;
			}else {
				System.out.println("파일삭제 실패");
				result = 0;
			}
		}else {
			System.out.println("파일이 존재하지 않습니다.");
			result = -1;
		}
		return result;
	}
}
