package com.example.lobster.superior.db;


import com.example.lobster.superior.model.Goods;

import org.litepal.crud.DataSupport;

import java.util.List;

public class GoodsLitepal {

    /**
     * 新建便签
     */
    public static void createNewGoods(Goods goods) {
        Goods newGoods = goods;
        newGoods.save();
    }

    public static List<Goods> queryGoodssAll() {
        List<Goods> goodsList = DataSupport.findAll(Goods.class);
        return goodsList;
    }
}
