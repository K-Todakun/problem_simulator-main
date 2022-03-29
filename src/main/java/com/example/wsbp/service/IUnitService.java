package com.example.wsbp.service;

import com.example.wsbp.data.Chat;
import com.example.wsbp.data.Unit;

import java.util.List;

public interface IUnitService {

    /**
     * 単元IDと単元の一覧を、Unit型のリストで検索する
     * @return Unit型のListインスタンス
     */
    public List<Unit> findUnits();
}
