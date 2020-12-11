package com.ws.ldy.others.websocket.model.vo;

import com.ws.ldy.common.utils.LocalDateTimeUtil;
import com.ws.ldy.others.base.model.Convert;
import com.ws.ldy.others.websocket.server.WebsocketServer;
import lombok.Data;

import java.time.LocalDateTime;


/**
 *   websocket客户端-接收端-返回参数
 *
 * @author 王松
 * @mail 1720696548@qq.com
 * @date 2020/6/30 0030 18:24
 */
@Data
public class SendMsgVO extends Convert {
    /**
     * 消息类型(1-上线通知 2-下线通知 3-在线名单通知  4-代表普通消息通知 )
     */
    private Integer mesType;
    /**
     * 发送人用户Id(来源Id,上线为上线线人的用户Id)
     */
    private String from;
    /**
     * 发送人用户名( 上下线为上线线人的用户名)
     */
    private String username;
    /**
     * 发送人头像
     */
    private String headPic;
    /**
     * 接收人用户Id (目标ID,逗号分隔) (所有人使用-ALL,包括自己在内也能接收)
     */
    private String to;
    /**
     * 发送内容
     */
    private String content;
    /**
     * 扩展消息字段(json)
     */
    private String extras;
    /**
     * 当前在线人数
     */
    private Integer onlineNum;
    /**
     * 消息创建时间(YYYY-MM-DD )
     */
    private String createTime;

//    /**
//     * 消息类型，int类型(0:text、1:image、2:voice、3:vedio、4:music、5:news)
//     */
//    private Integer msgType;

    /**
     * @param mesType  消息类型(1-上线通知 2-下线通知 3-在线名单通知  4-代表普通消息通知 )
     * @param from     发送人Id(来源Id),上下线为上线人的用户id
     * @param username 发送人姓名username，上下线为上线人的用户名
     * @param headPic 发送人头像，上下线为上线人
     * @param to       接收人Id(目标ID,逗号分隔,所有人使用-ALL)
     * @param content  发送消息内容
     * @param extras   发送消息扩展字段
     */
    public SendMsgVO(Integer mesType, String from, String username, String  headPic,String to, String content, String extras) {
        this.mesType = mesType;
        this.from = from;
        this.username = username;
        this.headPic = headPic;
        this.to = to;
        this.content = content;
        this.extras = extras;
        this.onlineNum = WebsocketServer.clients.size();
        this.createTime = LocalDateTimeUtil.parse(LocalDateTime.now());
    }
}
