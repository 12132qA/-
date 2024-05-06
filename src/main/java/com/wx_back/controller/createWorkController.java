package com.wx_back.controller;
import com.wx_back.common.R;
import com.wx_back.common.createWorkDto;
import com.wx_back.common.workDto;
import com.wx_back.pojo.createWork;
import com.wx_back.pojo.workImg;
import com.wx_back.server.impl.createWorkServiceImpl;
import com.wx_back.server.impl.workImgServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
/**
 *  新建 我的作品
 * */
@RestController
@CrossOrigin
@SuppressWarnings("all")
@RequestMapping("/create")
@Slf4j
public class createWorkController {
    @Autowired
    public createWorkServiceImpl createWorkService;
    //文件保存在磁盘的地址
    @Value("${wx_back.path}")
    String fileSavePath;
    @Autowired
     private workImgServiceImpl workImgService;
    /**
     * @Param createWorkDto
     *
     *    添加的作品具体信息的实体类
     * */

   @RequestMapping("/insert")
    public R<String> saveCreateWork(createWorkDto createWorkDto){
       createWork createWork = new createWork();
       BeanUtils.copyProperties(createWorkDto,createWork,"session_key","phone");
       String date = createWork.getDate();
       // 2023-07-28T19:11:07.826Z
       // 处理一下 date

       StringBuilder str = new StringBuilder(date);
       String resDate = str.substring(1,11);
       log.info("处理后的date : "+resDate);
       createWork.setDate(resDate);
       String res =  createWorkService.saveWork(createWork)?"存入成功":"失败";
        return R.success(res);
    }

    /**
     * 获取 作品类
     * @Param phone
     *     以 phone 为 用户主键id 获取 用户相应的 作品信息
     **/
    @RequestMapping("/get")
    public R<List<createWork>> getCreateWork(workDto workDto){
        log.info("获取 作品 phone 是{}", workDto.getPhone());
        List<createWork> res = createWorkService.getWork(workDto.getPhone());
        if(res.size()>=1) return R.success(res);
        return R.error("查找 错误");
    }

    /**
     * @Param filePath desc phone session_key
     *
     * */
    @RequestMapping("/upload")
    public R<String> upload(HttpServletRequest request,
                                @RequestParam("filePath") MultipartFile  myfile,
                            @RequestParam("desc") String name,
                            @RequestParam("phone") String phone,
                            @RequestParam("session_key") String session_key) throws IOException {

        log.info("upload 文件信息是 : filePath is {} ,desc is {} , phone is {} ,session_key is {}",myfile,name,phone,session_key);

        File fir=new File(fileSavePath);
        workImg workImg = new workImg();

        //不存在则创建文件夹
        if(!fir.exists()){
            fir.mkdirs();
        }
        //文件的后缀名
        String suffix= myfile.getOriginalFilename().substring(myfile.getOriginalFilename().lastIndexOf("."));
        //新文件名字 为了防止重复加上UUID
        String newFileName= UUID.randomUUID().toString().replaceAll("-","")+suffix;

        System.out.println("filesavfilePathepath:"+fileSavePath+" "+"newFileName:"+newFileName);
        //新的文件路径
        File newFile=new File(fileSavePath+newFileName);
        //把文件写入新的File文件
        myfile.transferTo(newFile);
        //url路径=http + :// + server名字 + port端口 + /imges/ + newFileName
        String url=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/images/" +newFileName;
        workImg.setUrl("/images/"+newFileName);
        workImg.setName(name);
        workImg.setPhone(phone);
        workImg.setSession_key(session_key);
        workImg.setFilePath(url); // 文件路径
        if(workImgService.saveImg(workImg) ){
            log.info("保存成功");
        }
        return R.success(url);
    }
    /**
     * 文件下载
     * @param name
     * @param response
     */
    @GetMapping("/download")
    public void download(String name, HttpServletResponse response){
       log.info(fileSavePath);
       log.info(name);
        try {
            //输入流，通过输入流读取文件内容                                            文件路径 + 文件名称
            FileInputStream fileInputStream = new FileInputStream(new File(fileSavePath+name));
            System.out.println( "......:"+fileInputStream);

            //输出流，通过输出流将文件写回浏览器，在浏览器展示图片了
            ServletOutputStream outputStream = response.getOutputStream();
            // 设置 返回文件类型
            response.setContentType("image/jpg");

            int len=0;
            byte[] bytes = new byte[1024];
            while ((len=fileInputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
                outputStream.flush(); //
            }
            //关闭资源 两个流
            outputStream.close();
            fileInputStream.close();

        } catch (Exception e) {
//            e.printStackTrace();
            log.info("未找到该文件");
//            System.out.println("未找到该文件");
        }
    }
    @RequestMapping("/getImgInfo")
    public R<String> getImg(String phone,String title){
        workImg w = workImgService.getOne(phone,title);
        return R.success(w.getFilePath()); // 获取 图片存储路径
    }

}
