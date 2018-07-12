package com.example.common.db;

import android.content.ContentValues;

import java.io.Serializable;

/**
 * <p>
 *  Created by lianbin.xu@ucarinc.com on 2017/11/20.<br>
 * 实体抽象类<br>
 * 声明表结构对应的实体类都需要继承此类<br>
 * （BaseEntity维护了id的属性，子类不需要再声明这个属性）<br>
 * Entity对象是业务层需要存储到数据库的数据抽象。 这里面有两个方法必须由业务层去实现：entityToContentValues 和 contentValuesToEntity。这两个方法分别对应着把实体类(Entity)转化成数据库类型(ContentValues) 和 把数据库类型转换成实体类
 */
public abstract class BaseEntity implements Cloneable, Serializable{
    /**
     * 主键
     */
    protected Long id;
    protected final String ID = "id";
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 实体类属性值转键值对<br>
     * 为了能存数据库，继承此类必须复写此方法<br>
     * 复写第一句需调super.entityToContentValues
     * @return
     * @throws Exception
     */
    public ContentValues entityToContentValues() throws Exception{
        ContentValues cv = new ContentValues();
        if(id != null) {
            cv.put(ID, id);
        }
        return cv;
    }

    /**
     * 键值对转实体类<br>
     * 为了能从数据库取出，继承此类必须复写此方法<br>
     * 复写第一句需调super.contentValuesToEntity<br>
     * @param contentValues
     */
    public void contentValuesToEntity(ContentValues contentValues){
        if(contentValues.containsKey(ID)){
            id = contentValues.getAsLong(ID);
        }
    }

    /**
     * 获取表名<br>
     * 可以不复写，如果类名重复，必须复写<br>
     * @return 表名
     */
    public String getTableName(){
        return getClass().getSimpleName();
    }
}
