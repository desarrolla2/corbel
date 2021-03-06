package io.corbel.resources.rem.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.corbel.lib.queries.request.ResourceQuery;
import io.corbel.resources.rem.dao.NotFoundException;
import io.corbel.resources.rem.dao.RelationMoveOperation;
import io.corbel.resources.rem.model.ResourceUri;
import io.corbel.resources.rem.request.CollectionParameters;
import io.corbel.resources.rem.request.RelationParameters;
import io.corbel.resources.rem.resmi.exception.InvalidApiParamException;
import io.corbel.resources.rem.resmi.exception.StartsWithUnderscoreException;
import org.springframework.data.mongodb.core.index.IndexDefinition;

import java.util.List;
import java.util.Optional;

/**
 * @author Francisco Sanchez
 */
public interface ResmiService {

    String ID = "id";
    String _ID = "_id";

    JsonArray findCollection(ResourceUri uri, Optional<CollectionParameters> apiParameters) throws BadConfigurationException, InvalidApiParamException;

    JsonArray findCollectionDistinct(ResourceUri uri, Optional<CollectionParameters> apiParameters, List<String> fields, boolean first)
            throws BadConfigurationException, InvalidApiParamException;

    JsonObject findResource(ResourceUri uri);

    JsonElement findRelation(ResourceUri uri, Optional<RelationParameters> apiParameters) throws BadConfigurationException, InvalidApiParamException;

    JsonElement aggregate(ResourceUri uri, CollectionParameters apiParameters) throws BadConfigurationException, InvalidApiParamException;

    JsonArray findRelationDistinct(ResourceUri uri, Optional<RelationParameters> apiParameters, List<String> fields, boolean first)
    throws BadConfigurationException, InvalidApiParamException;

    JsonObject saveResource(ResourceUri uri, JsonObject object, Optional<String> userId) throws StartsWithUnderscoreException;

    JsonObject updateCollection(ResourceUri uri, JsonObject object, List<ResourceQuery> resourceQueries)
            throws StartsWithUnderscoreException;

    JsonObject updateResource(ResourceUri uri, JsonObject jsonObject) throws StartsWithUnderscoreException;

    JsonObject conditionalUpdateResource(ResourceUri uri, JsonObject object, List<ResourceQuery> resourceQueries)
            throws StartsWithUnderscoreException;

    JsonObject upsertRelation(ResourceUri uri, JsonObject requestEntity) throws NotFoundException, StartsWithUnderscoreException;

    JsonObject condicionalUpdateRelation(ResourceUri uri, JsonObject requestEntity, List<ResourceQuery>
            resourceQueries) throws StartsWithUnderscoreException;

    void moveRelation(ResourceUri uri, RelationMoveOperation relationMoveOperation);

    void deleteCollection(ResourceUri uri, Optional<List<ResourceQuery>> queries);

    void deleteResource(ResourceUri uri);

    void deleteRelation(ResourceUri uri, Optional<List<ResourceQuery>> queries);

    void ensureExpireIndex(ResourceUri uri);

    void ensureIndex(ResourceUri uri, IndexDefinition indexDefinition);

    void removeObjectId(JsonObject object);

}
