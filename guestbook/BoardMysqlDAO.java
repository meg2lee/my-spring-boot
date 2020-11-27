package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.controller.BoardVO;
import com.example.demo.controller.UserVO;

@Repository("boardDao")
public class BoardMysqlDAO implements BoardDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public boolean add(BoardVO b) {
		String sql = "INSERT INTO board_tb VALUES(NULL,?,?,?,?)";
		int rows = jdbcTemplate.update(sql, b.getAuthor(), 
				b.getWdate(), b.getTitle(),b.getContents());
		return rows>0;
	}

	@Override
	public BoardVO select(int num) {
		String sql = "SELECT * FROM board_tb WHERE num=?";
		return jdbcTemplate.queryForObject(sql, (rs,i)-> 
			new BoardVO(rs.getInt(1),rs.getString(2),
					rs.getString(3), rs.getString(4), rs.getString(5))
			,num);
	}

	@Override
	public ArrayList<BoardVO> getList() {
		String sql = "SELECT * FROM board_tb";
		List<BoardVO> bList = jdbcTemplate.query(sql, 
		(rs,i)-> new BoardVO(rs.getInt(1),rs.getString(2),
				rs.getString(3), rs.getString(4),rs.getString(5))); 
		return new ArrayList<BoardVO>(bList);
	}

	@Override
	public boolean update(BoardVO b) {
		String sql = "UPDATE board_tb SET contents=? WHERE num=?";
		int rows = jdbcTemplate.update(sql, b.getContents(), b.getNum());
		return rows>0;
	}

	@Override
	public boolean delete(int num) {
		String sql = "DELETE FROM board_tb WHERE num=?";
		int rows = jdbcTemplate.update(sql, num);
		return rows>0;
	}

}
