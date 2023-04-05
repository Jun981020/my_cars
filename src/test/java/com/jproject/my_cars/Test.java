package com.jproject.my_cars;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @org.junit.jupiter.api.Test
    public void codeTest(){
        int num = 369;
        int result = 0;
        for(int i = 1; i<= num; i++){
            String temp = String.valueOf(i);
            if(temp.contains("3")){
                result++;
                continue;
            }
            if(temp.contains("6")){
                result++;
                continue;
            }
            if(temp.contains("9")){
                result++;
                continue;
            }
        }
        System.out.println("result = " + result);
    }
    @org.junit.jupiter.api.Test
    public void code2(){
        int num = 147;
        String answer = String.valueOf(num);
        HashMap<String, String> map = new HashMap<>();
        map.put("0","zero");
        map.put("1","one");
        map.put("2","two");
        map.put("3","three");
        map.put("4","four");
        map.put("5","five");
        map.put("6","six");
        map.put("7","seven");
        map.put("8","eight");
        map.put("9","nine");
        StringBuffer buffer = new StringBuffer();
        String[] split = answer.split("");
        for (String s : split) {
            String value = map.get(s);
            buffer.append(value);
        }
        System.out.println("buffer.toString() = " + buffer.toString());
    }
    @org.junit.jupiter.api.Test
    public void code3(){
        String pattern1 = "^010-\\d{4}-\\d{4}$";
        String pattern2 = "^010\\d{8}$";
        String pattern3 = "^\\+82-10-\\d{4}-\\d{4}$";
        String input1 = "010-1111-2222";
        String input2 = "01012341234";
        String input3 = "+82-10-1234-1234";
        if(input3.matches(pattern3)){
            System.out.println("일치함");
        }else
            System.out.println("불일치");
    }
    @org.junit.jupiter.api.Test
    public void inpo1(){
        int p = 1000000;
        int m = 9;
        int d = 1300000;
        double k = 0.01;
        while(true){
            int loopResult = loop(p, d, k);
            if(loopResult == m){
                break;
            }else{
                k = k+0.01;
            }
        }
        int answer = (int) k*100;
        System.out.println(answer);


    }
    public int loop(int p ,int d , double k){
        int month = 0;
        while(p < d){
            p = (int) (p * (1+k));
            month++;
        }
        return month;
    }
    @org.junit.jupiter.api.Test
    public void inpo2(){
        int[] arr = {1,5,8,2,10,5,4,6,4,8};
        Set<Integer> set = new LinkedHashSet<>();
        for (int i : arr) {
            set.add(i);
        }
        int[] answ = set.stream().mapToInt(i -> i).toArray();
    }
    @org.junit.jupiter.api.Test
    public void inpo3(){
        String[] orders = {"alex pizza past","alex pizza pizza","bob pizza past noodle"};
        HashMap<String,LinkedHashSet<String>> map = new HashMap<>();
        HashMap<String,String> map1 = new HashMap<>();
        map1.put("1","1");
        map1.put("1","2");
        map1.put("2","1");
        System.out.println("map1 = " + map1);
    }
    @org.junit.jupiter.api.Test
    public void inpo4(){
        String[] card = {"ABACDEFG","NOPQRSTU","HIJKLKMM"};
        String[] word = {"GPOM","GPMZ","EFU","MMNA"};
        StringBuffer sb = new StringBuffer();
        ArrayList<String> list = new ArrayList<>();
        List<String> cardList = new ArrayList<>();
        ArrayList<String> resultList = new ArrayList<>();
        for(int i = 0;i<card.length;i++){
            list.add("0");
        }
        for(int i = 0; i < card.length; i++){
            cardList.add(i,card[i]);
        }
        for (int i = 0; i < word.length; i++) {
            int parI = i;
            Arrays.stream(card).forEach(v -> {
                String[] split = word[parI].split("");
                for (String s : split) {
                    if(v.contains(s)){
                        sb.append(s);
                        list.remove(parI);
                    }
                }
            });
            if(sb.toString().equals(word[i])&&list.size()==0){
                resultList.add(word[i]);
            }
            sb.setLength(0);
        }
        String[] answer = resultList.toArray(new String[resultList.size()]);
        for (String s : answer) {
            System.out.println("s = " + s);
        }

//        for(int i =0; i <word.length; i++){
//            String oneWord = word[i]; //"GPOM"
//            String[] letters = oneWord.split("");// ["G","P","O","M"]
//            Arrays.stream(letters).forEach(
//                    s -> {
//                        for (String cardWord : card) {
//                            if(cardWord.contains(s)){
//                                sb.append(s);
//                            }
//                        }
//                    }
//            );
//            if(sb.toString().equals(word[i])){
//                list.add(word[i]);
//            }
//            sb.setLength(0);
//        }
//        String[] strings = list.toArray(new String[list.size()]);
//        for(int i= 0; i < word.length;i++){
//            String[] oneWord = word[i].split("");
//            for (String letter : oneWord) {
//                long count = Arrays.stream(card).peek(
//                        s -> {
//                            String[] split = s.split("");
//                            for (String s1 : split) {
//                                if (s1.equals(letter)) ;
//                            }
//                        }
//                ).count();
//                if(count != 0){
//
//                }
//            }
//
//        }
    }
    @org.junit.jupiter.api.Test
    public void program1(){
        String[] phone_book = {"119", "97674223", "1195524421"};
//        boolean answer = true;
//        for(int i = 0; i < phone_book.length; i++){
//            for(int j = i+1; j < phone_book.length; j++){
//                if(phone_book[j].startsWith(phone_book[i])){
//                    answer =  false;
//                }
//            }
//        }

    }

}

