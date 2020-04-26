package cn.wzy.mysql.sender;

import cn.wzy.mysql.dto.MySqlRowData;

//将MySqlRowData对象投递出去 的接口
public interface ISender {
    void sender(MySqlRowData mySqlRowData);
}
