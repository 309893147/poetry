package com.lss.poetry.service;

import java.util.List;

import com.lss.poetry.pojo.RootUser;
import com.lss.poetry.pojo.User;

public interface UserService {
//	//wx详情页查询
//	 public UserDetail getAllWithUser(String userId);
	//微信添加用户
	public int addWXUser(User userInfo);
	
    public RootUser checkLogin(RootUser user);

//    public int insert(User user);
//
//    public List<User> getAll();
//
//    // 删除
//    public void deleteUser(String id);
//
//    // 用户信息保存
//    public int saveUser(User user);
//
//    // 按照用户ID查询用户
//    public User getUser(String id);
//
//    // 修改用户
//    public void updateUser(User user);
//
//    public boolean checkUsername(String username);
//
//    public void deleteBatch(List<String> ids);
//
//    public void updateUserInfo(User user);
//
//    //查询用户信息
//
//    public User queryUserInfo(String userId);

}


