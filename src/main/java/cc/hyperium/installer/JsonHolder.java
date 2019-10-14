package cc.hyperium.installer;/*
 *     Copyright (C) 2018  Hyperium <https://hyperium.cc/>
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published
 *     by the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 *
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * JSON Holder Object
 */
public class JsonHolder {
    private JsonObject object;

    private JsonHolder(JsonObject object) {
        this.object = object;
    }

    /**
     * Constructor for creating object with raw JSON.
     *
     * @param raw String of the raw JSON
     */
    public JsonHolder(String raw) {
        if (raw == null || raw.isEmpty()) {
            object = new JsonObject();
            return;
        }
        try {
            this.object = new JsonParser().parse(raw).getAsJsonObject();
        } catch (Exception e) {
            this.object = new JsonObject();
            e.printStackTrace();
        }
    }

    /**
     * Creates an instance of JsonHolder with a empty JsonObject.
     */
    public JsonHolder() {
        this(new JsonObject());
    }

    /**
     * Get the JSON object as a {@link java.lang.String}.
     *
     * @return the object as a String
     */
    @Override
    public String toString() {
        if (object != null)
            return object.toString();
        return "{}";
    }

    /**
     * Put a key/value inside the JSON object.
     *
     * @param key The key for the dictionary
     * @param value The value to be associated with the key
     * @return the updated instance of this class
     */
    public JsonHolder put(String key, String value) {
        object.addProperty(key, value);
        return this;
    }

    private JsonHolder optJSONObject(String key, JsonObject fallBack) {
        try {
            return new JsonHolder(object.get(key).getAsJsonObject());
        } catch (Exception e) {
            return new JsonHolder(fallBack);
        }
    }

    private JsonArray optJSONArray(String key, JsonArray fallback) {
        try {
            return object.get(key).getAsJsonArray();
        } catch (Exception e) {
            return fallback;
        }
    }

    /**
     * Get a JSON array from the JSON object if it exists, otherwise get an empty JSON array.
     *
     * @param key The key to get the array for
     * @return The item for the specified key if it exists, otherwise an empty JSON array.
     */
    public JsonArray optJSONArray(String key) {
        return optJSONArray(key, new JsonArray());
    }

    /**
     * Returns if the JSON object has the named key.
     *
     * @param key The key to check for
     * @return The boolean value of if the key is in the JSON object
     */
    public boolean has(String key) {
        return object.has(key);
    }

    public JsonHolder optJSONObject(String key) {
        return optJSONObject(key, new JsonObject());
    }

    /**
     * Get a {@link java.lang.String String} by specified key from the JSON object if it exists.
     *
     * @param key The key to attempt to get the value for
     * @return The key if it exists, otherwise just an empty {@link java.lang.String String}
     */
    public String optString(String key) {
        try {
            return object.get(key).getAsString();
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * Get a {@link java.util.List List} of the keys in the JSON object.
     *
     * @return A list of {@link java.lang.String Strings}, a value for each key
     */
    public List<String> getKeys() {
        return object.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    /**
     * Get the object in {@link com.google.gson.JsonObject JsonObject} form.
     *
     * @return The object in {@link com.google.gson.JsonObject JsonObject} form
     */
    public JsonObject getObject() {
        return object;
    }

    public JsonHolder put(String values, JsonHolder values1) {
        return put(values, values1.getObject());
    }

    private JsonHolder put(String values, JsonObject object) {
        this.object.add(values, object);
        return this;
    }

    /**
     * Put a JSON array inside the object by the named key.
     *
     * @param key The key to use.
     * @param value The key's value as a {@link com.google.gson.JsonArray JsonArray}.
     * @return The new instance of this class
     */
    public JsonHolder put(String key, JsonArray value) {
        this.object.add(key, value);
        return this;
    }
}