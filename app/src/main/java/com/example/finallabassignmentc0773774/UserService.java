package com.example.finallabassignmentc0773774;

import java.util.List;

public interface UserService {
    List<UserM> getAll();

    void insertAll(UserM... users);

    void delete(UserM user);

    void update(UserM user);
}
