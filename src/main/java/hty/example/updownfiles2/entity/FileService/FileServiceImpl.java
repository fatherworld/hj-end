package hty.example.updownfiles2.entity.FileService;
import hty.example.updownfiles2.entity.vo.FileVo;
import hty.example.updownfiles2.mapper.FileInfoMapper;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import hty.example.updownfiles2.mapper.FileInfoMapper;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
//加载指定的配置文件
@PropertySource("classpath:/application.properties")
public class FileServiceImpl implements FileService {

    public FileServiceImpl()
    {
        id = 1000; //设置uuid或者后台数据库自动生成
    }

    //获取配置文件中的配置 为属性动态赋值 注解@Value
    @Value("${image.localPathDir}")
    private String localPathDir;  // Windows路径 例如 D:/files

    private String localUrlPath1="C:/Users/bayes/bayesdir/code/java/hty/updownfiles2/src/main/resources/uploadpath/";

    private int id ;
    @Override
    public FileVo upload(MultipartFile file) throws IOException {
        //1.1 获取文件名称
        String fileName = file.getOriginalFilename();
        //2. 目录结构
        //2.1 实现分目录存储  可以以时间维度年月日进行分隔 /yyyy/MM/dd/
        String datePath =
                new SimpleDateFormat("/yyyy/MM/dd/").format(new Date());
        //2.2 最终本地图片存储路径
        //    进行目录的拼接  "/Users/zhaoguoxing/Desktop/files/2022/03/22";
        System.out.println("datePath:"+datePath);
        System.out.println("localUrlPath:"+localUrlPath1);
        String localDir = localUrlPath1 + datePath;
        System.out.println("localDir:"+localDir);
        //2.3 需要创建目录
        File dirFile = new File(localDir);
        if (!dirFile.exists()) {
            System.out.println("dirfile not exists need to create");
            if(dirFile.getParentFile().exists())
            {
                dirFile.getParentFile().delete();
            }
            if(dirFile.mkdirs())
            {
                System.out.println("create dir successd");
            }
        }
        //3.文件名称重复  采用UUID防止文件重名 uuid.pdf
        String uuid = UUID.randomUUID().toString()
                .replace("-", "");
        //3.1.获取文件类型
        //fileName = abc.jpg  fileType=.pdf
        String fileType =
                fileName.substring(fileName.lastIndexOf("."));
        //3.2.重新拼接文件名  uuid.pdf
        String realFileName = uuid + fileType;

        //4.最终文件存储的路径+文件名 = /2021/11/11/uuid.pdf
        //可以在这里将路径存储到数据库 实际保存文件地址 此处省略
        String filePathAll = localDir + realFileName;
        //5.实现文件上传
        File realFile = new File(filePathAll);
        file.transferTo(realFile);
        System.out.println("realfile:"+realFile.getName());
        //6.封装FileVO对象  //2021/11/11/uuid.pdf 图片路径 稍后给前台传递
        //我们不可能将filePathAll告诉用户，这样不安全，容易被攻击
        //virtualPath 半个路径，没有具体盘符或根目录 /2021/11/11/uuid.pdf
        String virtualPath = datePath + realFileName;

        //获取当前时间
        Date time = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String StrDate = formatter.format(time);

        //7.将文件存储路径(半个路径，没有具体盘符或根目录) 和 重命名后的文件名 封装到实体类中
        //return new FileVo(id++,virtualPath, realFileName,StrDate);


        return new FileVo(virtualPath,realFileName,StrDate);
    }

    @Override
    public void download(OutputStream os, String filePath) throws IOException {
        Blob fileblob;
        //下载文件的路径
        String downPath = localUrlPath1 + filePath;
        //读取目标文件
        File f = new File(downPath);
        System.out.println("downPath:"+downPath);
        if(!f.exists())
        {
            System.out.println("文件不存在");
            return ;
        }
        System.out.println("文件名："+f.getName());
        System.out.println("绝对路径："+f.getAbsolutePath());
        //创建输入流
        InputStream is = new FileInputStream(f);
        //做一些业务判断，我这里简单点直接输出，你也可以封装到实体类返回具体信息
        if (is == null) {
            System.out.println("文件不存在");
            return;
        }
        byte[] filebyte = new byte[is.available()];
        while(is.read(filebyte) != -1)
        {
            os.write(filebyte);
        }
        /*
        //创建字节数组
        byte[] filebyte = new byte[is.available()];

        is.read(filebyte);

        //创建Blob对象
        try {
            fileblob = new SerialBlob(filebyte);
        } catch (SerialException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("downPath" + downPath);
        //利用IOUtils将输入流的内容 复制到输出流
        //org.apache.tomcat.util.http.fileupload.IOUtils
        //项目搭建是自动集成了这个类 直接使用即可
        is.close();
        */
    }
}
