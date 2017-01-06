import localStorage from 'localStorage'

module.exports = {
  login(username, password, redirectCallback) {
    if (localStorage.token) {
      redirectCallback(true)
      this.onChange(true)
      return
    }

    if (username === 'adam' && password === 'secreta') {
      localStorage.token = Math.random().toString(36).substring(7)
      redirectCallback(true)
      this.onChange(true)
    } else {
      redirectCallback(false)
      this.onChange(false)
    }
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
