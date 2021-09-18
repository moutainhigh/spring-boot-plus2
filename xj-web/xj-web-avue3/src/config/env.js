// 配置编译环境和线上环境之间的切换

let baseUrl = '';
let iconfontVersion = ['567566_82imxaft0by'];
let iconfontUrl = `//at.alicdn.com/t/font_$key.css`;
let codeUrl = `${baseUrl}/code`
const env = process.env
if (env.NODE_ENV == 'development') {
  baseUrl = `http://xijia.plus/`;  // 开发环境地址
} else if (env.NODE_ENV == 'production') {
  baseUrl = `http://xijia.plus/`;  //生产环境地址
} else if (env.NODE_ENV == 'test') {
  baseUrl = `http://xijia.plus/`;  //测试环境地址
}
export {
  baseUrl,
  iconfontUrl,
  iconfontVersion,
  codeUrl,
  env
}