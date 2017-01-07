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
      localStorage.token = Math.random().toString(36).substring(7)
      redirectCallback(true)
      this.onChange(true)
    })
  },

  logout() {
    delete localStorage.token
    this.onChange(false)
  },

  loggedIn() {
    return !!localStorage.token
  },

  onChange() {}
}
