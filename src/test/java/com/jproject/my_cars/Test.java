package com.jproject.my_cars;


public class Test {

    @org.junit.jupiter.api.Test
    public void test(){
      int n = 5;
      int arr1[] = {9, 20, 28, 18, 11};
      int arr2[] = {30, 1, 21, 17, 28};

        for (int i : arr1) {
            String s = Integer.toBinaryString(i);
            System.out.println("s1 = " + s);
        }
        for (int i : arr2) {
            String s = Integer.toBinaryString(i);
            System.out.println("s2 = " + s);
        }

    }
}
