package hty.example.updownfiles2.entity.FileService;
import hty.example.updownfiles2.entity.vo.FileVo;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.List;

public interface FileService {
    //文件上传
    public FileVo upload(MultipartFile file) throws IOException;

    public void download(OutputStream os, String filePath) throws IOException;

}

