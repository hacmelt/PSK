package lt.vu.usecases.mybatis.dao;

import lt.vu.usecases.mybatis.model.City;
import lt.vu.usecases.mybatis.model.City;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface CityMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(City record);

    City selectByPrimaryKey(Integer id);

    List<City> selectAll();

    int updateByPrimaryKey(City record);
}