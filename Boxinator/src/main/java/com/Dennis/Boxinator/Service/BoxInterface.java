package com.Dennis.Boxinator.Service;

import com.Dennis.Boxinator.Model.Box;

import java.util.List;

public interface BoxInterface {
    void add(Box box);

    List<Box> getBoxes();
}
