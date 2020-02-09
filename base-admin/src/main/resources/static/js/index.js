

// 系统级菜单展示  -->  root=2
function menuRoot1(){
    let html = "";
    $.each(dataJson, function (index) {
        html+= "<li class=\"layui-nav-item\" onclick=\"menuRoot1Click("+index+")\"><a href=\"JavaScript:void(0)\">" + dataJson[index].name + "</a></li>";
    });
    $("#menuRoot1").html(html);
}


// 系统级菜单点击事件切换导航菜单
function menuRoot1Click(e){
    treeMenu(e);
    //重新渲染菜单
    layui.element.render('nav');
}


//一级目录或页面 -->  root=2
function treeMenu(i) {
    $("#LAY-system-side-menu").html("");
    let html = "";
    //遍历拼接菜单
    let data = dataJson[i].menus;
    $.each(data, function (index, item) {
        //一级菜单（是否展开）
        // if (dataJson[index].open == 1) {
        //     htmlRoot2 += "<li  data-name='home' class='layui-nav-item layui-nav-itemed'>";
        // } else {
        html += "<li  data-name='home' class='layui-nav-item'>";                                 // 一级目录头
        html += "<a href='javascript:;' lay-tips='" + data[index].name + "' lay-direction='2'>"; // 名称
        html += "<i class='layui-icon " + data[index].icon + "'></i>" ;                          // 图标
        html += "<cite>" + data[index].name + "</cite> </a>";                                    // 菜单名称
        // html += "<spen class='layui-nav-more'><spen>";
        //判断是否存在下级目录
        if(data[index].menus.length > 0){
            html += "<dl class=\"layui-nav-child\">";                                            // 二级目录头
            html += nextMenuRoot3(data[index].menus) ;                                           // 二级菜单/页面 --> 获取
            html += "</dl>";                                                                     // 二级目录尾
        }
        html += "</li>";                                                                         // 一级目录尾
    });
    $("#LAY-system-side-menu").html(html);
}

//二级目录或页面 --> root=3
function nextMenuRoot3(data) {
    let html = "";
    //二级菜单
    $.each(data, function (index, item) {
        //判断存在下级目录， 默认展开-> class='layui-nav-itemed'
        if(data[index].menus.length > 0){
            html += "<dd  style='margin-left: 10%'><a href='javascript:;'><i class='layui-icon "+ data[index].icon+"'></i>"+data[index].name+"</a>";
            html += "<dl class='layui-nav-child'>";
            html += nextMenuRoot4(data[index].menus);  //三级菜单
            html += "</dl>";
            html += "</dd>";
        }else{
            html += "<dd  style='margin-left: 5%' data-name='console'><a lay-href='"+ADMIN_CONSOLE + data[index].url+"'>"+data[index].name+"</a></dd>";
        }
    });
    return html;
}

//页面 --> root=4
function nextMenuRoot4(data) {
    let html = "";
    $.each(data, function (index) {
        html +=  "<dd  style='margin-left: 3%'><a lay-href='"+ADMIN_CONSOLE+data[index].url+"'>"+data[index].name+"</a></dd>";
    });
    return html;
}