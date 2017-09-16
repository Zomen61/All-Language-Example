
//package idv.jk.web.parser;

import org.apache.commons.io.FileUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
//"s1""PRESTIGE""Moodyz""EBODY""OPPAI""TEK""Kawaii""AVOPEN"
//s1:(snis,soe)
//PRESTIGE:(abp,chn,siro,bgn,200GANA,259LUXU)
//Moodyz:(midd、mide、miad、migd、mimk)
//EBODY:(ebod、eyan)
//OPPAI:(pppd)
//Kawaii:(kawd、kwbd)
//TEK:(tek)
//SOD:(star)
//AVOPEN:(avop)
public class WebSourceCodeFetch{
    
    public String autoClassier(String type,String number,String dicFather){
    	return forDDM(type,number,dicFather);
    }
    
    private String forDDM(String type,String number,String dicFather){
    	String url; 
    	
    	switch (type){
    	case "abp":
    	case "chn":
    	case "bgn":
    		url="http://www.dmm.co.jp/digital/videoa/-/detail/=/cid=118"+type+"00"+number+"//";
    		break;
    	case "star":
    	case "sdmu":
    	case "sdab":
    	case "dandy":
    		url = "http://www.dmm.co.jp/digital/videoa/-/detail/=/cid=1"+type+"00"+number+"//";
    		break;
    		default:
    		url = "http://www.dmm.co.jp/digital/videoa/-/detail/=/cid="+type+"00"+number+"//";
    	}
		System.out.println(url);

		try {
			Connection conn = Jsoup.connect(url);
			conn.timeout(10000);
			Document doc = conn.get();
			// System.out.println(doc.html());
			String Name = "無出演者";
			Element namelist = doc.getElementById("performer");
			if (namelist != null) {
				Elements lists = namelist.getElementsByTag("a");
				for (Element list : lists) {
					Name = list.text();
				}
			}

			// Fecth Cover Image
			String imageurl = "";
			
			Elements imagelists = doc.getElementsByClass("tx10 pd-3");
			if (imagelists != null) {
				for (Element list : imagelists) {
					Elements imagelinkDatas = list.getElementsByTag("a");
					for (Element Data : imagelinkDatas) {
						imageurl = Data.attr("href");
						System.out.println(imageurl);
					}
				}
			}
			
			URL coverurl = new URL(imageurl);
			BufferedImage img = ImageIO.read(coverurl);
			File file = new File(dicFather+"//"+type+number+"//"+type+number+".jpg");
			ImageIO.write(img, "jpg", file);
			img.flush();
			
			File srcDir = new File(dicFather+"//"+type+number);
			File destDir = new File(dicFather+"//"+Name);
			FileUtils.moveDirectoryToDirectory(srcDir, destDir, true);
			//FileUtils.copyDirectory(srcDir, destDir);
			return "成功";
		} catch (IOException e) {
			System.out.println(type+number+"分類失敗...");
			System.out.println("原因:"+e.getMessage());
			//e.printStackTrace();
			return e.getMessage();
		}
    }
    
//    private void forS1(String type,String number,String dicFather){
//		String url = "http://www.s1s1s1.com/works/-/detail/=/cid="+type+number+"//";
//		try {
//			Document doc = Jsoup.connect(url).get();
//			// System.out.println(doc.html());
//			Elements content = doc.getElementsByClass("hd-actress-name");
//			String Name = "";
//			for (Element link : content) {
//				String linkHref = link.attr("href");
//				String linkText = link.text();
//				Name = link.text();
//			}
//			// Fecth Cover Image
//			Element imagelist = doc.getElementById("slide-photo");
//			Element image = imagelist.select("img").first();
//			String imageurl = image.absUrl("src");
//			
//			URL coverurl = new URL(imageurl);
//			BufferedImage img = ImageIO.read(coverurl);
//			File file = new File(dicFather+"//"+type+number+"//"+type+number+".jpg");
//			ImageIO.write(img, "jpg", file);
//			img.flush();
//			
//			File srcDir = new File(dicFather+"//"+type+number);
//			File destDir = new File(dicFather+"//"+Name);
//			FileUtils.moveDirectoryToDirectory(srcDir, destDir, true);
//			//FileUtils.copyDirectory(srcDir, destDir);
//
//		} catch (IOException e) {
//			System.out.println(type+number+"分類失敗...");
//			System.out.println("原因:"+e.getMessage());
//			//e.printStackTrace();
//		}
//    }
//    
//    private void forPRESTIGE(String type,String number,String dicFather){
//		String url = "http://www.prestige-av.com/goods/goods_detail.php?sku=tktabp-602";
//		try {
//			Document doc = Jsoup.connect(url).get();
//			System.out.println(doc.html());
//			Elements content = doc.getElementsByClass("lnk-actress");
//			String Name = "";
//			for (Element link : content) {
//				String linkHref = link.attr("href");
//				String linkText = link.text();
//				Name = link.text();
//			}
//			// Fecth Cover Image
////			Element imagelist = doc.getElementById("slide-photo");
////			Element image = imagelist.select("img").first();
////			String imageurl = image.absUrl("src");
//			Element image;
//			String imageurl = "";
//			Elements imagecontent = doc.getElementsByClass("bx-package");
//			for (Element link : imagecontent) {
//				image = link.select("img").first();
//				imageurl = image.absUrl("src");
//			}
//			
//			URL coverurl = new URL(imageurl);
//			BufferedImage img = ImageIO.read(coverurl);
//			File file = new File(dicFather+"//"+type+number+"//"+type+number+".jpg");
//			ImageIO.write(img, "jpg", file);
//			img.flush();
//			
//			File srcDir = new File(dicFather+"//"+type+number);
//			File destDir = new File(dicFather+"//"+Name);
//			FileUtils.moveDirectoryToDirectory(srcDir, destDir, true);
//			//FileUtils.copyDirectory(srcDir, destDir);
//
//		} catch (IOException e) {
//			System.out.println(type+number+"分類失敗...");
//			System.out.println("原因:"+e.getMessage());
//			//e.printStackTrace();
//		}
//    }
//    
//    private void forMoodyz(String type,String number,String dicFather){
//		String url = "https://www.moodyz.com/works/detail/"+type+number+"//";
//		try {
//			Document doc = Jsoup.connect(url).get();
//			// System.out.println(doc.html());
//			Elements content = doc.getElementsByClass("lnk-actress");
//			String Name = "";
//			for (Element link : content) {
//				String linkHref = link.attr("href");
//				String linkText = link.text();
//				Name = link.text();
//			}
//			// Fecth Cover Image
////			Element imagelist = doc.getElementById("slide-photo");
////			Element image = imagelist.select("img").first();
////			String imageurl = image.absUrl("src");
//			Element image;
//			String imageurl = "";
//			Elements imagecontent = doc.getElementsByClass("bx-package");
//			for (Element link : imagecontent) {
//				image = link.select("img").first();
//				imageurl = image.absUrl("src");
//			}
//			
//			URL coverurl = new URL(imageurl);
//			BufferedImage img = ImageIO.read(coverurl);
//			File file = new File(dicFather+"//"+type+number+"//"+type+number+".jpg");
//			ImageIO.write(img, "jpg", file);
//			img.flush();
//			
//			File srcDir = new File(dicFather+"//"+type+number);
//			File destDir = new File(dicFather+"//"+Name);
//			FileUtils.moveDirectoryToDirectory(srcDir, destDir, true);
//			//FileUtils.copyDirectory(srcDir, destDir);
//
//		} catch (IOException e) {
//			System.out.println(type+number+"分類失敗...");
//			System.out.println("原因:"+e.getMessage());
//			//e.printStackTrace();
//		}
//    }
//    
//    private void forEBODY(String type,String number,String dicFather){
//		String url = "http://www.av-e-body.com/works/detail/"+type+number+"//";
//		try {
//			Document doc = Jsoup.connect(url).get();
//			// System.out.println(doc.html());
//			Elements content = doc.getElementsByClass("works-button has");
//			String Name = "";
//			Boolean CanJump = false;
//			for (Element one : content) {
//				Elements links = one.getElementsByTag("a");
//				for (Element link : links) {
//					String linkHref = link.attr("href");
//					if (linkHref.indexOf("actress") != -1) {
//						String linkText = link.text();
//						Name = link.text();
//						CanJump = true;
//						break;
//					}
//				}
//				if(CanJump){
//					break;
//				}
//			}
//			
//			Element image;
//			String imageurl = "";
//			Elements imagecontent = doc.getElementsByClass("pic-pake-large");
//			for (Element link : imagecontent) {
//				image = link.select("img").first();
//				imageurl = image.absUrl("src");
//			}
//			
//			URL coverurl = new URL(imageurl);
//			BufferedImage img = ImageIO.read(coverurl);
//			File file = new File(dicFather+"//"+type+number+"//"+type+number+".jpg");
//			ImageIO.write(img, "jpg", file);
//			img.flush();
//			
//			File srcDir = new File(dicFather+"//"+type+number);
//			File destDir = new File(dicFather+"//"+Name);
//			FileUtils.moveDirectoryToDirectory(srcDir, destDir, true);
//			//FileUtils.copyDirectory(srcDir, destDir);
//
//		} catch (IOException e) {
//			System.out.println(type+number+"分類失敗...");
//			System.out.println("原因:"+e.getMessage());
//			//e.printStackTrace();
//		}
//    }
//    
//    private void forOPPAI(String type,String number,String dicFather){
//		String url = "http://www.oppai-av.com/works/-/detail/=/cid="+type+number+"//";
//		try {
//			Document doc = Jsoup.connect(url).get();
//			// System.out.println(doc.html());
//			Elements content = doc.getElementsByClass("tx-actress-name");
//			String Name = "";
//			Boolean CanJump = false;
//			for (Element one : content) {
//				Elements links = one.getElementsByTag("a");
//				for (Element link : links) {
//					String linkHref = link.attr("href");
//					if (linkHref.indexOf("actress") != -1) {
//						String linkText = link.text();
//						Name = link.text();
//						CanJump = true;
//						break;
//					}
//				}
//				if(CanJump){
//					break;
//				}
//			}
//			// Fecth Cover Image
////			Element imagelist = doc.getElementById("slide-photo");
////			Element image = imagelist.select("img").first();
////			String imageurl = image.absUrl("src");
//			Element image;
//			String imageurl = "";
//			Elements imagecontent = doc.getElementsByClass("img-package");
//			for (Element link : imagecontent) {
//				image = link.select("img").first();
//				imageurl = image.absUrl("src");
//			}
//			
//			URL coverurl = new URL(imageurl);
//			BufferedImage img = ImageIO.read(coverurl);
//			File file = new File(dicFather+"//"+type+number+"//"+type+number+".jpg");
//			ImageIO.write(img, "jpg", file);
//			img.flush();
//			
//			File srcDir = new File(dicFather+"//"+type+number);
//			File destDir = new File(dicFather+"//"+Name);
//			FileUtils.moveDirectoryToDirectory(srcDir, destDir, true);
//			//FileUtils.copyDirectory(srcDir, destDir);
//
//		} catch (IOException e) {
//			System.out.println(type+number+"分類失敗...");
//			System.out.println("原因:"+e.getMessage());
//			//e.printStackTrace();
//		}
//    }
    
//	switch (type) {
//	// S1
//	case "snis":
//	case "soe":
//		forS1(type, number, dicFather);
//		break;
//	// 蚊香社
//	case "abp":
//	case "chn":
//	case "siro":
//	case "bgn":
//	case "200GANA":
//	case "259LUXU":
//		//forPRESTIGE(type,number,dicFather);
//		//要VPN
//		break;
//	// Moodyz
//	case "midd":
//	case "mide":
//	case "miad":
//	case "migd":
//	case "mimk":
//		forMoodyz(type, number,dicFather);
//		break;
//	// EBODY:
//	case "ebod":
//	case "eyan":
//		forEBODY(type,number,dicFather);
//		break;
//	// OPPAI
//	case "pppd":
//		forOPPAI(type,number,dicFather);
//		break;
//	// Kawaii:(kawd、kwbd)
//	case "kawd":
//	case "kwbd":
//		//要VPN
//		break;
//	// TEK:(tek)
//	case "tek":
//		//要VPN
//		break;
//	// SOD:(star)
//	case "star":
//		
//		break;
//	// AVOPEN:(avop)
//	case "avop":
//		
//		break;
//	default:
//		System.out.println("不支援此片商");
//	}


}
