import localStorage from 'localStorage'
import { postLogin } from './fetcher'

module.exports = {
  login(username, password, redirectCallback) {
    if (localStorage.token) {
      redirectCallback(true)
      this.onChange(true)
      return
    }

    postLogin({username: username, password: password})
    .then((userSession) => {
      this.setSession(userSession)
      redirectCallback(true)
      this.onChange(true)
    })
  },

  logout() {
    this.deleteSession()
    this.onChange(false)
  },

  setSession(session) {
    localStorage.token = session.token
    localStorage.username = session.username
  },

  deleteSession() {
    delete localStorage.token
    delete localStorage.username
  },

  isLoggedIn() {
    return !!localStorage.token
  },

  onChange() {},

  getToken() {
    return localStorage.token
  },

  getUsername() {
    return localStorage.username
  }
}
