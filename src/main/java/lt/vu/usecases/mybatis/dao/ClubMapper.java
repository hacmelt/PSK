package lt.vu.usecases.mybatis.dao;

import lt.vu.usecases.mybatis.model.Club;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface ClubMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Club record);

    Club selectByPrimaryKey(Integer id);

    List<Club> selectAll();

    int updateByPrimaryKey(Club record);
}