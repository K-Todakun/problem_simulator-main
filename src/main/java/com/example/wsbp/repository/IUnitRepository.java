package com.example.wsbp.repository;

import com.example.wsbp.data.Unit;

import java.util.List;

public interface IUnitRepository {
    /**
     * UnitListテーブルのすべての情報を検索する
     * @return レコードの内容を {@link Unit} の {@link List} で返す
     */
    public List<Unit> find();
}
