package com.mr.item.service;


import com.mr.pojo.Item;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class ItemService {

    public Item saveItem(Item item) {

        item.setId(new Random().nextInt());
        return item;
    }
}
