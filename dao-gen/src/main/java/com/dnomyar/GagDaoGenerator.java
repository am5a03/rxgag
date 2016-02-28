package com.dnomyar;

import java.io.IOException;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Index;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class GagDaoGenerator {
    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "dnomyar.rxgag.models.db.sqlite");

        EntityList gagEntities = create3LevelEntities(schema, "Gag");
        gagEntities.entity.addStringProperty("title");
        gagEntities.entity.addStringProperty("image");
        gagEntities.entity.addLongProperty("score");

        new DaoGenerator().generateAll(schema, "app/src/main/java");
    }

    public static class EntityList {
        public Entity entity;
        public Entity entityList;
        public Entity entityListItem;

        public EntityList(Entity entity, Entity entityList, Entity entityListItem) {
            this.entity = entity;
            this.entityList = entityList;
            this.entityListItem = entityListItem;
        }
    }


    public static Entity createBaseEntity(Schema schema, String name) {
        Entity entity = schema.addEntity(name);
        entity.addIdProperty();
        entity.addLongProperty("updateTime");
        entity.addLongProperty("createdAt");
        return entity;
    }

    public static EntityList create3LevelEntities(Schema schema, String name) {

        String prefix = name.toLowerCase();

        Entity entity = createBaseEntity(schema, name);
        Property entityIdPro = entity.addStringProperty(prefix + "Id").getProperty();

        // unique index for object id
        Index idIndex = new Index();
        idIndex.addProperty(entityIdPro);
        idIndex.makeUnique();
        entity.addIndex(idIndex);

        Entity entityList = createBaseEntity(schema, name + "List");
        Property listKeyPro = entityList.addStringProperty("listKey").getProperty();
        entityList.addStringProperty("nextOffSet");
        entityList.addBooleanProperty("hasNext");

        // unique index for object list key
        Index listKeyIndex = new Index();
        listKeyIndex.addProperty(listKeyPro);
        listKeyIndex.makeUnique();
        entityList.addIndex(listKeyIndex);

        Entity entityListItem = createBaseEntity(schema, name + "ListItem");
        Property itemListKeyPro = entityListItem.addStringProperty("listKey").getProperty();
        Property itemListEntityDBIdPro = entityListItem.addLongProperty(prefix + "DBId").getProperty();
        Property itemListEntityIdPro = entityListItem.addStringProperty(prefix + "Id").getProperty();

        // toOne object relation
        entityListItem.addToOne(entity, itemListEntityDBIdPro, prefix);

        // unique index for object list item (listKey + mapped db id)
        Index listItemIndex = new Index();
        listItemIndex.addProperty(itemListKeyPro);
        listItemIndex.addProperty(itemListEntityDBIdPro);
        listItemIndex.makeUnique();
        entityListItem.addIndex(listItemIndex);


        return new EntityList(entity, entityList, entityListItem);
    }
}
