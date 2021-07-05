package practice.os.initmate.resources.memerory;

import java.util.Arrays;

/**
 * @AUTHOR LYF
 * @DATE 2021/3/30
 * @VERSION 1.0
 * @DESC
 */
public class Memory1 implements Memory{

    static {
        Arrays.fill(record,0);//初始化内存
    }

    // 读取信息
    @Override
    public int read(int addr) {

        return Memory1.record[addr];
    }

    @Override
    public boolean write(int addr, int content) {



        return false;
    }

    @Override
    public boolean encode(int addr) {
        return false;
    }

    @Override
    public boolean unCode(int addr) {
        return false;
    }
}
