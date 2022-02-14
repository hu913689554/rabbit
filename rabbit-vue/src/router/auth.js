import Cookies from 'js-cookie'   //引入这个 需要在命令行下 cpm install js-cookie 
import axios from 'axios'
const TokenKey = 'Authorization' //该TokenKey是需要和后端配置的一样的 上面后端流程中 yml中有配置这个
export function setToken(token) {
  Cookies.set(TokenKey, token) //存入cookie
	// 创建axios实例  全局加入header
	axios.defaults.headers.common['Authorization'] = token
}
export function getToken() {
    return {
        token: Cookies.get(TokenKey)
    }
}
