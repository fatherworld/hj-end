package hty.example.updownfiles2.controller;
import hty.example.updownfiles2.entity.FileService.FileServiceImpl;
import hty.example.updownfiles2.mapper.FileInfoBatisPlus;
import hty.example.updownfiles2.mapper.FileInfoMapper;
//import hty.example.updownfiles2.mapper.FileInfoMapperMybaitsPlus;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import hty.example.updownfiles2.entity.vo.FileVo;
import hty.example.updownfiles2.mapper.FileInfoMapper;
import hty.example.updownfiles2.entity.FileService.FileService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8082")
public class updownfile {
    private FileService fileService ;
    @Autowired
    private FileInfoMapper filemapper;
    //private FileInfoMapperMybaitsPlus fimm;
    @Autowired
    private FileInfoBatisPlus fbp;
    public updownfile() {
        this.fileService = new FileServiceImpl();
    }

    //http://localhost:8080/hello
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    //value是路由链接，method是请求的模式规则
    public String hello(String nickname,String phone)
    {
        return "你好啊" + nickname + "== 手机号："+phone;
    }

    @RequestMapping(value="/getTest2",method = RequestMethod.GET)
    public String getTest2(@RequestParam(value = "nickname",required = false) String name) //@RequestParam是将前端的nickname映射成name，// 前端必须要有nickname,require false是可以不传递
    {
        System.out.println("nickname"+name);
        return "Get请求";
    }
    @RequestMapping(value = "/gettest3",method = RequestMethod.POST)
    public String getPost2(String name,String passwd)
    {
        System.out.println(name + ":" + passwd);
        return "Post请求";
    }
    @PostMapping("/login")
    public void login(HttpServletResponse response,String username,String passwd)
    {

    }

    @PostMapping("/upload")
    public FileVo upload(HttpServletResponse response,MultipartFile file) throws IOException {
        System.out.println("upload--file is::::"+file.getOriginalFilename());
        //调用service中的业务方法
        FileVo fileVO = fileService.upload(file);
        response.reset();
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/x-download;charset=UTF-8");
        int idx = filemapper.insert(fileVO);
        if(idx >0)
        {
            System.out.println("插入数据成功");
        }
        else
        {
            System.out.println("插入数据失败");
        }
        return fileVO;
    }

    @GetMapping("/downloadFile")
    @ApiOperation(value = "下载文件接口",httpMethod = "GET",notes = "这是downloadFile的详细说明")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "filepath",value = "文件下载路径",defaultValue = "",paramType = "query",dataType="String",required = true),
            //@ApiImplicitParam(name = "password",value = "密码",defaultValue = "hedonpassword",paramType = "query",dataType="String",required = true)
    })
    public void downloadFile(HttpServletResponse response, String filePath) throws IOException
    {
        System.out.println("222222");
        // 清空输出流
        response.reset();
        response.setHeader("Access-Control-Allow-Origin","http://localhost:8082");
        response.setContentType("application/x-download;charset=UTF-8");
        //response.addHeader("Content-Disposition", "attachment;filename="+ new String(filePath.getBytes("utf-8"), "utf-8"));
        System.out.println("111111:"+filePath);
        fileService.download(response.getOutputStream(),filePath);
    }

    @GetMapping("/queryFile")
    public List<FileVo> queryFile(HttpServletResponse response) throws IOException
    {
        System.out.println("查询数据库");
        return fbp.selectList(null);
        //return filemapper.find(); //返回给前端的对象数据，会自动转换成JSON格式,mybaits的查询数据的方式
       //List<FileVo> list = fimm.selectList(null);
       //System.out.println(list);
       //return list;
    }

    @PostMapping("uploads")
    public String up(String nickname, MultipartFile photo, HttpServletRequest req) throws IOException
    {
        System.out.println("filename:"+nickname);
        String originfilename = photo.getOriginalFilename();
        String filetype = photo.getContentType();
        String path = req.getServletContext().getRealPath("/upload");
        System.out.print(path);
        saveFIle(photo,path);
        return "上传成功";
    }

    public void saveFIle(MultipartFile photo,String path) throws IOException
    {
        //判断存储的路径是否存在，不存在则创建
        File dir = new File(path);
        if(!dir.exists())
        {
            //创建目录
            dir.mkdir();
        }
        File file = new File(path+photo.getOriginalFilename());
        photo.transferTo(file);
    }

    @PostMapping("/test")
    public String test(HttpServletResponse response) throws IOException {
        System.out.println("upload--file is");

        return "test";
    }
}

