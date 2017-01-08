import localStorage from 'localStorage'
import { post } from './fetcher'

module.exports = {
  postLogin(body) {
    return post('login', body)
  },

  login(username, password, redirectCallback) {
    if (localStorage.token) {
      redirectCallback(true)
      this.onChange(true)
      return
    }

    this.postLogin({username: username, password: password})
    .then((userSession) => {
      localStorage.token = userSession.token
      localStorage.username = userSession.username
      redirectCallback(true)
      this.onChange(true)
    })
  },

  logout() {
    delete localStorage.token
    delete localStorage.username
    this.onChange(false)
  },

  isLoggedIn() {
    return !!localStorage.token
  },

  onChange() {},

  getUsername() {
    return localStorage.username
  }
}
