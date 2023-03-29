package com.jproject.my_cars;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Test {

    @org.junit.jupiter.api.Test
    public void test(){
//        DataQueue<Integer> dataQueue = new DataQueue<>();
//        dataQueue.enqueue(2);
//        dataQueue.enqueue(3);
//        dataQueue.enqueue(4);
//        System.out.println("dataQueue = " + dataQueue.dequeue());
//        System.out.println("dataQueue = " + dataQueue.dequeue());
//        System.out.println("dataQueue = " + dataQueue.dequeue());

        DataStack<Integer> dataStack = new DataStack<>();
        dataStack.push(1);
        dataStack.push(2);
        dataStack.push(3);
        dataStack.push(4);

        System.out.println("dataStack.pop() = " + dataStack.pop());
    }
//    public class DataQueue<D>{
//        private ArrayList<D> queue = new ArrayList<>();
//
//        public void enqueue(D data){
//            queue.add(data);
//        }
//        public D dequeue(){
//            if(queue.isEmpty()){
//                return null;
//            }
//            return queue.remove(0);
//        }
//    }

    public class DataStack<D>{
        private ArrayList<D> stack = new ArrayList<>();

        public void push(D data){
            stack.add(data);
        }
        public D pop(){
            if(stack.isEmpty()){
                return null;
            }
            return stack.remove(stack.size()-1);
        }

    }
    @org.junit.jupiter.api.Test
    public void test1(){
        String s1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String s2 = s1.toLowerCase();
        String s = "AB";
        StringBuilder result = new StringBuilder();
        int n = 1;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            System.out.println("c = " + c);
            if(s1.contains(String.valueOf(c))){
                int i1 = s1.indexOf(s.charAt(i));
                System.out.println("i1 = " + i1);
                int index = i1+n;
                System.out.println("index = " + index);
                char c1 = s1.charAt(index);
                System.out.println("c1 = " + c1);
                result.append(c1);
            }else{
                int i1 = s2.indexOf(s.charAt(i));
                System.out.println("i1 = " + i1);
                int index = i1+n;
                System.out.println("index = " + index);
                char c1 = s2.charAt(index);
                System.out.println("c1 = " + c1);
                result.append(c1);
            }
        }
        System.out.println("result = " + result);
    }
}

