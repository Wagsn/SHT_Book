// 注册登陆相关js脚本


// 登出
function signout() {
    // TODO: 
}




// 老版本注册方式
function oldsignupfn() {
    $.ajax({
        type : "post", /* 提交方式 */
        url : "signup", /* servlet 或者 url index.jsp */
        data : $("#form").serialize(), /* 将di为form的表单数据传送给servlet */
        contentType : "application/json;charset=utf-8", /* 采用json格式数据 */
        dateType : "json", /* 数据类型为json */
        success : function(result) { /* 请求成功时被调用，result为服务器传来的数据 */
            if (result.state == 0) {
                alert(result.successmsg);
            } else {
                alert(result.errormsg);
            }
        },
        error : function(XMLHttpRequest, textStatus, errorThrown) { /* 请求错误时调用，errorThrown为错误数据 */
            alert("错误："+errorThrown);
        }
    });
}