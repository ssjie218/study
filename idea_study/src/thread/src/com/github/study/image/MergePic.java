package thread.src.com.github.study.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 拼接图片
 * A4纸 210mm×297mm
 * Created by pc on 2018/9/13.
 */
public class MergePic {

    private static boolean merge(String[] pics, String type, String dst_pic,int bs) {

        int len = pics.length;
        if (len < 1) {
            System.out.println("pics len < 1");
            return false;
        }
        File[] src = new File[len];
        BufferedImage[] images = new BufferedImage[len];
        int[][] ImageArrays = new int[len][];
        for (int i = 0; i < len; i++) {
            try {
                src[i] = new File(pics[i]);
                images[i] = ImageIO.read(src[i]);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            int width = images[i].getWidth();
            int height = images[i].getHeight();
            ImageArrays[i] = new int[width * height];// 从图片中读取RGB
            ImageArrays[i] = images[i].getRGB(0, 0, width, height,
                    ImageArrays[i], 0, width);
        }

        int dst_height =  images[0].getHeight()*2;
        int dst_width = images[0].getWidth()*2;
        System.out.println(dst_width);
        System.out.println(dst_height);
        if (dst_height < 1) {
            System.out.println("dst_height < 1");
            return false;
        }

        // 生成新图片
        try {
            // dst_width = images[0].getWidth();
            BufferedImage ImageNew = new BufferedImage(dst_width, dst_height,
                    BufferedImage.TYPE_INT_RGB);
            int height_i = 0;
            int width_i = 0;
            for (int i = 0; i < images.length; i++) {
                if (i!=0 && i % bs == 0) {
                    height_i += images[i].getHeight();
                    width_i = 0;
                }
                System.out.println(width_i+"|"+height_i+i);
                System.out.println(ImageArrays.length);
                ImageNew.setRGB(width_i, height_i, images[i].getWidth(), images[i].getHeight(),
                        ImageArrays[i], 0, images[i].getWidth());
                width_i += images[i].getWidth();
            }

            File outFile = new File(dst_pic);
            ImageIO.write(ImageNew, type, outFile);// 写图片
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    private static String[] getPics(int num,int imgnum){
        String[] pics = new String[imgnum];
        int initNum = 1+imgnum*num;
        System.out.println(initNum);
        for (int i = 0; i < imgnum; i++) {
            System.out.println("F:/gzt/wechat2/ttt (" + initNum + ").PNG");
            pics[i] = "F:/gzt/wechat2/ttt (" + initNum + ").PNG";
            initNum ++;
        }
        return pics;
    }
    private static String[] getPicsT(){
        String[] pics = new String[2];
        int initNum = 253;
        System.out.println(initNum);
        for (int i = 0; i < 2; i++) {
            System.out.println("F:/gzt/wechat2/ttt (" + initNum + ").PNG");
            pics[i] = "F:/gzt/wechat2/ttt (" + initNum + ").PNG";
            initNum ++;
        }
        return pics;
    }
    public static void main(String[] args) {
//        int imgnum=4;
//        int page=254/imgnum+1;
//        //String[] pics = new String[totalNum];
//        for (int i = 0; i < page; i++) {
//            merge(getPics(i,imgnum), "PNG", "F:/gzt/result/"+i+".PNG",2);
//        }

        merge(getPicsT(), "PNG", "F:/gzt/result/"+63+".PNG",2);
    }
}
