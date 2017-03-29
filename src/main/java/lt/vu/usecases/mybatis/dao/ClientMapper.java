package lt.vu.usecases.mybatis.dao;

import lt.vu.usecases.mybatis.model.Client;
import lt.vu.usecases.mybatis.model.Client;
import org.mybatis.cdi.Mapper;

import java.util.List;

@Mapper
public interface ClientMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Client record);

    Client selectByPrimaryKey(Integer id);

    List<Client> selectAll();

    int updateByPrimaryKey(Client record);
}