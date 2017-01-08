import React from 'react'
import { render } from 'react-dom'
import auth from './auth'

class Login extends React.Component {
  constructor(props) {
    super(props)
    this.state = {}
    this.handleSubmit = this.handleSubmit.bind(this)
    this.redirectIfLoggedIn = this.redirectIfLoggedIn.bind(this)
  }

  redirectIfLoggedIn(isLoggedIn) {
    if (isLoggedIn) {
      this.props.router.replace('/')
    }
  }

  handleSubmit(event) {
    event.preventDefault()

    const username = this.refs.username.value
    const password = this.refs.password.value
    auth.login(username, password, this.redirectIfLoggedIn)
  }

  componentWillMount() {
    this.redirectIfLoggedIn(auth.isLoggedIn())
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <label><input className='username' ref='username' placeholder='username'/></label>
        <label><input className='password' ref='password' placeholder='password'/></label>
        <button className='submitButton' type='submit'>login</button>
      </form>
    )
  }
}

export default Login
