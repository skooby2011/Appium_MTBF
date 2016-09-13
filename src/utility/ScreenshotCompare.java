package utility;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.android.AndroidDriver;

public class ScreenshotCompare {
	
	public static File actualImage;

	
	//自动截图，并将图片保存至本地
	public static void takeScreenShotat(AndroidDriver driver,String testCaseName){  
		   try {   
			   String filePath = "D:\\Appium\\actual";
			   File path = new File(filePath);
			   if (!path.exists()) {  
		            path.mkdir(); // 目录不存在的情况下，会抛出异常  
		        }  
			   
			  	Date now = new Date(); 
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");//可以方便地修改日期格式
				String time = dateFormat.format(now).toString();
			   
			  
			   
			    File pic = new File(filePath+"\\"+testCaseName+".jpg");
			    if (!pic.exists()) {
		            pic.createNewFile();  
		        }  
		        File f1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		        FileUtils.copyFile(f1,pic);    
		        
		        actualImage = new File(filePath+"\\"+testCaseName+".jpg");	
		        
		   }       
		   catch (IOException e) {e.printStackTrace();}  
		} 
	
	//获取本地已保存的文件
	public static BufferedImage getImageFromFile(File f){
	    BufferedImage img = null;   
	    try {
	        img = javax.imageio.ImageIO.read(f);
	    }   
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	    return img;
	 }
	
	
	//进行图片对比
	public static boolean sameAs(BufferedImage myImage,BufferedImage otherImage, double percent){
	    if (otherImage.getWidth() != myImage.getWidth()) {  
	       return false;    
	    }
	    if (otherImage.getHeight() != myImage.getHeight()) {
	       return false;
	    }
	    int width = myImage.getWidth(); 
	    int height = myImage.getHeight();   
	    int numDiffPixels = 0;  
	    for (int y = 0; y < height; y++) {
	        for (int x = 0; x < width; x++) {
	             if (myImage.getRGB(x, y) != otherImage.getRGB(x, y)) {
	             numDiffPixels++;
	             }
	        }
	    }   
	    double numberPixels = height * width;   
	    double diffPercent = numDiffPixels / numberPixels;  
	    return percent <= 1.0D - diffPercent;
	 }
	
	//获取图片对比结果
	public static String getResult(File expectedImage){
    Boolean same;
    String result=null;


    try{
    		BufferedImage img1=getImageFromFile(actualImage);
    		BufferedImage img2=getImageFromFile(expectedImage);
    		same=sameAs(img1,img2,0.0);
    		result = String.valueOf(same);	
    	}
        catch (Exception e) {e.printStackTrace();}    
    return result;    
 }	

}
