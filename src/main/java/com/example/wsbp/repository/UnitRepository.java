package com.example.wsbp.repository;

import com.example.wsbp.data.Chat;
import com.example.wsbp.data.Unit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UnitRepository implements IUnitRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public UnitRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public List<Unit> find() {
        String sql = "select unit_id, unit_name from unit_list";

        List<Unit> units = jdbc.query(sql, DataClassRowMapper.newInstance(Unit.class));

        return units;
    }
}
