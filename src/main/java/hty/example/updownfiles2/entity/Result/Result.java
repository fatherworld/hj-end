package hty.example.updownfiles2.entity.Result;

/**
 * 统一API响应结果封装
 */
/**
 * 响应码枚举，参考HTTP状态码的语义
 */

public class Result {
    public Result(int code,String message,Object data)
    {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    private int code;

    private String message = "success";

    private Object data;

    // 后面result生成器需要以下方法
    public Result setCode(ResultCode resultCode){
        this.code = resultCode.code;
        return this;
    }

    public Result setMessage(String message){
        this.message = message;
        return this;
    }

    public Result setData(Object data){
        this.data = data;
        return this;
    }

}