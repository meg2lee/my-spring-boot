package com.example.demo.dao;

import java.util.ArrayList;

import com.example.demo.controller.BoardVO;
import com.example.demo.controller.UserVO;

public interface BoardDAO {

	boolean add(BoardVO b);
//	int insertAndGetId(BoardVO b);
	BoardVO select(int num);
//	int getCount();
//	String getName(int num);
	ArrayList<BoardVO> getList();
	
}
