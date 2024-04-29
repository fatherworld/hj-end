package hty.example.updownfiles2.mapper;
import hty.example.updownfiles2.entity.vo.FileVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileInfoMapper {
    //查询所有文件
    @Select("select * from fileinfo")
    public List<FileVo> find();

    @Insert("insert into fileinfo values(#{id},#{virtualPath},#{fileName},#{insertime})")
    public int insert(FileVo filevo);
}
