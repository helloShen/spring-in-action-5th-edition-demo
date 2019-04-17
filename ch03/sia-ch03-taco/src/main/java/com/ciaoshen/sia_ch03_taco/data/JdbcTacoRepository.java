package com.ciaoshen.sia_ch03_taco.data;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

import com.ciaoshen.sia_ch03_taco.Ingredient;
import com.ciaoshen.sia_ch03_taco.Taco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Repository
public class JdbcTacoRepository implements TacoRepository {

    private JdbcTemplate jdbc;
    private SimpleJdbcInsert tacoInserter;
    private ObjectMapper objectMapper;

    @Autowired
    public JdbcTacoRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.tacoInserter = new SimpleJdbcInsert(jdbc)
            .withTableName("Taco")
            .usingGeneratedKeyColumns("id");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Taco save(Taco taco) {
        long tacoId = saveTacoInfo(taco);
        taco.setId(tacoId);
        for (String ingredient : taco.getIngredients()) {
            saveIngredientToTaco(ingredient, tacoId);
        }
        return taco;
    }

    /** 作废！这里KeyHolder返回的null，导致design页面制作的Taco信息无法储存数据库 */
    // private long saveTacoInfo(Taco taco) {
    //     taco.setCreatedAt(new Date());
    //     PreparedStatementCreator psc = new PreparedStatementCreatorFactory(
    //         "insert into Taco (name, createdAt) values (?, ?)",
    //         Types.VARCHAR, Types.TIMESTAMP).newPreparedStatementCreator(
    //             Arrays.asList(taco.getName(),
    //             new Timestamp(taco.getCreatedAt().getTime())));
    //     KeyHolder keyHolder = new GeneratedKeyHolder();
    //     if (log.isInfoEnabled()) {
    //         log.info("New Taco Id = {}", keyHolder.getKey());
    //     }
    //     jdbc.update(psc, keyHolder);
    //     return keyHolder.getKey().longValue();
    // }

    /** 这个利用ObjectMapper的方案借鉴了JdbcOrderRepository.java */
    private long saveTacoInfo(Taco taco) {
        taco.setCreatedAt(new Date());
        @SuppressWarnings("unchecked")
        Map<String, Object> values = objectMapper.convertValue(taco, Map.class);
        values.put("createdAt", taco.getCreatedAt());
        long tacoId = tacoInserter.executeAndReturnKey(values).longValue();
        return tacoId;
    }

    private void saveIngredientToTaco(
        String ingredient, long tacoId) {
            jdbc.update("insert into Taco_Ingredients (taco, ingredient) " + 
                        "values (?, ?)", tacoId, ingredient);
    }
}