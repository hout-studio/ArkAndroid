package com.hout.ark.util.storage;

import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brlnt on 07/08/2016.
 * Convert one object to another,
 * it can only be used for identical object (different class with same field name)
 */
public class ModelConverter<T1, T2>{
    private final static String TAG = ModelConverter.class.getSimpleName();
    private static ModelConverter instance;

    private ModelConverter() {

    }

    /**
     * Get instance of ModelConverter
     * @return new / existing instance of class
     */
    public static ModelConverter getInstance() {
        if(instance == null) {
            instance = new ModelConverter();
        }

        return instance;
    }

    /**
     * Convert object into a new one (defined by the source & result class)
     * both of class (source and result) must have the same field, so the convertion can happen.
     * @param sourceClass The type of source class
     * @param resultClass The type of result class
     * @param source source object
     * @return result object
     */
    public T2 convert(Class<T1> sourceClass, Class<T2> resultClass, T1 source) {
        try {
            T2 result = resultClass.newInstance();

            Field fields[] = sourceClass.getFields();
            for (Field f : fields) {
                Field resultField = null;

                try {
                    resultField = resultClass.getField(f.getName());
                } catch (NoSuchFieldException ex){
                    Log.d(TAG, "Field not found: "+f.getName());
                }

                if(resultField != null) {
                    resultField.set(result, sourceClass.getField(f.getName()).get(source));
                }
            }

            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Convert list of object into a new one (defined by the source & result class).
     * both of class (source and result) must have the same field, so the convertion can happen.
     * @param sourceClass The type of source class
     * @param resultClass The type of result class
     * @param source list of source object
     * @return list of result object
     */
    public List<T2> convert(Class<T1> sourceClass, Class<T2> resultClass, List<T1> source) {
        List<T2> result = new ArrayList<>();
        for(int i=0; i < source.size(); i++) {
            result.add(convert(sourceClass, resultClass, source.get(i)));
        }

        return result;
    }
}
