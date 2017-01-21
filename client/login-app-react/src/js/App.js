import React from 'react'
import { render } from 'react-dom'
import { Link } from 'react-router'
import auth from './auth'

class App extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      isLoggedIn: auth.isLoggedIn(),
      username: auth.getUsername()
    }
    this.updateAuth = this.updateAuth.bind(this)
    this.logoutWasClicked = this.logoutWasClicked.bind(this)
  }

  updateAuth(isLoggedIn) {
    this.setState({
      isLoggedIn,
      username: auth.getUsername()
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

  profileLink() {
    if (this.state.isLoggedIn) {
      const path = `/users/${auth.getUsername()}`
      const description = `your profile (${this.state.username})`
      return this.linkUnlessCurrent(path, description)
    } else {
      return this.linkUnlessCurrent('/login', 'sign in')
    }
  }

  userDiv() {
    if (
      this.props.params &&
      this.props.params.username &&
      this.props.params.username != this.state.username
    ) {
      return <div className='current'>{this.props.params.username}</div>
    }
  }

  linkUnlessCurrent(path, label) {
    if (this.props.router.location.pathname == path) {
      return <div className='current'>{label}</div>
    } else {
      return <Link to={path}>{label}</Link>
    }
  }

  render() {
    return (
      <div>
        <header>
          <h2 className='h2'>login app</h2>
          {this.linkUnlessCurrent('/', 'home')}
          {this.linkUnlessCurrent('/users', 'users')}
          {this.profileLink()}
          {this.userDiv()}
        </header>
        {this.props.children }
      </div>
    )
  }
}

export default App

App.propTypes = {
  router: React.PropTypes.shape({
    location: React.PropTypes.shape({
      pathname: React.PropTypes.string.isRequired
    }).isRequired
  }).isRequired
}
