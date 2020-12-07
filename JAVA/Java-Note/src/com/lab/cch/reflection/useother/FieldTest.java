package com.lab.cch.reflection.useother;

import com.company.cch.reflection.other.Person;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 獲取當前運昔時類的屬性結構
 */
public class FieldTest {
    @Test
        public void test(){
            Class cl = Person.class;
            // 獲取當前運行時類和其父類中 public 權限的屬性
            Field[] fields = cl.getFields();
            for(Field f : fields) {
                System.out.println(f);
            }
            System.out.println();
            // 獲取當前運行時類中所有屬性
            Field[] fields1 = cl.getDeclaredFields();
            for(Field f : fields1) {
                System.out.println(f);
            }

        }
        @Test
        public void test1() {
            Class cl = Person.class;
            Field[] fields = cl.getDeclaredFields();
            for(Field f : fields) {
                // 權限修飾
                System.out.print(Modifier.toString(f.getModifiers()));
                // 數據類型
                System.out.print(f.getType().getName());
                // 變量名
                System.out.print(f.getName());
            }
    }
}
