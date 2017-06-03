package com.peng.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.peng.Bean.MyFile;
@Repository("uploadDao")
public class UploadDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void Upload(MyFile myfile){
			String sql = "insert into Myfile(name,path) values(?,?)";
			/*String path = myfile.getPath();
			String real = path.replaceAll("\\", "\\\\");*/
			jdbcTemplate.update(sql, myfile.getName(), myfile.getPath());
	}
	
	public List<MyFile> selectallfile(){
		String sql = "select name,path from Myfile";
		RowMapper<MyFile> mapper = new BeanPropertyRowMapper<MyFile>(MyFile.class);
		List<MyFile> list = jdbcTemplate.query(sql, mapper);
		return list;
	}
	
	public void delete(MyFile myfile){
		String sql = "delete from Myfile where name=?";
		jdbcTemplate.update(sql, myfile.getName());
	}
}
