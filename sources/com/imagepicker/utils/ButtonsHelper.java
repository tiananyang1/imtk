package com.imagepicker.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes08-dex2jar.jar:com/imagepicker/utils/ButtonsHelper.class */
public class ButtonsHelper {

    @Nullable
    public final Item btnCamera;

    @Nullable
    public final Item btnCancel;

    @Nullable
    public final Item btnLibrary;
    public final List<Item> customButtons;

    /* loaded from: classes08-dex2jar.jar:com/imagepicker/utils/ButtonsHelper$Item.class */
    public static class Item {
        public final String action;
        public final String title;

        public Item(@NonNull String str, @NonNull String str2) {
            this.title = str;
            this.action = str2;
        }
    }

    public ButtonsHelper(@Nullable Item item, @Nullable Item item2, @Nullable Item item3, @NonNull LinkedList<Item> linkedList) {
        this.btnCamera = item;
        this.btnLibrary = item2;
        this.btnCancel = item3;
        this.customButtons = linkedList;
    }

    @NonNull
    private static LinkedList<Item> getCustomButtons(@NonNull ReadableMap readableMap) {
        LinkedList<Item> linkedList = new LinkedList<>();
        if (!readableMap.hasKey("customButtons")) {
            return linkedList;
        }
        ReadableArray array = readableMap.getArray("customButtons");
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= array.size()) {
                return linkedList;
            }
            ReadableMap map = array.getMap(i2);
            linkedList.add(new Item(map.getString(SettingsJsonConstants.PROMPT_TITLE_KEY), map.getString("name")));
            i = i2 + 1;
        }
    }

    @Nullable
    private static Item getItemFromOption(@NonNull ReadableMap readableMap, @NonNull String str, @NonNull String str2) {
        if (ReadableMapUtils.hasAndNotEmptyString(readableMap, str)) {
            return new Item(readableMap.getString(str), str2);
        }
        return null;
    }

    public static ButtonsHelper newInstance(@NonNull ReadableMap readableMap) {
        return new ButtonsHelper(getItemFromOption(readableMap, "takePhotoButtonTitle", "photo"), getItemFromOption(readableMap, "chooseFromLibraryButtonTitle", "library"), getItemFromOption(readableMap, "cancelButtonTitle", "cancel"), getCustomButtons(readableMap));
    }

    public List<String> getActions() {
        LinkedList linkedList = new LinkedList();
        Item item = this.btnCamera;
        if (item != null) {
            linkedList.add(item.action);
        }
        Item item2 = this.btnLibrary;
        if (item2 != null) {
            linkedList.add(item2.action);
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.customButtons.size()) {
                return linkedList;
            }
            linkedList.add(this.customButtons.get(i2).action);
            i = i2 + 1;
        }
    }

    public List<String> getTitles() {
        LinkedList linkedList = new LinkedList();
        Item item = this.btnCamera;
        if (item != null) {
            linkedList.add(item.title);
        }
        Item item2 = this.btnLibrary;
        if (item2 != null) {
            linkedList.add(item2.title);
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= this.customButtons.size()) {
                return linkedList;
            }
            linkedList.add(this.customButtons.get(i2).title);
            i = i2 + 1;
        }
    }
}
