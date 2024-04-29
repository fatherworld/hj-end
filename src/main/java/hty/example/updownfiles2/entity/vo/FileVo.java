package hty.example.updownfiles2.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.Date;
@ApiModel("这是文件IO实体类")
@TableName("fileinfo")
public class FileVo {

    public FileVo(String virtualPath, String fileName,String insertime)  {
        //实体类的构造函数的参数顺序需要和数据库表中的字段顺序一致性，与this.属性名赋值的顺序一致
        this.virtualPath = virtualPath;
        //this.id = id;
        this.fileName = fileName;
        this.insertime = insertime;
    }
    public String getVirtualPath() {
        return virtualPath;
    }
    public String getFileName() {
        return fileName;
    }
    public void setVirtualPath(String virtualPath) {
        this.virtualPath = virtualPath;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    @ApiModelProperty("文件的路径")
    @TableField("virtualPath")
    private String virtualPath; //动态变化的路径
    @ApiModelProperty("文件的名称")
    @TableField("fileName")
    private String fileName;    //文件名称  uuid.pdf
    public void setInsertime(String insertime) {
        this.insertime = insertime;
    }
    public String getInsertime() {
        return insertime;
    }
    public int getId() {
        return id;
    }
    private String insertime;     //文件的插入时间
    public void setId(int id) {
        this.id = id;
    }
    private int id;
}
