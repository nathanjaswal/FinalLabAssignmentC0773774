package com.example.finallabassignmentc0773774;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    @Insert(onConflict = REPLACE)
    void insert(UserM... users);

    @Delete
    void delete(UserM user);

    @Query("SELECT * from user_data")
    List<UserM> getUsers();
}
