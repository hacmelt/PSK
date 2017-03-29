package lt.vu.usecases.mybatis.dao;

import lt.vu.usecases.mybatis.model.ClientClub;
import org.apache.ibatis.annotations.Param;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface ClientClubMapper {

    int deleteByPrimaryKey(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);

    int insert(ClientClub record);

    List<ClientClub> selectAll();
}