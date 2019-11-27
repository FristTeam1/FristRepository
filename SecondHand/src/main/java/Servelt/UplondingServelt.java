package Servelt;

import Service.HouseService;
import bean.House;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.UUID;

@WebServlet("/Uploading")
@MultipartConfig
public class UplondingServelt extends HttpServlet {//上传房源信息
    private static final long serialVersionUID = 1L;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            response.setContentType("text/" +
                    "html;charset=utf-8");
            request.setCharacterEncoding("utf-8");
            String faddress=request.getParameter("city");
            String farea=request.getParameter("square");
            String price=request.getParameter("pric");
            String thing=request.getParameter("self");
            String fx=request.getParameter("fx");
            Part file = request.getPart("file");
            Part file1 = request.getPart("file1");
            Part file2 = request.getPart("file2");
            Part file3= request.getPart("file3");
            String filename=file.getHeader("content-disposition");
            String filename1=file1.getHeader("content-disposition");
            String filename2=file2.getHeader("content-disposition");
            String filename3=file3.getHeader("content-disposition");
            String root=request.getServletContext().getRealPath("/img");//上传文件路径
            String str=filename.substring(filename.lastIndexOf("."), filename.length()-1);//文件后缀
            String str1=filename1.substring(filename1.lastIndexOf("."), filename1.length()-1);
            String str2=filename2.substring(filename2.lastIndexOf("."), filename2.length()-1);
            String str3=filename3.substring(filename3.lastIndexOf("."), filename3.length()-1);
            String file11= UUID.randomUUID().toString()+str;
            String file12= UUID.randomUUID().toString()+str1;
            String file13= UUID.randomUUID().toString()+str2;
            String file14= UUID.randomUUID().toString()+str3;
            String newfilename=root+"\\"+file11;
            String newfilename1=root+"\\"+file12;
            String newfilename2=root+"\\"+file13;
            String newfilename3=root+"\\"+file14;
            file.write(newfilename);
            file1.write(newfilename1);
            file2.write(newfilename2);
            file3.write(newfilename3);
            String B="D:\\360MoveData\\Users\\25650\\Documents\\GitHub\\FristRepository\\SecondHand\\src\\main\\webapp\\img\\"+file11;
            String B1="D:\\360MoveData\\Users\\25650\\Documents\\GitHub\\FristRepository\\SecondHand\\src\\main\\webapp\\img\\"+file12;
            String B2="D:\\360MoveData\\Users\\25650\\Documents\\GitHub\\FristRepository\\SecondHand\\src\\main\\webapp\\img\\"+file13;
            String B3="D:\\360MoveData\\Users\\25650\\Documents\\GitHub\\FristRepository\\SecondHand\\src\\main\\webapp\\img\\"+file14;
            FileInputStream fis = new FileInputStream(newfilename);                            //字节输入流,用来读取原路径的文件
            FileInputStream fis1 = new FileInputStream(newfilename1);
            FileInputStream fis2 = new FileInputStream(newfilename2);
            FileInputStream fis3 = new FileInputStream(newfilename3);
            DataInputStream dis= new DataInputStream(fis);                                //字节二进制输入流,用来读取原路径的文件
            DataInputStream dis1= new DataInputStream(fis1);
            DataInputStream dis2= new DataInputStream(fis2);
            DataInputStream dis3= new DataInputStream(fis3);
            FileOutputStream fos = new FileOutputStream(B);
            FileOutputStream fos1 = new FileOutputStream(B1);
            FileOutputStream fos2 = new FileOutputStream(B2);
            FileOutputStream fos3 = new FileOutputStream(B3);
            DataOutputStream dos = new DataOutputStream(fos);                           //字节二进制输出流,用来将文件写入新路径
            DataOutputStream dos1 = new DataOutputStream(fos1);
            DataOutputStream dos2 = new DataOutputStream(fos2);
            DataOutputStream dos3 = new DataOutputStream(fos3);
            byte[] b = new byte[1024];                                                        //储存读取的文件
            int length = -1;                                                                    //用来储存返回的文件长度
            //开始读取
            while((length = dis.read(b)) != -1) {                                                //使用read(byte[] b)方法将文件内容储存到缓冲区再判断长度是否大于零
                dos.write(b,0,length);                                                            //将byte数组存储的内容写入到指定文件
            }
            b=new byte[1024];
            length=-1;
            while((length = dis1.read(b)) != -1) {                                                //使用read(byte[] b)方法将文件内容储存到缓冲区再判断长度是否大于零
                dos1.write(b,0,length);                                                            //将byte数组存储的内容写入到指定文件
            }
            b=new byte[1024];
            length=-1;
            while((length = dis2.read(b)) != -1) {                                                //使用read(byte[] b)方法将文件内容储存到缓冲区再判断长度是否大于零
                dos2.write(b,0,length);                                                            //将byte数组存储的内容写入到指定文件
            }
            b=new byte[1024];
            length=-1;
            while((length = dis3.read(b)) != -1) {                                                //使用read(byte[] b)方法将文件内容储存到缓冲区再判断长度是否大于零
                dos3.write(b,0,length);                                                            //将byte数组存储的内容写入到指定文件
            }
            //关闭所有流
            dis.close();
            dos.flush();
            fis.close();
            fos.close();
            dis1.close();
            dos1.flush();
            fis1.close();
            fos1.close();
            dis2.close();
            dos2.flush();
            fis2.close();
            fos2.close();
            dis3.close();
            dos3.flush();
            fis3.close();
            fos3.close();
            System.out.println("文件复制完毕！");
            House house=new House();
            house.setFaddress(faddress);
            house.setFarea(farea);
            house.setPric(price);
            house.setThing(thing);
            house.setPicture1("/SecondHand/img/"+file11);
            house.setPicture2("/SecondHand/img/"+file12);
            house.setPicture3("/SecondHand/img/"+file13);
            house.setPicture4("/SecondHand/img/"+file14);
            house.setFx(fx);
            System.out.println(house);
            int a= HouseService.add(house);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/uploading.html").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
