package com.example.finallabassignmentc0773774;

import android.content.Context;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(Context context) {
        userDao = AppDatabase.getInstance(context).userDao();
    }

    @Override
    public List<UserM> getAll() {
        return userDao.getUsers();
    }

    @Override
    public void insertAll(UserM... users) {
        userDao.insert(users);
    }

    @Override
    public void delete(UserM user) {
        userDao.delete(user);
    }

    @Override
    public void update(UserM user) {
        userDao.update(user);
    }

}
