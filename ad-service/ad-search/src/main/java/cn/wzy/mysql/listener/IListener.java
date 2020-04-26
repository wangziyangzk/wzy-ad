package cn.wzy.mysql.listener;

import cn.wzy.mysql.dto.BinlogRowData;
//不同业务，对binglogRowData 做不同处理    register向aggregationListener.listenerMap注册
public interface IListener {
    public void register();

    public void OnEvent(BinlogRowData binlogRowData);

}
