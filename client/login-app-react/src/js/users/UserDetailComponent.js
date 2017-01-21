import React from 'react'
import { render } from 'react-dom'
import { get } from '../fetcher'
import auth from '../auth'
import UserDetail from './UserDetail'

class UserDetailComponent extends React.Component {
  constructor(props) {
    super(props)
    this.state = {
      user: null,
      username: props.params.username
    }
    this.logoutWasClicked = this.logoutWasClicked.bind(this)
  }

  getUser(username) {
    return get(auth.getToken(), 'user', username)
  }

  getUserAndSetToState(username) {
    return this.getUser(username).then((user) => {
        this.setState({user})
      })
  }

  componentWillMount() {
    this.getUserAndSetToState(this.state.username)
  }

  componentWillReceiveProps(nextProps) {
    this.setState({
      username: nextProps.params.username
    })
  }

  componentDidUpdate(prevProps, prevState) {
    if (this.state.username != prevState.username) {
      this.getUserAndSetToState(this.state.username)
    }
  }

  logoutWasClicked(event) {
    event.preventDefault()
    auth.logout()
    this.props.router.replace('/')
  }

  logoutLink() {
    if (auth.isLoggedIn()) {
      return <span>
      <a className='logout' href='#' onClick={this.logoutWasClicked}>Log out</a>
      </span>
    }
  }

  render() {
    let userDetail = () => {
      if (this.state.user) {
        return <UserDetail user={this.state.user}/>
      }
    }

    return (
      <div>
        <h1>User</h1>
        {userDetail()}
        {this.logoutLink()}
      </div>
    )
  }
}

UserDetailComponent.propTypes = {
  params: React.PropTypes.shape({
    username: React.PropTypes.string.isRequired
  }).isRequired
}

export default UserDetailComponent
