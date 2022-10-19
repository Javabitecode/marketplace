package org.zuzex.util;

import java.util.Random;

public class GeneratorId {

    /*TODO переделать на objectID*/
    public static Long generateId(){
        return new Random().nextLong(9999);
    }
}
