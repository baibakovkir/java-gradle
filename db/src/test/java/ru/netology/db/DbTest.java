package ru.netology.db;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class DbTest {
    @Test
    void constructor_createInitialEntity() {
        // given:
        DbSetting dbSetting = new DbSetting("name1", "password1");

        // when:
        Db db = new Db(dbSetting);

        // then:
        assertThat(db.getMyEntity().getName(), equalTo("first"));
        assertThat(db.getMyEntity().getId(), notNullValue());
    }

    @Test
    void constructor_preservesInitialSetting() {
        // given:
        DbSetting dbSetting = new DbSetting("name2", "password2");

        // when:
        Db db = new Db(dbSetting);

        // then:
        assertThat(db.getDbSetting(), sameInstance(dbSetting));
    }

    @Test
    void setMyEntity_replacesStoredEntity() {
        // given:
        Db db = new Db(new DbSetting("name3", "password3"));
        MyEntity replacement = new MyEntity("name4");

        // when:
        db.setMyEntity(replacement);

        // then:
        assertThat(db.getMyEntity(), sameInstance(replacement));
    }

    @Test
    void setMyEntity_preservesEntityData() {
        // given:
        Db db = new Db(new DbSetting("name5", "password5"));
        MyEntity myEntity = new MyEntity("name6");
        UUID id = UUID.randomUUID();
        myEntity.setId(id);

        // when:
        db.setMyEntity(myEntity);

        // then:
        assertThat(db.getMyEntity(), allOf(
                hasProperty("name", equalTo("name6")),
                hasProperty("id", equalTo(id))
        ));
    }

    @Test
    void toString_containsEntityData() {
        // given:
        MyEntity myEntity = new MyEntity("name8");
        UUID id = UUID.randomUUID();
        myEntity.setId(id);

        // when:
        String entityString = myEntity.toString();

        // then:
        assertThat(entityString, allOf(
                startsWith("MyEntity{"),
                containsString("id=" + id),
                containsString("name='name8'"),
                endsWith("}")
        ));
    }
}
