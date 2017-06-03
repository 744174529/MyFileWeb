package com.peng.Controller;

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.ModelAndView;

import com.peng.Bean.MyFile;
import com.peng.Exception.NoFileException;
import com.peng.Service.FileService;
@Controller
public class FileController {

	private FileService FileService;
	
	
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	public String upload(@RequestParam MultipartFile[] myfiles,HttpServletRequest request) throws IOException {  
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		FileService = (FileService)context.getBean("FileService");
		FileService.upload(myfiles, request);  
        return "uploadSuccess";  
    } 
	
	
	
	@RequestMapping(value="/select")
	public String selectallfile(HttpServletRequest request){
		//HttpSession session = request.getSession();
		System.out.println("Hello");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		FileService = (FileService)context.getBean("FileService");
		List<MyFile> list = FileService.selectallfile();
		request.setAttribute("filelist", list);
		return "show";
	}
	
	@RequestMapping(value="/userselect")
	public String userselectallfile(HttpServletRequest request){
		//HttpSession session = request.getSession();
		System.out.println("Hello");
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		FileService = (FileService)context.getBean("FileService");
		List<MyFile> list = FileService.selectallfile();
		request.setAttribute("filelist", list);
		return "usershow";
	}
	

	@RequestMapping(value="/download",method=RequestMethod.POST)    
	public ResponseEntity<byte[]> download(@RequestParam("path")String path,HttpServletRequest request) throws IOException {    
		File file=new File(path); 
        if(!file.exists()){
        	throw new NoFileException("文件可能已经丢失");
        }
        System.out.println(path);
        HttpHeaders headers = new HttpHeaders();    
        String fileName=new String(file.getName().getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
        headers.setContentDispositionFormData("attachment", fileName);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),    
                                          headers, HttpStatus.CREATED);
	    }  
	    
	
	
	    @RequestMapping(value="/delete",method=RequestMethod.POST)
	    public String delete(HttpServletRequest request){
	    	String filename = request.getParameter("name");
	    	String filepath = request.getParameter("path");
	    	MyFile myfile = new MyFile();
	    	myfile.setName(filename);
	    	myfile.setPath(filepath);
	    	FileService.delete(myfile);
	    	return "file";
	    }

}
