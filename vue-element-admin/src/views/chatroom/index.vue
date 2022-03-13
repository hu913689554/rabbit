<template>
  <div style="padding: 10px; margin-bottom: 50px">
    <el-row>
      <el-col :span="4">
        <el-card style="width: 300px; height: 300px; color: #333">
          <div style="padding-bottom: 10px; border-bottom: 1px solid #ccc">在线用户<span style="font-size: 12px">（点击聊天气泡开始聊天）</span></div>
          <div v-for="user in users" :key="user.username" style="padding: 10px 0">
            <span>{{ user.name }}</span>
            <span v-if="user.online" style="font-size: 12px;margin-left: 5px">(在线)</span>
            <i
              class="el-icon-chat-dot-round"
              style="margin-left: 10px; font-size: 16px; cursor: pointer"
              @click="selectChat(user)"
            />
            <span v-if="user.username === chatUser.username" style="font-size: 12px;color: limegreen; margin-left: 5px">chatting...</span>
          </div>
        </el-card>
      </el-col>

      <el-col :span="20">
        <div
          style="width: 800px; margin: 0 auto; background-color: white;
                    border-radius: 5px; box-shadow: 0 0 10px #ccc"
        >
          <div style="text-align: center; line-height: 50px;">
            Web聊天室（{{ chatUser.name }}）
          </div>
          <div ref="liaotianText" style="height: 350px; overflow:auto; border-top: 1px solid #ccc" v-html="content[chatUser.username]" />
          <div style="height: 200px">
            <textarea
              v-model="text"
              style="height: 160px; width: 100%; padding: 20px; border: none; border-top: 1px solid #ccc;
             border-bottom: 1px solid #ccc; outline: none"
            />
            <div style="text-align: right; padding-right: 10px">
              <el-button type="primary" size="mini" @click="send">发送</el-button>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>

import request from '@/utils/request'
import store from '@/store/index'
import { getChat } from '@/api/chat'
let socket

