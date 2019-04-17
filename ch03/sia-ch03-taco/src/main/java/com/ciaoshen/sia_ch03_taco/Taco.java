package com.ciaoshen.sia_ch03_taco;

import java.util.List;
import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class Taco {
    // @NotNull
    private Long id;
    // @NotNull
    private Date createdAt;
    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    /**
     * 书上原来是Ingredient的列表，和第二章List<String>不符。
     * 而且design.html表单也是赋值的也是Ingredient.id字段，
     * 和这里Ingredient不符合。
     * 所以先维持String的设定，在finish.html需要完整Ingredient信息的时候，
     * 再在FinishOrderController.java里通过Ingredient.id字段到数据库里去找Ingredient对象。
     */
    // private List<Ingredient> ingredients;
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<String> ingredients;
    
}