
package gg.convict.prison.util;

import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 09.03.2021 / 01:37
 * libraries / cc.invictusgames.libraries.utils
 */

public class ItemNbtUtil {

    public static void remove(ItemStack item, String key) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        if (stack.getTag() != null)
            stack.getTag().remove(key);
    }

    public static void set(ItemStack item, String key, String value) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        NBTTagCompound tag;
        if (stack.getTag() != null)
            tag = stack.getTag();
        else tag = new NBTTagCompound();

        tag.setString(key, value);
        stack.setTag(tag);
    }

    public static void set(ItemStack item, String key, double value) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        NBTTagCompound tag;
        if (stack.getTag() != null)
            tag = stack.getTag();
        else tag = new NBTTagCompound();

        tag.setDouble(key, value);
        stack.setTag(tag);
    }

    public static void set(ItemStack item, String key, int value) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        NBTTagCompound tag;
        if (stack.getTag() != null)
            tag = stack.getTag();
        else tag = new NBTTagCompound();

        tag.setInt(key, value);
        stack.setTag(tag);
    }

    public static void set(ItemStack item, String key, boolean value) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        NBTTagCompound tag;
        if (stack.getTag() != null)
            tag = stack.getTag();
        else tag = new NBTTagCompound();

        tag.setBoolean(key, value);
        stack.setTag(tag);
    }

    public static void set(ItemStack item, String key, long value) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        NBTTagCompound tag;
        if (stack.getTag() != null)
            tag = stack.getTag();
        else tag = new NBTTagCompound();

        tag.setLong(key, value);
        stack.setTag(tag);
    }

    public static void set(ItemStack item, String key, short value) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        NBTTagCompound tag;
        if (stack.getTag() != null)
            tag = stack.getTag();
        else tag = new NBTTagCompound();

        tag.setShort(key, value);
        stack.setTag(tag);
    }

    public static void set(ItemStack item, String key, byte value) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        NBTTagCompound tag;
        if (stack.getTag() != null)
            tag = stack.getTag();
        else tag = new NBTTagCompound();

        tag.setByte(key, value);
        stack.setTag(tag);
    }

    public static void set(ItemStack item, String key, byte[] value) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        NBTTagCompound tag;
        if (stack.getTag() != null)
            tag = stack.getTag();
        else tag = new NBTTagCompound();

        tag.setByteArray(key, value);
        stack.setTag(tag);
    }

    public static void set(ItemStack item, String key, int[] value) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        NBTTagCompound tag;
        if (stack.getTag() != null)
            tag = stack.getTag();
        else tag = new NBTTagCompound();

        tag.setIntArray(key, value);
        stack.setTag(tag);
    }

    public static void set(ItemStack item, String key, float value) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        NBTTagCompound tag;
        if (stack.getTag() != null)
            tag = stack.getTag();
        else tag = new NBTTagCompound();

        tag.setFloat(key, value);
        stack.setTag(tag);
    }

    public static void set(ItemStack item, String key, NBTBase value) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        NBTTagCompound tag;
        if (stack.getTag() != null)
            tag = stack.getTag();
        else tag = new NBTTagCompound();

        tag.set(key, value);
        stack.setTag(tag);
    }

    public static String getString(ItemStack item, String key) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        if (stack.getTag() == null)
            return null;

        NBTTagCompound tag = stack.getTag();
        return tag.getString(key);
    }

    public static double getDouble(ItemStack item, String key) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        if (stack.getTag() == null)
            return 0.0D;

        NBTTagCompound tag = stack.getTag();
        return tag.getDouble(key);
    }

    public static int getInt(ItemStack item, String key) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        if (stack.getTag() == null)
            return 0;

        NBTTagCompound tag = stack.getTag();
        return tag.getInt(key);
    }

    public static boolean getBoolean(ItemStack item, String key) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        if (stack.getTag() == null)
            return false;

        NBTTagCompound tag = stack.getTag();
        return tag.getBoolean(key);
    }

    public static long getLong(ItemStack item, String key) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        if (stack.getTag() == null)
            return 0L;

        NBTTagCompound tag = stack.getTag();
        return tag.getLong(key);
    }

    public static short getShort(ItemStack item, String key) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        if (stack.getTag() == null)
            return 0;

        NBTTagCompound tag = stack.getTag();
        return tag.getShort(key);
    }

    public static byte getByte(ItemStack item, String key) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        if (stack.getTag() == null)
            return 0;

        NBTTagCompound tag = stack.getTag();
        return tag.getByte(key);
    }

    public static byte[] getByteArray(ItemStack item, String key) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        if (stack.getTag() == null)
            return new byte[0];

        NBTTagCompound tag = stack.getTag();
        return tag.getByteArray(key);
    }

    public static int[] getIntArray(ItemStack item, String key) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        if (stack.getTag() == null)
            return new int[0];

        NBTTagCompound tag = stack.getTag();
        return tag.getIntArray(key);
    }

    public static float getFloat(ItemStack item, String key) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        if (stack.getTag() == null)
            return 0.0F;

        NBTTagCompound tag = stack.getTag();
        return tag.getFloat(key);
    }

    public static NBTBase getBase(ItemStack item, String key) {
        CraftItemStack craftItem = (CraftItemStack) item;
        net.minecraft.server.v1_8_R3.ItemStack stack = craftItem.handle;

        if (stack.getTag() == null)
            return null;

        NBTTagCompound tag = stack.getTag();
        return tag.get(key);
    }

}
