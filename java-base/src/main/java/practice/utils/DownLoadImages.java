package practice.utils;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/12
 * @VERSION 1.0
 * @DESC
 */
public class DownLoadImages {

    public static void main(String[]args) throws IOException {

        String str = "http://file.market.xiaomi.com/thumbnail/PNG/l62/AppStore/0406c15c8a5d844913eeddb60a6e374dacb1d7be7";

        String[]strs = {
                "http://file.market.xiaomi.com/thumbnail/PNG/l62/AppStore/072725ca573700292b92e636ec126f51ba4429a50",
                "http://file.market.xiaomi.com/thumbnail/PNG/l62/AppStore/0406c15c8a5d844913eeddb60a6e374dacb1d7be7",
                "http://file.market.xiaomi.com/thumbnail/PNG/l62/AppStore/0545964e015be4e1e1a21252b1f20b3373e234228",
                "http://file.market.xiaomi.com/thumbnail/PNG/l62/AppStore/075814d320b2fd1f6e6edfdb936140ba5034149df",
                "http://file.market.xiaomi.com/thumbnail/PNG/l62/AppStore/02f3bbfbeba7c4873986ef9f954cb6ee0b93887a2",
                "http://file.market.xiaomi.com/thumbnail/PNG/l62/AppStore/099a26415aa67428804aafa17d0e4d8314aa5d38c",
                "http://file.market.xiaomi.com/thumbnail/PNG/l62/AppStore/0ce694e909b4fd353239d74430ed59bfa0840576b",
                "http://file.market.xiaomi.com/thumbnail/PNG/l62/AppStore/0a577792b6d7847bf9d884bfe09f64ee142047fb9",
                "http://file.market.xiaomi.com/thumbnail/PNG/l62/AppStore/0fe9e65ec595c42f9328597d6844d0d9de3568eca",
                "http://file.market.xiaomi.com/thumbnail/PNG/l62/AppStore/0d037d42dbbb54f452ecb046a8e9b9424f4fd7031"
        };


        for(int i =0;i< strs.length;i++){
            URL url = new URL(strs[i]);
            InputStream inputStream = null;
            try {
                inputStream = url.openStream();

            }catch (Exception e){
                e.printStackTrace();
            }
            byte[] bytes = IOUtils.toByteArray(inputStream);
            File imageFile = new File("E:\\IdeaProjects\\java-base\\src\\main\\resources\\images\\" + (i+1)+".png");
            //创建输出流
            FileOutputStream outStream = new FileOutputStream(imageFile);
            //写入数据
            outStream.write(bytes);
            //关闭输出流
            outStream.close();
        }



    }
}
