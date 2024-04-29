package hty.example.updownfiles2.mapper;
import hty.example.updownfiles2.entity.person.user;
import hty.example.updownfiles2.entity.vo.FileVo;
import org.apache.ibatis.annotations.Insert;

public interface UserInfoMapper {
    @Insert("insert into user values(#{username},#{passwd},#{id})")
    public int insert(user usr);
}
