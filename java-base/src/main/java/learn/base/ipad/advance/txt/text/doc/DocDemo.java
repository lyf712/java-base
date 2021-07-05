package learn.base.ipad.advance.txt.text.doc;

import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/14
 * @VERSION 1.0
 * @DESC
 *
 * 进行doc文档的处理
 * 研究对象
 * 1.文字
 * 2.图片
 * 3.表格
 * 4.公式
 *
 */
public class DocDemo {

    // Java自带工具
    void test1() throws IOException {
        String path = "";
        InputStream inputStream = new FileInputStream(path);
        XWPFDocument document = new XWPFDocument(inputStream);

    }
}
