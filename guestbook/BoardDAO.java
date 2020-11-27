package com.example.demo.dao;

import java.util.ArrayList;

import com.example.demo.controller.BoardVO;
import com.example.demo.controller.UserVO;

public interface BoardDAO {

	boolean add(BoardVO b);
	BoardVO select(int num);
	boolean update(BoardVO b);
	boolean delete(int num);
	ArrayList<BoardVO> getList();

}
