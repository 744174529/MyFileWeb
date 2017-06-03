package com.peng.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.peng.Bean.MyFile;
import com.peng.Dao.UploadDao;
import com.peng.Exception.NoFileException;

@Service("FileService")
public class FileService {
	@Autowired
	private UploadDao dao;
	
	public void upload(MultipartFile[] myfiles,HttpServletRequest request) throws IOException {  
		for(MultipartFile file : myfiles){     
            if(file.isEmpty()){   
                System.out.println("文件未上传!");  
            }  
            else{  
                String fileName = file.getOriginalFilename();
                String path1 = request.getSession().getServletContext().getRealPath("\\")+File.separator;
                System.out.println(path1);
                String path = path1+ fileName;
                File localFile = new File(path);
                //localFile.setWritable(true, false);
                file.transferTo(localFile);
                MyFile myfile = new MyFile();
                myfile.setName(fileName);
                myfile.setPath(path);
                dao.Upload(myfile);
            }  
		}  
    } 
	
	
	public ResponseEntity<byte[]> download(String path,HttpServletRequest request) throws IOException {    
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
	
	
	
	
	
	
	public List<MyFile> selectallfile(){
		return dao.selectallfile();
	}
	
	public void delete(MyFile myfile){
		dao.delete(myfile);
		File file = new File(myfile.getPath());
		if(file.exists()){
			file.delete();
		}
	}
	
	
}
