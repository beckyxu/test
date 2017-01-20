package netease.test.dao;

import netease.test.entity.PersonEntity;

public interface IEntityDao {
	PersonEntity selectByPrimaryKey(Integer id);
	int insert(PersonEntity record);
}
