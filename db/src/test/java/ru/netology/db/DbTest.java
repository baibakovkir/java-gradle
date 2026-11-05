package ru.netology.db;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DbTest {
    @Test
    void constructor_createInitialEntity() {
        // given:
        DbSetting dbSetting = new DbSetting("name1", "password1");

        // when:
        Db db = new Db(dbSetting);

        // then:
        Assertions.assertEquals("first", db.getMyEntity().getName());
        Assertions.assertNotNull(db.getMyEntity().getId());
    }

    @Test
    void constructor_preservesInitialSetting() {
        // given:
        DbSetting dbSetting = new DbSetting("name2", "password2");

        // when:
        Db db = new Db(dbSetting);

        // then:
        Assertions.assertSame(dbSetting, db.getDbSetting());
    }

    @Test
    void setMyEntity_replacesStoredEntity() {
        // given:
        Db db = new Db(new DbSetting("name3", "password3"));
        MyEntity replacement = new MyEntity("name4");

        // when:
        db.setMyEntity(replacement);

        // then:
        Assertions.assertSame(replacement, db.getMyEntity());
    }
}
