import javax.xml.bind.Element;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

/**
 * @author zyh
 * @create 2020-11-12 20:08
 */
public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("请输入表达式：");
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        System.out.println(next.replaceAll(" ",""));
        Calculator(next);
    }

    private static int Calculator(String next){
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('#',0);
        map.put('(',1);
        map.put('-',2);
        map.put('+',2);
        map.put('*',3);
        map.put('/',3);
        map.put(')',4);


        char[] chars = next.toCharArray();
        Stack<Character> ch1 = new Stack<>();
        Stack<Character> ch2 = new Stack<>();

        for (char item : chars){
            if (map.get(item) != null){
                if (ch1.size() == 0){
                    ch1.push(item);
                }else{
                    if (map.get(ch1.peek()) < map.get(item)){
                        ch1.push(item);
                    }else{
                        while (map.get(ch1.peek()) > map.get(item)){
                            char top = ch1.pop();
                            ch2.push(top);
                        }
                        ch1.push(item);
                    }
                }
            }else{
                ch2.push(item);
            }
        }
        for (char item : ch1){
            ch2.push(item);
        }

        Stack<Character> ch3 = new Stack<>();
        for(char item : ch2){
            if(map.get(item) == null){
                ch3.push(item);
            }else {
                int tmp=0;
                char top = ch3.pop();
                char top2 = ch3.pop();
                switch (item){
                    case '+':
                         tmp = top2 + top;
                    case '-':
                        tmp = top2 - top;
                    case '*':
                        tmp = top2 * top;
                    case '/':
                        tmp = top2 / top;
                }
                ch3.push((char)tmp);
            }
        }

        return (int)ch3.pop();
    }
}


