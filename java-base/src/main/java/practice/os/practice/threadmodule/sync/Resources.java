package practice.os.practice.threadmodule.sync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/17
 * @VERSION 1.0
 * @DESC
 */
public class Resources {
    // 资源名,占用情况
    public static Map<String,String> IoResources = new HashMap<>();
    // 资源名,占用情况
    public static Map<String,String> EquipResource = new HashMap<>();
    public static List<String> dataPool = new ArrayList<>();


}
