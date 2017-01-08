import React from 'react'
import { render } from 'react-dom'
import { Link } from 'react-router'
import auth from './auth'

class App extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      loggedIn: auth.loggedIn()
    }
    this.updateAuth = this.updateAuth.bind(this)
  }

  updateAuth(loggedIn) {
    this.setState({
      loggedIn
    })
  }

  componentWillMount() {
    auth.onChange = this.updateAuth
  }

  loginLogoutLink() {
    if (this.state.loggedIn) {
      return (
        <li>
          <span className='username'>{auth.getUsername()} </span>
          <span><Link to='/logout'>Log out</Link></span>
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
          <li><Link to="/about">About</Link></li>
          <li><Link to='/users'>Users</Link></li>
          {this.loginLogoutLink()}
        </ul>
        {this.props.children }
      </div>
    )
  }
}

export default App