export default {
  name: 'Im',
  data() {
    return {
      circleUrl: 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png',
      user: {},
      isCollapse: false,
      users: [],
      chatUser: [],
      userMap: {},
      text: '',
      messages: [],
      content: {}
    }
  },
  created() {
    this.init()
  },

  methods: {

    selectChat(user) {
      const _this = this

      _this.chatUser = user
      _this.$set(_this.content, _this.chatUser.username, '')

      return new Promise((resolve, reject) => {
        getChat({ cfrom: _this.chatUser.username, cto: _this.user.username }).then(response => {
          response.data.forEach(route => {
            if (route.cfrom === _this.user.username) {
              _this.createContent(0, _this.userMap[route.cto], _this.user, route.ctext)
            } else {
              _this.createContent(1, _this.userMap[route.cfrom], _this.user, route.ctext)
            }
          })
          resolve()
        }).catch(error => {
          reject(error)
        })
      })
    },
    send() {
      console.log('dddddd')
      console.log(this.chatUser)
      if (!this.chatUser || this.chatUser.length <= 0) {
        this.$message({ type: 'warning', message: '请选择聊天对象' })
        return
      }
      if (!this.text) {
        this.$message({ type: 'warning', message: '请输入内容' })
      } else {
        if (typeof (WebSocket) === 'undefined') {
          console.log('您的浏览器不支持WebSocket')
        } else {
          console.log('您的浏览器支持WebSocket')
          // 组装待发送的消息 json
          // {"from": "zhang", "to": "admin", "text": "聊天文本"}
          const message = { from: this.user.username, to: this.chatUser.username, text: this.text }
          socket.send(JSON.stringify(message)) // 将组装好的json发送给服务端，由服务端进行转发
          this.messages.push({ user: this.user.username, text: this.text })
          // 构建消息内容，本人消息
          this.createContent(0, this.chatUser, this.user, this.text)
          this.text = ''
        }
      }
    },
    createContent(biaoshi, remoteUser, nowUser, text) { // 这个方法是用来将 json的聊天消息数据转换成 html的。 biaoshi=0 发送  biaoshi=1接受
      let html
      // 当前用户消息
      if (biaoshi === 0) { // nowUser 表示是否显示当前用户发送的聊天消息，绿色气泡
        html = '<div class="el-row" style="padding: 5px 0">\n' +
            '  <div class="el-col el-col-22" style="text-align: right; padding-right: 10px">\n' +
            '    <div class="tip left">' + text + '</div>\n' +
            '  </div>\n' +
            '  <div class="el-col el-col-2">\n' +
            '  <span class="el-avatar el-avatar--circle" style="height: 40px; width: 40px; line-height: 40px;">\n' +
            '    <img src="' + nowUser.avatar + ' " style="object-fit: cover;">\n' +
            '  </span>\n' +
            '  </div>\n' +
            '</div>'
        const yuanHtml = this.content[remoteUser.username]
        if (yuanHtml) {
          this.$set(this.content, remoteUser.username, yuanHtml + html)
        } else {
          this.$set(this.content, remoteUser.username, html)
        }
        this.$nextTick(() => {
          this.$refs.liaotianText.scrollTop = this.$refs.liaotianText.scrollHeight
        })
      } else if (biaoshi === 1) { // remoteUser表示远程用户聊天消息，蓝色的气泡
        html = '<div class="el-row" style="padding: 5px 0">\n' +
            '  <div class="el-col el-col-2" style="text-align: right">\n' +
            '  <span class="el-avatar el-avatar--circle" style="height: 40px; width: 40px; line-height: 40px;">\n' +
            '    <img src="' + remoteUser.avatar + '" style="object-fit: cover;">\n' +
            '  </span>\n' +
            '  </div>\n' +
            '  <div class="el-col el-col-22" style="text-align: left; padding-left: 10px">\n' +
            '    <div class="tip right">' + text + '</div>\n' +
            '  </div>\n' +
            '</div>'
        const yuanHtml = this.content[remoteUser.username]
        if (yuanHtml) {
          this.$set(this.content, remoteUser.username, yuanHtml + html)
        } else {
          this.$set(this.content, remoteUser.username, html)
        }
      }

      console.log(this.$refs.liaotianText.scrollHeight)
      // this.$refs.liaotianText.scrollHeight;
      // this.$refs.liaotianText.scrollTop =this.$refs.liaotianText.scrollHeight;
      // let str= this.content[remoteUser.username]+html
      // // console.log(html)
      // // this.content += html;
      // this.$set(this.content,remoteUser.username,route)
    },
    init() {
      // this.user = sessionStorage.getItem("user") ? JSON.parse(sessionStorage.getItem("user")) : {}
      // let username = this.user.username;
      this.user = store.getters
      const username = store.getters.username
      const _this = this
      if (typeof (WebSocket) === 'undefined') {
        console.log('您的浏览器不支持WebSocket')
      } else {
        console.log('您的浏览器支持WebSocket')
        const socketUrl = 'ws://localhost:8081/imserver/' + username
        if (socket != null) {
          socket.close()
          socket = null
        }
        // 开启一个websocket服务
        socket = new WebSocket(socketUrl)
        // 打开事件
        socket.onopen = function() {
          console.log('websocket已打开')
        }

        //  浏览器端收消息，获得从服务端发送过来的文本消息
        socket.onmessage = function(msg) {
          const data = JSON.parse(msg.data) // 对收到的json数据进行解析， 类似这样的： {"users": [{"username": "zhang"},{ "username": "admin"}]}
          if (data.usernamelist) { // 获取在线人员信息
            _this.users = data.usernamelist.filter(user => user.username !== username) // 获取当前连接的所有用户信息，并且排除自身，自己不会出现在自己的聊天列表里
            data.usernamelist.forEach(route => {
              const str = route.username
              _this.$set(_this.userMap, str, route)
            })
          } else {
            // 如果服务器端发送过来的json数据 不包含 users 这个key，那么发送过来的就是聊天文本json数据
            //  // {"from": "zhang", "text": "hello"}
            if (data.to === username) {
              _this.messages.push(data)
              // 构建消息内容
              _this.createContent(1, _this.userMap[data.from], _this.user, data.text)
            }
          }
        }
        // 关闭事件
        socket.onclose = function() {
          console.log('websocket已关闭')
        }
        // 发生了错误事件
        socket.onerror = function() {
          console.log('websocket发生了错误')
        }
      }
    }

  }
}

</script>

<style>
.tip {
  color: white;
  text-align: center;
  border-radius: 10px;
  font-family: sans-serif;
  padding: 10px;
  width:auto;
  display:inline-block !important;
  display:inline;
}

.right {
  background-color: deepskyblue;
}
.left {
  background-color: forestgreen;
}
</style>
