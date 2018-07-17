package com.qxt.dao;

import com.qxt.pojo.Account;
import java.util.List;

public interface AccountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Account record);

    Account selectByPrimaryKey(Long id);
    
    Account selectByUserName(String username);

    List<Account> selectAll();

    int updateByPrimaryKey(Account record);
    
    Account selectByUnAndPwAndType(Account record);
    
    int updatePwByPrimaryKey(Account record);
}