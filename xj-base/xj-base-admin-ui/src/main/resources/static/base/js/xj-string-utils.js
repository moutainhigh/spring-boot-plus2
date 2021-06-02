

/**
 * 字符串处理工具
 * @type {{byteToString: StringUtils.byteToString, stringToByte: (function(*): any[])}}
 */
StringUtils = {
    /**
     *字符串 转 byte
     * @author ws
     * @mail  1720696548@qq.com
     * @date  2020/3/29 0029 23:54
     * @return
     * @param str
     */
    stringToByte: function (str) {
        let bytes = new Array();
        let len, c;
        len = str.length;
        for (let i = 0; i < len; i++) {
            c = str.charCodeAt(i);
            if (c >= 0x010000 && c <= 0x10FFFF) {
                bytes.push(((c >> 18) & 0x07) | 0xF0);
                bytes.push(((c >> 12) & 0x3F) | 0x80);
                bytes.push(((c >> 6) & 0x3F) | 0x80);
                bytes.push((c & 0x3F) | 0x80);
            } else if (c >= 0x000800 && c <= 0x00FFFF) {
                bytes.push(((c >> 12) & 0x0F) | 0xE0);
                bytes.push(((c >> 6) & 0x3F) | 0x80);
                bytes.push((c & 0x3F) | 0x80);
            } else if (c >= 0x000080 && c <= 0x0007FF) {
                bytes.push(((c >> 6) & 0x1F) | 0xC0);
                bytes.push((c & 0x3F) | 0x80);
            } else {
                bytes.push(c & 0xFF);
            }
        }
        return bytes;
    },


    /**
     * byte转字符串
     * @author ws
     * @mail  1720696548@qq.com
     * @date  2020/3/29 0029 23:54
     * @return
     * @param arr
     */
    byteToString: function (arr) {
        if (typeof arr === 'string') {
            return arr;
        }
        let str = '',
            _arr = arr;
        for (let i = 0; i < _arr.length; i++) {
            let one = _arr[i].toString(2),
                v = one.match(/^1+?(?=0)/);
            if (v && one.length == 8) {
                let bytesLength = v[0].length;
                let store = _arr[i].toString(2).slice(7 - bytesLength);
                for (let st = 1; st < bytesLength; st++) {
                    store += _arr[st + i].toString(2).slice(2);
                }
                str += String.fromCharCode(parseInt(store, 2));
                i += bytesLength - 1;
            } else {
                str += String.fromCharCode(_arr[i]);
            }
        }
        return str;
    },
    /**
     * 把想要校验的字符串作为参数传给checkNumber 这个函数，若是纯数字则会返回true，若不是纯数字则会返回false
     * @param String
     * @returns {boolean}
     */
    checkNumber: function (String) {
        let reg = /^[0-9]+.?[0-9]*$/
        if (reg.test(String)) {
            return true
        }
        return false
    }
};
