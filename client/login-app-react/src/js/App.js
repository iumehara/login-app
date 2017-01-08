import React from 'react'
import { render } from 'react-dom'
import { Link } from 'react-router'
import auth from './auth'

class App extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      isLoggedIn: auth.isLoggedIn()
    }
    this.updateAuth = this.updateAuth.bind(this)
    this.logoutWasClicked = this.logoutWasClicked.bind(this)
  }

  updateAuth(isLoggedIn) {
    this.setState({
      isLoggedIn
    })
  }

  componentWillMount() {
    auth.onChange = this.updateAuth
  }

  logoutWasClicked(event) {
    event.preventDefault()
    auth.logout()
    this.props.router.replace('/')
  }

  loginLogoutLink() {
    if (this.state.isLoggedIn) {
      return (
        <li>
          <span className='username'>{auth.getUsername()} </span>
          <span><a className='logout' href='#' onClick={this.logoutWasClicked}>Log out</a></span>
        </li>
      )
    } else {
      return (
        <li>
          <Link to='/login'>Sign in</Link>
        </li>
      )
    }
  }

  render() {
    return (
      <div>
        <ul>
          <li><Link to="/">Home</Link></li>
          <li><Link to='/users'>Users</Link></li>
          {this.loginLogoutLink()}
        </ul>
        {this.props.children }
      </div>
    )
  }
}

export default App
