package com.liyun.stream;

import java.util.stream.Stream;

/**
 * limit方法:用于截取流中的元素
 * limit方法可以对流进行截取，只取用前n个。方法签名:
 * Stream<T> limit(long maxSize);
 *      参数是一个long型，如果集合当前长度大于参数则进行截取；否则不进行操作
 * limit方法是一个延迟方法,对流中的元素进行截取，返回的是一个新的流，所以可以继续调用stream流中的其他方法
 */
public class Stream_Limit {
    public static void main(String[] args) {
        String[] arr={"张三","李四","王五","赵六","田七"};
        Stream<String> stream = Stream.of(arr);

        Stream<String> stream2 = stream.limit(3);
        //遍历stream2流
        stream2.forEach((name)->{
            System.out.println(name);
        });
    }
}
