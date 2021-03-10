

// 当前选中的tab
let isRead = "";

/**
 * 点击铃铛执行
 */
function msg() {
    // 点击铃铛弹出消息
    Pop.tipsWindownRight($("#msgTemplate").html(), '320px', null, '消息列表', []);
    // 查询数据（第1页）
    let res = findPage(1);
    // 监听tab切换
    msgTab();
    // 加载分页插件
    xijiaLayPage( res);
}


/**
 * 消息tab 切换
 */
function msgTab() {
    //触发事件
    layui.element.on('tab(docDemoTabBrief)', function (elem) {
        // 重新获取数据并加载
        isRead = $(this).attr('lay-id');
        let res = findPage(1);
        // 重新加载分页插件
        xijiaLayPage(laypage, res);
    });
}


/**
 * 获取消息
 */
function findPage(page) {
    let res = Ajax.get(adminMsgFindPage + getPage(page, 8) + "&isRead=" + isRead);
    // 加载数据
    showText(res.data.records);
    return res;
}


/**
 * 消息分页配置, 数据总数会在下一次点击事更新上一次数据库的总数量
 * @author ws
 * @mail  1720696548@qq.com
 * @param result 后台返回数据, 用于第一此加载总数据
 * @date  2020/4/22 0022 1:18
 * @return
 */
function xijiaLayPage(laypage, res) {
    layui.laypage.render({
        // 注意，这里的 page-table 是 ID，不用加 # 号
        elem: 'page-table'
        , count: res.data.total    // 数据总数，从服务端得到
        , limit: res.data.size     // 每页数量
        // 基础信息配置
        , layout: ['count', 'prev', 'page', 'next']
        , curr: pageJson.curr
        , groups: 1
        , prev: "<-"
        , next: "->"
        // 监听 obj包含了当前分页的所有参数， 比如： obj.curr =当前页, obj.limit = 每页显示条数
        , jump: function (obj, first) {
            //首次不执行, 第一次加载时 first=true
            if (first) {
                return;
            }
            // 加载新数据
            findPage(obj.curr);
        }
    });
}


/**
 *  消息内容，拼接加载
 */
function showText(dataJson) {
    /**
     * 拼最后展示的数据
     */
    let text = "";
    $.each(dataJson, function (index, item) {
        console.log(dataJson[index]);
        text += dataJson[index].isRead === 0 ? "<span style='margin-right: 3%;' class='layui-badge-dot'></span>" : "";
        text += "<span>" + dataJson[index].content + "</span>";
        text += "<div style='color: #dad8d5'>" + dataJson[index].createTime + "</div>";
        text += "<hr> ";
    });
    $("#tab" + isRead).html(text);
}

