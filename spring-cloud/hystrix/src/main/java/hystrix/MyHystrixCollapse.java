package hystrix;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/17
 * @VERSION 1.0
 * @DESC
 * Hystrix合并请求
 */
//public class MyHystrixCollapse extends HystrixCollapser<List<String>,String> {
//    @Override
//    public Object getRequestArgument() {
//        return null;
//    }
//
//    @Override
//    protected void mapResponseToRequests(Object o, Collection collection) {
//
//    }
//
////    @Override
////    protected void mapResponseToRequests(Object o, Collection collection) {
////
////    }
//
//    @Override
//    protected HystrixCommand<List<String>> createCommand(Collection collection) {
//        return null;
//    }
//}
