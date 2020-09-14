package com.sp;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import static java.lang.Math.abs;
public class Main {

    public static void main(String[] args) {

        readText();
    }

    private static void readText() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("text.txt")))) {
            String line;
            ArrayList<String> worlds = new ArrayList<>();
            List<String> w = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                w=getAllWorlds(line);
                worlds.addAll(w);
            }
            worlds = removeDuplicatesAndEmptys(worlds);
            for(String c : getWorldsDifference(worlds))
            {
                System.out.println(c);
            }
        }
        catch (IOException e) {
            System.out.println("cannot read file");
        }
    }
    private static ArrayList<String> removeDuplicatesAndEmptys(ArrayList<String> list)
    {
        Set<String> set = new LinkedHashSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        list.removeAll(Arrays.asList("",null));
        return list;
    }
    private static String clearPunctuation(String s){
        s = s.replaceAll("[^a-zA-Z а-яА-Я]", "");
        return s;
    }
    private static List<String> getAllWorlds(String s){

        List<String> worlds = Arrays.asList(clearPunctuation(s).split(" "));

        return worlds;
    }
    private static List<String> getWorldsDifference (ArrayList<String> list)
    {
        int difference=0,maxDifference=difference;
        List<String> worldWithMaxDifference=new ArrayList<>();
        List<String> usedWorlds = new ArrayList<>();
        for(var w1 :list){
            for(var w2:list){
                if(usedWorlds.contains(w2))
                    continue;
                int length = w1.length()>w2.length()?w2.length():w1.length();
                for(int i=0;i<length;i++)
                {
                    if(!(w1.charAt(i) == w2.charAt(i)))
                        difference++;
                }
                difference+=abs(w1.length()-w2.length());
                if(difference>maxDifference)
                {
                    maxDifference=difference;
                    worldWithMaxDifference.clear();
                    worldWithMaxDifference.add(w1);worldWithMaxDifference.add(w2);
                }
                else if(difference == maxDifference)
                {
                    worldWithMaxDifference.add(w1);worldWithMaxDifference.add(w2);
                }
                difference=0;
            }
            usedWorlds.add(w1);
        }
        return worldWithMaxDifference;
    }
}
