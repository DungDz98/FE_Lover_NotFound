package com.codegym.repository;

import com.codegym.model.entity.UserTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

@Repository
public interface ITestSelectRepo extends JpaRepository<UserTest, Long> {

    @Query(value = "select user.id, user.name, user.avatar, user.description, SUBSTRING_INDEX(GROUP_CONCAT((category.name)ORDER BY RAND() SEPARATOR ' - '), ' - ', 3) AS category_name , user.price from category  inner join user_service on user_service.category_id = category.id  inner join user on user.id = user_service.user_id where (user.statusccdv = 1 or user.statusccdv = 3) GROUP BY ID order by user.id desc ", nativeQuery = true)
    public Iterable<UserTest> findAllTest();

    @Query(value = "select user.id, user.name, user.avatar, user.description, SUBSTRING_INDEX(GROUP_CONCAT(category.name SEPARATOR ' - '), ' - ', 3) AS category_name , user.price from category  inner join user_service on user_service.category_id = category.id  inner join user on user.id = user_service.user_id where (user.statusccdv = 1 or user.statusccdv = 3) GROUP BY ID order by user.id", nativeQuery = true)
    public Iterable<UserTest> findAllByCCDV();

    @Query(value = "select user.id, user.name, user.avatar, user.description, SUBSTRING_INDEX(GROUP_CONCAT(category.name SEPARATOR ' - '), ' - ', 3) AS category_name , user.price from category  inner join user_service on user_service.category_id = category.id  inner join user on user.id = user_service.user_id where (user.statusccdv = 1 or user.statusccdv = 3) and user.gender like :gender GROUP BY ID ", nativeQuery = true)
    public Iterable<UserTest> findAllUserCCDVByGender(@Param("gender")String gender);

    @Query(value = "select user.id, user.name, user.avatar, user.description, SUBSTRING_INDEX(GROUP_CONCAT(category.name SEPARATOR ' - '), ' - ', 3) AS category_name , user.price from category  inner join user_service on user_service.category_id = category.id  inner join user on user.id = user_service.user_id where (user.statusccdv = 1 or user.statusccdv = 3) and user.city like :city GROUP BY ID", nativeQuery = true)
    public Iterable<UserTest> findAllByCity(@Param("city") String city);


    @Query(value = "select user.id, user.name, user.avatar, user.description, SUBSTRING_INDEX(GROUP_CONCAT(category.name SEPARATOR ' - '), ' - ', 3) AS category_name , user.price from category  inner join user_service on user_service.category_id = category.id  inner join user on user.id = user_service.user_id where (user.statusccdv = 1 or user.statusccdv = 3) and user.name like concat('%',:name,'%') GROUP BY ID", nativeQuery = true)
    public Iterable<UserTest> findAllByName(@Param("name") String name);

    //    danh sách gợi ý
    @Query(value = "select user.id, user.name, user.avatar, user.description, SUBSTRING_INDEX(GROUP_CONCAT((category.name)ORDER BY RAND() SEPARATOR ' - '), ' - ', 3) AS category_name , user.price from category  inner join user_service on user_service.category_id = category.id  inner join user on user.id = user_service.user_id where (user.statusccdv = 1 or user.statusccdv = 3) and user.gender like :gender GROUP BY ID order by rand() limit 8;", nativeQuery = true)
    public Iterable<UserTest> findAllByGoiY (@Param("gender") String gender);

    //. danh sách user vip mới -> cũ
    @Query(value = "select user.id, user.name, user.avatar, user.description, SUBSTRING_INDEX(GROUP_CONCAT((category.name)ORDER BY RAND() SEPARATOR ' - '), ' - ', 3) AS category_name , user.price from category  inner join user_service on user_service.category_id = category.id  inner join user on user.id = user_service.user_id where user.statusccdv = 3 GROUP BY ID order by user.id desc limit 8;", nativeQuery = true)
    public Iterable<UserTest> findAllUserVip();

    //test select nhieu truong
    @Query(value = "select user.id, user.name, user.avatar, user.description, SUBSTRING_INDEX(GROUP_CONCAT((category.name)ORDER BY RAND() SEPARATOR ' - '), ' - ', 3) AS category_name , user.price from category  inner join user_service on user_service.category_id = category.id  inner join user on user.id = user_service.user_id where user.statusccdv = 1 and user.gender like concat('%',:gender,'%') and user.city like concat('%',:city,'%') and user.name like concat('%',:name,'%') GROUP BY ID order by user.id;", nativeQuery = true)
    public Iterable<UserTest> findAllByField(@Param("gender") String gender, @Param("city") String city, @Param("name") String name);
}
