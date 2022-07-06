package com.develop.jpabasic.service;


import com.develop.jpabasic.domain.item.Item;
import com.develop.jpabasic.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item){
        itemRepository.save(item);
    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);

    }

    public List<Item> findAll(){
        return itemRepository.findAll();
    }
}
